package com.bwei.fanying20190422.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwei.fanying20190422.R;
import com.bwei.fanying20190422.bean.BeanAll;
import com.bwei.fanying20190422.bean.ShowBean;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author：莹
 * @E-mail： 2016906034@qq.com
 * @Date：2019/4/22 10:25
 * @Description：描述信息
 */
public class RecyclerAdapter extends RecyclerView.Adapter{

    private Context context;
    private List<BeanAll> list = new ArrayList<>();


    public RecyclerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        int type = getItemViewType(i);
        RecyclerView.ViewHolder holder = null;
        switch (type){
            case 0:
                //热销新品
                View view = View.inflate(context,R.layout.adapter_rxxp,null);
                holder = new RxxpViewHolder(view);
                break;
            case 1:
                //魔丽时尚
                View view1 = View.inflate(context,R.layout.adapter_mlss,null);
                holder = new MlssViewHolder(view1);
                break;
            case 2:
                //品质生活
                View view2 = View.inflate(context,R.layout.adapter_pzsh,null);
                holder = new PzshViewHolder(view2);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        //热销新品
        if (viewHolder instanceof RxxpViewHolder && list.get(i) instanceof ShowBean.ResultBean.RxxpBean){
            RxxpViewHolder holder = (RxxpViewHolder) viewHolder;
            ShowBean.ResultBean.RxxpBean rxxpBean = (ShowBean.ResultBean.RxxpBean) list.get(i);
            ((RxxpViewHolder) viewHolder).title.setText(rxxpBean.getName());
            //创建适配器
            RxxpAdapter adapter = new RxxpAdapter(context,rxxpBean.getCommodityList());
            //创建布局管理器
            LinearLayoutManager manager = new LinearLayoutManager(context);
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            holder.recyclerView.setLayoutManager(manager);
            holder.recyclerView.setAdapter(adapter);

        }else if (viewHolder instanceof MlssViewHolder && list.get(i) instanceof ShowBean.ResultBean.MlssBean){//魔力时尚
            MlssViewHolder holder = (MlssViewHolder) viewHolder;
            ShowBean.ResultBean.MlssBean mlssBean = (ShowBean.ResultBean.MlssBean) list.get(i);
            ((MlssViewHolder) viewHolder).title.setText(mlssBean.getName());
            //创建适配器
            MlssAdapter adapter = new MlssAdapter(context,mlssBean.getCommodityList());
            //创建布局管理器
            LinearLayoutManager manager = new LinearLayoutManager(context);
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            holder.recyclerView.setLayoutManager(manager);
            holder.recyclerView.setAdapter(adapter);

        }else if (viewHolder instanceof PzshViewHolder && list.get(i) instanceof ShowBean.ResultBean.PzshBean){//品质生活
            PzshViewHolder holder = (PzshViewHolder) viewHolder;
            ShowBean.ResultBean.PzshBean pzshBean = (ShowBean.ResultBean.PzshBean) list.get(i);
            ((PzshViewHolder) viewHolder).title.setText(pzshBean.getName());
            //创建适配器
            PzshAdapter adapter = new PzshAdapter(context,pzshBean.getCommodityList());
            //创建布局管理器
            GridLayoutManager manager = new GridLayoutManager(context, 2);
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
            holder.recyclerView.setLayoutManager(manager);
            holder.recyclerView.setAdapter(adapter);

        }
    }
    //多条目
    @Override
    public int getItemViewType(int position) {
        if (list.get(position) instanceof ShowBean.ResultBean.RxxpBean){
            return 0;
        }else if (list.get(position) instanceof ShowBean.ResultBean.MlssBean){
            return 1;
        }else {
            return 2;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(List<BeanAll> allBeans) {
        this.list=allBeans;
        notifyDataSetChanged();
    }

    //热销新品
    public class RxxpViewHolder extends  RecyclerView.ViewHolder{

        RecyclerView recyclerView;
        TextView title;

        public RxxpViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            recyclerView = itemView.findViewById(R.id.rxxp_recycler);
        }
    }
    //品质生活
    public class PzshViewHolder extends  RecyclerView.ViewHolder{

        RecyclerView recyclerView;
        TextView title;

        public PzshViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title);
            recyclerView = itemView.findViewById(R.id.recycler);
        }
    }
    //魔丽时尚
    public class MlssViewHolder extends  RecyclerView.ViewHolder{

        RecyclerView recyclerView;
        TextView title;

        public MlssViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.home_list_01_text);
            recyclerView = itemView.findViewById(R.id.recycler_mlss);
        }
    }
}
