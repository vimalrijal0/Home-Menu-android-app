package com.AOCChomemenu.vimal.anytimecook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.vimal.anytimecook.R;
import com.github.barteksc.pdfviewer.PDFView;

public class Learn_app extends AppCompatActivity {

    private PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_app);

        pdfView = (PDFView) findViewById(R.id.pdfView);
        pdfView.fromAsset("HomeMenu.pdf").load();
    }
}
