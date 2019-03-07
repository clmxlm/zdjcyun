package com.zdjc.zdjcyun.mvp.ui.adapter;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.utils.ToastUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseRecyclerAdapter;
import com.zdjc.zdjcyun.base.RecyclerViewHolder;
import com.zdjc.zdjcyun.mvp.entity.MemberMsgEntity;

import butterknife.Bind;


public class MemberMsgRecycViewAdapter extends BaseRecyclerAdapter<MemberMsgEntity.DataBean.MembersBean> {

    private Activity context;

    public MemberMsgRecycViewAdapter(Activity context) {
        super(context);
        this.context = context;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_member_message_item;
    }

    @Override
    protected RecyclerViewHolder getHolder(View v) {
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void setView(RecyclerViewHolder o, int position, MemberMsgEntity.DataBean.MembersBean data) {
        ViewHolder holder = (ViewHolder) o;
        holder.mTvName.setText(data.getMemberName());
        holder.mTvPost.setText("/" + data.getSectorRole());
        holder.mTvEmail.setText(data.getMemberEmail());
        holder.mTvPhone.setText(data.getMemberPhone());
        holder.mTvCompany.setText("所在单位: " + data.getMemberCompany());
        holder.mBtnPhone.setOnClickListener(v -> {
            RxPermissions rxPermission = new RxPermissions(context);
            rxPermission
                    .requestEach(Manifest.permission.CALL_PHONE)
                    .subscribe(permission -> {
                        if (permission.granted) {
                            // 用户已经同意该权限
                            Intent intent = new Intent(Intent.ACTION_CALL);
                            Uri dataU = Uri.parse("tel:" + data.getMemberPhone());
                            intent.setData(dataU);
                            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                // TODO: Consider calling
                                //    ActivityCompat#requestPermissions
                                // here to request the missing permissions, and then overriding
                                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                //                                          int[] grantResults)
                                // to handle the case where the user grants the permission. See the documentation
                                // for ActivityCompat#requestPermissions for more details.
                                return;
                            }
                            context.startActivity(intent);
                            Log.d("cc", permission.name + " is granted.");
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                            Log.d("cc", permission.name + " is denied. More info should be provided.");
                        } else {
                            // 用户拒绝了该权限，并且选中『不再询问』
                            Log.d("cc", permission.name + " is denied.");
                        }
                    });
        });
        holder.mTvPhone.setOnClickListener(v -> {
            RxPermissions rxPermission = new RxPermissions(context);
            rxPermission
                    .requestEach(Manifest.permission.CALL_PHONE)
                    .subscribe(permission -> {
                        if (permission.granted) {
                            // 用户已经同意该权限
                            Intent intent = new Intent(Intent.ACTION_CALL);
                            Uri dataU = Uri.parse("tel:" + data.getMemberPhone());
                            intent.setData(dataU);
                            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                // TODO: Consider calling
                                //    ActivityCompat#requestPermissions
                                // here to request the missing permissions, and then overriding
                                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                //                                          int[] grantResults)
                                // to handle the case where the user grants the permission. See the documentation
                                // for ActivityCompat#requestPermissions for more details.
                                return;
                            }
                            context.startActivity(intent);
                            Log.d("cc", permission.name + " is granted.");
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                            Log.d("cc", permission.name + " is denied. More info should be provided.");
                        } else {
                            // 用户拒绝了该权限，并且选中『不再询问』
                            Log.d("cc", permission.name + " is denied.");
                        }
                    });
        });

        holder.mBtnEmail.setOnClickListener(v -> {
            ClipboardManager clip = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            //clip.getText(); // 粘贴
            // 复制
            clip.setText(data.getMemberEmail());
            ToastUtils.showShortToast("已复制: "+data.getMemberEmail());
        });
    }

    class ViewHolder extends RecyclerViewHolder {

        @Bind(R.id.tv_name)
        TextView mTvName;
        @Bind(R.id.tv_post)
        TextView mTvPost;
        @Bind(R.id.btn_email)
        Button mBtnEmail;
        @Bind(R.id.btn_phone)
        Button mBtnPhone;
        @Bind(R.id.tv_email)
        TextView mTvEmail;
        @Bind(R.id.tv_phone)
        TextView mTvPhone;
        @Bind(R.id.tv_company)
        TextView mTvCompany;


        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

}
