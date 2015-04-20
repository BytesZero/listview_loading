package com.zhengsonglan.listview_loading;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhengsonglan.listview_loading.adapter.MyAdapter;
import com.zhengsonglan.listview_loading.entity.UserEnity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements AbsListView.OnScrollListener {

    ListView lv_show;
    MyAdapter myAdapter;
    List<UserEnity> userEnities;

    String image_url="http://img1.imgtn.bdimg.com/it/u=2067963443,1654290884&fm=11&gp=0.jpg";

    String image_url1="http://p2.gexing.com/G1/M00/10/70/rBACFFHyEqaQ-sdJAAAUGXUr90M649_200x200_3.jpg";

    String image_url2="http://p4.gexing.com/G1/M00/D7/0A/rBABFFHjc0zznLDsAAAhB1o6dOM059_200x200_3.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initEvent();
        initData();
    }

    private void initView() {
        lv_show= (ListView) findViewById(R.id.main_lv_loading);

    }

    private void initEvent() {
        lv_show.setOnScrollListener(this);
    }

    private void initData() {

        userEnities=new ArrayList<UserEnity>();
        for (int i=0;i<100;i++){
            String url;
            int num=i%3;
            if (num==2){
                url=image_url;
            }else if(num==1){
                url=image_url1;
            }else{
                url=image_url2;
            }
            Log.e("MainActivity",url);
            UserEnity userEnity=new UserEnity(url,"name"+i);
            userEnities.add(userEnity);
        }
        myAdapter=new MyAdapter(MainActivity.this,userEnities);
        lv_show.setAdapter(myAdapter);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState){

            case AbsListView.OnScrollListener.SCROLL_STATE_IDLE://停止滚动
            {
                //设置为停止滚动
                myAdapter.setScrollState(false);
                //当前屏幕中listview的子项的个数
                int count = view.getChildCount();
                Log.e("MainActivity",count+"");

                for (int i = 0; i < count; i++) {
                    //获取到item的name
                    TextView tv_name = (TextView) view.getChildAt(i).findViewById(R.id.main_item_tv_name);
                    //获取到item的头像
                    ImageView iv_show= (ImageView) view.getChildAt(i).findViewById(R.id.main_item_iv_icon);

                    if (tv_name.getTag() != null) { //非null说明需要加载数据
                        tv_name.setText(tv_name.getTag().toString());//直接从Tag中取出我们存储的数据name并且赋值
                        tv_name.setTag(null);//设置为已加载过数据
                    }

                    if (!iv_show.getTag().equals("1")){//!="1"说明需要加载数据
                        String image_url=iv_show.getTag().toString();//直接从Tag中取出我们存储的数据image——url
                        ImageLoader.getInstance().displayImage(image_url, iv_show);//显示图片
                        iv_show.setTag("1");//设置为已加载过数据
                    }
                }
                break;
            }
            case AbsListView.OnScrollListener.SCROLL_STATE_FLING://滚动做出了抛的动作
            {
                //设置为正在滚动
                myAdapter.setScrollState(true);
                break;
            }

            case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL://正在滚动
            {
                //设置为正在滚动
                myAdapter.setScrollState(true);
                break;
            }
        }
    }


    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }


}
