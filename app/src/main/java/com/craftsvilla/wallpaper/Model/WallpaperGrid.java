package com.craftsvilla.wallpaper.Model;

/**
 * Created by naresh on 2/6/16.
 */
public class WallpaperGrid {
    String img;
    String name;

    public WallpaperGrid(String img, String name) {
        this.img = img;
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
