package com.hawk.saa.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.RadioGroup;

import java.util.List;

/**
 * Created with IntelliJ IDEA. Author: wangjie email:tiantian.china.@gmail.com
 * Date: -- Time: 上午:
 * 
 * 翻看了Android官方Doc，和一些组件的源代码，发现，replace()这个方法只是在上一个Fragment不再需要时采用的简便方法。
 * 正确的切换方式是add()，切换时hide()，add()另一个Fragment；再次切换时，只需hide()当前，show()另一个。
 * 这样就能做到多个Fragment切换不重新实例化
 */
public class TabFragmentTool implements RadioGroup.OnCheckedChangeListener {
	private static final String TAG = "TabFragmentTool";
	private List<Fragment> fragmentsList; // 一个tab页面对应一个Fragment
	private RadioGroup mRadioGroup; // 用于切换tab
	private Fragment fragmentActivity; // Fragment所属的Activity
	private int fragmentContentId; // Activity中所要被替换的区域的id

	private int tabsCount;
	private int currentTab; // 当前Tab页面索引

	private OnRgsExtraCheckedChangedListener onRgsExtraCheckedChangedListener; // 用于让调用者在切换tab时候增加新的功能

	public void init(Fragment fragActivity, List<Fragment> fragments,
			int fragContentId, RadioGroup rgs) {
		fragmentsList = fragments;
		mRadioGroup = rgs;
		fragmentActivity = fragActivity;
		fragmentContentId = fragContentId;
		tabsCount = fragments.size();

		// 默认显示第一页
		FragmentTransaction ft = fragmentActivity.getChildFragmentManager()
				.beginTransaction();

		for (int i = 1; i < tabsCount; i++) {
			ft.add(fragmentContentId, fragments.get(i)).hide(fragments.get(i));
		}
		ft.add(fragmentContentId, fragments.get(0)).commit();

		rgs.setOnCheckedChangeListener(this);
	}

	@Override
	public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
		Log.i(TAG, "checkedId=" + checkedId + ";currenttab=" + currentTab);
		for (int i = 0; i < tabsCount; i++) {
			if (mRadioGroup.getChildAt(i).getId() == checkedId
					&& currentTab != i) {
				showTab(i); // 显示目标tab
				// 如果设置了切换tab额外功能功能接口
				if (null != onRgsExtraCheckedChangedListener) {
					onRgsExtraCheckedChangedListener.OnRgsExtraCheckedChanged(
							radioGroup, checkedId, i);
				}
				break;
			}
		}
	}

	private void showTab(int idx) {
		FragmentTransaction ft = fragmentActivity.getChildFragmentManager()
				.beginTransaction();
		ft.hide(fragmentsList.get(currentTab)).show(fragmentsList.get(idx))
				.commit();
		currentTab = idx; // 更新目标tab为当前tab
	}

	public void setOnRgsExtraCheckedChangedListener(
			OnRgsExtraCheckedChangedListener onRgsExtraCheckedChangedListener) {
		this.onRgsExtraCheckedChangedListener = onRgsExtraCheckedChangedListener;
	}

	public static class OnRgsExtraCheckedChangedListener {
		public void OnRgsExtraCheckedChanged(RadioGroup radioGroup,
				int checkedId, int index) {

		}
	}

}