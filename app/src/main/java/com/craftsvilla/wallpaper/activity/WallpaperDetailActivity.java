package com.craftsvilla.wallpaper.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.craftsvilla.wallpaper.R;

/**
 * Created by naresh on 27/5/16.
 */
public class WallpaperDetailActivity extends AppCompatActivity {
    public static String TAG = WallpaperDetailActivity.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collapsing);

 /*       Toolbar toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        setSupportActionBar(toolbar);*/


        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapse_toolbar);
//        collapsingToolbar.setTitle("");
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.heart_animation);
        collapsingToolbar.startAnimation(animation);

       /* collapsingToolbar.setCollapsedTitleTextColor (ContextCompat.getColor(getApplicationContext(),R.color.orange));
        collapsingToolbar.setExpandedTitleColor(ContextCompat.getColor(getApplicationContext(),R.color.abc));*/
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
