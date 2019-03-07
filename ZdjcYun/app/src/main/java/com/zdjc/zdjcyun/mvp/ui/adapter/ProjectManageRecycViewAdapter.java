package com.zdjc.zdjcyun.mvp.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseRecyclerAdapter;
import com.zdjc.zdjcyun.base.RecyclerViewHolder;
import com.zdjc.zdjcyun.mvp.entity.ProjectManageEntity;

import butterknife.Bind;


public class ProjectManageRecycViewAdapter extends BaseRecyclerAdapter<ProjectManageEntity.DataBean> {

    private Activity context;
    private ProjectManageInRecycViewAdapter projectManageInRecycViewAdapter;

    public ProjectManageRecycViewAdapter(Activity context) {
        super(context);
        this.context = context;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.text;
    }

    @Override
    protected RecyclerViewHolder getHolder(View v) {
        return new ViewHolder(v);
    }

    @Override
    protected void setView(RecyclerViewHolder o, int position, ProjectManageEntity.DataBean data) {
        ViewHolder holder= (ViewHolder) o;

        holder.text.setText(data.getProjectName());
        projectManageInRecycViewAdapter = new ProjectManageInRecycViewAdapter(context);
        holder.rlProjectManageIn.setLayoutManager(new GridLayoutManager(context,1));
        holder.rlProjectManageIn.setAdapter(projectManageInRecycViewAdapter);
        projectManageInRecycViewAdapter.setDataList(data.getList());
    }

    class ViewHolder extends RecyclerViewHolder {

        @Bind(R.id.text)
        TextView text;
        @Bind(R.id.rl_project_manage_in)
        RecyclerView rlProjectManageIn;


        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

}
