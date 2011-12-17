package com.seedotech.utils;

import com.seedotech.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class SdtAnimationUtils {

	public static void startRotationAnimation(final View view, final Context context, final boolean clockwise) {
		if (view == null)
			return;

		Animation rotation = null;
		if (clockwise)
			rotation = AnimationUtils.loadAnimation(context, R.anim.clockwise_rotation);
		else
			rotation = AnimationUtils.loadAnimation(context, R.anim.counter_clockwise_rotation);
		
		/*// Create an animation instance
		if (clockwise)
			rotation = new RotateAnimation(0f, 90f, 0.5f, 0.5f);
		else
			rotation = new RotateAnimation(90f, 0f, 0.5f, 0.5f);
		
	    rotation.setStartOffset(0);
	    rotation.setDuration(500);
		rotation.setRepeatCount(0);                	// -1 = infinite repeated
		rotation.setRepeatMode(Animation.REVERSE); 	// reverses each repeat
		rotation.setFillAfter(true);				// keep rotation after animation
		rotation.setInterpolator(new LinearInterpolator());*/

		rotation.reset();
		view.clearAnimation();
		view.startAnimation(rotation);
	}
	
	public static void setViewAnimationSlideFromLeftToRight(final View view, final Context context) {
		if (view == null)
			return;
		
		Animation animation = new AlphaAnimation(0.0f, 1.0f);

		animation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f);
		animation.setDuration(300);
		
		animation.reset();
		view.clearAnimation();
		view.startAnimation(animation);
		
		// Hide view when animation done
		animation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				view.setVisibility(View.GONE);
			}
		});
	}
	
	public static void setViewAnimationSlideFromRightToLeft(final View view, final Context context) {
		if (view == null)
			return;
		
		Animation animation = new AlphaAnimation(0.0f, 1.0f);

		animation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 1.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f);
		animation.setDuration(300);
		
		animation.reset();
		view.clearAnimation();
		view.startAnimation(animation);
	}
	
	public static void setLayoutAnimSlidedownFromTop(ViewGroup panel, Context ctx) {

		AnimationSet set = new AnimationSet(true);

		Animation animation = new AlphaAnimation(0.0f, 1.0f);
		animation.setDuration(100);
		set.addAnimation(animation);

		animation = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, -1.0f, Animation.RELATIVE_TO_SELF, 0.0f
		);
		animation.setDuration(500);
		set.addAnimation(animation);

		LayoutAnimationController controller =
			new LayoutAnimationController(set, 0.25f);
		panel.setLayoutAnimation(controller);

	}

	public static void setLayoutAnimSlideUpfromBottom(ViewGroup panel, Context ctx) {

		AnimationSet set = new AnimationSet(true);

		Animation animation = new AlphaAnimation(0.0f, 1.0f);
		animation.setDuration(100);
		set.addAnimation(animation);

		animation = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
				Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF, 0.0f
		);
		animation.setDuration(500);
		set.addAnimation(animation);

		//  set.setFillBefore(false);
		//  set.setFillAfter(false);

		LayoutAnimationController controller =
			new LayoutAnimationController(set, 0.25f);
		panel.setLayoutAnimation(controller);

	}
}//end class AnimationUtils
