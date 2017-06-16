package statusbrew.soin.com.docket;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.onesignal.OneSignal;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();

    public static String []states={"Andaman and Nicobar Islands",
            "Andhra Pradesh",
            "Arunachal Pradesh",
            "Assam",
            "Bihar",
            "Chandigarh",
            "Chhattisgarh",
            "Dadra and Nagar Haveli",
            "Delhi",
            "Goa",
            "Gujarat",
            "Haryana",
            "Himachal Pradesh",
            "Jammu and Kashmir",
            "Jharkhand",
            "Karnataka",
            "Karnatka",
            "Kerala",
            "Madhya Pradesh",
            "Maharashtra",
            "Manipur",
            "Meghalaya",
            "Mizoram",
            "Nagaland",
            "Odisha",
            "Puducherry",
            "Punjab",
            "Rajasthan",
            "Tamil Nadu",
            "Telangana",
            "Tripura",
            "Uttar Pradesh",
            "Uttarakhand",
            "West Bengal"};
     //URL to get contacts JSON
    String state1="Andaman and Nicobar Islands";
    private static String url = "http://sbcon.cmcderm.org/api/fetch_cities?state=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
        JSONObject tags = new JSONObject();
        try {
            tags.put("city", "city");//city vsrisble name
            tags.put("bloodgp", "O+");//blood group variable name
        } catch (JSONException e) {
            e.printStackTrace();
        }

        OneSignal.sendTags(tags);
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.myoptionmenu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.share:
                //startActivity(new Intent(this, About.class));
                ApplicationInfo app = getApplicationContext().getApplicationInfo();
                String filePath = app.sourceDir;

                Intent intent = new Intent(Intent.ACTION_SEND);

                // MIME of .apk is "application/vnd.android.package-archive".
                // but Bluetooth does not accept this. Let's use "/" instead.
                intent.setType("/");


                // Append file and send Intent
                intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(filePath)));
                startActivity(Intent.createChooser(intent, "Share app via"));
                return true;
            case R.id.logout:
            {
                SharedPreferences sharedPreferences = getSharedPreferences("LoginData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("email", "");
                editor.putString("password", "");
                editor.putString("name", "");
                editor.putString("city", "");
                editor.putString("state", "");
                editor.putString("bloodgroup", "");
                editor.putString("phone", "");
                editor.commit();

                startActivity(new Intent(this, SplashFinal.class));
                finish();
            }

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    public void onStart() {
        super.onStart();
         setupFAB();
    }
    private void setupFAB() {
        FloatingActionMenu floatingActionMenu = (FloatingActionMenu) this.findViewById(R.id.floatingMenu);
        floatingActionMenu.setClosedOnTouchOutside(true);
        FloatingActionButton addAlert = (FloatingActionButton) this.findViewById(R.id.add_alert);
        FloatingActionButton addNote = (FloatingActionButton) this.findViewById(R.id.add_note);

        addAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), DonarActivity.class));
            }
        });
        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), RecieverActivity.class));
            }
        });

    }
}
