package com.AOCChomemenu.vimal.anytimecook;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.vimal.anytimecook.R;

public class ABOUT_US extends AppCompatActivity {

    private ImageButton facebook,gmail,twitter,instagram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about__us);

        facebook = (ImageButton) findViewById(R.id.facebook);
        gmail = (ImageButton) findViewById(R.id.gmail);
        twitter = (ImageButton) findViewById(R.id.twitter);
        instagram = (ImageButton) findViewById(R.id.instagram);


        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = openfacebook(ABOUT_US.this);
                startActivity(intent);

            }
        });

        gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ABOUT_US.this, "not available yet  ", Toast.LENGTH_SHORT).show();
            }
        });
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ABOUT_US.this, "not available yet  ", Toast.LENGTH_SHORT).show();
            }
        });
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ABOUT_US.this, "not available yet  ", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public static Intent openfacebook(Context context){
        try{
                context.getPackageManager()
                        .getPackageInfo("com.facebook.katana",0);
                return new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.facebook.com/codingcommunityAOCC/"));
            }
            catch (Exception e){
                return new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.facebook.com/codingcommunityAOCC/"));
        }
    }


}
