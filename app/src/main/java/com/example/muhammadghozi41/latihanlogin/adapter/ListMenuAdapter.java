package com.example.muhammadghozi41.latihanlogin.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.muhammadghozi41.latihanlogin.model.ListMenuItem;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.example.muhammadghozi41.latihanlogin.R;


import java.util.ArrayList;

/**
 * Created by muhammad.ghozi41 on 14/06/17.
 */

public class ListMenuAdapter extends ArrayAdapter<ListMenuItem> {
    private final LayoutInflater mInflater;
    private final ImageLoader imageLoader;
    private ArrayList<ListMenuItem> menuList;

    public ListMenuAdapter(@NonNull Context context, @LayoutRes int resource, ArrayList<ListMenuItem> objects) {
        super(context, resource, objects);
        menuList = objects;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader = ImageLoader.getInstance();

        if (!imageLoader.isInited()) {
            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .build();

            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                    .defaultDisplayImageOptions(options)
                    .build();
            imageLoader.init(config);
        }
    }


    @Override
    public int getCount() {
        return menuList.size();
    }

    @Nullable
    @Override
    public ListMenuItem getItem(int position) {
        return menuList.get(position);
    }

    @Override
    public int getPosition(@Nullable ListMenuItem item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return menuList.get(position).getId();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LinearLayout layout;

        if(convertView == null)
            layout = (LinearLayout) mInflater.inflate(R.layout.list_menu_layout, parent, false);
        else
            layout = (LinearLayout) convertView;

        ImageView icon = (ImageView) layout.findViewById(R.id.menu_icon);
        TextView label = (TextView) layout.findViewById(R.id.menu_label);
        TextView desc = (TextView) layout.findViewById(R.id.menu_desc);

        ListMenuItem itemMenu = menuList.get(position);

        if(itemMenu.getIconUrl() != null && itemMenu.getIconUrl().length() > 0)
            imageLoader.displayImage(itemMenu.getIconUrl(), icon);

        label.setText(itemMenu.getLabel());
        desc.setText(itemMenu.getDescription());


        return layout;
    }
}