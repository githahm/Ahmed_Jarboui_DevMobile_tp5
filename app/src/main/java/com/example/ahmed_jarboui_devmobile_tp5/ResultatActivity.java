package com.example.ahmed_jarboui_devmobile_tp5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultatActivity extends AppCompatActivity {

    private TextView textIMC, textETAT;
    private Button btnBack, btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat);

        textIMC = findViewById(R.id.textIMC);
        textETAT = findViewById(R.id.textETAT);
        btnBack = findViewById(R.id.btnBack);
        btnCall = findViewById(R.id.btnCall);

        Intent intent = getIntent();
        String prenom = intent.getStringExtra("prenom");
        double poids = intent.getDoubleExtra("poids", 0);
        double taille = intent.getDoubleExtra("taille", 0);
        String sexe = intent.getStringExtra("sexe");

        // Add this to handle if the user typed 170 instead of 1.70
        if (taille > 3) {
            taille = taille / 100;
        }

        double imc = poids / (taille * taille);
        String etat;
        int couleur;

        if (imc < 18.5) {
            etat = "maigreur";
            couleur = ContextCompat.getColor(this, R.color.colorOrange);
        } else if (imc < 25) {
            etat = "normal";
            couleur = ContextCompat.getColor(this, R.color.colorGreen);
        } else if (imc < 30) {
            etat = "surpoids";
            couleur = ContextCompat.getColor(this, R.color.colorOrange);
        } else {
            etat = "obésité";
            couleur = ContextCompat.getColor(this, R.color.colorRed);
        }

        textIMC.setText(String.format("%s %s votre IMC est : %.2f", sexe, prenom, imc));
        textIMC.setTextColor(couleur);
        textETAT.setText(etat);
        textETAT.setTextColor(couleur);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:123"));
                startActivity(callIntent);
            }
        });
    }
}