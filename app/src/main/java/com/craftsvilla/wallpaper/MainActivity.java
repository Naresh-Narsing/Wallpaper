package com.craftsvilla.wallpaper;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;

import com.craftsvilla.wallpaper.Fragment.NavigationDrawerFragment;
import com.craftsvilla.wallpaper.Model.MainWallpaperModel;
import com.craftsvilla.wallpaper.Model.WallpaperGrid;
import com.craftsvilla.wallpaper.adapter.MainAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    List<MainWallpaperModel> mMainWallpaperModels;
    TabLayout mTabLayout;
    ViewPager mViewPager;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    public static List<WallpaperGrid> mAddToFav;

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(Gravity.LEFT))
        {
            drawerLayout.closeDrawer(Gravity.LEFT);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mAddToFav = new ArrayList<>();

        toolbar = (Toolbar) findViewById(R.id.toolbar_layout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationDrawerFragment nv = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);

        nv.setUp(R.id.fragment_navigation_drawer,drawerLayout,toolbar);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mMainWallpaperModels = new ArrayList<>();
        /*for (int i = 0; i <= 6; i++) {
            mMainWallpaperModels.add(new MainWallpaperModel(" " + i, "Wallpaper " + i));
        }*/

        parseJson(loadJsonFromAsset());

        /*mViewPager.setAdapter(new MainAdapter(getSupportFragmentManager(), mMainWallpaperModels));
        mTabLayout.setupWithViewPager(mViewPager);*/


    }

        public String loadJsonFromAsset(){
            String json = null;
            InputStream inputStream = null;
            try {
                inputStream = MainActivity.this.getAssets().open("Tab");
                int size = inputStream.available();
                byte[] buffer = new byte[size];
                inputStream.read(buffer);
                inputStream.close();
                json = new String(buffer, "UTF-8");
            }catch (Exception e){
                e.printStackTrace();
            }
            return json;
        }

        private void parseJson(String json){
            Log.i(TAG, "parseJson: PARSING JSON");
            String id;
            String category;
            String name;

            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONArray jsonArray = jsonObject.getJSONArray("category");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    name = jsonObject1.getString("name");
                    id = jsonObject1.getString("id");
                    mMainWallpaperModels.add(new MainWallpaperModel(id,name));
                }
                if (mMainWallpaperModels!= null && mMainWallpaperModels.size()>0) {
                    mViewPager.setAdapter(new MainAdapter(getSupportFragmentManager(), mMainWallpaperModels));
                    mTabLayout.setupWithViewPager(mViewPager);
                }
            }catch (JSONException e){
                e.printStackTrace();

            }

        }
}
