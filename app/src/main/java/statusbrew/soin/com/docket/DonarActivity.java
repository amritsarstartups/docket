package statusbrew.soin.com.docket;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class DonarActivity extends AppCompatActivity {

    private ArrayAdapter city1dadapter;
    private ArrayAdapter state1dadapter;
    private ArrayAdapter blooddadapter;
    private Button taptodonate;
    private Spinner spdcity1,spdstate1,spdblood;
    String city1d,state1d,bloodd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donar);
        spdcity1 = (Spinner) findViewById(R.id.spdcity);
        spdstate1 = (Spinner) findViewById(R.id.spdstate);
        spdblood = (Spinner) findViewById(R.id.spdblood);
        taptodonate = (Button) findViewById(R.id.taptodonate);

        city1dadapter = ArrayAdapter.createFromResource(DonarActivity.this, R.array.BloodGroup, android.R.layout.simple_spinner_dropdown_item);
        spdcity1.setAdapter(city1dadapter);

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

        state1dadapter = ArrayAdapter.createFromResource(DonarActivity.this, R.array.BloodGroup, android.R.layout.simple_spinner_dropdown_item);
        spdstate1.setAdapter(state1dadapter);

        spdstate1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                state1d = spdstate1.getItemAtPosition(position).toString();
                //       Toast.makeText(getApplicationContext(),""+yearName,Toast.LENGTH_LONG).show();
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
}
