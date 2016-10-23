package com.wlalzy.propertyanimator;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity implements Animation.AnimationListener {


    private ImageView imageView;
    private AnimationDrawable animationDrawable;
    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Toast.makeText(this, "animationEnd", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    private enum AnimationType {
        SCALE,TRANSLATION,ALPHA,ROTATE,MULTIPLE,DRAWABLE
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.animationImg);
        imageView.setBackgroundResource(R.drawable.drawable_animation);
        animationDrawable = (AnimationDrawable) imageView.getBackground();

    }
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void imgClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.alphaBtn:
                startAnimation(imageView,AnimationType.ALPHA);
                break;
            case R.id.translationBtn:
                startAnimation(imageView,AnimationType.TRANSLATION);
                break;
            case R.id.scaleBtn:
                startAnimation(imageView,AnimationType.SCALE);
                break;
            case R.id.rotateBtn:
                startAnimation(imageView,AnimationType.ROTATE);
                break;
            case R.id.multipleBtn:
                startAnimation(imageView,AnimationType.MULTIPLE);
                break;
            case R.id.drawableBtn:
                animationDrawable.stop();
            default:
                break;
        }

        //        ValueAnimator animator = ValueAnimator.ofFloat(0f,10.5f,5.0f,0f);
//        animator.setDuration(1000);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                Log.e("TAG",String.valueOf(animation.getAnimatedValue()));
//            }
//        });
//        animator.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            animationDrawable.stop();
            return true;
        }
        return super.onTouchEvent(event);
    }

    public void startAnimation(View view, AnimationType type) {
        Animation  animation = null;
        if (type.equals(AnimationType.SCALE)) {
            animation = AnimationUtils.loadAnimation(this, R.anim.scale_animation);
        }else if (type.equals(AnimationType.TRANSLATION)) {
            animation = AnimationUtils.loadAnimation(this,R.anim.translation_animation);
        }else if (type.equals(AnimationType.ALPHA)) {
            animation = AnimationUtils.loadAnimation(this, R.anim.alpha_animation);
        }else if (type.equals(AnimationType.ROTATE)) {
            animation = AnimationUtils.loadAnimation(this,R.anim.rotate_animation);
        }else if (type.equals(AnimationType.MULTIPLE)) {
            animation = AnimationUtils.loadAnimation(this,R.anim.multiple_animation);
        }
        animation.setAnimationListener(this);
        animation.setInterpolator(new LinearInterpolator());
        view.startAnimation(animation);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            animationDrawable.start();
        }
    }
}
