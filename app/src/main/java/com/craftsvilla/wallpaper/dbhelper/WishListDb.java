package com.craftsvilla.wallpaper.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.craftsvilla.wallpaper.Model.WallpaperGrid;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by naresh on 3/6/16.
 */
public class WishListDb extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FavItemDataBase";
    public static final String TABLE_NAME = "FavouritesItems";
    //table columns
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "(" + KEY_ID + " TEXT PRIMARY KEY, " +
                    KEY_NAME + " TEXT" + ")";
    private static final String TAG = "Database";

    public WishListDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_NAME);
        onCreate(db);
    }

    public void addFavItem(final WallpaperGrid wallpaperGrid) {
        try {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            Log.i(TAG, "run: ADDING TO DATABASE");
            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY_NAME, wallpaperGrid.name);
            contentValues.put(KEY_ID, wallpaperGrid.id);
            sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
            Log.i(TAG, "run: SUCCESSFULLY INSERTED");
            sqLiteDatabase.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<WallpaperGrid> getListOfFavItems() {
        final List<WallpaperGrid> favList = new ArrayList<>();
        final String selectQuery = "SELECT * FROM " + TABLE_NAME;
        final SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        final Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    WallpaperGrid wallpaperGrid = new WallpaperGrid();
                    wallpaperGrid.id = cursor.getString(cursor.getColumnIndex(KEY_ID));
                    wallpaperGrid.name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
                    favList.add(wallpaperGrid);
                } while (cursor.moveToNext());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                Log.i(TAG, "getListOfFavItems: FINALLY CURSOR METHOD IS OPEN");
                cursor.close();
            }
        }
        Log.i(TAG, "getListOfFavItems: size of fav items from DB" + favList.size());
        return favList;
    }

    public int removeFavItemDb(final String keyId) {
        int i = 0;
        final SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        try {

            i = sqLiteDatabase.delete(TABLE_NAME, KEY_ID + " =?",
                    new String[]{String.valueOf(keyId)});
            String countQuery = "SELECT * FROM " + TABLE_NAME;
            Cursor cursor = sqLiteDatabase.rawQuery(countQuery, null);
            int count = cursor.getCount();
            cursor.close();
            return count;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    public boolean isIdExist(String id) {
        boolean isExist = false;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
//        String Query = "SELECT * FROM " + TABLE_NAME + " WHERE " + KEY_ID + " = " + id;
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, new String[]{KEY_ID},
                KEY_ID + " =?", new String[]{id},
                null, null, null, null);
        if (cursor.moveToFirst())
            isExist = true;
        return isExist;
    }
}
