package com.AOCChomemenu.vimal.anytimecook;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vimal.anytimecook.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class createAccount extends AppCompatActivity {

    private static final String TAG = "Unfortunately";
    private EditText mDisplayName;
    private EditText mEmail;
    private EditText mPassword;


    private android.support.v7.widget.Toolbar mcToolbar;

    private FirebaseAuth mAuth;

    //progress dialogue
    private ProgressDialog mRegProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);


        mAuth = FirebaseAuth.getInstance();



        mDisplayName = (EditText) findViewById(R.id.reg_display_name);
        mEmail = (EditText) findViewById(R.id.reg_email);
        mPassword = (EditText) findViewById(R.id.reg_password);
        Button mCreatebtn = (Button) findViewById(R.id.reg_create_btn);

        //TOOL BAR SET

//        mcToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.resource_page_toolbar);
//        setSupportActionBar(mcToolbar);
//        getSupportActionBar().setTitle("Create Account");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //progress bar
        mRegProgress = new ProgressDialog(this);



        // when we click this button the input information will be stored in the fire base databse!!!!
        mCreatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //creating the variable which will store the value of edit text!!!!
                String display_name =mDisplayName.getText().toString();
                String email =mEmail.getText().toString();
                String password =mPassword.getText().toString();

                // if the fields is noit empty then only it will register the information to the forebase database!!!!
                if (!TextUtils.isEmpty(display_name) || !TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)){

                    //along with this the progress bar will also shown to make meore interactive!!!!

                    mRegProgress.setTitle("Registering user");
                    mRegProgress.setMessage("please wait while we create an account");
                    mRegProgress.setCanceledOnTouchOutside(false);
                    mRegProgress.show();



                    register_user(display_name, email, password);

                }
                else
                {
                    Toast.makeText(createAccount.this, "Please fille the form fully", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    public void register_user(String display_name, String email, String password) {


        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (!task.isSuccessful())
                        {
                            mRegProgress.dismiss();

                            try
                            {
                                throw task.getException();
                            }
                            // if the password is weak
                            catch (FirebaseAuthWeakPasswordException weakPassword)
                            {
                                Log.d(TAG, "onComplete: weak_password");

                                Toast.makeText(createAccount.this, "Weak password!! choose strong password \uD83D\uDE14", Toast.LENGTH_SHORT).show();

                            }

                            // if the email is already exist
                            catch (FirebaseAuthUserCollisionException existEmail)
                            {
                                Log.d(TAG, "onComplete: exist_email");

                                Toast.makeText(createAccount.this, "Email Already Exist \uD83D\uDE14", Toast.LENGTH_SHORT).show();

                            }
                            // if user enter wrong format email
                            catch (FirebaseAuthInvalidCredentialsException malformedEmail)
                            {
                                Log.d(TAG, "onComplete: malformed_email");

                                Toast.makeText(createAccount.this, "Wrong Email Format \uD83D\uDE14", Toast.LENGTH_SHORT).show();

                            }
                            catch (Exception e)
                            {
                                Log.d(TAG, "onComplete: " + e.getMessage());
                            }
                        }
                        else if (task.isSuccessful()) {

                            mRegProgress.dismiss(); // after register it will move to mainactivity page so we dont want to see the progress bar there!!!!
                            Intent intent = new Intent( createAccount.this,HomeMenu.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();




                        } else {
                            // If sign in fails, display a message to the user.
                            mRegProgress.hide();
//                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(createAccount.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }


                    }
                });
    }
}