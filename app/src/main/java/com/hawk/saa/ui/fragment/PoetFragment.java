package com.hawk.saa.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hawk.saa.R;

import org.json.JSONException;
import org.json.JSONObject;

public class PoetFragment extends BaseFragment {
    private JSONObject poet;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        try {
            if (null != arguments) {
                poet = new JSONObject(arguments.getString("poet"));
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        setTitle("诗人生平");
        setHomeBackEnable(true);

        View layout = inflater.inflate(R.layout.fragment_poet, null);
        ImageView poet_avater_iv = (ImageView) layout.findViewById(R.id.poet_avater_iv);
        TextView poet_name_tv = (TextView) layout.findViewById(R.id.poet_name_tv);
        TextView poet_dynasty_tv = (TextView) layout.findViewById(R.id.poet_dynasty_tv);

        poet_avater_iv.setBackgroundResource(R.mipmap.ic_launcher);
        poet_name_tv.setText(poet.optString("name"));
        poet_dynasty_tv.setText(poet.optString("dynasty"));

        return layout;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.action_right).setTitle("回到首页").setVisible(true);
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId=item.getItemId();
        if(itemId==R.id.action_right){
            mAppFragmentTool.popBackStackInclusive();
        }else if(itemId==android.R.id.home){
            onBackPressed();
        }
        return true;
    }
}
