package com.hawk.saa.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.hawk.saa.R;
import com.hawk.saa.ui.MainActivity;
import com.hawk.saa.util.TabFragmentTool;
import com.hawk.saa.util.ToastTool;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends BaseFragment {
    private View layout;
    private RadioGroup rg_tabs;
    private List<Fragment> fragmentList;
    private String[] tabsName = {"唐诗", "宋词", "元曲"};
    private int tabFlag;
    private long firstTime;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(null,null,null);
        setHomeBackEnable(false);
        if (null == layout) {
            layout = inflater.inflate(R.layout.fragment_main, null);
            rg_tabs = (RadioGroup) layout.findViewById(R.id.rg_tabs);
        }
        return layout;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);
        // 这里加一个判断，因为fragment切换时候会重新onCreateView、onActivityCreated...，
        // 但是数据还存在，不需要初始化数据
        if (null == fragmentList) {
            initFragmentList();
            TabFragmentTool tabFragManager = new TabFragmentTool();
            tabFragManager.init(this, fragmentList, R.id.fragment_container, rg_tabs);
            //设置页面切换处理
            tabFragManager.setOnRgsExtraCheckedChangedListener(new TabFragmentTool.OnRgsExtraCheckedChangedListener() {
                public void OnRgsExtraCheckedChanged(RadioGroup radioGroup,
                                                     int checkedId, int index) {
                    tabFlag = index;
                    setTitle(tabsName[tabFlag]);
                }
            });
        }
    }

    /**
     * 初始化FragmentList
     */
    private void initFragmentList() {
        fragmentList = new ArrayList<>();

        CommonFragment tempFragment;
        Bundle tempBundle;

        // 添加tab-唐诗到fragmentList
        tempFragment = new CommonFragment();
        tempBundle = new Bundle();
        tempBundle.putString("tabflag", "TangShi");
        tempFragment.setArguments(tempBundle);
        fragmentList.add(tempFragment);

        // 添加tab-宋词到fragmentList
        tempFragment = new CommonFragment();
        tempBundle = new Bundle();
        tempBundle.putString("tabflag", "SongCi");
        tempFragment.setArguments(tempBundle);
        fragmentList.add(tempFragment);

        // 添加tab-元曲到fragmentList
        tempFragment = new CommonFragment();
        tempBundle = new Bundle();
        tempBundle.putString("tabflag", "YuanQu");
        tempFragment.setArguments(tempBundle);
        fragmentList.add(tempFragment);
    }

    @Override
    public void onBackPressed() {
        long secondTime = System.currentTimeMillis();
        Log.i(TAG, "firstTime=" + firstTime + ";secondTime=" + secondTime);

        if ((secondTime - firstTime) > 1000) {
            ToastTool.show("再按一次退出");
            firstTime = secondTime;
        } else {
            MainActivity mMainActivity= (MainActivity) getContext();
            mMainActivity.finish();
        }
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_right).setTitle("沉璧浮光").setVisible(true);
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.action_right){
            startFragment(WebViewFragment.class.getSimpleName(),null);
        }
        return super.onOptionsItemSelected(item);
    }
}
