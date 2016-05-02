package com.hawk.saa.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hawk.saa.ui.MainActivity;
import com.hawk.saa.util.AppFragmentTool;

public abstract class BaseFragment extends Fragment {
    public String TAG = "BaseFragment";
    public AppFragmentTool mAppFragmentTool;
    private MainActivity mMainActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mMainActivity = (MainActivity) context;
        mAppFragmentTool = mMainActivity.getAppFragmentTool();

        TAG = this.getClass().getSimpleName();
        Log.i(TAG, "this fragment=" + TAG);
//        mAppFragmentTool.setCurrentFragmentTag(TAG);

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mAppFragmentTool.setCurrentFragmentTag(TAG);
        return null;
    }

    public void startFragment(String fragmentTag, Bundle bundle) {
        mAppFragmentTool.switchFragment(fragmentTag, bundle);
    }

    public void onBackPressed() {
        mAppFragmentTool.popBackStack();
    }

    public void setTitle(String title) {
        mMainActivity.setTitle(title);
    }

    public void setHomeBackEnable(boolean option) {
        mMainActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(option);
    }
}
