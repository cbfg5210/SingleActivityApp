package com.hawk.saa.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;

import com.hawk.saa.R;
import com.hawk.saa.ui.fragment.BaseFragment;
import com.hawk.saa.util.AppFragmentTool;
import com.hawk.saa.widget.SplashDialog;

public class MainActivity extends AppCompatActivity{
    private static final String TAG = "MainActivity";
    private AppFragmentTool mAppFragmentTool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new SplashDialog(this).show();
        mAppFragmentTool = new AppFragmentTool(this);

        mAppFragmentTool.initMainFragment(MainFragment.class.getSimpleName());
    }

    public AppFragmentTool getAppFragmentTool() {
        return mAppFragmentTool;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onFBackPressed();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    private void onFBackPressed() {
        Log.i(TAG, "mAppFragmentTool.getCurrentFragmentTag()=" + mAppFragmentTool.getCurrentFragmentTag());
        BaseFragment currentFragment = mAppFragmentTool.getCurrentFragment();
        Log.i(TAG, "currentFragment=" + currentFragment);
        if (null != currentFragment) {
            mAppFragmentTool.getCurrentFragment().onBackPressed();
        }
    }
}
