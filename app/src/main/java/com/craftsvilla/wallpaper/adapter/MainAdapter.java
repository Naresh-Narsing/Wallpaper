package com.craftsvilla.wallpaper.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.craftsvilla.wallpaper.Fragment.MainFragment;
import com.craftsvilla.wallpaper.Model.MainWallpaperModel;

import java.util.List;

/**
 * Created by naresh on 19/5/16.
 */
public class MainAdapter extends FragmentPagerAdapter {
    List<MainWallpaperModel> mainWallpaperModels;

    public MainAdapter(FragmentManager fm, List<MainWallpaperModel> mainWallpaperModels) {
        super(fm);
        this.mainWallpaperModels = mainWallpaperModels;
    }

    @Override
    public Fragment getItem(int position) {
        return MainFragment.newInstance(position, mainWallpaperModels.get(position).getName());
    }

    @Override
    public int getCount() {
        return mainWallpaperModels.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mainWallpaperModels.get(position).getName();
    }
}
