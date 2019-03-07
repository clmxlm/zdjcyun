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


    public ReportRecycleViewAdapter(Context context) {
        super(context);
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
        holder.tvPdfName.setText(data.getReportName());
        holder.tvCommitUser.setText(data.getCommit_user());
        holder.tvCommitTime.setText(data.getCommit_time());
        holder.tvTimeReport.setText(data.getTimeof_Report());
        holder.tvPersonInCharge.setText(data.getPersonIn_charge());
    }


    class ViewHolder extends RecyclerViewHolder {
        @Bind(R.id.tv_pdf_name)
        TextView tvPdfName;
        @Bind(R.id.tv_commit_user)
        TextView tvCommitUser;
        @Bind(R.id.tv_commit_time)
        TextView tvCommitTime;
        @Bind(R.id.tv_time_report)
        TextView tvTimeReport;
        @Bind(R.id.tv_personIn_charge)
        TextView tvPersonInCharge;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
