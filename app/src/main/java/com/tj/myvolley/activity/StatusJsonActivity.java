package com.tj.myvolley.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.orhanobut.logger.Logger;
import com.tj.myvolley.R;
import com.tj.myvolley.base.BaseApplication;

import org.apache.http.util.EncodingUtils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 设置header的请求
 */
public class StatusJsonActivity extends AppCompatActivity {

    @Bind(R.id.test) TextView test;
    @Bind(R.id.aBtn) Button aBtn;
    @Bind(R.id.bBtn) Button bBtn;
    @Bind(R.id.clearBtn) Button clearBtn;

    String url="";
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_json);
        ButterKnife.bind(this);

        queue=BaseApplication.mInstance.getRequestQueue();

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                test.setText("");
            }
        });

        aBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                strHeaderRequest.setShouldCache(false);
                BaseApplication.mInstance.addRequestQueue(strHeaderRequesta);

            }
        });

        bBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "text/html");
                strHeaderRequest.setHeaders(headers);
                strHeaderRequest.setBodyContentType("Content-Type='text/html'; charset=utf-8");

                BaseApplication.mInstance.addRequestQueue(strHeaderRequest);
            }
        });
    }

    StringRequest strHeaderRequesta = new StringRequest(
            Request.Method.GET,"http://192.168.10.149:8080/serverjson/custom_order.html",
            new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    test.setText(response);


                    Logger.i(response);
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    test.setText(error.toString());
                }
            });

    StringRequest strHeaderRequest = new StringRequest(
            Request.Method.GET,"http://192.168.10.149:8080/serverjson/aaa.html",
            new Response.Listener<String>() {

                @Override
                public void onResponse(String response) {
                    test.setText(response);

                    Logger.i(response);
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    test.setText(error.toString());
                }
            });

}
