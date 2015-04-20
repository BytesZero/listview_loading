package com.zhengsonglan.listview_loading.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhengsonglan.listview_loading.R;
import com.zhengsonglan.listview_loading.entity.UserEnity;

import java.util.List;

/**
 * Created by Administrator on 2015/4/20.
 */
public class MyAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    List<UserEnity> lists;

    private  boolean scrollState=false;

    public void setScrollState(boolean scrollState) {
        this.scrollState = scrollState;
    }


    public MyAdapter(Context context, List<UserEnity> lists) {
        this.context=context;
        this.inflater=LayoutInflater.from(context);
        this.lists=lists;
    }

    @Override
    public int getCount() {
        return lists!=null?lists.size():0;
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView=inflater.inflate(R.layout.main_item,null,true);
            viewHolder=new ViewHolder();
            viewHolder.iv_icon= (ImageView) convertView.findViewById(R.id.main_item_iv_icon);
            viewHolder.tv_name= (TextView) convertView.findViewById(R.id.main_item_tv_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        UserEnity userEnity=lists.get(position);

        String img_url=userEnity.getIcon();
        if (!scrollState){
            viewHolder.tv_name.setText(userEnity.getName());
            viewHolder.tv_name.setTag(null);
            ImageLoader.getInstance().displayImage(img_url,viewHolder.iv_icon);
            viewHolder.iv_icon.setTag("1");
        }else{
            viewHolder.tv_name.setText("加载中");
            viewHolder.tv_name.setTag(userEnity.getName());
            viewHolder.iv_icon.setTag(img_url);
            viewHolder.iv_icon.setImageResource(R.mipmap.ic_launcher);

        }
        return convertView;

    }

    static class ViewHolder{
        TextView tv_name;
        ImageView iv_icon;
    }
}
