package com.tj.myvolley.activity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.tj.myvolley.R;
import com.tj.myvolley.base.BaseApplication;
import com.tj.myvolley.utils.BitmapCache;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 对网络图片进行解析和操作 示例(与picasso 相仿)
 */
public class ImageRequestActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG="ImageRequestActivity";
    private RequestQueue requestQueue;

    private ImageLoader imageLoader;
    private ImageLoader.ImageListener listener;

    @Bind(R.id.request_netImg) ImageView request_netImg;
    @Bind(R.id.loader_netImg) ImageView loader_netImg;
    @Bind(R.id.network_image_view) NetworkImageView network_image_view;

    @Bind(R.id.imgReqBtn) Button imgReqBtn;
    @Bind(R.id.imgloaderBtn) Button imgloaderBtn;
    @Bind(R.id.NetworkImageViewBtn) Button NetworkImageViewBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_request);
        ButterKnife.bind(this);

        imgReqBtn.setOnClickListener(this);
        imgloaderBtn.setOnClickListener(this);
        NetworkImageViewBtn.setOnClickListener(this);

        requestQueue=BaseApplication.getInstance().getRequestQueue();
        imageLoader = new ImageLoader(requestQueue, new BitmapCache());
        listener = ImageLoader.getImageListener(loader_netImg,R.drawable.github_loading_inner,
                R.drawable.err_img);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgReqBtn:
                BaseApplication.mInstance.addRequestQueue(imgRequest,"img1");//第一种方式
                break;
            case R.id.imgloaderBtn:
                imageLoader.get("http://highsea90.com/wp-content/uploads/2014/05/1.jpg",listener);
                break;
            case R.id.NetworkImageViewBtn:
                network_image_view.setDefaultImageResId(R.drawable.github_loading_inner);
                network_image_view.setErrorImageResId(R.drawable.err_img);
                network_image_view.setImageUrl("http://img.blog.csdn.net/20150201224858245",
                        imageLoader);
                break;
        }
    }

    ImageRequest imgRequest = new ImageRequest("http://highsea90.com/wp-content/uploads/2014/05/1.jpg",
            new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap response) {
                    request_netImg.setImageBitmap(response);
                }
            }, 0, 0, ImageView.ScaleType.FIT_XY, Bitmap.Config.ARGB_8888,
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    request_netImg.setImageResource(R.drawable.test_alone);
                }
            });

}
