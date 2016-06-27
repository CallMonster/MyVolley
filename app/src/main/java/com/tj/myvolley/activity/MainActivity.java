package com.tj.myvolley.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.orhanobut.logger.Logger;
import com.tj.myvolley.R;
import com.tj.myvolley.base.BaseApplication;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends Activity implements View.OnClickListener, Response.ErrorListener{

    private Button SrBtn,JumpActBtn,JumpImgReqActBtn;
    @Bind(R.id.JumpXMLReqActBtn) Button JumpXMLReqActBtn;
    @Bind(R.id.JumpGsonReqActBtn) Button JumpGsonReqActBtn;
    @Bind(R.id.HeaderSrBtn) Button HeaderSrBtn;
    @Bind(R.id.JumpCacheReqActBtn) Button JumpCacheReqActBtn;
    @Bind(R.id.JumpListReqActBtn) Button JumpListReqActBtn;
    @Bind(R.id.JumpMultipartReqActBtn) Button JumpMultipartReqActBtn;
    @Bind(R.id.JumpStringReqActBtn) Button JumpStringReqActBtn;
    @Bind(R.id.JumpHttpsReqActBtn) Button JumpHttpsReqActBtn;
    @Bind(R.id.statusJsonBtn) Button statusJsonBtn;

    private TextView reqView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        reqView= (TextView) findViewById(R.id.reqView);
        SrBtn= (Button) findViewById(R.id.SrBtn);
        SrBtn.setOnClickListener(this);

        JumpActBtn= (Button) findViewById(R.id.JumpJOActBtn);
        JumpActBtn.setOnClickListener(this);

        JumpImgReqActBtn= (Button) findViewById(R.id.JumpImgReqActBtn);
        JumpImgReqActBtn.setOnClickListener(this);

        JumpXMLReqActBtn.setOnClickListener(this);
        JumpGsonReqActBtn.setOnClickListener(this);
        HeaderSrBtn.setOnClickListener(this);
        JumpCacheReqActBtn.setOnClickListener(this);
        JumpListReqActBtn.setOnClickListener(this);
        JumpMultipartReqActBtn.setOnClickListener(this);
        JumpStringReqActBtn.setOnClickListener(this);
        JumpHttpsReqActBtn.setOnClickListener(this);
        statusJsonBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.SrBtn:
                strRequest.setRetryPolicy(new DefaultRetryPolicy(10000, 3, 1f));//超时时间、连接次数
                BaseApplication.mInstance.addRequestQueue(strRequest);
                break;
            case R.id.HeaderSrBtn:
                Map<String, String> header = new HashMap<String, String>();
                header.put("Host", "query.sse.com.cn");
                header.put("Connection", "Keep-Alive");
                header.put("Accept", "*/*");
                header.put("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; MyIE9; BTRS123646; .NET CLR 2.0.50727; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
                header.put("Referer", "http://www.sse.com.cn/market/dealingdata/overview/margin/");
                header.put("Accept-Encoding", "gzip, deflate, sdch");
                header.put("Accept-Language", "zh-CN,zh;q=0.8");

                strHeaderRequest.setHeaders(header);
                BaseApplication.mInstance.addRequestQueue(strHeaderRequest);
                break;
            case R.id.JumpJOActBtn:
                Intent jsonObjIntent=new Intent(this,JsonObjectRequestActivity.class);
                startActivity(jsonObjIntent);
                break;
            case R.id.JumpImgReqActBtn:
                Intent imgReqIntent=new Intent(this,ImageRequestActivity.class);
                startActivity(imgReqIntent);
                break;
            case R.id.JumpXMLReqActBtn:
                Intent xmlReqIntent=new Intent(this,XMLRequestActivity.class);
                startActivity(xmlReqIntent);
                break;
            case R.id.JumpGsonReqActBtn:
                Intent gsonReqIntent=new Intent(this,GsonRequestActivity.class);
                startActivity(gsonReqIntent);
                break;
            case R.id.JumpCacheReqActBtn:
                Intent cacheReqIntent=new Intent(this,CacheRequestActivity.class);
                startActivity(cacheReqIntent);
                break;
            case R.id.JumpListReqActBtn:
                Intent arrReqIntent=new Intent(this,ListViewActivity.class);
                startActivity(arrReqIntent);
                break;
            case R.id.JumpMultipartReqActBtn:
                Intent multipartReqIntent=new Intent(this,MultipartActivity.class);
                startActivity(multipartReqIntent);
                break;
            case R.id.JumpStringReqActBtn:
                Intent strReqIntent=new Intent(this,StringRequestActivity.class);
                startActivity(strReqIntent);
                break;
            case R.id.JumpHttpsReqActBtn:
                Intent httpsReqIntent=new Intent(this,SSLRequestActivity.class);
                startActivity(httpsReqIntent);
                break;
            case R.id.statusJsonBtn:
                Intent statusJsonIntent=new Intent(this,StatusJsonActivity.class);
                startActivity(statusJsonIntent);
                break;
        }
    }

    StringRequest strRequest = new StringRequest(
            Request.Method.GET,
            "https://e.abchina.com/tj/emobile_payment/MainServlet",
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Logger.d(response);
                    reqView.setText(response.toString());
                }
            }, this) {
        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {

            Map<String, String> header = new HashMap<String, String>();
            header.put("Host", "query.sse.com.cn");
            header.put("Connection", "Keep-Alive");
            header.put("Accept", "*/*");
            header.put("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; MyIE9; BTRS123646; .NET CLR 2.0.50727; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
            header.put("Referer", "http://www.sse.com.cn/market/dealingdata/overview/margin/");
            header.put("Accept-Encoding", "gzip, deflate, sdch");
            header.put("Accept-Language", "zh-CN,zh;q=0.8");

            return header;
        }

    };

    StringRequest strHeaderRequest = new StringRequest(
            Request.Method.GET,
            "http://query.sse.com.cn/marketdata/tradedata/queryMargin.do?jsonCallBack=jsonpCallback98613&isPagination=true&tabType=&pageHelp.pageSize=100&_=1438876224537",
            new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Logger.d(response);
                    reqView.setText(response.toString());
                }
            }, this);


