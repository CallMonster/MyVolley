package com.tj.myvolley.activity;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.XMLRequest;
import com.tj.myvolley.R;
import com.tj.myvolley.base.BaseApplication;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * XML请求与解析
 */
public class XMLRequestActivity extends AppCompatActivity implements View.OnClickListener {
    private String TAG="XMLRequestActivity";

    @Bind(R.id.getXMLBtn) Button getXMLBtn;
    @Bind(R.id.dataView) TextView dataView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xmlrequest);
        ButterKnife.bind(this);

        getXMLBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.getXMLBtn:
                xmlRequest.setRetryPolicy(new DefaultRetryPolicy(5000,3,1f));
                BaseApplication.mInstance.addRequestQueue(xmlRequest);
                break;
        }
    }

    XMLRequest xmlRequest = new XMLRequest(Request.Method.GET,
            "http://flash.weather.com.cn/wmaps/xml/china.xml",
            new Response.Listener<XmlPullParser>() {
                @Override
                public void onResponse(XmlPullParser response) {
                    try {
                        Log.i(TAG,"完成");
                        StringBuffer buffer=new StringBuffer("");
                        int eventType = response.getEventType();
                        while (eventType != XmlPullParser.END_DOCUMENT){
                            switch (eventType){
                                case XmlPullParser.START_TAG:
                                    String nodeName = response.getName();
                                    if ("city".equals(nodeName)) {
                                        String pName = response.getAttributeValue(0);
                                        String pYname=response.getAttributeValue(1);
                                        Log.i(TAG, "value:  "+pName+"-"+pYname);
                                        buffer.append(pName + "-"+pYname);
                                    }
                                    break;
                            }
                            eventType = response.next();
                        }

                        dataView.setText(buffer.toString());
                    } catch (XmlPullParserException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });

}
