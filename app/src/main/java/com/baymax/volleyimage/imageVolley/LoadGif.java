package com.baymax.volleyimage.imageVolley;

import android.widget.ImageView;

import pl.droidsonroids.gif.GifDrawable;

/**
 * Created by baymax on 2016/5/16.
 */
public class LoadGif {
    //判断是不是GIF图片，如果是则加载Gif
    public static boolean isGif(ImageView imageView, byte[] data) {
        if ( data[0] == 'G' && data[1] == 'I' && data[2] == 'F') {
            try {
                GifDrawable gifDrawable = new GifDrawable(data);
                imageView.setImageDrawable(gifDrawable);
                return true;
            } catch (Throwable e) {
                //  pl.droidsonroids.gif.GifDrawable not found
            }
        }
        return false;
    }
}
