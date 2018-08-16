package com.zdjc.zdjcyun.mvp.ui.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.mvp.entity.AllProjectListEntity;
import com.zdjc.zdjcyun.mvp.ui.adapter.base.BaseRecyclerViewAdapter;
import com.zdjc.zdjcyun.util.DateUtil;
import com.zdjc.zdjcyun.util.SuperViewHolder;


public class ProjectListRecycViewAdapter extends BaseRecyclerViewAdapter<AllProjectListEntity.DataBean> {

    private Context context;
    private int selectedPosition;

    public ProjectListRecycViewAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.recycview_item;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        fillValue(position, holder);
    }

    public  void getPosition(int selectedPosition){
        this.selectedPosition = selectedPosition;
    }

    private void fillValue(int position, SuperViewHolder viewHolder) {
        TextView tv_project_name = viewHolder.getView(R.id.tv_project_name);
        TextView tv_project_address = viewHolder.getView(R.id.tv_project_address);
        TextView tv_project_principal = viewHolder.getView(R.id.tv_project_principal);
        TextView tv_project_type = viewHolder.getView(R.id.tv_project_type);
        TextView tv_project_type_warning = viewHolder.getView(R.id.tv_project_type_warning);
        TextView tv_progress_bar = viewHolder.getView(R.id.tv_progress_bar);
        ImageView iv_subscript = viewHolder.getView(R.id.iv_subscript);

        NumberProgressBar number_progress_bar = viewHolder.getView(R.id.number_progress_bar);
        long starTime = DateUtil.formatDuring(mDataList.get(position).getProjectBeginTime());
        long endTime = DateUtil.formatDuring(mDataList.get(position).getProjectEndTime());
        long currentTime = DateUtil.getCurrentTimeInLong();
        long aa = currentTime-starTime;
        long bb = endTime-starTime;
        int ss = (int) (DateUtil.div(aa,bb,2)*100);
        number_progress_bar.setProgress(ss);

        String projectName = context.getResources().getString(R.string.project_name)+" "+
                mDataList.get(position).getProjectName();

        String projectAddress = context.getResources().getString(R.string.project_address)+" "+
                mDataList.get(position).getProjectAddress();

        String projectPrincipal = context.getResources().getString(R.string.project_principal)+" "+
                mDataList.get(position).getProjectPrincipal();

        String projectType = context.getResources().getString(R.string.project_type)+" "+
                mDataList.get(position).getProjectTypeName();

        String projectWarning = context.getResources().getString(R.string.project_warning)+" "+
                mDataList.get(position).getAlCount();

        tv_project_name.setText(projectName);
        tv_project_address.setText(projectAddress);
        tv_project_principal.setText(projectPrincipal);
        tv_project_type.setText(projectType);
        tv_project_type_warning.setText(projectWarning);
        tv_progress_bar.setText(context.getResources().getString(R.string.project_time));

        if (position==selectedPosition){
            iv_subscript.setVisibility(View.VISIBLE);
        }else {
            iv_subscript.setVisibility(View.GONE);
        }
    }
}
