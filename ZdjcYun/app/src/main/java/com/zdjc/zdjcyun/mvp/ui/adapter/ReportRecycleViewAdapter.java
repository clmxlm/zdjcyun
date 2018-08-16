package com.zdjc.zdjcyun.mvp.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseRecyclerAdapter;
import com.zdjc.zdjcyun.base.RecyclerViewHolder;
import com.zdjc.zdjcyun.mvp.entity.PageReportEntity;

import butterknife.Bind;


public class ReportRecycleViewAdapter extends BaseRecyclerAdapter<PageReportEntity.DataBean.DataListBean> {

    private Context context;

    public ReportRecycleViewAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.recycview_report_item;
    }

    @Override
    protected RecyclerViewHolder getHolder(View v) {
        return new ViewHolder(v);
    }

    @Override
    protected void setView(RecyclerViewHolder o, int position, PageReportEntity.DataBean.DataListBean data) {
        ViewHolder holder= (ViewHolder) o;
        holder.tv_pdf_name.setText(data.getReportName());
        holder.tv_commit_user.setText(data.getCommit_user());
        holder.tv_commit_time.setText(data.getCommit_time());
        holder.tv_time_report.setText(data.getTimeof_Report());
        holder.tv_personIn_charge.setText(data.getPersonIn_charge());
    }


    class ViewHolder extends RecyclerViewHolder {
        @Bind(R.id.tv_pdf_name)
        TextView tv_pdf_name;
        @Bind(R.id.tv_commit_user)
        TextView tv_commit_user;
        @Bind(R.id.tv_commit_time)
        TextView tv_commit_time;
        @Bind(R.id.tv_time_report)
        TextView tv_time_report;
        @Bind(R.id.tv_personIn_charge)
        TextView tv_personIn_charge;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
