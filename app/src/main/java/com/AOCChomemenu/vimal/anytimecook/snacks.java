package com.AOCChomemenu.vimal.anytimecook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.vimal.anytimecook.R;

public class snacks extends AppCompatActivity {

    private ImageButton cheese_pakoda,maggi,macroni,bread_pakoda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snacks);

        cheese_pakoda = (ImageButton) findViewById(R.id.cheese_pakoda);
        macroni = (ImageButton) findViewById(R.id.macroni);
        maggi = (ImageButton) findViewById(R.id.maggi);
        bread_pakoda = (ImageButton) findViewById(R.id.bread_pakoda);
    }

    public void get_snacks(View view){

        switch (view.getId())
        {
            case R.id.cheese_pakoda:Intent intent1 = new Intent(snacks.this,cheese_pakoda.class);startActivity(intent1);break;
            case R.id.macroni:Intent intent2 = new Intent(snacks.this,macroni.class);startActivity(intent2);break;
            case R.id.maggi:Intent intent3 = new Intent(snacks.this,maggi.class);startActivity(intent3);break;
            case R.id.bread_pakoda:Intent intent4 = new Intent(snacks.this,bread_pakoda.class);startActivity(intent4);break;

        }

    }
}
