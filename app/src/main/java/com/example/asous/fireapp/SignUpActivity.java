package com.example.asous.fireapp;

import android.app.Activity;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import static android.content.ContentValues.TAG;


public class SignUpActivity extends Activity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener ;
    EditText username,email,password ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth = FirebaseAuth.getInstance() ;
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
        email = (EditText)findViewById(R.id.email) ;
        password = (EditText)findViewById(R.id.password) ;
        username = (EditText)findViewById(R.id.username) ;
    }
    public  void signUP(View v){
        final String Susername = username.getText().toString().trim() ;
        final String Semail = email.getText().toString().trim() ;
        String Spassword = password.getText().toString().trim() ;
        if (Semail.isEmpty()|Spassword.isEmpty()| userExist(Susername) ){
            Toast.makeText(SignUpActivity.this,"Please check your informations !",Toast.LENGTH_LONG).show(); ;
            return ;
        }
        else {
            mAuth.createUserWithEmailAndPassword(Semail, Spassword)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
                            if (!task.isSuccessful()) {
                                Toast.makeText(SignUpActivity.this, "Please try again !",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SignUpActivity.this, "Now you are a member !",
                                        Toast.LENGTH_SHORT).show();
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference myRef = database.getReference("users");
                                DatabaseReference myRef2 = database.getReference("usernames");
                                myRef.child(mAuth.getCurrentUser().getUid()).child("username").setValue(Susername);
                                myRef.child(mAuth.getCurrentUser().getUid()).child("email").setValue(Semail);
                                myRef2.child(Susername).setValue(0) ;


                            }

                            // ...
                        }
                    });
        }

    }
    boolean res ;
    private boolean userExist(final String susername) {

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("usernames") ;
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.hasChild(susername)) {
                    res = true ;
                }else res = false ;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return res ;
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
