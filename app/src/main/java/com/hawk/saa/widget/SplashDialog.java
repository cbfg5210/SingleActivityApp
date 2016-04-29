package com.hawk.saa.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.hawk.saa.R;

public class SplashDialog extends Dialog{
	private static final String TAG = "SplashDialog";
	
	public SplashDialog(Context context) {
		
		super(context, R.style.FullScreenFragmentDialog);

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View layout = inflater.inflate(R.layout.dialog_splash, null);
		setContentView(layout);
		setCancelable(false);
		
		layout.postDelayed(new Runnable() {
			@Override
			public void run() {
				dismiss();
			}
		}, 3000);
	}
}
