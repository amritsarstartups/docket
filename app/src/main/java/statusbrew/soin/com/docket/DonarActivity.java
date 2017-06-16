package statusbrew.soin.com.docket;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class DonarActivity extends AppCompatActivity {

    private ArrayAdapter city1dadapter;
    private ArrayAdapter state1dadapter;
    private ArrayAdapter blooddadapter;
    private Button taptodonate;
    private Spinner spdcity1,spdstate1,spdblood;
    String city1d,state1d,bloodd;
      String state1dd="Andaman and Nicobar Islands";
    ArrayList<String> cityList;
    private String TAG = MainActivity.class.getSimpleName();
    String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donar);
        spdcity1 = (Spinner) findViewById(R.id.spdcity);
        spdstate1 = (Spinner) findViewById(R.id.spdstate);
        spdblood = (Spinner) findViewById(R.id.spdblood);
        taptodonate = (Button) findViewById(R.id.taptodonate);
        cityList = new ArrayList<>();
       /* city1dadapter = ArrayAdapter.createFromResource(DonarActivity.this, R.array.BloodGroup, android.R.layout.simple_spinner_dropdown_item);
        spdcity1.setAdapter(city1dadapter);*/

        spdcity1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                city1d = spdcity1.getItemAtPosition(position).toString();
                //       Toast.makeText(getApplicationContext(),""+yearName,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        state1dadapter =new ArrayAdapter(DonarActivity.this, android.R.layout.simple_spinner_dropdown_item,MainActivity.states);
        spdstate1.setAdapter(state1dadapter);

        spdstate1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                state1dd=MainActivity.states[position];
                cityList.clear();
                new DonarActivity.Getcityyy().execute();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        blooddadapter = ArrayAdapter.createFromResource(DonarActivity.this, R.array.BloodGroup, android.R.layout.simple_spinner_dropdown_item);
        spdblood.setAdapter(blooddadapter);

        spdblood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bloodd = spdblood.getItemAtPosition(position).toString();
                //       Toast.makeText(getApplicationContext(),""+yearName,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        taptodonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    private class Getcityyy extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            try {
                query = URLEncoder.encode(state1dd, "utf-8");
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
            spdcity1.setAdapter(new ArrayAdapter<>(DonarActivity.this,android.R.layout.simple_spinner_dropdown_item,cityList));

        }


    }
}
