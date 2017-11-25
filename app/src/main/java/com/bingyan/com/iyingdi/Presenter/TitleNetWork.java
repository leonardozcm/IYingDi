package com.bingyan.com.iyingdi.Presenter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.bingyan.com.iyingdi.Model.ItemBeam;
import com.bingyan.com.iyingdi.Model.TitleBeam;
import com.bingyan.com.iyingdi.View.TitleAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 59771 on 2017/11/24.
 * 用于请求主页面的信息
 */

public class TitleNetWork {
    private List<ItemBeam> itemBeams=new ArrayList<>();
    private String titleurl="http://www.iyingdi.cn/feed/list/seed?web=1&seed=2&system=web";
    private List<TitleBeam.FeedsBean> feedsBeans=new ArrayList<>();
    private TitleAdapter mTitleAdapter;
    private Activity mActivity;

    public TitleNetWork(TitleAdapter mTitleAdapter,Activity activity) {
        this.mTitleAdapter = mTitleAdapter;
        mActivity=activity;
    }

    public void sendRequest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client=new OkHttpClient();
                    Request request=new Request.Builder()
                            .url(titleurl)
                            .build();
                    Response response=client.newCall(request).execute();
                    String responseData=response.body().string();
                    parseJSON(responseData,client);
                    switchTreadToShow();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJSON(String data,OkHttpClient client) {
        Gson gson=new Gson();
        itemBeams=BuildupItemBeam(gson.fromJson(data, TitleBeam.class),client);
    }

    private List<ItemBeam> BuildupItemBeam(TitleBeam titleBeam,OkHttpClient client){
        feedsBeans=titleBeam.getFeeds();
        List<ItemBeam> list=new ArrayList<>();
        for (TitleBeam.FeedsBean item:feedsBeans
             ) {
            ItemBeam itemBeam=new ItemBeam();
            itemBeam.setAuthor(item.getFeed().getAuthor());
            itemBeam.setIntro(item.getFeed().getDescription());
            itemBeam.setTitleText(item.getFeed().getTitle());
            itemBeam.setCover_bitmap(getBitmap(item.getFeed().getIcon(),client));
            list.add(itemBeam);
        }
        return list;
    }

    private void switchTreadToShow(){
       mActivity.runOnUiThread(new Runnable() {
           @Override
           public void run() {
               mTitleAdapter.setItemBeamList(itemBeams);
           }
       });
    }
    private Bitmap getBitmap(String url,OkHttpClient client){
        Request request=new Request.Builder()
                .url(url)
                .build();
        byte[] bitmap_in_byte=null;
        try {
            Response response=client.newCall(request).execute();
           bitmap_in_byte=response.body().bytes();
        }catch (Exception e){
            e.printStackTrace();
        }
        return BitmapFactory.decodeByteArray(bitmap_in_byte,0,bitmap_in_byte.length);

    }
}
