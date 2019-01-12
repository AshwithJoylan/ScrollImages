package com.facebook.nextimage;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.api.Context;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends PagerAdapter {
    ArrayList<String> mdata;
    android.content.Context context;
    LayoutInflater inflater;

    public Adapter() {

    }

    public Adapter(ArrayList<String> mdata, android.content.Context context) {
        this.mdata = mdata;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mdata.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view.equals(o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull final ViewGroup container, final int position) {

        inflater = LayoutInflater.from(container.getContext());

        View view = inflater.inflate(R.layout.pager_item, container, false);

        ImageView wallpaper;
        Button btn;

        wallpaper = view.findViewById(R.id.walpaperImage);

        Picasso.get().load(mdata.get(position)).into(wallpaper);

        container.addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}