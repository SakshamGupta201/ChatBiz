package com.saksham.chatbiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;
import com.saksham.chatbiz.adapter.FragmentAdapter;
import com.saksham.chatbiz.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    private FirebaseAuth mAuth;

    private FragmentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        adapter = new FragmentAdapter(this);

        binding.viewPager2.setAdapter(adapter);

        new TabLayoutMediator(binding.tabLayout,binding.viewPager2,
                (tab, position) -> {
                    tab.setText("Tab "+(position+1));
                }).attach();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater  = new MenuInflater(this);
        inflater.inflate(R.menu.main_ac_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.setting){
            Toast.makeText(MainActivity.this, "Settings", Toast.LENGTH_SHORT).show();
        }else {
            mAuth.signOut();
            Intent intent = new Intent(MainActivity.this, SignInActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}