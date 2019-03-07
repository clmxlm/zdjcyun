package com.zdjc.zdjcyun.mvp.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseRecyclerAdapter;
import com.zdjc.zdjcyun.base.RecyclerViewHolder;
import com.zdjc.zdjcyun.charting.data.Entry;
import com.zdjc.zdjcyun.charting.data.LineDataSet;
import com.zdjc.zdjcyun.charting.interfaces.datasets.ILineDataSet;
import com.zdjc.zdjcyun.util.PreferenceUtils;

import java.text.DecimalFormat;

import butterknife.Bind;


public class MarkViewRecycViewAdapter extends BaseRecyclerAdapter<ILineDataSet> {

    private Context context;
    private DecimalFormat df = new DecimalFormat("0.000");
    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    private Entry entry;

    public MarkViewRecycViewAdapter(Context context) {
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

    @SuppressLint("SetTextI18n")
    @Override
    protected void setView(RecyclerViewHolder o, int position, ILineDataSet data) {
        ViewHolder holder= (ViewHolder) o;
        LineDataSet dataSet = (LineDataSet) data;
        if (dataSet.getValues().size()>((int) entry.getX())){
            float y = dataSet.getValues().get((int) entry.getX()).getY();
            String rateUnit = PreferenceUtils.getString(context,"rateUnit");
            String otherUnit = PreferenceUtils.getString(context,"otherUnit");
            if ("变化速率".equals(PreferenceUtils.getString(context,"AmountOfChangeName"))){
                holder.text.setText(dataSet.getLabel() + "：" + df.format(y)+ "("+rateUnit+")");
            }else {
                holder.text.setText(dataSet.getLabel() + "：" + df.format(y)+ "("+otherUnit+")");
            }
            holder.text.setTextColor(context.getResources().getColor(R.color.white));
        }
        holder.rlProjectManageIn.setVisibility(View.GONE);

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
