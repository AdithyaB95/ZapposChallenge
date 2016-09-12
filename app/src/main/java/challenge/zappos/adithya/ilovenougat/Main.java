package challenge.zappos.adithya.ilovenougat;

/**
 * Created by adithya95 on 09-10-2016.
 */
import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;

import android.widget.ImageView;


public class Main extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page);




        ImageView shoes_one = (ImageView) findViewById(R.id.shoes_one);
        ImageView shoes_two = (ImageView) findViewById(R.id.shoes_two);
        ImageView bags = (ImageView) findViewById(R.id.bags_one);
        ImageView watch = (ImageView) findViewById(R.id.watch);
        ImageView gloves = (ImageView) findViewById(R.id.gloves);
        ImageView woman = (ImageView) findViewById(R.id.woman);
        ImageView man = (ImageView) findViewById(R.id.man);
        ImageView hat = (ImageView) findViewById(R.id.hat);
        ImageView bag_two = (ImageView) findViewById(R.id.bag_two);
        Button search_button = (Button) findViewById(R.id.search_button);

        search_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startActivity(new Intent(Main.this, MainActivity.class));

            }
        });






        Animation shoes_anime_1 = new AlphaAnimation(0.0f, 1.0f);
        shoes_anime_1.setDuration(2500);
        shoes_anime_1.setStartOffset(175);
        shoes_anime_1.setRepeatMode(Animation.REVERSE);
        shoes_anime_1.setRepeatCount(Animation.INFINITE);
        shoes_one.startAnimation(shoes_anime_1);
        gloves.startAnimation(shoes_anime_1);
        man.startAnimation(shoes_anime_1);



        Animation bags_anime = new AlphaAnimation(0.0f, 1.0f);
        bags_anime.setDuration(3000);
        bags_anime.setStartOffset(100);
        bags_anime.setRepeatMode(Animation.REVERSE);
        bags_anime.setRepeatCount(Animation.INFINITE);
        bags.startAnimation(bags_anime);




        Animation shoes_anime_2 = new AlphaAnimation(0.0f, 1.0f);
        shoes_anime_2.setDuration(3500);
        shoes_anime_2.setStartOffset(175);
        shoes_anime_2.setRepeatMode(Animation.REVERSE);
        shoes_anime_2.setRepeatCount(Animation.INFINITE);
        shoes_two.startAnimation(shoes_anime_2);
        watch.startAnimation(shoes_anime_2);
        woman.startAnimation(shoes_anime_2);
        hat.startAnimation(shoes_anime_2);
        bag_two.startAnimation(shoes_anime_2);
    }


}

