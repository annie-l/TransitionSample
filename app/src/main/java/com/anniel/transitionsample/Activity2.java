package com.anniel.transitionsample;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        final ImageView imageView = (ImageView) findViewById(R.id.imageView);
        final ViewGroup.LayoutParams imageLayoutParams = imageView.getLayoutParams();

        //-----comment all of this out to see original behavior-----
        postponeEnterTransition();

        final ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();

        viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override public boolean onPreDraw() {
                imageView.getViewTreeObserver().removeOnPreDrawListener(this);

                imageLayoutParams.width = Math.round(getIntent().getExtras()
                        .getFloat("img_width"));
                imageView.setLayoutParams(imageLayoutParams);

                startPostponedEnterTransition();
                return true;
            }
        });
        //-----end comment out-----

        getWindow()
                .getSharedElementEnterTransition()
                .addListener(new Transition.TransitionListener() {
                    @Override
                    public void onTransitionStart(Transition transition) {
                    }

                    @Override
                    public void onTransitionEnd(Transition transition) {
                        getWindow().getSharedElementEnterTransition().removeListener(this);

                        //set image back to WRAP_CONTENT to get to final real width
                        imageLayoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
                        imageView.setLayoutParams(imageLayoutParams);

                        //switch to nicer thumbnail version of logo after activity transition
                        imageView.setImageResource(R.drawable.first_element_thumb);
                    }

                    @Override
                    public void onTransitionCancel(Transition transition) {

                    }

                    @Override
                    public void onTransitionPause(Transition transition) {

                    }

                    @Override
                    public void onTransitionResume(Transition transition) {

                    }
                });

    }

    public void goToOne(View view) {
        Log.i("2", "goToOne");
        finishAfterTransition();
    }
}
