package com.tj.myvolley.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.tj.myvolley.R;
import com.tj.myvolley.base.BaseApplication;
import com.tj.myvolley.vo.ListView_info;
import com.tj.myvolley.vo.ListView_info_userinfo;

import java.util.ArrayList;

/**
 * Created by Lee on 2015/12/14.
 */
public class ListView_Adapter extends BaseAdapter {

    private Context context;
    private ListView_info info;

    private ImageLoader imageLoader;
    public ListView_Adapter(Context context,ListView_info info){
        this.context=context;
        this.info=info;

        imageLoader= BaseApplication.getInstance().getImageLoader();
    }

    @Override
    public int getCount() {
        return info==null?0:info.getUserinfo().size();
    }

    @Override
    public Object getItem(int position) {
        return info.getUserinfo().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.item_listview,null);
            holder.name= (TextView) convertView.findViewById(R.id.name);
            holder.age= (TextView) convertView.findViewById(R.id.age);
            holder.sex= (TextView) convertView.findViewById(R.id.sex);
            holder.worker= (TextView) convertView.findViewById(R.id.worker);
            holder.img= (NetworkImageView) convertView.findViewById(R.id.img);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }
        ListView_info_userinfo userinfo=info.getUserinfo().get(position);

        holder.name.setText(userinfo.getName());
        holder.age.setText(userinfo.getAge()+"岁");
        holder.sex.setText(userinfo.getSex()==1?"男":"女");
        holder.worker.setText(userinfo.getName());

        holder.img.setDefaultImageResId(R.drawable.github_loading_inner);
        holder.img.setErrorImageResId(R.drawable.err_img);
        holder.img.setImageUrl(userinfo.getImg().replace(" ",""), imageLoader);
        holder.img.setTag(userinfo.getImg());

        return convertView;
    }

    private class ViewHolder{
        private TextView name;
        private TextView age;
        private TextView sex;
        private TextView worker;
        private NetworkImageView img;
    }

}
