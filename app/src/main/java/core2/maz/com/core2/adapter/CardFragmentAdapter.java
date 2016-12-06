package core2.maz.com.core2.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import core2.maz.com.core2.R;
import core2.maz.com.core2.activities.WebViewActivity;
import core2.maz.com.core2.model.Menu;

/**
 * Created by Ankur Jain on 05-12-2016.
 */
public class CardFragmentAdapter  extends RecyclerView.Adapter<CardFragmentAdapter.MainViewHolder>{

    private Context context;
    private ArrayList<Menu> menus;
    private final int CUSTOM_FEED = 1;
    private final int ARTICLE=2;
    private final int  AD = 3;

    public CardFragmentAdapter(Context context,ArrayList<Menu> menus)
    {

        this.context = context;
        this.menus = menus;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        MainViewHolder mainViewHolder = null;
        View view = null;

        if(viewType==ARTICLE)
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_items,parent,false);
            mainViewHolder = new ArticleViewHolder(view);
        }
        else if(viewType == AD)
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ad_fragment_layout,parent,false);
            mainViewHolder = new AdViewHolder(view);
        }
        else
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customfeed_layout_listview,parent,false);
            mainViewHolder = new CustomViewHolder(view);
        }
        return mainViewHolder;
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position)
    {
        Menu menu = menus.get(position);
        if(holder instanceof ArticleViewHolder)
        {
            ArticleViewHolder articleViewHolder = (ArticleViewHolder)holder;
            articleViewHolder.feedTitle.setText(menu.getTitle());
            Picasso.with(context).load(menu.getImage()).into(articleViewHolder.feedImage);

            if(menu.getType().equalsIgnoreCase("vid"))
            {
                articleViewHolder.imageViewCardPlayVideo.setVisibility(View.VISIBLE);
            }
            else
            {
                articleViewHolder.imageViewCardPlayVideo.setVisibility(View.GONE);
            }
        }
        else if(holder instanceof CustomViewHolder)
        {
            CustomViewHolder customViewHolder = (CustomViewHolder)holder;
            customViewHolder.textViewHeader.setText(menu.getTitle());
        }
        else
        {
            holder.itemView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return menus.size();
    }

    @Override
    public int getItemViewType(int position)
    {
        Menu menu = menus.get(position);
        if(menu.getType().equals("header"))
        {
            return CUSTOM_FEED;
        }
        else if(menu.getType().equalsIgnoreCase("ad"))
        {
            return AD;
        }
        else
        {
            return ARTICLE;
        }
    }

    public class MainViewHolder extends RecyclerView.ViewHolder
    {
        public MainViewHolder(View itemView)
        {
            super(itemView);
        }
    }

    public class CustomViewHolder extends MainViewHolder
    {
        public TextView textViewHeader;
        public CustomViewHolder(View itemView)
        {
            super(itemView);
            textViewHeader = (TextView)itemView.findViewById(R.id.textViewHeader);
        }
    }

    public class AdViewHolder extends MainViewHolder
    {

        public AdViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class ArticleViewHolder extends MainViewHolder implements View.OnClickListener
    {

        public LinearLayout linearLayoutCardViewRootContainer;
        private CardView cardViewSubRootContainer;
        private RelativeLayout relativeLayoutCardRootContainer;
        public TextView feedTitle;
        public ImageView feedImage;
        public ImageView imageViewCardGradient;
        public ImageView imageViewCardPlayVideo;
        public ArticleViewHolder(View itemView)
        {
            super(itemView);
            linearLayoutCardViewRootContainer = (LinearLayout) itemView.findViewById(R.id.linearLayoutCardViewRootContainer);
            cardViewSubRootContainer = (CardView) itemView.findViewById(R.id.cardViewSubRootContainer);
            relativeLayoutCardRootContainer = (RelativeLayout) itemView.findViewById(R.id.relativeLayoutCardRootContainer);
            feedTitle = (TextView) itemView.findViewById(R.id.cardFeedText);
            feedImage = (ImageView) itemView.findViewById(R.id.cardFeedImage);
            imageViewCardGradient = (ImageView) itemView.findViewById(R.id.imageViewCardGradient);
            imageViewCardPlayVideo = (ImageView) itemView.findViewById(R.id.imageViewCardPlayVideo);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            int position = getAdapterPosition();
            Menu menu = menus.get(position);
            String title = menu.getTitle();

            if (menu.getType().equals("gallery") || menu.getType().equalsIgnoreCase("article"))
            {
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("menus",menus);
                intent.putExtra("position",position);
                context.startActivity(intent);
            }

            /*else if (!menu.getType().equals("vid"))
            {
                ArrayList<Menu> menus = AppFeedManager.getMenus(menu.getIdentifier());
                if (menus != null && !menus.isEmpty())
                    ((BaseFragment) ListViewAdapter.this.listFragment.getParentFragment()).replaceFragment(title, sectionIdentifier, menus);

            }*/

        }
    }

}
