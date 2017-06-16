package statusbrew.soin.com.docket;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    EditText etEmail, etUserPass, etUserName, etPhoneNo;
    Button btnRegister;
    TextView txtForLogin;
    Spinner spCity, spState, spBloodGrp;
    private ArrayAdapter<CharSequence> bloodgroupadapter;
    String bloodgrp;
    public static final String REGISTER_URL = "http://sbcon.cmcderm.org/api/register";
    private  String state1="Andaman and Nicobar Islands";
     ArrayList<String> cityList;
    private String TAG = MainActivity.class.getSimpleName();
    String query;
    String cityname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        etUserName = (EditText) findViewById(R.id.etUserName);
        etEmail = (EditText) findViewById(R.id.etUserEmail);
        etUserPass = (EditText) findViewById(R.id.etUserPass);
        etPhoneNo = (EditText) findViewById(R.id.etPhoneNo);
        spCity = (Spinner) findViewById(R.id.spCity);
        spBloodGrp = (Spinner) findViewById(R.id.spBloodGrp);
        spState = (Spinner) findViewById(R.id.spState);
        btnRegister = (Button) findViewById(R.id.btnSignUp);
        txtForLogin = (TextView) findViewById(R.id.txtForLogin);

        cityList = new ArrayList<>();

        ArrayAdapter stateadapter=new ArrayAdapter(SignUpActivity.this,android.R.layout.simple_spinner_dropdown_item,MainActivity.states);
        spState.setAdapter(stateadapter);

        spState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                state1=MainActivity.states[position];
                cityList.clear();
                new Getcity().execute();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bloodgroupadapter = ArrayAdapter.createFromResource(SignUpActivity.this, R.array.BloodGroup, android.R.layout.simple_spinner_dropdown_item);
        spBloodGrp.setAdapter(bloodgroupadapter);

        spBloodGrp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bloodgrp = spBloodGrp.getItemAtPosition(position).toString();
                //       Toast.makeText(getApplicationContext(),""+yearName,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cityname = spCity.getItemAtPosition(position).toString();
                //       Toast.makeText(getApplicationContext(),""+yearName,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name = etUserName.getText().toString().trim();
                final String email = etEmail.getText().toString().trim();
                final String phone = etPhoneNo.getText().toString().trim();
                final String password = etUserPass.getText().toString().trim();
                final String city = cityname;
                final String state = state1;
                final String bloodgroup = bloodgrp;


                StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                        new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(SignUpActivity.this, ""+response, Toast.LENGTH_LONG).show();
                                if (response.equals("Registered successfully!")) {

                                SharedPreferences sharedPreferences = getSharedPreferences("LoginData", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("email", email);
                                editor.putString("password", password);
                                    editor.putString("city", city);
                                    editor.putString("state", state);
                                    editor.putString("name", name);
                                    editor.putString("phone", phone);
                                    editor.putString("bloodgroup", bloodgroup);

                                    editor.commit();
                                    startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                                    finish();
                                }
                                else{

                                }

                            }

                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(SignUpActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("name", name);
                        params.put("email", email);
                        params.put("phone", phone);
                        params.put("password", password);
                        params.put("city", city);
                        params.put("state", state);
                        params.put("bloodgroup", bloodgroup);

                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(SignUpActivity.this);
                requestQueue.add(stringRequest);

            }
        });

        txtForLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        });
    }
    private class Getcity extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            try {
                query = URLEncoder.encode(state1, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            String jsonStr = sh.makeServiceCall("http://sbcon.cmcderm.org/api/fetch_cities?state=" + query);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {

                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray city = jsonObj.getJSONArray("cities");
                    //JSONArray city = new JSONArray();
                    String cities[] = new String[city.length()];
                    //Toast.makeText(MainActivity.this,cities[2],Toast.LENGTH_LONG).show();
                    // looping through All Contacts
                    for (int i = 0; i < cities.length; i++) {

                        //JSONObject c = city.getJSONObject(i);
                        cities[i] = city.getString(i);
                        //Toast.makeText(MainActivity.this, cities[i], Toast.LENGTH_LONG).show();
                        //String id = c.getString("id");
                        //String interest = c.getString("interest");
                        //String cover = c.getString("cover");


                        //HashMap<String,String> citi = new HashMap<>();
                        //citi.put("city", cities[i]);

                        cityList.add(cities[i]);
                        //cityList.add(citi);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });


                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

           // Spinner city = (Spinner) findViewById(R.id.city);

            // Spinner adapter
            //if (pDialog.isShowing())
            //   pDialog.dismiss();
            //ArrayAdapter adapter=new ArrayAdapter(MainActivity.this,android.R.layout.simple_spinner_dropdown_item,cityList);
            //city.setAdapter(adapter);
            spCity.setAdapter(new ArrayAdapter<>(SignUpActivity.this,android.R.layout.simple_spinner_dropdown_item,cityList));

        }


    }
}
