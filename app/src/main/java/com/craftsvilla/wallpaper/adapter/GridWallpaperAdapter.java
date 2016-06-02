package com.craftsvilla.wallpaper.adapter;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.craftsvilla.wallpaper.MainActivity;
import com.craftsvilla.wallpaper.Model.WallpaperGrid;
import com.craftsvilla.wallpaper.R;
import com.craftsvilla.wallpaper.activity.WallpaperDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by naresh on 26/5/16.
 */
public class GridWallpaperAdapter extends BaseAdapter {
    public static final String TAG = GridWallpaperAdapter.class.getSimpleName();
    Context mContext;

    List<WallpaperGrid> mData;
    LayoutInflater mLayoutInflater;
    ImageView wallpaperImage;
    TextView wallpaperTitle;
    ImageView wallpaperLike;
    AnimatorSet heartAnimation;

    public GridWallpaperAdapter(Context mContext, List<WallpaperGrid> mData) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;

        if (mLayoutInflater == null)
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            try {
                convertView = mLayoutInflater.inflate(R.layout.grid_wallpaper, null);
            }catch (Exception e){
                e.printStackTrace();
            }

        viewHolder = new ViewHolder();

        viewHolder.wallpaperImage = (ImageView) convertView.findViewById(R.id.wallpaperImage);
        Glide.with(mContext)
                .load(mData.get(position).getImg())
                .into(viewHolder.wallpaperImage);

        viewHolder.wallpaperTitle = (TextView) convertView.findViewById(R.id.wallpaperTitle);
        viewHolder.wallpaperTitle.setText(mData.get(position).getName());

        viewHolder.wallpaperLike = (ImageView) convertView.findViewById(R.id.wallpaperLike);
        viewHolder.wallpaperLike.setImageResource(R.drawable.heart);
        viewHolder.wallpaperLike.setTag("1");

        viewHolder.wallpaperLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewHolder.wallpaperLike.getTag() == "1") {
                    Log.i(TAG, "onClick: " + "INSIDE LIKE ANIMATION");
                    if (MainActivity.mAddToFav != null) {
                        Log.i(TAG, "onClick: INSIDE ");
                        MainActivity.mAddToFav.add(mData.get(position));
                    }
                    heartAnimation = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.heart_animation);
                    heartAnimation.setTarget(wallpaperLike);
                    heartAnimation.start();
                }else {
                    Log.i(TAG, "onClick: ERROR");
                }
            }
        });

        viewHolder.wallpaperImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Hiie", "onClick: " + "onClick");
                Intent intent = new Intent(mContext, WallpaperDetailActivity.class);
               /* ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation((Activity) mContext, wallpaperImage, "profile");
                mContext.startActivity(intent, options.toBundle());*/
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }

    static class ViewHolder{
        ImageView wallpaperImage;
        ImageView wallpaperLike;
        TextView wallpaperTitle;
    }
}
