package com.bwei.fanying20190422.mvp.model;

import com.bwei.fanying20190422.net.OkHttpUtils;

/**
 * @Author：莹
 * @E-mail： 2016906034@qq.com
 * @Date：2019/4/22 9:45
 * @Description：描述信息
 */
public class ShowModel implements Model {
    @Override
    public void showData(final int type, String url, final CallBack callBack) {
        new OkHttpUtils().get(url).request(new OkHttpUtils.HttpListener() {
            @Override
            public void success(String data) {
                callBack.success(type,data);
            }

            @Override
            public void fail(String error) {
                callBack.fail(error);
            }
        });
    }
}
