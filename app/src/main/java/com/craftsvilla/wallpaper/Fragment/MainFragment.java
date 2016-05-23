package com.craftsvilla.wallpaper.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.craftsvilla.wallpaper.R;

/**
 * Created by naresh on 19/5/16.
 */
public class MainFragment extends Fragment {
    private static final String KEY_POSITION = "position";

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
        View view = inflater.inflate(R.layout.fragment_wallpaper, container, false);
        TextView wallpaperName = (TextView) view.findViewById(R.id.wallpaperName);
        int position = getArguments().getInt(KEY_POSITION, -1);
        String name = getArguments().getString("name");
        wallpaperName.setText(name);
        return view;
    }
}
