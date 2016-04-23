package com.ab.smartwallet;

import android.content.Intent;
import com.daimajia.androidanimations.library.Techniques;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;
/**
 * Created by Abdullah on 3/9/2016.
 */
public class Splash extends AwesomeSplash {
   @Override
   public void initSplash(ConfigSplash configSplash) {

       //Customize Circular Reveal
       configSplash.setBackgroundColor(R.color.bayan); //any color you want form colors.xml
       configSplash.setAnimCircularRevealDuration(1500); //int ms
       configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);  //or Flags.REVEAL_LEFT
       configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP

       //Choose LOGO OR PATH; if you don't provide String value for path it's logo by default


       configSplash.setTitleSplash("Smart Wallet");
       configSplash.setTitleTextColor(R.color.bayan1);
       configSplash.setAnimTitleTechnique(Techniques.Flash);

       //Customize Logo
       configSplash.setLogoSplash(R.drawable.splash1); //or any other drawable
       configSplash.setAnimLogoSplashDuration(1500); //int ms
       configSplash.setAnimLogoSplashTechnique(Techniques.Bounce); //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)



   }

    @Override
    public void animationsFinished() {
        Intent intent = new Intent(Splash.this,main.class);
        startActivity(intent);
        finish();

    }
}