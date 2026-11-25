package com.example.ahmed_jarboui_devmobile_tp5;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etPrenom, etPoids, etTaille;
    private RadioGroup rgSexe;
    private RadioButton rbHomme, rbFemme;
    private Button btnCalculer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPrenom = findViewById(R.id.etPrenom);
        etPoids = findViewById(R.id.etPoids);
        etTaille = findViewById(R.id.etTaille);
        rgSexe = findViewById(R.id.rgSexe);
        rbHomme = findViewById(R.id.rbHomme);
        rbFemme = findViewById(R.id.rbFemme);
        btnCalculer = findViewById(R.id.btnCalculer);

        btnCalculer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prenom = etPrenom.getText().toString().trim();
                String poidsStr = etPoids.getText().toString().trim();
                String tailleStr = etTaille.getText().toString().trim();

                if (prenom.isEmpty() || poidsStr.isEmpty() || tailleStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    double poids = Double.parseDouble(poidsStr);
                    double taille = Double.parseDouble(tailleStr);

                    if (taille <= 0) {
                        Toast.makeText(MainActivity.this, "Taille invalide", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    String sexe = rbHomme.isChecked() ? "Monsieur" : "Madame";

                    Intent intent = new Intent(MainActivity.this, ResultatActivity.class);
                    intent.putExtra("prenom", prenom);
                    intent.putExtra("poids", poids);
                    intent.putExtra("taille", taille);
                    intent.putExtra("sexe", sexe);
                    startActivity(intent);

                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.this, "Veuillez entrer des nombres valides", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}