package statusbrew.soin.com.docket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.HashMap;
import java.util.Map;

import static statusbrew.soin.com.docket.R.id.spBloodGrp;

public class RecieverActivity extends AppCompatActivity {

    private Spinner spblood,spcity1,spcity2,spcity3,spstate1;
    private EditText etPhone;
    private Button btnSubmit;
    private ArrayAdapter city1adapter;
    private ArrayAdapter city3adapter;
    private ArrayAdapter city2adapter;
    private ArrayAdapter state1adapter;
    private ArrayAdapter bloodadapter;
    private String bloodgrp,state1,city1,city2,city3;

    public static final String REGISTER_URL = "http://sbcon.cmcderm.org/api/receiver_request";

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
        state1adapter = ArrayAdapter.createFromResource(RecieverActivity.this, R.array.BloodGroup, android.R.layout.simple_spinner_dropdown_item);
        spstate1.setAdapter(state1adapter);

        spstate1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                state1 = spstate1.getItemAtPosition(position).toString();
                //       Toast.makeText(getApplicationContext(),""+yearName,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        city1adapter = ArrayAdapter.createFromResource(RecieverActivity.this, R.array.BloodGroup, android.R.layout.simple_spinner_dropdown_item);
        spcity1.setAdapter(city1adapter);

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

        city2adapter = ArrayAdapter.createFromResource(RecieverActivity.this, R.array.BloodGroup, android.R.layout.simple_spinner_dropdown_item);
        spcity2.setAdapter(city2adapter);

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

        city3adapter = ArrayAdapter.createFromResource(RecieverActivity.this, R.array.BloodGroup, android.R.layout.simple_spinner_dropdown_item);
        spcity3.setAdapter(city3adapter);

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

                final String city1="cc";
                final String city2="c2c";
                final String city3="c3c";
                final String state="sss";
                final String bloodgroup="bgbg";
                final String phone="cc";
                final String email="piyushfzr96@gmail.com";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
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
                        params.put("bloodgroup", bloodgroup);
                        params.put("email", email);
                        params.put("phone", phone);
                        params.put("state", state);
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
}
