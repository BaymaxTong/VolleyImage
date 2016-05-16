package com.baymax.volleyimage.net;

import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.baymax.volleyimage.R;
import com.baymax.volleyimage.base.MyApplication;

/**
 * Created by baymax on 2016/1/21.
 */
public class RequestImageLoader {
    /**
     *  ImageLoader  加载图片 防止内存泄露
     */
    public static void loadImage(String url,ImageView view){
        ImageLoader imageLoader = new ImageLoader(MyApplication.getVolleyQueue(), new BitmapCache());
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(view, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        imageLoader.get(url, listener);
    }

}
