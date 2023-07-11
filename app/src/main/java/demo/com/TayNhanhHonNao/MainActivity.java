package demo.com.TayNhanhHonNao;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView ivarrow,ivbutton;
    TextView tvpoint;
    ProgressBar progressBar;
    Handler handler;
    Runnable runnable;
    Random r;
    private  final static int STATE_BLUE =1;
    private  final static int STATE_RED =2;
    private  final static int STATE_YELLOW=3;
    private  final static int STATE_GREEN =4;
    int buttonState =STATE_BLUE;
    int arrowState = STATE_BLUE;

    int currentTime = 4000;
    int startTime =4000;
    int currentPoint =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        progressBar.setMax(startTime);
        progressBar.setProgress(startTime);

        tvpoint.setText("Point: "+currentPoint);

        r = new Random();
        arrowState = r.nextInt(4)+1;
        setArrowImage(arrowState);

        ivbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setButtonImage(setButtonPosition(buttonState));

            }
        });

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                currentTime = currentTime -100;
                progressBar.setProgress(currentTime);
                if (currentTime > 0) {

                    handler.postDelayed(runnable,100);
                }else{
                    if(buttonState == arrowState){
                        currentPoint =currentPoint +1;
                        tvpoint.setText("Point: "+currentPoint);
                        startTime =startTime-100;
                        if(startTime<1000){
                            startTime =2000;
                        }
                        progressBar.setMax(startTime);
                        currentTime = startTime;
                        progressBar.setProgress(currentTime);
                        arrowState = r.nextInt(4) +1;
                        setArrowImage(arrowState);
                        handler.postDelayed(runnable,100);
                    }else {
                        ivbutton.setEnabled(false);
                        //Toast.makeText(MainActivity.this, "Game Over!", Toast.LENGTH_SHORT).show();
                        showDialog("Game Over!");

                    }
                }
            }
        };
        handler.postDelayed(runnable,100);
      }

      private void setArrowImage(int state){
        switch (state){
            case STATE_BLUE:
                ivarrow.setImageResource(R.drawable.blue);
                arrowState = STATE_BLUE;
                break;
            case STATE_RED:
                ivarrow.setImageResource(R.drawable.red);
                arrowState = STATE_RED;
                break;
            case STATE_YELLOW:
                ivarrow.setImageResource(R.drawable.yellow);
                arrowState = STATE_YELLOW;
                break;
            case STATE_GREEN:
                ivarrow.setImageResource(R.drawable.green);
                arrowState = STATE_GREEN;
                break;
        }
    }
    private void setRoation(final ImageView image,final int drawable){

        RotateAnimation rotateAnimation = new RotateAnimation(0,90, Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(100);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                image.setImageResource(drawable);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });image.startAnimation(rotateAnimation);
    }
    private int setButtonPosition(int position){
        position = position +1;
        if(position == 5){
            position =1;
        }
        return position;
    }
    private void setButtonImage(int state){
        switch (state){
            case STATE_RED:
                setRoation(ivbutton, R.drawable.cross2);
                buttonState = STATE_RED;
                break;
            case STATE_BLUE:
                setRoation(ivbutton, R.drawable.cross);
                  buttonState = STATE_BLUE;
                 break;
            case STATE_GREEN:
                setRoation(ivbutton, R.drawable.cross3);
                buttonState = STATE_GREEN;
                break;
            case STATE_YELLOW:
                setRoation(ivbutton, R.drawable.cross1);
                buttonState = STATE_YELLOW;
                break;
        }
    }
    private void showDialog(String message){

        MediaPlayer song = MediaPlayer.create(MainActivity.this,R.raw.laught);
        song.start();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent = new Intent(MainActivity.this,Menu.class);
                startActivity(intent);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    public void init(){
        ivarrow = findViewById(R.id.ivarrow);
        ivbutton = findViewById(R.id.ivbutton);
        tvpoint = findViewById(R.id.tvpoint);
        progressBar = findViewById(R.id.tvpro);
    }
}