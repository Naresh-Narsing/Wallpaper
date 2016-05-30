package com.craftsvilla.wallpaper;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.craftsvilla.wallpaper.Model.MainWallpaperModel;
import com.craftsvilla.wallpaper.adapter.MainAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    List<MainWallpaperModel> mMainWallpaperModels;
    TabLayout mTabLayout;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mMainWallpaperModels = new ArrayList<>();
        for (int i = 0; i <= 6; i++) {
            mMainWallpaperModels.add(new MainWallpaperModel(" " + i, "Wallpaper " + i));
        }
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(new MainAdapter(getSupportFragmentManager(), mMainWallpaperModels));
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
