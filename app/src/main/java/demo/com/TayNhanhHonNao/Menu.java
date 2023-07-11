package demo.com.TayNhanhHonNao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends AppCompatActivity {
    Button nor,hard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        nor = findViewById(R.id.button);
        hard = findViewById(R.id.hardcore);
        nor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this,MainActivity.class);
                startActivity(intent);
                MediaPlayer song = MediaPlayer.create(Menu.this,R.raw.musicbackground);
                song.start();
            }
        });

        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this,HardCore.class);
                startActivity(intent);
                MediaPlayer song = MediaPlayer.create(Menu.this,R.raw.musicbackground);
                song.start();
            }
        });
    }
}