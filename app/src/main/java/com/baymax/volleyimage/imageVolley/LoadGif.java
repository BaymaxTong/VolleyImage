package com.baymax.volleyimage.imageVolley;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.widget.ImageView;

import pl.droidsonroids.gif.GifDrawable;

/**
 * 加载Gif图片的工具类
 * Created by baymax on 2016/5/16.
 */
public class LoadGif {
    /**
     * 判断是不是GIF图片，如果是则加载Gif
     * @param imageView 传入ImageView对象
     * @param data      传入数据字节流
     * @return
     */
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
    /**
     * 转换图片成圆形
     * @param bitmap 传入Bitmap对象
     * @return Bitmap
     */
    public static Bitmap toRoundBitmap(Bitmap bitmap)
    {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float roundPx;
        float left,top,right,bottom,dst_left,dst_top,dst_right,dst_bottom;
        if (width <= height) {             //高或者宽两者中比较小的，在取一半
            roundPx = width / 2;
            top = 0;
            bottom = width;
            left = 0;
            right = width;
            height = width;                //高度修给成宽度等同
            dst_left = 0;
            dst_top = 0;
            dst_right = width;
            dst_bottom = width;
        } else {
            roundPx = height / 2;
            float clip = (width - height) / 2;
            left = clip;
            right = width - clip;
            top = 0;
            bottom = height;
            width = height;
            dst_left = 0;
            dst_top = 0;
            dst_right = height;
            dst_bottom = height;
        }

        Bitmap output = Bitmap.createBitmap(width,
                height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        int color = 0xff424242;

        Rect src = new Rect((int)left, (int)top, (int)right, (int)bottom);
        Rect dst = new Rect((int)dst_left, (int)dst_top, (int)dst_right, (int)dst_bottom);
        RectF rectF = new RectF(dst_left, dst_top, dst_right, dst_bottom);

        Paint paint = new Paint();
        paint.setAntiAlias(true);              //边缘无锯齿
        paint.setColor(color);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, src, dst, paint);
        return output;
    }
}
