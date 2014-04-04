package com.amurani.tally.anims;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

public class FadeIn {
	
	private Animation mAnimation;
	
	public FadeIn(View mView) {
		mAnimation = new AlphaAnimation(0.0f, 1.0f);
		mAnimation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationEnd(Animation arg0) {
				
			}

			@Override
			public void onAnimationRepeat(Animation arg0) { }
			@Override
			public void onAnimationStart(Animation arg0) {	}
		});
		mAnimation.setDuration(400);
		mAnimation.setFillAfter(true);
		mView.startAnimation(mAnimation);
	}
	
}