package statusbrew.soin.com.docket;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.view.Window;


import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

public class SplashFinal extends AwesomeSplash {


    @Override
    public void initSplash(ConfigSplash configSplash) {

        SharedPreferences sharedPreferences = getSharedPreferences("LoginData", Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "");
        String Password = sharedPreferences.getString("Password", "");


        if (!email.equals("") && !Password.equals("")) {

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Window window = getWindow();
            window.setStatusBarColor(Color.rgb(212, 0, 0));

//background
            configSplash.setBackgroundColor(R.color.colorPrimary);
            configSplash.setAnimCircularRevealDuration(2000);
            configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);
            configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM);

//logo picture
            configSplash.setLogoSplash(R.drawable.splash_icon);
            configSplash.setAnimLogoSplashDuration(1500);
//title
            configSplash.setTitleSplash("Docket");
            configSplash.setTitleTextColor(R.color.oxford_blue);
            configSplash.setTitleTextSize(30f);
            configSplash.setAnimTitleDuration(1000);
//        configSplash.setTitleFont("fonts/myfont.ttf");
        }
    }

    @Override
    public void animationsFinished() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashFinal.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
