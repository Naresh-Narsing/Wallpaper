package com.craftsvilla.wallpaper.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.craftsvilla.wallpaper.MainActivity;
import com.craftsvilla.wallpaper.Model.WallpaperGrid;
import com.craftsvilla.wallpaper.R;
import com.craftsvilla.wallpaper.adapter.GridWallpaperAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by naresh on 19/5/16.
 */
public class MainFragment extends Fragment {
    private static final String KEY_POSITION = "position";
    public static String TAG = MainFragment.class.getSimpleName();
//    List<Integer> list;
    List<WallpaperGrid> mWallpaperGrid;
    GridView gridView;
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
        gridView = (GridView) view.findViewById(R.id.gridView);

        mWallpaperGrid = new ArrayList<>();

        String name = getArguments().getString("name");

        if (name != null && !name.equals("Suits")) {
            parseJson(loadJsonFromAsset());
        }else {
            Log.i(TAG, "onCreateView: IN ELSE"+MainActivity.mAddToFav.size());
            gridWallpaperAdapter = new GridWallpaperAdapter(getActivity(), MainActivity.mAddToFav);
            gridView.setAdapter(gridWallpaperAdapter);
        }

        /*list = new ArrayList<>();
        for (int i = 0; i <= 25; i++) {
            list.add(image[i % 4]);
        }*/

       /* gridWallpaperAdapter = new GridWallpaperAdapter(getActivity(), list);
        gridView.setAdapter(gridWallpaperAdapter);*/
        return view;
    }

    public String loadJsonFromAsset(){
        String json = null;
        InputStream inputStream = null;
        try {
            inputStream = getActivity().getAssets().open("wallpapergrid");
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
        Log.i(TAG, "parseJson: DETAIL JSON PARSING");
        String img;
        String name;

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                img = jsonObject1.getString("image");
                name = jsonObject1.getString("name");
                mWallpaperGrid.add(new WallpaperGrid(img,name));
            }
            if (mWallpaperGrid != null && mWallpaperGrid.size() > 0) {
                gridWallpaperAdapter = new GridWallpaperAdapter(getActivity(), mWallpaperGrid);
                gridView.setAdapter(gridWallpaperAdapter);
            }

        }catch (JSONException e){
            e.printStackTrace();
        }

    }
}
