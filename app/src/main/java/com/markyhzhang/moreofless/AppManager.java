package com.markyhzhang.moreofless;

import android.content.SharedPreferences;

public class AppManager {

//    private SharedPreferences storage;

    /*
     in order from top left to bottom right by going down each column;
     */
//    private SparseArray<AppAction> apps = new SparseArray<>();
//
//    public AppManager(SharedPreferences storage){
//        this.storage = storage;
//        for (int i = 0; i < apps.size(); i++) {
//            if (!storage.contains("APP"+i))continue;
//            String[] raw = storage.getString("APP"+i,"!!NULL ERROR!!").split(",");
//            String nameString = raw[0];
//            String packageString = raw[1];
//            apps.put(i,new AppAction(nameString, packageString));
//        }
//    }

    public static boolean contains(SharedPreferences storage, int i){
        return storage.contains("APPSTR"+i);
    }

    public static AppAction get(SharedPreferences storage, int i){
        if (!contains(storage, i))return null;
        return new AppAction(storage.getString("APPSTR"+i,"NULL"),
                storage.getString("PKGSTR"+i,"NULL"));
    }

    public static void set(SharedPreferences storage, int i, String nameString, String packageString){
        SharedPreferences.Editor editor = storage.edit();
        editor.putString("APPSTR"+i, nameString);
        editor.putString("PKGSTR"+i, packageString);
        editor.apply();
    }



}
