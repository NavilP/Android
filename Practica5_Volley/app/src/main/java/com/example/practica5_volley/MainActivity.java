package com.example.practica5_volley;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText userText, titleText, bodyText;
    Button btnEnviar, btnCrear, btnActualizar, btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userText = findViewById(R.id.userText);
        titleText = findViewById(R.id.titleText);
        bodyText = findViewById(R.id.bodyText);

        // Para leer
        btnEnviar = findViewById(R.id.sendButton);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                //readWs();
                readWs();
            }
        });

        // Para crear
        btnCrear = findViewById(R.id.createButton);

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                //readWs();
                createWs(titleText.getText().toString(), bodyText.getText().toString(), userText.getText().toString());
            }
        });

        // Para actualizar
        btnActualizar = findViewById(R.id.updateButton);

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                //readWs();
                updateWs(titleText.getText().toString(), bodyText.getText().toString(), userText.getText().toString());
            }
        });

        // Para eliminar
        btnEliminar = findViewById(R.id.deleteButton);

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                //readWs();
                deleteWs(userText.getText().toString());
            }
        });
    }

    private void readWs() {
        String url = "https://jsonplaceholder.typicode.com/posts/1";

        StringRequest postRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Aquí puedes procesar la respuesta de la solicitud
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    userText.setText(jsonObject.getString("userId"));
                    String title = jsonObject.getString("title");
                    titleText.setText(title);
                    bodyText.setText(jsonObject.getString("body"));
                } catch (JSONException e){
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Aquí puedes manejar los errores de la solicitud
                Log.e("Error", error.getMessage());
            }
        });

        // Agrega la solicitud a la cola de solicitudes de Volley
        Volley.newRequestQueue(this).add(postRequest);
    }

    // Creacion de registros
    private void createWs(String title, String body, String userId) {
        String url = "https://jsonplaceholder.typicode.com/posts";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Aquí puedes procesar la respuesta de la solicitud
                        //Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                        showResponseDialog(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Aquí puedes manejar los errores de la solicitud
                        Log.e("Error", error.getMessage());
                    }
                }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("title", title);
                params.put("body", body);
                params.put("userId", userId);
                return params;
            }
        };

        // Agrega la solicitud a la cola de solicitudes de Volley
        Volley.newRequestQueue(this).add(postRequest);
    }

    // Actualizacion de registros
    private void updateWs(String title, String body, String userId) {
        String url = "https://jsonplaceholder.typicode.com/posts/1";
        StringRequest postRequest = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Aquí puedes procesar la respuesta de la solicitud
                        //Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                        showResponseDialog(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Aquí puedes manejar los errores de la solicitud
                        Log.e("Error", error.getMessage());
                    }
                }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("title", title);
                params.put("body", body);
                params.put("userId", userId);
                params.put("id", "1");
                return params;
            }
        };

        // Agrega la solicitud a la cola de solicitudes de Volley
        Volley.newRequestQueue(this).add(postRequest);
    }

    // Eliminacion de registros
    private void deleteWs(String id) {
        String url = "https://jsonplaceholder.typicode.com/posts/1";
        StringRequest postRequest = new StringRequest(Request.Method.DELETE, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Aquí puedes procesar la respuesta de la solicitud
                Toast.makeText(MainActivity.this, "Registro Eliminado", Toast.LENGTH_LONG).show();
                //showResponseDialog(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Aquí puedes manejar los errores de la solicitud
                        Log.e("Error", error.getMessage());
                    }
                });

        // Agrega la solicitud a la cola de solicitudes de Volley
        Volley.newRequestQueue(this).add(postRequest);
    }


    // Para mostrar resultado
    private void showResponseDialog(String response) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Response")
                .setMessage(response)
                .setPositiveButton("Aceptar", null)
                .show();
    }
}