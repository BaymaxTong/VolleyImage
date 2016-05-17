package com.baymax.volleyimage.imageVolley;

import android.content.Context;
import com.android.volley.RequestQueue;
import com.baymax.volleyimage.base.MyApplication;

/**
 * 加载GIF图片的入口类
 * Created by baymax on 2016/5/16.
 */
public class GifVolley {
    private static RequestQueue mRequestQueue;
    private static GifImageLoader mGifImageLoader;
    private static GifVolley mGifVolley = null;
    private Context mContext = null;

    private GifVolley(Context context){
        mContext = context;
        mRequestQueue = MyApplication.getVolleyQueue();
        mGifImageLoader = new GifImageLoader(context, mRequestQueue);
    }

    public static GifVolley with(Context context){
        if(mGifVolley == null){
            mGifVolley = new GifVolley(context.getApplicationContext());
        }
        return mGifVolley;
    }

    public GifImageLoader getImageLoader() {
        return mGifImageLoader;
    }
}
