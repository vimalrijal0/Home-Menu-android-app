package com.AOCChomemenu.vimal.anytimecook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.vimal.anytimecook.R;

public class MenuPage extends AppCompatActivity {

    private ImageButton rice,dal,sabji,chatni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);

        // CREATING THE REFERENCE TO THE IMAGE BUTTON....

        rice = (ImageButton) findViewById(R.id.rice);
        dal = (ImageButton) findViewById(R.id.dal);
        sabji = (ImageButton) findViewById(R.id.sabji);
        chatni = (ImageButton) findViewById(R.id.chatni);





    }
    // VIMAL DEFINED FUNCTION
    public void clicked(View view){
        //open rice page..
        if (view.getId() == R.id.rice){

            Intent intent = new Intent(MenuPage.this,rice.class);
            startActivity(intent);
        }

        //open dal page..
        else if (view.getId() == R.id.dal){

            Intent intent = new Intent(MenuPage.this,dal.class);
            startActivity(intent);

        }

        //open sabji page..
        else if (view.getId() == R.id.sabji){

            Intent intent = new Intent(MenuPage.this,sabji.class);
            startActivity(intent);

        }

        //open chatni page..
        else if (view.getId() == R.id.chatni){

            Intent intent = new Intent(MenuPage.this,chatni.class);
            startActivity(intent);

        }

    }

}
