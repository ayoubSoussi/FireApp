package com.example.asous.fireapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;


public class MainActivity extends Activity {


    RelativeLayout rl ;
    Animation animation ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rl = (RelativeLayout)findViewById(R.id.rl) ;
        animation = AnimationUtils.loadAnimation(this,R.anim.uptodown) ;
        rl.setAnimation(animation);



    }
    public  void goToSignIn(View v) {
        Intent intent = new Intent(this,SignInActivity.class);
        startActivity(intent);

    }
}
