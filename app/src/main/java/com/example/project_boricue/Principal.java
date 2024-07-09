package com.example.project_boricue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Principal extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RequestQueue requestQueue;
    private List<Producto> productoList;
    private ProductoAdapter productoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        recyclerView = findViewById(R.id.rv_vista);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productoList = new ArrayList<>();
        productoAdapter = new ProductoAdapter(productoList, this);
        recyclerView.setAdapter(productoAdapter);

        requestQueue = Volley.newRequestQueue(this);

        fetchProductos();
    }

    private void fetchProductos() {
        String url = "https://msbackendsuprabarber-production.up.railway.app/api/producto";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONArray bodyArray = response.getJSONArray("body");
                        for (int i = 0; i < bodyArray.length(); i++) {
                            JSONObject productoObject = bodyArray.getJSONObject(i);

                            Producto producto = new Producto(
                                    productoObject.getString("nombre"),
                                    productoObject.getString("descripcion"),
                                    productoObject.getInt("precio"),
                                    productoObject.getString("url_imagen")
                            );
                            productoList.add(producto);
                        }
                        productoAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    Toast.makeText(this, "Error al obtener los datos", Toast.LENGTH_SHORT).show();
                });

        requestQueue.add(jsonObjectRequest);
    }
}