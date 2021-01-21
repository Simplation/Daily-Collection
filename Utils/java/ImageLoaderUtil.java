package com.example.testutils.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.testutils.R;

public class ImageLoaderUtils {

    static ImageLoaderUtils instance;

    public ImageLoaderUtils() {
    }

    public static ImageLoaderUtils getInstance() {
        if (null == instance) {
            synchronized (ImageLoaderUtils.class) {
                if (null == instance) {
                    instance = new ImageLoaderUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 普通加载
     *
     * @param context   上下文对象
     * @param url       链接地址
     * @param imageView 图片控件
     */
    public void loadImage(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.ic_load_default)
                .error(R.drawable.ic_load_fail);
        Glide.with(context).load(url).apply(options).into(imageView);
    }

    /**
     * 加载圆形图片
     *
     * @param context   上下文对象
     * @param url       链接地址
     * @param imageView 图片控件
     */
    public void loadCircleImage(Context context, String url, ImageView imageView) {
        RequestOptions options = new RequestOptions()
                .circleCrop()
                .placeholder(R.drawable.ic_load_default)
                .error(R.drawable.ic_load_fail);
        Glide.with(context).load(url).apply(options).into(imageView);
    }

    /**
     * 加载圆角图片
     *
     * @param context   上下文对象
     * @param url       链接
     * @param imageView 图片控件
     */
    public void loadRoundImage(Context context, String url, ImageView imageView) {
        RequestOptions options = RequestOptions
                .bitmapTransform(new RoundedCorners(20))
                .placeholder(R.drawable.ic_load_default)
                .error(R.drawable.ic_load_fail);

        Glide.with(context).load(url).apply(options).into(imageView);
    }
}
