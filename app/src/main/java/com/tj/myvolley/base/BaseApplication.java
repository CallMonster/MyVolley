package com.tj.myvolley.base;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.android.volley.toolbox.multipart.MultiPartStack;

/**
 * Created by Lee on 2015/11/13.
 * @desc 自写版本
 */
public class BaseApplication extends Application {
    public static final String TAG = BaseApplication.class
            .getSimpleName();
    public static final String CA_FILE_DIR="certificate/tt.cer";//ca证书的路径

    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    public static BaseApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Volley.DESIGN_CACHE_DIR="vcache";//设置缓存目录
    }

    public static synchronized BaseApplication getInstance() {
        return mInstance;
    }

    /**
     * 获取请求队列
     * @return RequestQueue
     */
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext(),new HurlStack());
        }
        return mRequestQueue;
    }

    /**
     * 获取HTTPS请求队列
     * @return RequestQueue
     */
    public RequestQueue getRequestQueue(String cer,boolean isHttps) {
        if (mRequestQueue == null&&isHttps) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext(),cer);
        }
        return mRequestQueue;
    }

    /**
     * 获取请求队列
     * @param multiPartFlag 1:表明请求的是MultiPart的请求
     * @return
     */
    public RequestQueue getRequestQueue(int multiPartFlag) {
        if (mRequestQueue == null&&multiPartFlag==1) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext(),new MultiPartStack());
        }

        return mRequestQueue;
    }

    /**
     * 获取ImageLoader
     * @return
     */
    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader(this.mRequestQueue,
                    new LruBitmapCache());
        }
        return this.mImageLoader;
    }

    /**
     * 添加请求队列
     * @param req 请求Request
     * @param tag Request添加tag
     * @param <T>
     */
    public <T> void addRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    /**
     * 添加请求队列
     * @param req 请求Request
     * @param <T>
     */
    public <T> void addRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    /**
     * 添加https请求队列
     * @param req 请求Request
     * @param <T>
     */
    public <T> void addRequestQueue(Request<T> req,boolean isHttps) {
        req.setTag(TAG);
        RequestQueue queue=getRequestQueue(CA_FILE_DIR, isHttps);
        if(queue!=null){
            queue.add(req);
        }
    }

    /**
     * 添加MultiPart请求队列
     * @param req 请求Request
     * @param <T>
     */
    public <T> void addRequestQueue(Request<T> req,int multiPartFlag) {
        req.setTag(TAG);
        getRequestQueue(multiPartFlag).add(req);
    }

    /**
     * 取消所有请求队列
     * @param tag
     */
    public void cancelAllRequests(Object tag) {
        if (getInstance().getRequestQueue() != null) {
            getInstance().getRequestQueue().cancelAll(tag);
        }
    }

    /**
     * 获取Cache
     * @return
     */
    public Cache getCache(){
        return getRequestQueue().getCache();
    }

}
