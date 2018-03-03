package com.example.asous.fireapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import static android.content.ContentValues.TAG;

public class SignInActivity extends Activity {
    FirebaseAuth mAuth ;
    String email,password ;
    EditText emailT,passwordT ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        emailT = (EditText)findViewById(R.id.emailin) ;
        passwordT = (EditText)findViewById(R.id.passwordin) ;
        mAuth = FirebaseAuth.getInstance() ;
    }


    public void signIN(View v){
        email = emailT.getText().toString().trim() ;
        password = passwordT.getText().toString().trim() ;
        if (Objects.equals(mAuth.getCurrentUser().getEmail().trim(), email)){
            Toast.makeText(SignInActivity.this, "YOU ARE ALREADY IN",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SignInActivity.this,ContactsActivity.class) ;
            startActivity(intent);
            return ;
        }
        if (email.isEmpty() | password.isEmpty()) return ;
        else {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());


                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Log.w(TAG, "signInWithEmail", task.getException());
                                Toast.makeText(SignInActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SignInActivity.this, "YOU ARE IN ",
                                        Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignInActivity.this,ContactsActivity.class) ;
                                startActivity(intent);
                            }

                            // ...
                        }
                    });
        }
    }
    public void signup(View v){
        Intent intent = new Intent(SignInActivity.this,SignUpActivity.class) ;
        startActivity(intent);
    }
}
