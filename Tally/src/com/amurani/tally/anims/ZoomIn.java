package com.amurani.tally.anims;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

public class ZoomIn {
	
	private Animation mAnimation;
	
	public ZoomIn(View mView) {
		mAnimation = new ScaleAnimation(
				1f, 1f,
				0f, 0f,
				Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f
		);
		mAnimation.setDuration(400);
		mAnimation.setFillAfter(true);
		mView.startAnimation(mAnimation);
	}
	
}