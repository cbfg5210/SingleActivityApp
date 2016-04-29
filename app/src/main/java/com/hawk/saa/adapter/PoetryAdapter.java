package com.hawk.saa.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hawk.saa.R;

import org.json.JSONObject;

import java.util.List;

public class PoetryAdapter extends BaseListAdapter<JSONObject> {

	public PoetryAdapter(Context ctx, List<JSONObject> list) {
		super(ctx, list);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View bindView(int position, View layoutView, ViewGroup viewGroup) {
		// TODO Auto-generated method stub
		if(null==layoutView){
			layoutView=mInflater.inflate(R.layout.listitem_poetry,null);
		}
		TextView text_title=(TextView) layoutView.findViewById(R.id.text_title);
		TextView text_author=(TextView) layoutView.findViewById(R.id.text_author);
		
		JSONObject itemArray=mList.get(position);
		text_title.setText(itemArray.optJSONObject("poem").optString("title"));
		text_author.setText(itemArray.optJSONObject("poet").optString("name"));
		
		return layoutView;
	}

}
