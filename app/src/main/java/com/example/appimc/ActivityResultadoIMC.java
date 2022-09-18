package com.example.appimc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.Formatter;

public class ActivityResultadoIMC extends AppCompatActivity {

    TextView textViewNome;
    TextView textViewPeso;
    TextView textViewAltura;
    TextView textViewImc;
    ImageView imageViewResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_imc);

        textViewNome = findViewById(R.id.textViewNome);
        textViewPeso = findViewById(R.id.textViewPeso);
        textViewAltura = findViewById(R.id.textViewAltura);
        textViewImc = findViewById(R.id.textViewIMC);
        imageViewResultado = findViewById(R.id.imageViewResultado);

        String nome = getIntent().getExtras().getString("nome");
        double peso =  getIntent().getExtras().getDouble("peso");
        double altura = getIntent().getExtras().getDouble("altura");
        double imc = getIntent().getExtras().getDouble("imc");

        textViewNome.setText(nome);
        textViewPeso.setText(Double.toString(peso));
        textViewAltura.setText(Double.toString(altura));
        textViewImc.setText(Double.toString(imc));

        int idImagem = R.drawable.abaixopeso;

        if (imc >= 18.5 && imc <= 24.9)
            idImagem = R.drawable.normal;
        else if (imc >= 25 && imc <= 29.9)
            idImagem = R.drawable.sobrepeso;
        else if (imc >= 30 && imc <= 34.9)
            idImagem = R.drawable.obesidade1;
        else if (imc >= 35 && imc <= 39.9)
            idImagem = R.drawable.obesidade2;
        else if (imc >= 40)
            idImagem = R.drawable.obesidade3;

        imageViewResultado.setImageResource(idImagem);
    }
}
