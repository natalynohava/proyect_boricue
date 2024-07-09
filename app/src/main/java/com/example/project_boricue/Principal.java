package com.example.project_boricue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Principal extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RequestQueue requestQueue;

    private List<producto> productoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        recyclerView = findViewById(R.id.rv_vista);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        requestQueue = VolleySingleton.getmInstance(this).getRequestQueue();

        fetchProductos();

    }

    private void fetchProductos() {
        String url = "https://msbackendsuprabarber-production.up.railway.app/api/producto";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0 ; i > response.length() ; i ++){
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String titulo = jsonObject.getString("nombre");

                        producto producto = new producto(titulo);
                        productoList.add(producto);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    productoAdaptador adaptador = new productoAdaptador(Principal.this , productoList);

                    recyclerView.setAdapter(adaptador);

                }
            }
        }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Principal.this, error.getMessage(), Toast.LENGTH_SHORT).show();

                }
        });

        requestQueue.add(jsonArrayRequest);
    }
}