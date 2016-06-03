package com.craftsvilla.wallpaper.Model;

/**
 * Created by naresh on 2/6/16.
 */
public class WallpaperGrid {
    public String id;
    public String name;
    public String img;


    public WallpaperGrid(String id,String img, String name) {
        this.id  = id;
        this.img = img;
        this.name = name;
    }

    public WallpaperGrid() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
