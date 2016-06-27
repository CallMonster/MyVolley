package com.tj.myvolley.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.orhanobut.logger.Logger;
import com.tj.myvolley.R;
import com.tj.myvolley.base.BaseApplication;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 请求缓存示例
 */
public class CacheRequestActivity extends AppCompatActivity implements View.OnClickListener,Response.ErrorListener{
    private String TAG="CacheRequestActivity";

    @Bind(R.id.cacheReqBtn) Button cacheReqBtn;
    @Bind(R.id.reqTipView) TextView reqTipView;

    private String CACHE_URL="http://query.sse.com.cn/marketdata/tradedata/queryMargin.do?jsonCallBack=jsonpCallback98613&isPagination=true&tabType=&pageHelp.pageSize=100&_=1438876224537";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cache_request);
        ButterKnife.bind(this);

        cacheReqBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cacheReqBtn:

                Map<String, String> header = new HashMap<String, String>();
                header.put("Host", "query.sse.com.cn");
                header.put("Connection", "Keep-Alive");
                header.put("Accept", "*/*");
                header.put("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; MyIE9; BTRS123646; .NET CLR 2.0.50727; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
                header.put("Referer", "http://www.sse.com.cn/market/dealingdata/overview/margin/");
                header.put("Accept-Encoding", "gzip, deflate, sdch");
                header.put("Accept-Language", "zh-CN,zh;q=0.8");

                cacheReq.setHeaders(header);
                BaseApplication.mInstance.addRequestQueue(cacheReq);
                break;
        }
    }

    StringRequest cacheReq=new StringRequest(Request.Method.GET,CACHE_URL,
               new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Logger.d(response);
                    reqTipView.setText(response.toString());
                }
            }, this);

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e(TAG, error.toString());

        Cache.Entry entry = BaseApplication.getInstance().getCache().get(CACHE_URL);
        if(entry != null){
            try {
                String data = new String(entry.data, "UTF-8");
                reqTipView.setText(data);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

    }
}
