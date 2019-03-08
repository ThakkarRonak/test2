package com.example.paresh.test.MediaPlayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import com.example.paresh.test.R;

public class MediaPlayerActivity extends AppCompatActivity implements Runnable {
    MediaPlayer player = new MediaPlayer();
    boolean wasplaying = false;
    SeekBar seekBar;
    FloatingActionButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);

        button = findViewById(R.id.fb_btn);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                playSong();
            }
        });

        final TextView seekBarHint = findViewById(R.id.txt);
        seekBar = findViewById(R.id.Sb_music);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

                seekBarHint.setVisibility(View.VISIBLE);
            }


            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                seekBarHint.setVisibility(View.VISIBLE);
                int x = (int) Math.ceil(progress / 1000f);

                if (x < 10) {
                    seekBarHint.setText("0:0" + x);
                } else
                    seekBarHint.setText("0:" + x);

                double percent = progress / (double) seekBar.getMax();
                int offset = seekBar.getThumbOffset();
                int seekWidth = seekBar.getWidth();
                int val = (int) Math.round(percent * (seekWidth - 2 * offset));
                int labelWidth = seekBarHint.getWidth();
                seekBarHint.setX(offset + seekBar.getX() + val - Math.round(percent * offset) - Math.round(labelWidth / 2));

                if (progress > 0 && player != null && !player.isPlaying()) {
                    clearMediaPlayer();
                    MediaPlayerActivity.this.seekBar.setProgress(0);
                }
            }


            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                if (player != null && player.isPlaying()) {
                    player.seekTo(seekBar.getProgress());

                }
            }
        });
    }

    private void playSong() {
        try {

            if (player != null && player.isPlaying()) {
                clearMediaPlayer();
                seekBar.setProgress(0);
                wasplaying = true;
                button.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_action_play));

            }
            if (!wasplaying) {
                if (player == null) {
                    player = new MediaPlayer();

                }
                button.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_action_pause));



            /*AssetFileDescriptor descriptor = getAssets().openFd("ring_wow.mp3");
            player.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();

            player.prepare();
            player.setLooping(false);
            seekBar.setMax(player.getDuration());
            player.start();*/


                player = new MediaPlayer();
                player.start();
                new Thread(this).start();

//                player = MediaPlayer.create(MediaPlayerActivity.this, R.raw.mp3_ring_wow);

                MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.mp3_ring_wow);

                mediaPlayer.setLooping(false);
                seekBar.setMax(mediaPlayer.getDuration());
                mediaPlayer.start();
//                player = MediaPlayer.create(MediaPlayerActivity.this, R.raw.mp3_ring_wow);
//                player = new MediaPlayer();

            /*MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.mp3_ring_wow);
           mediaPlayer.start();
           mediaPlayer.setDataSource(String.valueOf(R.raw.mp3_ring_wow));*/

            }
            wasplaying = false;
        } catch (Exception e) {
            e.printStackTrace();
        }

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clearMediaPlayer();
    }

    private void clearMediaPlayer() {
        player.stop();
        player.release();
        player = null;
    }
}
