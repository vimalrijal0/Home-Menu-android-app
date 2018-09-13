package com.AOCChomemenu.vimal.anytimecook;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.vimal.anytimecook.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeMenu extends AppCompatActivity {

    private ImageButton breakfast,lunch,snacks,dinner;


    private FirebaseAuth mAuth;

    FirebaseAuth.AuthStateListener mAuthListner;

//    @Override
//    protected void onStart() {
//        super.onStart();
//        mAuth.addAuthStateListener(mAuthListner);
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_home_menu);

            mAuth = FirebaseAuth.getInstance();

            breakfast = (ImageButton)findViewById(R.id.breakfast);
            lunch = (ImageButton)findViewById(R.id.lunch);
            snacks = (ImageButton)findViewById(R.id.snacks);
            dinner = (ImageButton)findViewById(R.id.dinner);








        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    startActivity(new Intent(HomeMenu.this, Login.class));
                }
            }
        };





        breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeMenu.this,Breakfast.class);
                startActivity(intent);
            }
        });

        lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeMenu.this,lunch.class);
                startActivity(intent);
            }
        });

        snacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeMenu.this,snacks.class);
                startActivity(intent);
            }
        });

        dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeMenu.this,MenuPage.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        mAuth.addAuthStateListener(mAuthListner);
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null){
            sendToStart();


        }

    }

    private void sendToStart() {
        Intent intent = new Intent(HomeMenu.this,Login.class);
        startActivity(intent);
    }


    //THIS TWO BELOW METHOD FOR MENU AND MENU ITEMS
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_main,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.setting:
                Toast.makeText(this,"will Update very soon :)",Toast.LENGTH_SHORT).show();
                break;

            case R.id.Contact:
                Intent intent2 = new Intent(HomeMenu.this,contact_us.class);
                startActivity(intent2);
                break;

            case R.id.about:
                Intent intent3 = new Intent(HomeMenu.this,ABOUT_US.class);
                startActivity(intent3);
                break;
            case R.id.logout:
                mAuth.signOut();
                break;
            case R.id.app:
                Intent intent = new Intent(HomeMenu.this,Learn_app.class);
                startActivity(intent);
                break;
        }


        return super.onOptionsItemSelected(item);
    }
}
