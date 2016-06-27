package com.tj.myvolley.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.GsonRequest;
import com.tj.myvolley.R;
import com.tj.myvolley.base.BaseApplication;
import com.tj.myvolley.vo.Weather;
import com.tj.myvolley.vo.WeatherInfo;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Gson返回解析示例
 */
public class GsonRequestActivity extends AppCompatActivity implements View.OnClickListener{

    @Bind(R.id.gsonBtn) Button gsonBtn;
    @Bind(R.id.gsonView) TextView gsonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson_request);
        ButterKnife.bind(this);

        gsonBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.gsonBtn:
                BaseApplication.getInstance().getRequestQueue().getCache().invalidate("", true);

                BaseApplication.mInstance.addRequestQueue(request);
                break;
        }
    }

    GsonRequest request = new GsonRequest(Request.Method.GET,
        "http://www.weather.com.cn/adat/sk/101030100.html",Weather.class,
        new Response.Listener<Weather>() {
            @Override
            public void onResponse(Weather weather) {
                WeatherInfo info=weather.getWeatherinfo();
                gsonView.setText("城市:"+info.getCity()+"\n"
                        +"时间:"+info.getTime()
                        +"\n" +"温度："+info.getTemp());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });



}
