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
 *
 */
public class MyAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    List<UserEnity> lists;

    //定义当前listview是否在滑动状态
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
        if (!scrollState){//如果当前不是滑动的状态，我们填充真数据
            //填充数据
            viewHolder.tv_name.setText(userEnity.getName());
            //设置Tag中数据为空表示数据已填充
            viewHolder.tv_name.setTag(null);
            //加载图片
            ImageLoader.getInstance().displayImage(img_url,viewHolder.iv_icon);
            //设置tag为1表示已加载过数据
            viewHolder.iv_icon.setTag("1");

        }else{//如果当前是滑动的状态，我们填充假数据
            viewHolder.tv_name.setText("加载中");
            //将数据name保存在Tag当中
            viewHolder.tv_name.setTag(userEnity.getName());
            //将数据image_url保存在Tag当中
            viewHolder.iv_icon.setTag(img_url);
            //设置默认显示图片（最好是本地资源的图片）
            viewHolder.iv_icon.setImageResource(R.mipmap.ic_launcher);

        }
        return convertView;

    }

    static class ViewHolder{
        TextView tv_name;
        ImageView iv_icon;
    }
}
