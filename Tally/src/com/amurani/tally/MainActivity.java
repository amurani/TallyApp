package com.amurani.tally;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		
		final Button newTally = (Button) findViewById(R.id.new_tally);
		final Button help = (Button) findViewById(R.id.help);
		final Button about = (Button) findViewById(R.id.about);
		
		newTally.setVisibility(View.INVISIBLE);
		help.setVisibility(View.INVISIBLE);
		about.setVisibility(View.INVISIBLE);
		
		final ButtonAnimation aboutAnimation = new ButtonAnimation(this, about, null);
		final ButtonAnimation helpAnimation = new ButtonAnimation(this, help, new OnAnimationEndListener() {
			@Override
			public void OnAnimationEnd() { aboutAnimation.animate(); }
		});
		ButtonAnimation newTallyAnimation = new ButtonAnimation(this, newTally, new OnAnimationEndListener() {
			@Override
			public void OnAnimationEnd() { helpAnimation.animate(); }
		});
		
		newTallyAnimation.animate();
		
		newTally.setOnClickListener(this);
		help.setOnClickListener(this);
		about.setOnClickListener(this);
	}
	
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.new_tally:
				goToActivity(TallyActivity.class);
				break;
			case R.id.help:
				toast("Help");
				// goToActivity(HelpActivity.class);
				break;
			case R.id.about:
				toast("About");
				// goToActivity(AboutActivity.class);
				break;
		}
	}
	
	public void goToActivity(Class<?> mClass) {
		Intent mIntent = new Intent(MainActivity.this, mClass);
		startActivity(mIntent);
	}
	
	public void toast(String text) {
		Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
	}
	
	protected class ButtonAnimation {
		
		final private int duration = 250;
		
		private Animation mAnimation;
		private Context mContext;
		private Button mButton;
		private OnAnimationEndListener mOnAnimationEndListener = null;
		
		public ButtonAnimation(Context mContext, Button mButton, OnAnimationEndListener mOnAnimationEndListener) {
			this.mContext = mContext;
			this.mButton = mButton;
			this.mOnAnimationEndListener = mOnAnimationEndListener;
		}
		
		public void animate() {
			mAnimation = AnimationUtils.loadAnimation(mContext, R.anim.zoom_in);
			mAnimation.setAnimationListener(new AnimationListener() {
				@Override
				public void onAnimationEnd(Animation arg0) {
					mButton.setVisibility(View.VISIBLE);
					
					if (mOnAnimationEndListener != null)
						mOnAnimationEndListener.OnAnimationEnd();
				}

				@Override
				public void onAnimationRepeat(Animation arg0) { }
				@Override
				public void onAnimationStart(Animation arg0) {	 }
			});
			mAnimation.setDuration(duration);
			mButton.startAnimation(mAnimation);
		}
	}
}