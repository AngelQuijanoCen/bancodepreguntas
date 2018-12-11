public class Pregunta {
    private int midResTexto;
    private boolean mVerdadera;

    public Pregunta(int idRestexto, boolean verdadera){
        midResTexto = idRestexto;
        mVerdadera = verdadera;
    }

    public  int getIdResTexto(){
        return midResTexto;
    }
    public void setIdResTexto(int idResTexto){
        midResTexto = idResTexto;
    }
    public boolean isVerdadera(){
        return mVerdadera;
    }
    public void setVerdadera(boolean verdadera){
        mVerdadera = verdadera;
    }

}

package com.quijanocen.angel.bancodepreguntas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.quijanocen.angel.android.geoapp.model.bancodepreguntas;
import com.quijanocen.angel.android.geoapp.model.Pregunta;


public class Pregunta extends AppCompatActivity {

    private Button mBotonCierto;
    private Button mBotonFalso;
    private Button mBotonAnterior;
    private Button mBotonSiguiente;

    private TextView mTextoPregunta;
    private Bancodepreguntas banco;
    private MainActivity mPreguntaActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo);

        crearBancoDePreguntas();
        mPreguntaActual = banco.get(0);

        mTextoPregunta = (TextView) findViewById(R.id.texto_pregunta);
        mBotonCierto = (Button) findViewById(R.id.BotonCierto);
        mBotonFalso = (Button) findViewById(R.id.BotonFalso);
        mBotonAnterior = (Button) findViewById(R.id.BotonAnterior);
        mBotonSiguiente = (Button) findViewById(R.id.BotonSiguiente);


        mBotonCierto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VerficarRespuesta(true);
            }
        });

        mBotonFalso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VerficarRespuesta(false);
            }
        });

        mBotonAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPreguntaActual = banco.previous();
                actualizarPregunta();
            }
        });
        mBotonSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPreguntaActual = banco.next();
                actualizarPregunta();
            }
        });
    }

    private void crearBancoDePreguntas() {
        banco = new BancoPreguntas();
        banco.add(new Pregunta(R.string.texto_pregunta_1,false));
        banco.add(new Pregunta(R.string.texto_pregunta_2, true));
        banco.add(new Pregunta(R.string.texto_pregunta_3, true));
        banco.add(new Pregunta(R.string.texto_pregunta_4,true));
        banco.add(new Pregunta(R.string.texto_pregunta_5, false));
    }

    private void actualizarPregunta() {
        mTextoPregunta.setText(mPreguntaActual.getIdResTexto());
    }

    private void VerficarRespuesta(boolean botonOprimido){
        boolean respuestaEsVerdadera = mPreguntaActual.isVerdadera();
        if (botonOprimido == respuestaEsVerdadera) {
            Toast.makeText(GeoActivity.this, R.string.texto_correcto,Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(GeoActivity.this,R.string.texto_incorrecto,Toast.LENGTH_SHORT).show();
        }
    }

}

package .com.quijanocen.angel.android.geoapp.model;

        import java.util.ArrayList;
        import java.util.List;

public class BancoPreguntas {
    private final List<Pregunta> banco;
    private int posicionActual;
    private int numElementos;

    public BancoPreguntas() {
        banco = new ArrayList<>();
        posicionActual = -1;
    }

    public void add(Pregunta pregunta) {
        banco.add(pregunta);
        if (size() == 1) {
            posicionActual = 0;
        }

    }

    public int size() {
        return banco.size();
    }

    public Pregunta get(int posicion) {
        return banco.get(posicion);
    }

    public boolean isEmpty() {
        return banco.isEmpty();
    }

    public Pregunta next() {
        if (isEmpty()) {
            return null;
        } else {
            if (posicionActual == size() - 1) {
                posicionActual = 0;
            }
            else {
                posicionActual++;
            }
            return banco.get(posicionActual);
        }

    }
    public Pregunta previous(){
        if (isEmpty()){
            return null;
        }
        else {
            if (posicionActual == 0){
                posicionActual = size() - 1;
            } else {
                posicionActual--;
            }
            return banco.get(posicionActual);
        }
    }
}

