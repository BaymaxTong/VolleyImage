package com.baymax.volleyimage.imageVolley;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.baymax.volleyimage.R;
import java.nio.ByteBuffer;


/**
 * 从缓冲区异步读取数据流
 * Created by baymax on 2016/5/16.
 */
public class ReadImageTask extends AsyncTask<String, Integer, ByteBuffer> {
    private final int DEFAULT_IMAGE_LOADING = R.drawable.image_loading;
    private final int DEFAULT_IMAGE_ERROR = R.drawable.image_load_error;

    private ImageView mImageView;
    private String mImageUrl;
    private int mLoadingImage = DEFAULT_IMAGE_LOADING;
    private int mErrorImage = DEFAULT_IMAGE_ERROR;
    private ImageLoader.ImageCache mImageCache;
    private ImageLoader mImageLoader;
    private Context mContext;
    private int mMaxSize;
    private boolean isCircle = false;

    public ReadImageTask(Context context, ImageLoader.ImageCache imageCache, ImageLoader imageLoader, String imageUrl) {
        mImageUrl = imageUrl;
        mImageCache = imageCache;
        mImageLoader = imageLoader;
        mContext = context;
    }

    public ReadImageTask(Context context, ImageLoader.ImageCache imageCache, ImageLoader imageLoader, String imageUrl, boolean isCircle) {
        mImageUrl = imageUrl;
        mImageCache = imageCache;
        mImageLoader = imageLoader;
        mContext = context;
        this.isCircle = isCircle;
    }

    @Override
    protected ByteBuffer doInBackground(String... params) {
        if ( mImageUrl == null || mImageUrl.isEmpty() || !mImageUrl.startsWith("http") ) {
            return null;
        }
        return mImageCache.getBitmap(mImageUrl);
    }

    @Override
    protected void onPostExecute(ByteBuffer byteBuffer) {
        if ( byteBuffer != null ) {
            byte[] bytes = byteBuffer.array();
            // 判断是否为GIF图片，如果是则加载
            if ( LoadGif.isGif(mImageView, bytes) )
                return;

            Bitmap image = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            //判断是否为圆形图片，如果是则加载
            if(isCircle){
                mImageView.setImageBitmap(LoadGif.toRoundBitmap(image));
            }else{
                mImageView.setImageBitmap(image);
            }
        } else if ( mImageUrl == null || mImageUrl.isEmpty() || !mImageUrl.startsWith("http") ) {
            mImageView.setImageDrawable(mContext.getResources().getDrawable(mErrorImage));
        } else {
            GifImageListener listener = new GifImageListener(mContext, mImageView, mImageCache , isCircle);
            listener.setDefaultImage(mLoadingImage, mErrorImage);
            listener.setMaxSize(mMaxSize);
            mImageLoader.get(mImageUrl, listener);
        }
    }

    protected void setView(ImageView imageView, int image_loading, int image_error) {
        mImageView = imageView;
        mLoadingImage = image_loading;
        mErrorImage = image_error;
    }

    protected void setView(ImageView imageView) {
        mImageView = imageView;
    }

    protected void setSize(int size) {
        mMaxSize = size;
    }

}
