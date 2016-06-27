package com.tj.myvolley.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.orhanobut.logger.Logger;
import com.tj.myvolley.R;
import com.tj.myvolley.base.BaseApplication;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 进行SSL的https的请求
 */
public class SSLRequestActivity extends AppCompatActivity implements View.OnClickListener{

    @Bind(R.id.httpsReqActBtn) Button httpsReqActBtn;
    @Bind(R.id.responseView) TextView responseView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sslrequest);
        ButterKnife.bind(this);


        httpsReqActBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.httpsReqActBtn:
                BaseApplication.mInstance.addRequestQueue(strHeaderRequest,true);
                break;
        }
    }

    StringRequest strHeaderRequest = new StringRequest(
            Request.Method.GET,
            "https://www1.test.95599.cn/tjbranch/mobile/MainServlet",
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Logger.d(response);
                    responseView.setText("正确:"+response.toString());
                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            responseView.setText("错误:"+error.getMessage());
        }
    });

}
