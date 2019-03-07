package com.zdjc.zdjcyun.mvp.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.app.Constant;
import com.zdjc.zdjcyun.base.BaseRecyclerAdapter;
import com.zdjc.zdjcyun.base.RecyclerViewHolder;
import com.zdjc.zdjcyun.mvp.entity.ImageListEntity;
import com.zdjc.zdjcyun.util.ImageLoaderUtils;
import com.zdjc.zdjcyun.util.ScreenUtil;
import com.zdjc.zdjcyun.util.ZoomUtils;

import butterknife.Bind;


public class ProjectImagesRecycViewAdapter extends BaseRecyclerAdapter<ImageListEntity.DataBean.ImagesBean> {

    private Context context;
    private int imageWidth;

    public ProjectImagesRecycViewAdapter(Context context) {
        super(context);
        this.context = context;
        imageWidth = (ScreenUtil.getScreenWidth(context) - ZoomUtils.dip2px(5)) / 2;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_project_images_item;
    }

    @Override
    protected RecyclerViewHolder getHolder(View v) {
        return new ViewHolder(v);
    }

    @Override
    protected void setView(RecyclerViewHolder o, int position, ImageListEntity.DataBean.ImagesBean data) {
        ViewHolder holder= (ViewHolder) o;
        holder.miVItem.setTag(null);
        String url = Constant.IMAGE_URL+data.getImageUrl();

        holder.rlImage.setLayoutParams(new RelativeLayout.LayoutParams(imageWidth, imageWidth));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(imageWidth, imageWidth);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        holder.miVItem.setLayoutParams(layoutParams);

        ImageLoaderUtils.imageDisPlayNotLoadingByApp(url,holder.miVItem);

    }

    class ViewHolder extends RecyclerViewHolder {

        @Bind(R.id.image_item)
        ImageView miVItem;
        @Bind(R.id.rl_image)
        RelativeLayout rlImage;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

}
