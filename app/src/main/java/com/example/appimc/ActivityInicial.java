
package com.example.appimc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ActivityInicial extends AppCompatActivity implements View.OnClickListener {

    EditText editTextNome;
    EditText editTextPeso;
    EditText editTextAltura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);

        editTextNome = findViewById(R.id.editTextNome);
        editTextPeso = findViewById(R.id.editTextPeso);
        editTextAltura = findViewById(R.id.editTextAltura);
        findViewById(R.id.imageViewPerfil).setBackgroundResource(R.drawable.perfil);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCalcular:
            {
                String textNome = editTextNome.getText().toString().trim();
                String textPeso = editTextPeso.getText().toString().trim();
                String textAltura = editTextAltura.getText().toString().trim();

                if (textNome.isEmpty()) {
                    mostraMensagem(R.string.digite_o_nome);
                    return;
                }

                if (textPeso.isEmpty() || Double.parseDouble(textPeso) == 0) {
                    mostraMensagem(R.string.digite_o_peso);
                    return;
                }

                if (textAltura.isEmpty() || Double.parseDouble(textAltura) == 0) {
                    mostraMensagem(R.string.digite_a_altura);
                    return;
                }

                double peso = new BigDecimal(Double.parseDouble(textPeso)).setScale(2, RoundingMode.HALF_DOWN).doubleValue();
                double altura = new BigDecimal(Double.parseDouble(textAltura)).setScale(2, RoundingMode.HALF_DOWN).doubleValue();
                double imc = new BigDecimal(calculaIMC(peso, altura)).setScale(2, RoundingMode.HALF_DOWN).doubleValue();

                Intent intent = new Intent(getApplicationContext(), ActivityResultadoIMC.class);
                intent.putExtra("nome", textNome);
                intent.putExtra("peso", peso);
                intent.putExtra("altura", altura);
                intent.putExtra("imc", imc);
                startActivity(intent);
            }
            break;
        }
    }

    private double calculaIMC(double peso, double altura) {
        return peso / (altura * altura);
    }

    private void mostraMensagem(String mensagem) {
        Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_LONG).show();
    }

    private void mostraMensagem(int stringResourceId) {
        Toast.makeText(getApplicationContext(), stringResourceId, Toast.LENGTH_LONG).show();
    }
}