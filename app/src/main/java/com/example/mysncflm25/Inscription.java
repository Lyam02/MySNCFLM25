package com.example.mysncflm25;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Inscription extends AppCompatActivity implements View.OnClickListener
{

    private EditText txtNom, txtPrenom, txtEmail;

    private Spinner spAge, spFrequence;
    private Button btParticiper;

    private String rer;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_inscription);

        this.txtNom = (EditText) findViewById(R.id.idNom);
        this.txtPrenom = (EditText) findViewById(R.id.idPrenom);
        this.txtEmail = (EditText) findViewById(R.id.idEmail);
        this.btParticiper = (Button) findViewById(R.id.idParticiper);
        this.spAge = (Spinner) findViewById(R.id.idAge);
        this.spFrequence = (Spinner) findViewById(R.id.idFrequence);

        this.btParticiper.setOnClickListener(this);

        // On recupère le nom rer
        this.rer = this.getIntent().getStringExtra("rer");

        //remplir les spinner
        ArrayList<String> listeAge = new ArrayList<String>();
        listeAge.add("6 ans à 18 ans");
        listeAge.add("19 ans à 35 ans");
        listeAge.add("35 ans à 64 ans");
        listeAge.add("65 ans et plus");
        ArrayAdapter<String> adapterAge = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listeAge);
        this.spAge.setAdapter(adapterAge);

        ArrayList<String> listeFrequence = new ArrayList<String>();
        listeFrequence.add("Journalière");
        listeFrequence.add("Hebdomadaire");
        listeFrequence.add("Mensuelle");
        listeFrequence.add("Annuelle");
        ArrayAdapter<String> adapterFrequence = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listeFrequence);
        this.spFrequence.setAdapter(adapterFrequence);



    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.idParticiper) {
            Intent unIntent = new Intent(this, Page1.class);
            String nom = this.txtNom.getText().toString();
            String prenom = this.txtPrenom.getText().toString();
            String email = this.txtEmail.getText().toString();
            String age = this.spAge.getSelectedItem().toString();
            String frequence = this.spFrequence.getSelectedItem().toString();

            //enregistrer le candidat
            Participant unParticipant = new Participant(nom, prenom, email, age, frequence);
            //on ajoute ce participant à l'enquete qu'il a choisit
            SNCF.getEnquete(this.rer).ajouterParticipant(unParticipant);

            //on va faire passer le rer et email
            unIntent.putExtra("rer", this.rer);
            unIntent.putExtra("email", email); // ne pas utiliser this.email car ca utilise la definition qui est vide
            Toast.makeText(this, "BIENVENUE"+nom+" "+prenom, Toast.LENGTH_LONG).show();
            startActivity(unIntent);
        }

    }
}