package com.saksham.chatbiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.saksham.chatbiz.databinding.ActivitySplashScreenBinding;

import java.util.Objects;

public class SplashScreenActivity extends AppCompatActivity {
    private static final long SPLASH_SCREEN = 3500;
    ActivitySplashScreenBinding binding;
    Animation topAnim;
    Animation bottomAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Objects.requireNonNull(getSupportActionBar()).hide();

        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen);

        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_anim);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_anim);

        binding.logo.setAnimation(topAnim);
        binding.appName.setAnimation(bottomAnim);
        binding.textView2.setAnimation(bottomAnim);
        binding.textView3.setAnimation(bottomAnim);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreenActivity.this, SignInActivity.class);

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SplashScreenActivity.this, new Pair<>(binding.logo, "logo"));
            startActivity(intent,options.toBundle());
            finish();
        },SPLASH_SCREEN);
    }
}