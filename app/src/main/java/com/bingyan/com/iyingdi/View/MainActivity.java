package com.bingyan.com.iyingdi.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bingyan.com.iyingdi.Model.TitleBeam;
import com.bingyan.com.iyingdi.Presenter.TitleNetWork;
import com.bingyan.com.iyingdi.R;

public class MainActivity extends AppCompatActivity {
   private TitleBeam titleBeam=new TitleBeam();
   private TitleNetWork mTitleNetWork;
   private RecyclerView mRecyclerView;
  private TitleAdapter mTitleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mTitleNetWork=new TitleNetWork(mTitleAdapter,this);
        mTitleNetWork.sendRequest();

    }

    private void initView(){
        mRecyclerView=(RecyclerView)findViewById(R.id.titlelist);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mTitleAdapter=new TitleAdapter();
        mRecyclerView.setAdapter(mTitleAdapter);
    }

   /* private void sendRequest(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client=new OkHttpClient();
                    Request request=new Request.Builder()
                            .url("http://www.iyingdi.cn/feed/list/seed?web=1&seed=2&system=web")
                            .build();
                    Response response=client.newCall(request).execute();
                    String responseData=response.body().string();
                    parseJSON(responseData);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void parseJSON(String data){
        Gson gson=new Gson();
       titleBeam=gson.fromJson(data, TitleBeam.class);
        Log.d("MainActivity", "parseJSON: "+titleBeam.getFeeds().get(0).getFeed().getTitle());
    }*/
}
