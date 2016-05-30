package com.craftsvilla.wallpaper.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.craftsvilla.wallpaper.R;
import com.craftsvilla.wallpaper.activity.WallpaperDetailActivity;

import java.util.List;

/**
 * Created by naresh on 26/5/16.
 */
public class GridWallpaperAdapter extends BaseAdapter {
    Context mContext;
    List<Integer> mData;
    LayoutInflater mLayoutInflater;

    public GridWallpaperAdapter(Context mContext, List<Integer> mData) {
        this.mData = mData;
        this.mContext = mContext;
    }


    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (mLayoutInflater == null)
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = mLayoutInflater.inflate(R.layout.grid_wallpaper, null);

        final ImageView mWallpaperLike = (ImageView) convertView.findViewById(R.id.wallpaperLike);
        mWallpaperLike.setImageResource(mData.get(position));
        mWallpaperLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation heartAnimation = AnimationUtils.loadAnimation(mContext,R.anim.heart_animation);
                mWallpaperLike.startAnimation(heartAnimation);
            }
        });


        final ImageView mWallpaper = (ImageView) convertView.findViewById(R.id.wallpaperImage);
        mWallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Hiie", "onClick: " + "onClick");
                Intent intent = new Intent(mContext, WallpaperDetailActivity.class);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation((Activity) mContext,mWallpaper, "profile");
                mContext.startActivity(intent,options.toBundle());
            }
        });
        return convertView;
    }
}
