package com.example.mysncflm25;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Page1 extends AppCompatActivity implements View.OnClickListener
{

    private RadioGroup rgRegularite, rgProprete;
    private Button btSuivant;
    private String email, rer;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_page1);

        this.rgRegularite = (RadioGroup) findViewById(R.id.idRegularite);
        this.rgProprete = (RadioGroup) findViewById(R.id.idProprete);
        this.btSuivant = (Button) findViewById(R.id.idSuivant);

        this.rer = this.getIntent().getStringExtra("rer").toString();
        this.email = this.getIntent().getStringExtra("email").toString();

        this.btSuivant.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.idSuivant) {
            //on enregistre les scores de réponses
            int score = 0;
            if (this.rgRegularite.getCheckedRadioButtonId() == R.id.idRegularite1) {
                score = 16;
            } else if (this.rgRegularite.getCheckedRadioButtonId() == R.id.idRegularite2) {

                score = 12;
            } else if (this.rgRegularite.getCheckedRadioButtonId() == R.id.idRegularite3) {
                score = 8;
            }
            String question = "regularite";
            Log.e("email", this.email);
            Log.e("rer", this.rer);
            SNCF.getEnquete(this.rer).getParticipant(this.email).ajouterReponse(question, score);

            if (this.rgProprete.getCheckedRadioButtonId() == R.id.idProprete1) {
                score = 16;
            } else if (this.rgProprete.getCheckedRadioButtonId() == R.id.idProprete2){
                score = 12;
            }else if (this.rgProprete.getCheckedRadioButtonId() == R.id.idProprete3){
                score = 8;
            }
            question = "Proprete";
            SNCF.getEnquete(this.rer).getParticipant(this.email).ajouterReponse(question, score);

            // on passe à la suivante
            Intent unIntent = new Intent(this, Page2.class);
            unIntent.putExtra("rer", this.rer);
            unIntent.putExtra("email", this.email);
            startActivity(unIntent);

        }
    }
}