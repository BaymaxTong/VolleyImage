package com.baymax.volleyimage.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.baymax.volleyimage.R;
import com.baymax.volleyimage.imageVolley.GifVolley;
import com.baymax.volleyimage.imageVolley.LoadGif;
import com.baymax.volleyimage.net.RequestImageLoader;
import com.baymax.volleyimage.utils.Images;


public class MainActivity extends AppCompatActivity {

    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        loadData();
    }

    private void initView() {
        image1 = (ImageView) findViewById(R.id.image1);
        image2 = (ImageView) findViewById(R.id.image2);
        image3 = (ImageView) findViewById(R.id.image3);
        image4 = (ImageView) findViewById(R.id.image4);
    }

    public void loadData(){
        //加载GIF图片
        GifVolley.with(this).getImageLoader().load(Images.imageThumbUrls[0]).into(image1);
        //加载圆形图片
        GifVolley.with(this).getImageLoader().loadCircle(Images.imageThumbUrls[2],true).into(image2);
        //加载普通图片
        GifVolley.with(this).getImageLoader().load(Images.imageThumbUrls[3]).into(image4);
        //加载普通图片
        GifVolley.with(this).getImageLoader().load(Images.imageThumbUrls[2]).into(image3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
