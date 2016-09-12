package challenge.zappos.adithya.ilovenougat;

/**
 * Created by adithya95 on 09-10-2016.
 */
import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

public class Opening extends Activity{



    private boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);

        // this = current Activity
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.opening);
        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent openStarting = new Intent(Opening.this,Main.class);
                    startActivity(openStarting);
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.doubleBackToExitPressedOnce = false;
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this,"Press again to exit!", Toast.LENGTH_SHORT).show();
    }

}


