package core2.maz.com.core2.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.source.smoothstreaming.DefaultSsChunkSource;
import com.google.android.exoplayer2.source.smoothstreaming.SsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveVideoTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.PlaybackControlView;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.util.Util;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.ArrayList;

import core2.maz.com.core2.R;
import core2.maz.com.core2.activities.MainActivity;
import core2.maz.com.core2.model.Menu;
import core2.maz.com.core2.utills.AppUtils;
import core2.maz.com.core2.utills.UiUtil;

/**
 * Created by Ankur Jain on 29-11-2016.
 */
public class VideoFragment extends Fragment implements  ExoPlayer.EventListener,
        MappingTrackSelector.EventListener, PlaybackControlView.VisibilityListener{

    public static final String DRM_SCHEME_UUID_EXTRA = "drm_scheme_uuid";
    public static final String DRM_LICENSE_URL = "drm_license_url";
    public static final String DRM_KEY_REQUEST_PROPERTIES = "drm_key_request_properties";
    public static final String PREFER_EXTENSION_DECODERS = "prefer_extension_decoders";
    private Dialog dialog;


    private static final DefaultBandwidthMeter BANDWIDTH_METER = new DefaultBandwidthMeter();
    private static final CookieManager DEFAULT_COOKIE_MANAGER;

    static {
        DEFAULT_COOKIE_MANAGER = new CookieManager();
        DEFAULT_COOKIE_MANAGER.setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER);
    }

    private Handler mainHandler;
    private SimpleExoPlayerView simpleExoPlayerView;


    private String userAgent;
    private DataSource.Factory mediaDataSourceFactory;
    private SimpleExoPlayer player;
    private MappingTrackSelector trackSelector;
    private ArrayList<Menu> menus;

    private boolean playerNeedsSource;

    private boolean shouldAutoPlay;
    private boolean shouldRestorePosition;
    private int playerWindow;
    private long playerPosition;
    private  boolean isVisibleToUser;
    private int currentItem =0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.video_fragment_layout,container,false);

        menus = (ArrayList<Menu>) getArguments().getSerializable("list");
        simpleExoPlayerView = (SimpleExoPlayerView)view.findViewById(R.id.player_view);
        simpleExoPlayerView.requestFocus();
        simpleExoPlayerView.setBackgroundColor(Color.parseColor("#000000"));
        mainHandler = new Handler();
        userAgent = Util.getUserAgent(getActivity(), "ExoPlayerDemo");
        mediaDataSourceFactory = buildDataSourceFactory(true);
        shouldAutoPlay = true;
        if(savedInstanceState==null)
        {
            if (isVisibleToUser)
            {
                if(menus!=null &&!menus.isEmpty())
                initializePlayer(menus.get(currentItem).getContentUrl());
            }
        }
        else
        {
            restoreState(savedInstanceState);
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putInt("currentItem", currentItem);
        outState.putBoolean("restorePosition", true);
        outState.putInt("playerWindow", playerWindow);
        outState.putLong("playerPosition", playerPosition);
        initializePlayer(menus.get(currentItem).getContentUrl());

    }

    private void restoreState(Bundle bundle)
    {
        this.currentItem = bundle.getInt("currentItem");
        this.shouldRestorePosition = bundle.getBoolean("restorePosition");
        this.playerWindow = bundle.getInt("playerWindow");
        this.playerPosition = bundle.getLong("playerPosition");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        VideoFragment videoFragment =this;
        if(videoFragment!=null) {
            if (isVisibleToUser) {
                if (menus != null && !menus.isEmpty())
                    initializePlayer(menus.get(currentItem).getContentUrl());
            } else {
                if (player != null) {
                    releasePlayer();
                }
            }
        }
    }

    @Override
    public void onLoadingChanged(boolean b) {

    }

    @Override
    public void onPlayerStateChanged(boolean b, int playbackState)
    {
        switch (playbackState) {
            case ExoPlayer.STATE_BUFFERING:
                dialog =UiUtil.showProgressDialog(getActivity());
                break;
            case ExoPlayer.STATE_ENDED:
                currentItem++;
                nextVideo();
                break;
            case ExoPlayer.STATE_IDLE:
                break;
            case ExoPlayer.STATE_READY:
                UiUtil.dismissDialog(dialog);
                break;
            default:
                break;
        }
    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object o) {

    }

    @Override
    public void onPlayerError(ExoPlaybackException e) {

    }

    @Override
    public void onPositionDiscontinuity() {

    }

    @Override
    public void onTracksChanged(MappingTrackSelector.TrackInfo trackInfo) {

    }

    @Override
    public void onVisibilityChange(int i) {

    }

    private DataSource.Factory buildDataSourceFactory(boolean useBandwidthMeter) {
        return new DefaultDataSourceFactory(getActivity(), useBandwidthMeter ? BANDWIDTH_METER : null,
                buildHttpDataSourceFactory(useBandwidthMeter));
    }


    private HttpDataSource.Factory buildHttpDataSourceFactory(boolean useBandwidthMeter) {
        return new DefaultHttpDataSourceFactory(userAgent, useBandwidthMeter ? BANDWIDTH_METER : null);
    }



    private void initializePlayer(String simulatedUrl) {

        if (player == null) {
            TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveVideoTrackSelection.Factory(BANDWIDTH_METER);
            trackSelector = new DefaultTrackSelector(mainHandler, videoTrackSelectionFactory);
            trackSelector.addListener(this);
            Context context = getContext();
            if (context != null) {
                player = ExoPlayerFactory.newSimpleInstance(context, trackSelector, new DefaultLoadControl(), null, false);
                player.addListener(this);
                simpleExoPlayerView.setPlayer(player);
                player.setPlayWhenReady(shouldAutoPlay);
                playerNeedsSource = true;
                if (shouldRestorePosition)
                {
                    if (playerPosition == C.TIME_UNSET)
                    {
                        player.seekToDefaultPosition(playerWindow);
                    }
                    else
                    {
                        player.seekTo(playerWindow, playerPosition);
                    }
                }

                if (playerNeedsSource) {
                    Uri uri = Uri.parse(simulatedUrl);
                    MediaSource mediaSource = buildMediaSource(uri, null);
                    player.prepare(mediaSource, !shouldRestorePosition);
                }
            }
        }

    }

    private MediaSource buildMediaSource(Uri uri, String overrideExtension) {
        int type = Util.inferContentType(!TextUtils.isEmpty(overrideExtension) ? "." + overrideExtension
                : uri.getLastPathSegment());
        switch (type) {
            case Util.TYPE_SS:
                return new SsMediaSource(uri, buildDataSourceFactory(false),
                        new DefaultSsChunkSource.Factory(mediaDataSourceFactory), mainHandler, null);
            case Util.TYPE_DASH:
                return new DashMediaSource(uri, buildDataSourceFactory(false),
                        new DefaultDashChunkSource.Factory(mediaDataSourceFactory), mainHandler, null);
            case Util.TYPE_HLS:
                return new HlsMediaSource(uri, mediaDataSourceFactory, mainHandler, null);
            case Util.TYPE_OTHER:
                return new ExtractorMediaSource(uri, mediaDataSourceFactory, new DefaultExtractorsFactory(),
                        mainHandler, null);
            default: {
                throw new IllegalStateException("Unsupported type: " + type);
            }
        }
    }


    @Override
    public void onStop()
    {

        releasePlayer();
        if(AppUtils.isDeviceInPortraitView())
        {
            ((MainActivity) getActivity()).showTop();
        }
        super.onStop();
    }

    private void releasePlayer() {
        if (player != null) {
            shouldAutoPlay = player.getPlayWhenReady();
            shouldRestorePosition = false;
            Timeline timeline = player.getCurrentTimeline();
            if (timeline != null) {
                playerWindow = player.getCurrentWindowIndex();
                Timeline.Window window = timeline.getWindow(playerWindow, new Timeline.Window());
                if (!window.isDynamic) {
                    shouldRestorePosition = true;
                    playerPosition = window.isSeekable ? player.getCurrentPosition() : C.TIME_UNSET;
                }
            }
            player.release();
            player = null;
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if(isVisibleToUser)
        {
            if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                ((MainActivity) getActivity()).hideTop();
            } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
                ((MainActivity) getActivity()).showTop();
            }
        }
    }

    private void nextVideo()
    {
        if(currentItem>=menus.size())
        {
            player.stop();
            currentItem=0;
            initializePlayer((menus.get(currentItem).getContentUrl()));
        }
        else
        {
            player.stop();
            initializePlayer(menus.get(currentItem).getContentUrl());
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if(isVisibleToUser)
        {
            initializePlayer(menus.get(currentItem).getContentUrl());
            //if(AppUtils.isDeviceInPortraitView())
        }
    }
}
