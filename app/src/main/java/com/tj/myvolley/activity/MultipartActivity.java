package com.tj.myvolley.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.multipart.MultiPartStringRequest;
import com.orhanobut.logger.Logger;
import com.tj.myvolley.R;
import com.tj.myvolley.base.BaseApplication;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 对于文件类的相关提交
 */
public class MultipartActivity extends AppCompatActivity implements View.OnClickListener{

    @Bind(R.id.uploadBtn) Button uploadBtn;
    @Bind(R.id.jsonResult) TextView jsonResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multipart);
        ButterKnife.bind(this);

        uploadBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.uploadBtn:
                File uploadFile=new File("/sdcard/DCIM/background/10.jpg");
                multipartReq.addFileParams("path", uploadFile);
                multipartReq.addStringParams("tok", "c70bb3913e1649899fc3552a6bf855e2");
                BaseApplication.mInstance.addRequestQueue(multipartReq,1);
                break;
        }
    }

   MultiPartStringRequest multipartReq=new MultiPartStringRequest(
           Request.Method.PUT,
           "http://apps.wenaaa.com:52659/setting/upload",
           new Response.Listener<String>() {
               @Override
               public void onResponse(String response) {
                    Logger.d("正确:"+response);
                   jsonResult.setText(response);
               }
           },
           new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {
                   Logger.d("错误:"+error.getMessage());
                   jsonResult.setText("错误:" + error.getMessage());
               }
           }){};

}
/*
headers.put("Accept", "application/json");
        // headers.put("Accept", "multipart/form-data");
        // headers.put("Content-Type", "application/json");
        headers.put("Content-Type", "text/plain");
        //headers.put("Content-Length", "302");
        headers.put("Content-Type","application/x-www-form-urlencoded");*/

//"tok","c70bb3913e1649899fc3552a6bf855e2"

//              /storage/emulated/0/Pictures/Screenshots/Screenshot_2015-12-08-09-24-33.png
//        ("path","/storage/sdcard0/DCIM/Camera/10.jpg");
//        ("tok","c70bb3913e1649899fc3552a6bf855e2");
//        "http://apps.wenaaa.com:52659/setting/upload"