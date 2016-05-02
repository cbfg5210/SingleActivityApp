package com.hawk.saa.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hawk.saa.R;

import org.json.JSONException;
import org.json.JSONObject;

public class PoetryDetailFragment extends BaseFragment{
	private JSONObject poetry;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle arguments=getArguments();
		if(null!=arguments){
			try {
				poetry=new JSONObject(arguments.getString("poetry"));
				Log.i(TAG,"poetry="+poetry.toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(null,null,null);
		View layout=inflater.inflate(R.layout.fragment_poetrydetail,null);
		
		TextView text_title=(TextView) layout.findViewById(R.id.text_title);
		TextView text_author=(TextView) layout.findViewById(R.id.text_author);
		TextView text_content=(TextView) layout.findViewById(R.id.text_content);

		setTitle(poetry.optJSONObject("poem").optString("title"));
		setHomeBackEnable(true);

		 text_title.setText(poetry.optJSONObject("poem").optString("title"));
		 text_author.setText(poetry.optJSONObject("poet").optString("name"));
		 text_content.setText(poetry.optJSONObject("poem").optString("content"));
		 
		return layout;
	}

	@Override
	public void onPrepareOptionsMenu(Menu menu) {
		menu.findItem(R.id.action_right).setTitle("诗人生平").setVisible(true);
		super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int itemId=item.getItemId();
		if(itemId==R.id.action_right){
			Bundle arguments=new Bundle();
			arguments.putString("poet",poetry.optJSONObject("poet").toString());
			startFragment(PoetFragment.class.getSimpleName(),arguments);
		}else if(itemId==android.R.id.home){
			onBackPressed();
		}
		return true;
	}
}