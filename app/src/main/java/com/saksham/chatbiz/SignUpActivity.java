package com.saksham.chatbiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.saksham.chatbiz.databinding.ActivitySignUpBinding;
import com.saksham.chatbiz.model.Users;

public class SignUpActivity extends AppCompatActivity {
    private ActivitySignUpBinding binding;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);

        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        dialog = new ProgressDialog(this);
        dialog.setTitle("Creating Account");
        dialog.setMessage("We're Creating your account");
        dialog.setCancelable(false);

        binding.signUp.setOnClickListener(view -> {

            if (!TextUtils.isEmpty(binding.email.getEditText().getText()) || !TextUtils.isEmpty(binding.password.getEditText().getText())) {
                dialog.show();
                mAuth.createUserWithEmailAndPassword(binding.email.getEditText().getText().toString(),
                        binding.password.getEditText().getText().toString()).addOnCompleteListener(task -> {
                    dialog.dismiss();
                    if (task.isSuccessful()){
                        Users users = new Users(binding.name.getEditText().getText().toString(),
                                binding.username.getEditText().getText().toString(),
                                binding.email.getEditText().getText().toString(),
                                binding.password.getEditText().getText().toString());

                        String id = task.getResult().getUser().getUid();

                        database.getReference().child("Users").child(id).setValue(users);

                        Toast.makeText(SignUpActivity.this, "Created", Toast.LENGTH_SHORT).show();
                    }else
                        Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        });
        binding.signIn.setOnClickListener(view -> {
            Intent intent = new Intent(SignUpActivity.this,SignInActivity.class);
            startActivity(intent);
        });
    }

}