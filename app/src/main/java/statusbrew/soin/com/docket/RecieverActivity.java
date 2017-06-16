package statusbrew.soin.com.docket;

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

import static statusbrew.soin.com.docket.R.id.spBloodGrp;

public class RecieverActivity extends AppCompatActivity {

     Spinner spblood,spcity1,spcity2,spcity3,spstate1;
    private EditText etPhone;
    private Button btnSubmit;
    private ArrayAdapter state1adapter;
    private ArrayAdapter bloodadapter;
   // public static final String REGISTER_URL = "http://sbcon.cmcderm.org/api/register";
    String stateString="Andaman and Nicobar Islands";
     ArrayList<String> cityList;
    String TAG = MainActivity.class.getSimpleName();
    String query;

    private String bloodgrp,city1,city2,city3;

    public static final String REGISTER_URLL = "http://sbcon.cmcderm.org/api/receiver_request";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reciever);
        spblood = (Spinner) findViewById(R.id.spbloodgroup);
        spstate1 = (Spinner) findViewById(R.id.spState1);
        spcity1 = (Spinner) findViewById(R.id.spCity1);
        spcity2 = (Spinner) findViewById(R.id.spCity2);
        spcity3 = (Spinner) findViewById(R.id.spCity3);
        etPhone = (EditText) findViewById(R.id.etPhone);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        cityList = new ArrayList<>();


        ArrayAdapter stateadapter=new ArrayAdapter(RecieverActivity.this,android.R.layout.simple_spinner_dropdown_item,MainActivity.states);
        spstate1.setAdapter(stateadapter);

        spstate1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stateString=MainActivity.states[position];
                cityList.clear();
                new RecieverActivity.Getcityy().execute();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        bloodadapter = ArrayAdapter.createFromResource(RecieverActivity.this, R.array.BloodGroup, android.R.layout.simple_spinner_dropdown_item);
        spblood.setAdapter(bloodadapter);

        spblood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bloodgrp = spblood.getItemAtPosition(position).toString();
                //       Toast.makeText(getApplicationContext(),""+yearName,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spcity1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                city1 = spcity1.getItemAtPosition(position).toString();
                //       Toast.makeText(getApplicationContext(),""+yearName,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spcity2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                city2 = spcity2.getItemAtPosition(position).toString();
                //       Toast.makeText(getApplicationContext(),""+yearName,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spcity3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                city3 = spcity3.getItemAtPosition(position).toString();
                //       Toast.makeText(getApplicationContext(),""+yearName,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String phone=etPhone.getText().toString().trim();
                String email="a@b.com";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URLL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(RecieverActivity.this, ""+response, Toast.LENGTH_LONG).show();


                            }

                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(RecieverActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("bloodgroup", bloodgrp);
                        params.put("email", "piyushfzr96@gmail.com");
                        params.put("phone", "989977");
                        params.put("state", stateString);
                        params.put("city1", city1);
                        params.put("city2", city2);
                        params.put("city3", city3);

                        return params;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(RecieverActivity.this);
                requestQueue.add(stringRequest);

            }
        });
    }
    private class Getcityy extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            try {
                query = URLEncoder.encode(stateString, "utf-8");
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
            spcity1.setAdapter(new ArrayAdapter<>(RecieverActivity.this,android.R.layout.simple_spinner_dropdown_item,cityList));
            spcity2.setAdapter(new ArrayAdapter<>(RecieverActivity.this,android.R.layout.simple_spinner_dropdown_item,cityList));
            spcity3.setAdapter(new ArrayAdapter<>(RecieverActivity.this,android.R.layout.simple_spinner_dropdown_item,cityList));

        }


    }

}
