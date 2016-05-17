package com.baymax.volleyimage.imageVolley;


import android.content.Context;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
/**
 * 配合LruCache和AsyncTask实现缓存与读取显示图片
 * Created by baymax on 2016/5/16.
 */
public class GifImageLoader {
    private ImageLoader mImageLoader;
    private Context mContext;
    private final static int CACHE_SIZE = 1024 * 1024 * 10;   //缓存大小设置
    private LruImageCache mLruImageCache;
    private ReadImageTask mReadImageTask;
    private int mMaxSize = 0;

    public GifImageLoader(Context context, RequestQueue requestQueue){
        mContext = context;
        mLruImageCache = new LruImageCache(CACHE_SIZE);
        mImageLoader = new ImageLoader(requestQueue, mLruImageCache);
    }

    public GifImageLoader load(String url) {
        mReadImageTask = new ReadImageTask(mContext, mLruImageCache, mImageLoader, url);
        return this;
    }

    public void into(ImageView imageView) {
        mReadImageTask.setView(imageView);
        mReadImageTask.setSize(mMaxSize);
        mReadImageTask.execute();
    }

    public void into(ImageView imageView, int loadingImage, int errorImage) {
        mReadImageTask.setView(imageView, loadingImage, errorImage);
        mReadImageTask.setSize(mMaxSize);
        mReadImageTask.execute();
    }

    public GifImageLoader resize(int maxPix) {
        mMaxSize = maxPix;
        return this;
    }
}
