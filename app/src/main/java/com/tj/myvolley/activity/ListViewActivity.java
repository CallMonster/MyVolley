package com.tj.myvolley.activity;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.tj.myvolley.R;
import com.tj.myvolley.adapter.ListView_Adapter;
import com.tj.myvolley.base.BaseApplication;
import com.tj.myvolley.vo.ListView_info;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 针对于NetworkImageView控件图片对于listview的示例
 */
public class ListViewActivity extends AppCompatActivity implements View.OnClickListener{
    private String TAG="ListViewActivity";
    private final int HANDLER_MESSAGE_ADAPTER=1;/*对adapter的数据进行填充*/
    private final int HANDLER_MESSAGE_ERR=9;/*错误*/

    @Bind(R.id.volleyListView) ListView volleyListView;

    private ListView_info info;
    private String errTip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        ButterKnife.bind(this);
        readNetData();
    }

    @Override
    public void onClick(View v) {

    }

    private void readNetData(){
        strRequest.setShouldCache(true);
        BaseApplication.mInstance.addRequestQueue(strRequest);
    }

    StringRequest strRequest = new StringRequest(Request.Method.GET,
            "http://211.151.183.204:9000/AppDownload/testdata.html",
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Logger.d(response);
                    Gson gson=new Gson();
                    info=gson.fromJson(response, ListView_info.class);
                    if(info.getCode()==200){
                        Message msg=mUIHandler.obtainMessage(HANDLER_MESSAGE_ADAPTER);
                        msg.sendToTarget();
                    }else{
                        errTip="错误";
                        Message msg=mUIHandler.obtainMessage(HANDLER_MESSAGE_ERR);
                        msg.sendToTarget();
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG,error.getMessage());
                    errTip="错误"+error.getMessage();
                    Message msg=mUIHandler.obtainMessage(HANDLER_MESSAGE_ERR);
                    msg.sendToTarget();
                }
            });

    private Handler mUIHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case HANDLER_MESSAGE_ADAPTER:
                    ListView_Adapter adapter=new ListView_Adapter(ListViewActivity.this,info);
                    volleyListView.setAdapter(adapter);
                    break;
                case HANDLER_MESSAGE_ERR:
                    Toast.makeText(ListViewActivity.this,errTip,Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

}
