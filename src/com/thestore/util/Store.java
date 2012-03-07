// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.thestore.util;

import android.app.Activity;
import android.content.SharedPreferences;

public class Store
{

    public Store(Activity activity1)
    {
        activity = activity1;
        sharedPreferences = createStore();
    }

    private SharedPreferences createStore()
    {
        return activity.getSharedPreferences("com.thestore.version_preferences", 0);
    }

    public int getInt(String s, int i)
    {
        return sharedPreferences.getInt(s, i);
    }

    public long getLong(String s, long l)
    {
        return sharedPreferences.getLong(s, l);
    }

    public String getString(String s, String s1)
    {
        return sharedPreferences.getString(s, s1);
    }

    public void storeInt(String s, int i)
    {
        sharedPreferences.edit().putInt(s, i).commit();
    }

    public void storeLong(String s, long l)
    {
        sharedPreferences.edit().putLong(s, l).commit();
    }

    public void storeString(String s, String s1)
    {
        sharedPreferences.edit().putString(s, s1).commit();
    }

    private Activity activity;
    public SharedPreferences sharedPreferences;
}
