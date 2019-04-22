package com.bwei.fanying20190422;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.bwei.fanying20190422.adapter.RecyclerAdapter;
import com.bwei.fanying20190422.bean.BeanAll;
import com.bwei.fanying20190422.bean.ShowBean;
import com.bwei.fanying20190422.mvp.model.ShowModel;
import com.bwei.fanying20190422.mvp.presenter.ShowPresenter;
import com.bwei.fanying20190422.mvp.view.ShowView;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ShowView {

    private XRecyclerView xrecyclerview;
    private ShowPresenter presenter;
    private String ShowUrl = "http://172.17.8.100/small/commodity/v1/commodityList";
    private RecyclerAdapter adapter;
    private LinearLayoutManager manager;
    private List<BeanAll> allBeans = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化组件
        xrecyclerview = findViewById(R.id.recycler);
        presenter = new ShowPresenter(new ShowModel(), this);
        //创建布局管理器
        manager = new LinearLayoutManager(MainActivity.this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        //设置布局管理器
        xrecyclerview.setLayoutManager(manager);
        //创建适配器
        adapter = new RecyclerAdapter(MainActivity.this);
        //设置适配器
        xrecyclerview.setAdapter(adapter);
        //设置图片
        presenter.showget(1,ShowUrl);
    }

    @Override
    public void success(int type, String data) {
        if (type == 1){
            ShowBean shopBean = new Gson().fromJson(data, ShowBean.class);
            ShowBean.ResultBean result = shopBean.getResult();
            // 热销新品的bean
            ShowBean.ResultBean.RxxpBean rxxp = result.getRxxp();
            //魔力时尚的bean
            ShowBean.ResultBean.MlssBean mlss = result.getMlss();
            //品质生活的bean
            ShowBean.ResultBean.PzshBean pzsh = result.getPzsh();

            //空的allBean
            allBeans.add(rxxp);
            allBeans.add(mlss);
            allBeans.add(pzsh);
            adapter.setData(allBeans);
        }
    }

    @Override
    public void fail(String error) {
        Toast.makeText(MainActivity.this,error,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }
}
