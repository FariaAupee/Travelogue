package com.example.mylogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylogin.R;
import com.example.mylogin.SignUpActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText inemail,inpass;
    private Button signin;
    private TextView signup;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Sign In Activity");

        mAuth = FirebaseAuth.getInstance();

        inemail=findViewById(R.id.signinemailid);
        inpass=findViewById(R.id.signinpassid);
        signin=findViewById(R.id.btnsigninid);
        signup=findViewById(R.id.tvsignupid);
        progressBar=findViewById(R.id.progressbarId);

        signup.setOnClickListener(this);
        signin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btnsigninid:
                userLogin();
                break;
            case R.id.tvsignupid:
                Intent tosignup = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(tosignup);
                break;
        }
    }

    private void userLogin() {
        String email = inemail.getText().toString().trim();
        String pass = inpass.getText().toString().trim();
        if(email.isEmpty())
        {
            inemail.setError("Enter your Email please");
            inemail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            inemail.setError("Enter a valid email address");
            inemail.requestFocus();
            return;
        }
        if(pass.isEmpty())
        {
            inpass.setError("Enter your Password please");
            inpass.requestFocus();
            return;
        }
        if(pass.length()<6)
        {
            inpass.setError("Minimum length of password should be 6");
            inpass.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if(task.isSuccessful())
                {
                    finish();
                    Intent toProfile = new Intent(getApplicationContext(),ProfileActivity.class);
                    toProfile.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(toProfile);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Sign In Unsuccessful",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