//    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
//            Request.Method.GET,
//            "http://query.sse.com.cn/marketdata/tradedata/queryMargin.do?jsonCallBack=jsonpCallback'+callnum+'&isPagination=true&tabType=&pageHelp.pageSize=100&_=1438876224537", null,
//            new Response.Listener<JSONObject>() {
//                @Override
//                public void onResponse(JSONObject response) {
//                    reqView.setText(response.toString());
//                }
//            }, this){
//        @Override
//        public Map<String, String> getHeaders() throws AuthFailureError {
//
//            Map<String,String> header= new HashMap<String,String>();
//            header.put("Host", "query.sse.com.cn");
//            header.put("Connection", "Keep-Alive");
//            header.put("Accept", "*/*");
//            header.put("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; MyIE9; BTRS123646; .NET CLR 2.0.50727; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
//            header.put("Referer", "http://www.sse.com.cn/market/dealingdata/overview/margin/");
//            header.put("Accept-Encoding", "gzip, deflate, sdch");
//            header.put("Accept-Language", "zh-CN,zh;q=0.8");
//
//            return super.getHeaders();
//        }
//    };



    @Override
    public void onErrorResponse(VolleyError error) {

    }

//    {
//        @Override
//        public Map<String, String> getHeaders() throws AuthFailureError {
//
//        Map<String,String> header= new HashMap<String,String>();
//        header.put("Host", "query.sse.com.cn");
//        header.put("Connection", "Keep-Alive");
//        header.put("Accept", "*/*");
//        header.put("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; MyIE9; BTRS123646; .NET CLR 2.0.50727; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
//        header.put("Referer", "http://www.sse.com.cn/market/dealingdata/overview/margin/");
//        header.put("Accept-Encoding", "gzip, deflate, sdch");
//        header.put("Accept-Language", "zh-CN,zh;q=0.8");
//
//        return header;
//    }
//    }


//    request.add_header("Host", "query.sse.com.cn")
//            request.add_header("Connection", "Keep-Alive")
//            request.add_header("Accept", "*/*")
//            request.add_header("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; MyIE9; BTRS123646; .NET CLR 2.0.50727; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)")
//            request.add_header('Referer', "http://www.sse.com.cn/market/dealingdata/overview/margin/")
//            request.add_header("Accept-Encoding", "gzip, deflate, sdch")
//            request.add_header("Accept-Language", "zh-CN,zh;q=0.8")

}
