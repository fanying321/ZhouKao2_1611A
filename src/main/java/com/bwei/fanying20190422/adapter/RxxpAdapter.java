package com.bwei.fanying20190422.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwei.fanying20190422.R;
import com.bwei.fanying20190422.bean.ShowBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：莹
 * @E-mail： 2016906034@qq.com
 * @Date：2019/4/22 10:30
 * @Description：描述信息
 */
public class RxxpAdapter extends RecyclerView.Adapter<RxxpAdapter.ViewHolder> {
    private Context context;
    private List<ShowBean.ResultBean.RxxpBean.CommodityListBeanXX> commodityList;


    public RxxpAdapter(Context context, List<ShowBean.ResultBean.RxxpBean.CommodityListBeanXX> commodityList) {
        this.context = context;
        this.commodityList = commodityList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context,R.layout.rxxp_item,null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {


        viewHolder.title.setText(commodityList.get(i).getCommodityName());

        viewHolder.price.setText("￥" + commodityList.get(i).getPrice()+"");

        Glide.with(context).load(commodityList.get(i).getMasterPic()).into(viewHolder.image);
    }

    @Override
    public int getItemCount() {
        return commodityList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView price;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.rxxp_title);
            price = (TextView) itemView.findViewById(R.id.rxxp_price);
            image = (ImageView) itemView.findViewById(R.id.rxxp_image);
        }
    }
}
