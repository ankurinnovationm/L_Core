package core2.maz.com.core2.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import core2.maz.com.core2.R;
import core2.maz.com.core2.activities.WebViewActivity;
import core2.maz.com.core2.fragments.BaseFragment;
import core2.maz.com.core2.fragments.ListFragment;
import core2.maz.com.core2.managers.AppFeedManager;
import core2.maz.com.core2.model.Menu;

/**
 * Created by Ankur Jain on 16-11-2016.
 */
public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.MyViewHolder> {

    private ArrayList<Menu> menus;
    Context context;
    private AQuery aQuery ;
    private ListFragment listFragment;
    private int sectionIdentifier;
    private final int CUSTOM_FEED = 1;
    private final int ARTICLE=2;
    private final int  AD = 3;
    public ListViewAdapter(ArrayList<Menu> menus,Context context, ListFragment listFragment,int sectionIdentifier)
    {
        this.menus = menus;
        this.context = context;
        this.listFragment = listFragment;
        this.sectionIdentifier = sectionIdentifier;
        aQuery = new AQuery(context);
        if(menus==null ||menus.isEmpty())
        {
            return;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        MyViewHolder myViewHolder =null;
        View view = null;
        if(viewType ==ARTICLE)
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_items, parent, false);
            myViewHolder = new ArticleViewHolder(view);
        }
        else if(viewType == CUSTOM_FEED)
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customfeed_layout_listview,parent,false);
            myViewHolder = new CustomViewHolder(view);
        }
        else if(viewType == AD)
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ad,parent,false);
            myViewHolder = new AdViewHolder(view);
        }



        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        if(holder!=null)
        {
            Menu menu = menus.get(position);
            if (holder instanceof ArticleViewHolder)
            {
                final ArticleViewHolder articleViewHolder = (ArticleViewHolder) holder;
                articleViewHolder.menuTitle.setText(menu.getTitle());
                Picasso.with(context).load(menu.getImage()).placeholder(R.drawable.img_placeholder).into(articleViewHolder.menuCoverImage);
            }
            else if (holder instanceof CustomViewHolder)
            {
                final CustomViewHolder customViewHolder = (CustomViewHolder) holder;
                customViewHolder.textViewHeader.setText(menu.getTitle());
                holder.itemView.setVisibility(View.GONE);

            }
            else
            {
                AdViewHolder adViewHolder = (AdViewHolder)holder;
                holder.itemView.setVisibility(View.GONE);
            }
        }

    }


    @Override
    public int getItemCount()
    {
        if(menus!=null)
            return menus.size();
        else
        {
            return 0;
        }
    }



    public class MyViewHolder extends RecyclerView.ViewHolder
    {

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }


    public class CustomViewHolder extends MyViewHolder
    {
        public TextView textViewHeader;
        public CustomViewHolder(View itemView)
        {
            super(itemView);
            textViewHeader = (TextView)itemView.findViewById(R.id.textViewHeader);
        }
    }

    public class AdViewHolder extends MyViewHolder
    {

        public AdViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class ArticleViewHolder extends MyViewHolder implements View.OnClickListener
    {

        private ImageView menuCoverImage;
        private TextView menuTitle;
        public ArticleViewHolder(View itemView)
        {
            super(itemView);
            menuCoverImage = (ImageView) itemView.findViewById(R.id.imageViewArticle);
            menuTitle = (TextView) itemView.findViewById(R.id.textViewArticleText);
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

            else if (!menu.getType().equals("vid"))
            {
                ArrayList<Menu> menus = AppFeedManager.getMenus(menu.getIdentifier());
                if (menus != null && !menus.isEmpty())
                    ((BaseFragment) ListViewAdapter.this.listFragment.getParentFragment()).replaceFragment(title, sectionIdentifier, menus);

            }

        }
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
}
