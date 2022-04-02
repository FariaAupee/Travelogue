package com.example.mylogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mylogin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText upemail,uppass;
    private Button btnsignup;
    private TextView tvsignin;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        this.setTitle("Sign Up Activity");

        mAuth = FirebaseAuth.getInstance();

        upemail=findViewById(R.id.signupemailid);
        uppass=findViewById(R.id.signuppassid);
        btnsignup=findViewById(R.id.btnsignupid);
        tvsignin=findViewById(R.id.tvsigninid);
        progressBar=findViewById(R.id.progressbarId);

        btnsignup.setOnClickListener(this);
        tvsignin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.btnsignupid:
                userRegister();
                break;

            case R.id.tvsigninid:
                Intent tosignup = new Intent(getApplicationContext(), com.example.mylogin.MainActivity.class);
                startActivity(tosignup);
                break;
        }
    }

    private void userRegister() {
        String email = upemail.getText().toString().trim();
        String pass = uppass.getText().toString().trim();
        if(email.isEmpty())
        {
            upemail.setError("Enter your Email please");
            upemail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            upemail.setError("Enter a valid email address");
            upemail.requestFocus();
            return;
        }
        if(pass.isEmpty())
        {
            uppass.setError("Enter your Password please");
            uppass.requestFocus();
            return;
        }
        if(pass.length()<6)
        {
            uppass.setError("Minimum length of password should be 6");
            uppass.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Register is successful",Toast.LENGTH_SHORT).show();
                    finish();
                    Intent toProfile = new Intent(getApplicationContext(),ProfileActivity.class);
                    toProfile.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(toProfile);
                } else
                {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException)
                    {
                        Toast.makeText(getApplicationContext(),"User is already registered",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Error :"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}

