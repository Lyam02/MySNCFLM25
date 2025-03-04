package com.example.mysncflm25;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Fin extends AppCompatActivity implements View.OnClickListener
{

    private ImageView imSmiley;
    private ListView lvListe;
    private Button btRetour;
    private String rer, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fin);

        this.imSmiley = (ImageView) findViewById(R.id.idSmiley);
        this.lvListe = (ListView) findViewById(R.id.idList);
        this.btRetour = (Button) findViewById(R.id.idRetour);

        this.btRetour.setOnClickListener(this);

        this.rer = this.getIntent().getStringExtra("rer");
        this.email = this.getIntent().getStringExtra("email");

        float moyenne = SNCF.getEnquete(this.rer).getParticipant(this.email).moyenne();
        if (moyenne < 10) {
            this.imSmiley.setImageResource(R.drawable.smiley_3);
        }else if (moyenne < 14) {
            this.imSmiley.setImageResource(R.drawable.smiley_2);
        }else {
            this.imSmiley.setImageResource(R.drawable.smiley_1);
        }

        //remplire la list view
        ArrayList<String> lesParticipants = new ArrayList<>();
        for (Participant unP : SNCF.getEnquete(this.rer).getLesParticipants().values()) {
            lesParticipants.add(unP.getNom()+" "+unP.getPrenom()+" "+unP.moyenne());
        }
        ArrayAdapter unAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lesParticipants);
        this.lvListe.setAdapter(unAdapter);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.idRetour) {
            Intent unIntent = new Intent(this, MainActivity.class);
            this.startActivity(unIntent);
        }
    }
}