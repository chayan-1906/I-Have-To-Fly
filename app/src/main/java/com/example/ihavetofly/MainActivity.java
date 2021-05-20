package com.example.ihavetofly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private boolean isMute;
    private Button btnPlay;
    private TextView txtHighScore;
    private TextView txtIHaveToFly;
    static Typeface Andes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        btnPlay = findViewById(R.id.btnPlay);
        txtHighScore = findViewById(R.id.txtHighScore);
        txtIHaveToFly = findViewById ( R.id.txtIHaveToFly );

        Andes = Typeface.createFromAsset ( getAssets (), "fonts/andesneuealtmediumit.ttf" );

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, GameActivity.class));
            }
        });

        final SharedPreferences prefs = getSharedPreferences("game", MODE_PRIVATE);
        txtHighScore.setText("High Score: " + prefs.getInt("highscore", 0));
        txtHighScore.setTypeface ( Andes );
        txtIHaveToFly.setTypeface ( Andes );
        btnPlay.setTypeface ( Andes );

        isMute = prefs.getBoolean("isMute", false);

        final ImageView volumeCtrl = findViewById(R.id.imgViewVolume);

        if (isMute)
            volumeCtrl.setImageResource(R.drawable.ic_volume_off_black_24dp);
        else
            volumeCtrl.setImageResource(R.drawable.ic_volume_up_black_24dp);

        volumeCtrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isMute = !isMute;
                if (isMute)
                    volumeCtrl.setImageResource(R.drawable.ic_volume_off_black_24dp);
                else
                    volumeCtrl.setImageResource(R.drawable.ic_volume_up_black_24dp);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("isMute", isMute);
                editor.apply();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed ( );
        finish ();
    }
}