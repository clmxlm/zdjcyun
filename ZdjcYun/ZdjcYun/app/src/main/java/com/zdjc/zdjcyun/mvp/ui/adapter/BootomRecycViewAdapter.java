package com.zdjc.zdjcyun.mvp.ui.adapter;

import android.content.Context;
import android.widget.Button;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.mvp.ui.adapter.base.BaseRecyclerViewAdapter;
import com.zdjc.zdjcyun.util.SuperViewHolder;


public class BootomRecycViewAdapter extends BaseRecyclerViewAdapter<String> {

    private Context context;
    private int  selectedPosition;

    public BootomRecycViewAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public int getLayoutId() {
        return R.layout.tv;
    }

    @Override
    public void onBindItemHolder(SuperViewHolder holder, int position) {
        fillValue(position, holder);
    }

    public  void getPosition(int selectedPosition){
        this.selectedPosition = selectedPosition;
    }

    private void fillValue(int position, SuperViewHolder viewHolder) {
        Button btnLogin = viewHolder.getView(R.id.btn);
        btnLogin.setText(getDataList().get(position));
        if (selectedPosition==position){
        }else {
        }

    }
}
