package com.koraykaya.silyonaskerigiyim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ViewUtils;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    private TextView mSigninText;
    private EditText mNameText,mEmailText,mPasswordText;
    private Button mRegisterBtn;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //Tanımlamalar
        mSigninText = findViewById(R.id.register_signinText);
        mNameText = findViewById(R.id.register_username_textview);
        mEmailText = findViewById(R.id.register_email_textview);
        mPasswordText = findViewById(R.id.register_password_textview);
        mRegisterBtn = findViewById(R.id.register_registerbtn);
        mAuth=FirebaseAuth.getInstance();


        //OnClick
        mSigninText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,SignInActivity.class);
                startActivity(intent);
            }
        });
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterWithEmail();
            }
        });
    }

    private void RegisterWithEmail() {
        String mEmail=mEmailText.getText().toString();
        String mPassword = mPasswordText.getText().toString();

        mAuth.createUserWithEmailAndPassword(mEmail, mPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(RegisterActivity.this, "Kayıt işlemi başarılı",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this,HomeActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(RegisterActivity.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();

                        }

                    }
                });

    }
}