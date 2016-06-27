package com.tj.myvolley.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tj.myvolley.R;
import com.tj.myvolley.base.BaseApplication;
import com.tj.myvolley.vo.PostJson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2015/12/7.
 * @desc 修改header、setBodyContentType，通过gson进行json数据的网络请求
 */
public class JsonObjectRequestActivity extends Activity implements View.OnClickListener,
        Response.ErrorListener {
    private String TAG = "JsonObjectRequestActivity";
    private static final String BASE_URL="http://211.151.183.204:9000/Post";
    private Button JoBtn;
    private TextView reqView;
    @Bind(R.id.postJson) Button postJsonBtn;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsonobj_request);
        ButterKnife.bind(this);

        reqView = (TextView) findViewById(R.id.reqView);
        JoBtn = (Button) findViewById(R.id.JoBtn);
        JoBtn.setOnClickListener(this);

        gson = new Gson();
        postJsonBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.JoBtn:
                /*普通请求 返回json*/
                BaseApplication.mInstance.addRequestQueue(jbRequest);
                break;
            case R.id.postJson:
                /*发送json请求*/
                PostJson pj=new PostJson();
                pj.setUserId("diyige");
                pj.setUserIp("diyige");
                pj.setCustomerId("diyige");
                pj.setBusinessCode("diyige");
                postJsonReq.setRequestBody(gson.toJson(pj));


                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                postJsonReq.setHeaders(headers);

                postJsonReq.setBodyContentType("application/json; charset=UTF-8");
                BaseApplication.mInstance.addRequestQueue(postJsonReq);
                break;
        }
    }

    JsonObjectRequest jbRequest = new JsonObjectRequest(Request.Method.GET,
            "http://www.weather.com.cn/data/sk/101030100.html",null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Log.i(TAG, response.toString());
                    reqView.setText(response.toString());
                }
            },this);


    JsonObjectRequest postJsonReq = new JsonObjectRequest(
            Request.Method.POST,BASE_URL,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    reqView.setText(response.toString());
                }
            }, this);

    @Override
    public void onErrorResponse(VolleyError error) {
        reqView.setText(error.getMessage()+"--"+error.getLocalizedMessage()+error.toString());
    }
}
