package com.tj.myvolley.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.tj.myvolley.R;
import com.tj.myvolley.base.BaseApplication;
import com.tj.myvolley.vo.PostJson;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StringRequestActivity extends AppCompatActivity implements View.OnClickListener{

    private Gson gson;
    @Bind(R.id.jbSendBtn) Button jbSendBtn;
    @Bind(R.id.reqView) TextView reqView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_string_request);
        ButterKnife.bind(this);
        gson=new Gson();

        jbSendBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.jbSendBtn:
                /*发送json请求*/
                PostJson pj=new PostJson();
                pj.setUserId("diyige");
                pj.setUserIp("diyige");
                pj.setCustomerId("diyige");
                pj.setBusinessCode("diyige");
                strRequest.setRequestBody(gson.toJson(pj));

                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                strRequest.setHeaders(headers);

                strRequest.setBodyContentType("application/json; charset=UTF-8");
                BaseApplication.mInstance.addRequestQueue(strRequest);
                break;
        }
    }

    StringRequest strRequest=new StringRequest(Request.Method.POST,
            "http://211.151.183.204:9000/Post",
            new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    Logger.d(response);
                    reqView.setText(response.toString());
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    reqView.setText(error.getMessage()+"--"+error.networkResponse.statusCode);
                }
            });
}
