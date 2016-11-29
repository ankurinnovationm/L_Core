package core2.maz.com.core2.adapter;

import android.content.Context;
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
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_view_items, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        Menu menu = menus.get(position);
        holder.menuTitle.setText(menu.getTitle());
        Picasso.with(context).load(menu.getImage()).placeholder(R.drawable.img_placeholder).into(holder.menuCoverImage);

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



    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private ImageView menuCoverImage;
        private TextView menuTitle;
        public MyViewHolder(View itemView)
        {
            super(itemView);
            menuCoverImage = (ImageView) itemView.findViewById(R.id.imageViewArticle);
            menuTitle  = (TextView) itemView.findViewById(R.id.textViewArticleText);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            int position = getAdapterPosition();
            Menu menu = menus.get(position);
            String title = menu.getTitle();
            if (!menu.getType().equals("vid"))
            {
                ArrayList<Menu> menus = AppFeedManager.getMenus(menu.getIdentifier());
                if(menus!=null && !menus.isEmpty())
                ((BaseFragment)ListViewAdapter.this.listFragment.getParentFragment()).replaceFragment(title,sectionIdentifier,menus);

            }
            //else if(menu.getType())
        }
    }

}
