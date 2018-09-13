package com.AOCChomemenu.vimal.anytimecook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.vimal.anytimecook.R;

public class Breakfast extends AppCompatActivity {

    private ImageButton aloo_parantha,cheese_omlet,upma,paneer_sandwich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast);

        aloo_parantha = (ImageButton) findViewById(R.id.aloo_parantha);
        cheese_omlet = (ImageButton) findViewById(R.id.cheese_omlet);
        upma = (ImageButton) findViewById(R.id.upma);
        paneer_sandwich = (ImageButton) findViewById(R.id.paneer_sandwich);


    }

    public void breakfast_function(View view){

        if (view.getId() == R.id.aloo_parantha){
            Intent intent = new Intent(Breakfast.this,aloo_parantha.class);
            startActivity(intent);
        }
        else if (view.getId() == R.id.cheese_omlet){

            Intent intent = new Intent(Breakfast.this,cheese_omlet.class);
            startActivity(intent);

        }
        else if (view.getId() == R.id.upma){

            Intent intent = new Intent(Breakfast.this,upma.class);
            startActivity(intent);

        }
        else if (view.getId() == R.id.paneer_sandwich){

            Intent intent = new Intent(Breakfast.this,paneer_sandwich.class);
            startActivity(intent);

        }

    }
}
