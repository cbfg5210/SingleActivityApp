package com.hawk.saa.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.hawk.saa.R;
import com.hawk.saa.adapter.PoetryAdapter;
import com.hawk.saa.util.AppConstant;
import com.hawk.saa.util.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CommonFragment extends BaseFragment implements OnItemClickListener {
    private ListView list_poetry;
    private PoetryAdapter mPoetryAdapter;
    private String tabflag;
    private String tabTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tabflag = getArguments().getString("tabflag");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        mAppFragmentTool.setCurrentFragmentTag(MainFragment.class.getSimpleName());//这里不用
        View layoutView = inflater.inflate(R.layout.fragment_common, null);
//        if (tabflag.equals("TangShi")) {
//            tabTitle = "唐诗";
//        } else if (tabflag.equals("SongCi")) {
//            tabTitle = "宋词";
//        } else if (tabflag.equals("YuanQu")) {
//            tabTitle = "元曲";
//        }
//        setTitle(tabTitle);

        list_poetry = getView(layoutView,R.id.list_poetry);
        mPoetryAdapter = new PoetryAdapter(getActivity(), null);
        list_poetry.setAdapter(mPoetryAdapter);
        list_poetry.setOnItemClickListener(this);

        fetchData();

        return layoutView;
    }

    private void fetchData() {
        try {
            List<JSONObject> poemList = new ArrayList<JSONObject>();
            for (int i = 0; i < 10; i++) {
                if (tabflag.equals("TangShi")) {
                    poemList.add(new JSONObject(AppConstant.TANGSHI));
                } else if (tabflag.equals("SongCi")) {
                    poemList.add(new JSONObject(AppConstant.SONGCI));
                } else if (tabflag.equals("YuanQu")) {
                    poemList.add(new JSONObject(AppConstant.YUANQU));
                }
            }
            mPoetryAdapter.setList(poemList);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            ToastUtil.show("添加数据出错");
        }
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        // TODO Auto-generated method stub
        JSONObject jobj = (JSONObject) arg0.getItemAtPosition(arg2);
        Bundle bundle = new Bundle();
        bundle.putString("poetry", jobj.toString());

        startFragment(PoetryDetailFragment.class.getSimpleName(),bundle);
    }
}
