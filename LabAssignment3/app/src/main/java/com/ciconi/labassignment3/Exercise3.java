package com.ciconi.labassignment3;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Exercise3 extends AppCompatActivity {

    private Animation earthAnimatorSet;
    private Animation moonAnimatorSet;

    private Button startButton;
    private Button stopButton;

    private ImageView earth;
    private ImageView moon;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise3);


        earthAnimatorSet = AnimationUtils.loadAnimation(this,R.anim.earth_moviment);

        earth = (ImageView) findViewById(R.id.ImageView_Earth);

        moonAnimatorSet = AnimationUtils.loadAnimation(this, R.anim.moon_moviment);

        moon =(ImageView)findViewById(R.id.ImageView_Moon);



        earth.setImageResource(R.drawable.earth);
        moon.setImageResource(R.drawable.moon);


        earthAnimatorSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Toast.makeText(getApplicationContext(), "Animation started!",
                        Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Toast.makeText(getApplicationContext(), "Animation stoped!",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        moonAnimatorSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        // Handle Start Button
        startButton = (Button) findViewById(R.id.ButtonStart);
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                earth.startAnimation(earthAnimatorSet);
                moon.startAnimation(moonAnimatorSet);
            }
        });
        // Handle Stop Button
        stopButton = (Button) findViewById(R.id.ButtonStop);
        stopButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                earth.clearAnimation();
                moon.clearAnimation();
            }
        });
    }

}
