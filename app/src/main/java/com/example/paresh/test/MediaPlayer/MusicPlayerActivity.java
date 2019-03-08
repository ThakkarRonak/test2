package com.example.paresh.test.MediaPlayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.paresh.test.R;

public class MusicPlayerActivity extends AppCompatActivity implements Runnable{

    MediaPlayer player = new MediaPlayer();
    TextView tvDuration;
    ImageButton btnPlay,btnPause;
    SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        btnPlay = findViewById(R.id.ib_play);
        btnPause = findViewById(R.id.ib_pause);
        seekBar = findViewById(R.id.seekbar);
        tvDuration = findViewById(R.id.tv_duration);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playMusic();
            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseMusic();
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvDuration.setVisibility(View.VISIBLE);
                int x = (int) Math.ceil(progress / 1000f);

                if (x < 10) {
                    tvDuration.setText("0:0" + x);
                } else
                    tvDuration.setText("0:" + x);

                double percent = progress / (double) seekBar.getMax();
                int offset = seekBar.getThumbOffset();
                int seekWidth = seekBar.getWidth();
                int val = (int) Math.round(percent * (seekWidth - 2 * offset));
                int labelWidth = tvDuration.getWidth();
                tvDuration.setX(offset + seekBar.getX() + val - Math.round(percent * offset) - Math.round(labelWidth / 2.0));

                if (progress > 0 && player != null && !player.isPlaying()) {
                    clearMediaPlayer();
                    MusicPlayerActivity.this.seekBar.setProgress(0);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                tvDuration.setVisibility(View.VISIBLE);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    private void pauseMusic() {
        player.stop();
    }

    private void playMusic() {

        if (player != null && player.isPlaying()) {
            clearMediaPlayer();
        } else {
            player = new MediaPlayer();
            player.start();

            player = MediaPlayer.create(MusicPlayerActivity.this, R.raw.mp3_ring_wow);
            seekBar.setMax(player.getDuration());

        }
    }

    private void clearMediaPlayer() {
        player.stop();
        player.release();
        player = null;
    }

    @Override
    public void run() {
        int currentpossion = player.getCurrentPosition();
        int total = player.getDuration();

        while (player != null && player.isPlaying() && currentpossion < total) {
            try {
                Thread.sleep(1000);
                currentpossion = player.getCurrentPosition();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                return;
            }
            seekBar.setProgress(currentpossion);
        }
    }
}

