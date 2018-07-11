package com.zdjc.zdjcyun.mvp.ui.fragment;

import android.net.Uri;
import android.os.Bundle;

import com.zdjc.zdjcyun.R;
import com.zdjc.zdjcyun.base.BaseFragment;
import com.zdjc.zdjcyun.databinding.FragmentWarningMessageBinding;
import com.zdjc.zdjcyun.mvp.viewmodel.impl.WarningModel;

public class WarningMessageFragment extends BaseFragment<FragmentWarningMessageBinding,WarningModel> {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public WarningMessageFragment() {
        // Required empty public constructor
    }

    public static WarningMessageFragment newInstance(String param2) {
        WarningMessageFragment fragment = new WarningMessageFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_warning_message;
    }

    @Override
    public void initView() {

    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
