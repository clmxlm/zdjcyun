package com.zdjc.zdjcyun.mvp.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.app.Constant;
import com.zdjc.zdjcyun.base.BaseRecyclerAdapter;
import com.zdjc.zdjcyun.base.RecyclerViewHolder;
import com.zdjc.zdjcyun.mvp.entity.ProjectListEntity;
import com.zdjc.zdjcyun.mvp.ui.activities.PictureActivity;
import com.zdjc.zdjcyun.mvp.ui.activities.ReportActivity;
import com.zdjc.zdjcyun.mvp.ui.activities.VideoActivity;
import com.zdjc.zdjcyun.util.ImageLoaderUtils;
import com.zdjc.zdjcyun.util.PreferenceUtils;

import butterknife.Bind;


public class ProjectRecycViewAdapter extends BaseRecyclerAdapter<ProjectListEntity.DataBean.RowsBean> {

    private Context context;
    private boolean or = false;

    public ProjectRecycViewAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.news_item;
    }

    @Override
    protected RecyclerViewHolder getHolder(View v) {
        return new ViewHolder(v);
    }

    @Override
    protected void setView(RecyclerViewHolder o, int position, ProjectListEntity.DataBean.RowsBean data) {
        ViewHolder holder= (ViewHolder) o;
        //设置TextView背景为半透明
        holder.news_title.setBackgroundColor(Color.argb(20, 0, 0, 0));

        ImageLoaderUtils.imageDisPlayByAppNotAnimate(Constant.IMAGE_URL+data.getProjectImageUrl(),holder.news_photo);
        holder.news_title.setText(data.getProjectAddress());
        holder.news_desc.setText(data.getProjectName());

        holder.btn_video.setOnClickListener(v -> {

        });
        holder.btn_report.setOnClickListener(v -> {
            Intent intent = new Intent(context,ReportActivity.class);
            PreferenceUtils.putInt(context,"projectId", data.getProjectId());
            context.startActivity(intent);
        });

        holder.btn_video.setOnClickListener(v -> {
            if (or){
                Intent intent = new Intent(context,PictureActivity.class);
                context.startActivity(intent);
                or = false;
            }else {
                Intent intent = new Intent(context,VideoActivity.class);
                context.startActivity(intent);
                or = true;
            }
        });
    }
    class ViewHolder extends RecyclerViewHolder {
        @Bind(R.id.btn_video)
        Button btn_video;
        @Bind(R.id.btn_report)
        Button btn_report;
        @Bind(R.id.news_photo)
        ImageView news_photo;
        @Bind(R.id.news_title)
        TextView news_title;
        @Bind(R.id.news_desc)
        TextView news_desc;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }


}
