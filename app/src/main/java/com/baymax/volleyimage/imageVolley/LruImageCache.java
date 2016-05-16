package com.baymax.volleyimage.imageVolley;

import android.util.LruCache;

import java.nio.ByteBuffer;

/**
 * 基于LruCache的缓存算法
 * Created by baymax on 2016/5/16.
 */
public class LruImageCache extends LruCache<String, ByteBuffer> implements ImageLoader.ImageCache {
	
	public LruImageCache(int maxSize) {
		super(maxSize);
	}
	
	@Override
	protected int sizeOf(String key, ByteBuffer value) {
		return value.capacity();
	}
	
	@Override
	public ByteBuffer getBitmap(String url) {
		return get(url);
	}

	@Override
	public void putBitmap(String url, ByteBuffer bitmap) {
		put(url, bitmap);
	}
}
