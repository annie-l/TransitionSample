package com.anniel.transitionsample;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;

public class Activity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        final ImageView imageView = (ImageView) findViewById(R.id.imageView);

        getWindow()
                .getSharedElementEnterTransition()
                .addListener(new Transition.TransitionListener() {
                    @Override
                    public void onTransitionStart(Transition transition) {
                    }

                    @Override
                    public void onTransitionEnd(Transition transition) {
                        getWindow().getSharedElementEnterTransition().removeListener(this);

                        // switch back to the high res version of the image
                        imageView.setImageResource(R.drawable.first_element);
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

    public void goToTwo(View view) {
        Log.i("1", "goToTwo");
        ActivityOptions activityOptions =
                ActivityOptions.makeSceneTransitionAnimation(
                        Activity1.this,
                        Pair.create(findViewById(R.id.imageView),
                                getString(R.string.image_transition_name)),
                        Pair.create(findViewById(R.id.textView),
                                getString(R.string.text_transition_name)));

        Intent intent = new Intent(Activity1.this, Activity2.class);

        final ImageView imageView = (ImageView) findViewById(R.id.imageView);
        float newWidth = (imageView.getWidth() / imageView.getHeight()) *
                getResources().getDimension(R.dimen.bottom_bar_height) +
                getResources().getDimension(R.dimen.padding);
        intent.putExtra("img_width", newWidth);

        startActivity(intent, activityOptions.toBundle());
    }
}
