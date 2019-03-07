package com.zdjc.zdjcyun.mvp.ui.adapter;

import android.content.Context;
import android.widget.Button;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.mvp.ui.adapter.base.BaseRecyclerViewAdapter;
import com.zdjc.zdjcyun.util.SuperViewHolder;


public class TopRecycViewAdapter extends BaseRecyclerViewAdapter<String> {

    private Context context;
    private int selectedPosition;
    private String projectName = "";

    public TopRecycViewAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.btn;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        fillValue(position, holder);
    }

    public  void getPosition(int selectedPosition){
        this.selectedPosition = selectedPosition;
    }

    public  void getProjectName(String projectName){
        this.projectName = projectName;
        notifyDataSetChanged();
    }
    private void fillValue(int position, SuperViewHolder viewHolder) {
        Button btnLogin = viewHolder.getView(R.id.btn);
        if ("".equals(projectName)){
            btnLogin.setText(getDataList().get(position));
        }else {
            if (selectedPosition==position){
                String name = getDataList().get(position)+"("+projectName+")";
                btnLogin.setText(name);
            }else {
                btnLogin.setText(getDataList().get(position));
            }
        }
        if (selectedPosition==position){
            btnLogin.setTextColor(context.getResources().getColor(R.color.timepicker_toolbar_bg));
        }else {
            btnLogin.setTextColor(context.getResources().getColor(R.color.white));
        }

    }
}
