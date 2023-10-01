package com.example.project_boricue;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Registro extends AppCompatActivity{
    EditText id, nombres, direccion, telefono, correo, contrasena;
    TextView login;
    Button boton;
    Spinner spinner;
    String item,ID,name,address,phone,email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        id = (EditText) findViewById(R.id.Tidentificacion_reg);
        nombres = (EditText) findViewById(R.id.Tnombres_reg);
        direccion = (EditText) findViewById(R.id.Tdireccion_reg);
        telefono = (EditText) findViewById(R.id.Ttelefono_reg);
        correo = (EditText) findViewById(R.id.Tcorreo_reg);
        contrasena = (EditText) findViewById(R.id.Tcontrasena_reg);
        boton = (Button) findViewById(R.id.Bregistrar);
        login = (TextView) findViewById(R.id.Tlogin_reg);
        spinner = (Spinner) findViewById(R.id.spinner1);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(Registro.this, "Rol: " + item, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Usuario");
        arrayList.add("Empresa");
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayList);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner.setAdapter(adapter);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loguiar = new Intent(Registro.this, Login.class);
                startActivity(loguiar);
            }
        });

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (view == boton){
                    validarusuario("https://angienohava2023.000webhostapp.com/apiboricue/usuario.php");
                }

            }
        });

    }//cerrar oncreate
    private void validarusuario(String URL){


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                ID = id.getText().toString();
                name = nombres.getText().toString();
                address = direccion.getText().toString();
                phone = telefono.getText().toString();
                email = correo.getText().toString();
                password = contrasena.getText().toString();

                if(!ID.isEmpty() && !name.isEmpty() && !address.isEmpty() && !phone.isEmpty() && !email.isEmpty() && !password.isEmpty()){
                    Intent seguir = new Intent(Registro.this, MainActivity.class);
                    startActivity(seguir);
                }else{
                    Toast.makeText(Registro.this, "Datos incompletos", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Registro.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>parametros= new HashMap<String,String>();
                parametros.put("texto1",id.getText().toString());
                parametros.put("texto2",nombres.getText().toString());
                parametros.put("texto3",direccion.getText().toString());
                parametros.put("texto4",telefono.getText().toString());
                parametros.put("texto5",correo.getText().toString());
                parametros.put("texto6",contrasena.getText().toString());
                parametros.put("texto7",spinner.getSelectedItem().toString());

                return parametros;
            }
        };
        RequestQueue requestqueve = Volley.newRequestQueue(this);
        requestqueve.add(stringRequest);
    }

    public void moveToRegistration(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();

    }

}