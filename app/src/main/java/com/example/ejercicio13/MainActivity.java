package com.example.ejercicio13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nombre, apellido, edad, correo, direccion;
    Button guardar, ver, eliminar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = findViewById(R.id.txtnombre);
        apellido = findViewById(R.id.txtapellidos);
        edad = findViewById(R.id.txtedad);
        correo = findViewById(R.id.txtcorreo);
        direccion = findViewById(R.id.txtdireccion);
        guardar = findViewById(R.id.btnguardar);
        ver = findViewById(R.id.btnver);
        eliminar = findViewById(R.id.btneliminar);


        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AgregarPersonas();
            }
        });

        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), mostrar.class);
                startActivity(intent);
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), cambiar.class);
                startActivity(intent);
            }
        });



    }

    private void AgregarPersonas() {

        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombres, nombre.getText().toString());
        valores.put(Transacciones.apellidos, apellido.getText().toString());
        valores.put(Transacciones.edad, edad.getText().toString());
        valores.put(Transacciones.correo, correo.getText().toString());
        valores.put(Transacciones.direccion, direccion.getText().toString());

        Long resultado = db.insert(Transacciones.tablaPersonas,Transacciones.id,valores);

        Toast.makeText(getApplicationContext(), "Registro Ingresado : Codigo : " + resultado.toString(), Toast.LENGTH_LONG).show();

        db.close();

        LimpiarPantalla();

    }

    private void LimpiarPantalla() {

        nombre.setText("");
        apellido.setText("");
        edad.setText("");
        correo.setText("");
        direccion.setText("");

    }
}