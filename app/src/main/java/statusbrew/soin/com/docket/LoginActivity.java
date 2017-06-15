package statusbrew.soin.com.docket;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnLogin;
    private static final String REGISTER_URL = "http://sbcon.cmcderm.org/api/login";

    public static final String KEY_EMAIL = "id";
    public static final String KEY_PASSWORD = "pass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnlogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               final String id = etEmail.getText().toString();
                final String pass = etPassword.getText().toString();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                               // Toast.makeText(LoginActivity.this, ""+response, Toast.LENGTH_LONG).show();
                               /* if (response.equals("OK")) {
                                    Toast.makeText(LoginActivity.this,"lolol",Toast.LENGTH_SHORT).show();
                              *//*  SharedPreferences sharedPreferences = getSharedPreferences("LoginData", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("email", email);
                                editor.putString("password", password);

                                editor.commit();*//*}
                                else{
                                    Toast.makeText(LoginActivity.this,"hahshs",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplication(), MainActivity.class));
                                    finish();
                                }*/
                                if(response.equals("Logged in")){
                                    Toast.makeText(LoginActivity.this,"Successfully login",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplication(), MainActivity.class));
                                finish();
                                }
                            else
                                {
                                    Toast.makeText(LoginActivity.this,"Login failed try again",Toast.LENGTH_SHORT).show();
                                }}

                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("id", id);
                        params.put("pass", pass);
                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
                requestQueue.add(stringRequest);

            }
        });
    }

/*
    private void loginUser() throws JSONException {


        String id = etEmail.getText().toString();
        String pass = etPassword.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(LoginActivity.this, response, Toast.LENGTH_LONG).show();
                        if (response.equals("Successfully Registered")) {
                              */
/*  SharedPreferences sharedPreferences = getSharedPreferences("LoginData", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("email", email);
                                editor.putString("password", password);

                                editor.commit();*//*

                            startActivity(new Intent(getApplication(), MainActivity.class));
                            finish();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_Email, id);
                params.put(KEY_PASSWORD, pass);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
*/

    }
