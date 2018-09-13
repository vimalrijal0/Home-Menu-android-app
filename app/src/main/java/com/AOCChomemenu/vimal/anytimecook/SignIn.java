package com.AOCChomemenu.vimal.anytimecook;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.vimal.anytimecook.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

import ng.max.slideview.SlideView;

public class SignIn extends AppCompatActivity {

    private static final String TAG = "Unfortunately";

    private FirebaseAuth mAuth;

    private TextInputEditText mLoginEmail;
    private TextInputEditText mLoginPassword;
    private ProgressDialog mLoginProgress;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();


//        TOOLBAR
//        Toolbar mToolbar = (Toolbar) findViewById(R.id.login_toolbar);
//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setTitle("login");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mLoginEmail = (TextInputEditText) findViewById(R.id.email);
        mLoginPassword = (TextInputEditText) findViewById(R.id.password);
        Button mLogin_btn = (Button) findViewById(R.id.login_btn);
        final Button forgot_password_btn = (Button) findViewById(R.id.forgot_password_btn);


        //------------ FORGOT PASSWORD BUTTON ----------------
        forgot_password_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                forgot_password_btn.setVisibility(View.INVISIBLE);
                LinearLayout reset_password = (LinearLayout) findViewById(R.id.reset_password);
                reset_password.setVisibility(View.VISIBLE);




            }
        });

        // ---------- WHEN WE SWIPE THE SLIDE THIS METHOD WILL BE CALLED
        SlideView slideView = (SlideView) findViewById(R.id.slideView);
        slideView.setOnSlideCompleteListener(new SlideView.OnSlideCompleteListener() {


            @Override
            public void onSlideComplete(SlideView slideView) {

                // vibrate the device
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(1000);


                final TextInputEditText RESET_EMAIL =(TextInputEditText) findViewById(R.id.RESET_EMAIL);
                String email = RESET_EMAIL.getText().toString();

                if(!email.isEmpty()) {

                    final ProgressDialog progressDialog = new ProgressDialog(SignIn.this);
                    progressDialog.setTitle("Reset Password \uD83D\uDE03 ");
                    progressDialog.setMessage("verifying..");
                    progressDialog.show();
                    mAuth.sendPasswordResetEmail(email)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (!task.isSuccessful()) {
                                        progressDialog.dismiss();
                                        try {
                                            throw task.getException();
                                        }
                                        // if user enters wrong email.
                                        catch (FirebaseAuthInvalidUserException invalidEmail) {


                                            Toast.makeText(SignIn.this, "Email doesnt exist \uD83D\uDE14  ", Toast.LENGTH_LONG).show();
                                            RESET_EMAIL.setText(null);
                                        }
                                        // if user enter wrong format email
                                        catch (FirebaseAuthInvalidCredentialsException malformedEmail) {

                                            Toast.makeText(SignIn.this, "Wrong Email Format \uD83D\uDE14", Toast.LENGTH_LONG).show();
                                            RESET_EMAIL.setText(null);

                                        } catch (Exception e) {
                                            Log.d(TAG, "onComplete: " + e.getMessage());
                                        }
                                    } else if (task.isSuccessful()) {
                                        progressDialog.dismiss();
                                        Toast.makeText(SignIn.this, "Please check your mail we have sent an reset password", Toast.LENGTH_LONG).show();
                                        RESET_EMAIL.setText(null);
                                    } else {
                                        progressDialog.dismiss();
                                        Toast.makeText(getApplicationContext(),
                                                "ERROR!!!! \uD83D\uDE14 ", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                }
                else if(email.isEmpty()){
                    Toast.makeText(SignIn.this, "please fill the email field with existence email", Toast.LENGTH_LONG).show();
                }












            }
        });







        //progress dialogue
        mLoginProgress = new ProgressDialog(this);


        mLogin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = mLoginEmail.getText().toString();
                String password = mLoginPassword.getText().toString();

                if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password))
                {

                    mLoginProgress.setTitle("Logging In");
                    mLoginProgress.setMessage("please wait while logging you account");
                    mLoginProgress.setCanceledOnTouchOutside(false);
                    mLoginProgress.show();

                    //this method is call when the user will be logged in!!!!
                    loginUser(email, password);

                }

            }
        });



    }

    // THIS METHOD IS FOR, WHEN THE USER WILL BE LOGGED IN!!!!
    private void loginUser(String email, String password) {

        // this down code is gonna check wheather the user is logged in or not!!!!
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful())
                        {
                            mLoginProgress.dismiss();
                            try
                            {
                                throw task.getException();
                            }
                            // if user enters wrong email.
                            catch (FirebaseAuthInvalidUserException invalidEmail)
                            {
                                Log.d(TAG, "onComplete: invalid_email");

                                Toast.makeText(SignIn.this, "Invalid Email \uD83D\uDE14 please create an account before logged in \uD83D\uDE03 ", Toast.LENGTH_LONG).show();
                            }
                            // if user enters wrong password.
                            catch (FirebaseAuthInvalidCredentialsException wrongPassword)
                            {
                                Log.d(TAG, "onComplete: wrong_password");

                                Toast.makeText(SignIn.this, "Wrong Password \uD83D\uDE14 ", Toast.LENGTH_LONG).show();
                            }
                            catch (Exception e)
                            {
                                Log.d(TAG, "onComplete: " + e.getMessage());
                            }
                        }

                        // if the user logged in succesfully then we will redirect him to the main page(CHATTING PAGE)
                        else if (task.isSuccessful()){

                            mLoginProgress.dismiss();

                            Intent intent = new Intent(SignIn.this,HomeMenu.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();
                        }
                        // otherwise not!!!!
                        else{
                            mLoginProgress.hide();
                            Toast.makeText(SignIn.this, "cannot logged in please do it again", Toast.LENGTH_LONG).show();

                        }


                    }
                });
    }
}
