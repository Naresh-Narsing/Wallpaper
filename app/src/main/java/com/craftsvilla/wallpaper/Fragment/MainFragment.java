package com.craftsvilla.wallpaper.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.craftsvilla.wallpaper.R;
import com.craftsvilla.wallpaper.adapter.GridWallpaperAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by naresh on 19/5/16.
 */
public class MainFragment extends Fragment {
    private static final String KEY_POSITION = "position";
    public static String TAG = MainFragment.class.getSimpleName();
    List<Integer> list;
    Integer[] image = {R.drawable.heart, R.drawable.heart, R.drawable.heart, R.drawable.heart};
    GridWallpaperAdapter gridWallpaperAdapter;

    public static Fragment newInstance(int position, String name) {
        MainFragment mainFragment = new MainFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_POSITION, position);
        args.putString("name", name);
        mainFragment.setArguments(args);
        return mainFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_gridview, container, false);
        GridView gridView = (GridView) view.findViewById(R.id.gridView);

        list = new ArrayList<>();
        for (int i = 0; i <= 25; i++) {
            list.add(image[i % 4]);
        }

        gridWallpaperAdapter = new GridWallpaperAdapter(getActivity(), list);
        gridView.setAdapter(gridWallpaperAdapter);
        return view;
    }
}
