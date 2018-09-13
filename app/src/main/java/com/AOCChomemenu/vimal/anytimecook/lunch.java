package com.AOCChomemenu.vimal.anytimecook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.vimal.anytimecook.R;

public class lunch extends AppCompatActivity {

    private ImageButton curd_rice,dhokla,lemon_rice,sambhar_rice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch);

        curd_rice = (ImageButton) findViewById(R.id.curd_rice);
        dhokla = (ImageButton) findViewById(R.id.dhokla);
        lemon_rice = (ImageButton) findViewById(R.id.lemon_rice);
        sambhar_rice = (ImageButton) findViewById(R.id.sambhar_rice);


    }

    public void get_lunch(View view){

        // FETCHING THE ID OF EACH IMAGE_BUTTON AND CONVERTED TO THE STRING TO PERFORM THE SWITCH CASE CONCEPT!!!!
        //String ID=String.valueOf(view.getId());

        switch (view.getId()){

            case R.id.curd_rice: Intent intent1 = new Intent(lunch.this,curd_rice.class); startActivity(intent1); break;
            case R.id.dhokla: Intent intent2 = new Intent(lunch.this,dhokla.class); startActivity(intent2); break;
            case R.id.lemon_rice: Intent intent3 = new Intent(lunch.this,lemon_rice.class); startActivity(intent3);break;
            case R.id.sambhar_rice: Intent intent4 = new Intent(lunch.this,sambhar_rice.class); startActivity(intent4); break;

        }



    }
}
