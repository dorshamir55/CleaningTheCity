package com.example.cleaningthecity;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatButton;


public class MusicButton extends AppCompatButton {
    MediaPlayer mediaPlayer;
    private boolean play = true;
    public MusicButton(Context context) {
        super(context);
        mediaPlayer = MediaPlayer.create(context, R.raw.gamesound);

    }

    public MusicButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mediaPlayer = MediaPlayer.create(context, R.raw.gamesound);
    }

    public MusicButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mediaPlayer = MediaPlayer.create(context, R.raw.gamesound);
    }

    public void destroySound()
    {
        mediaPlayer.release();
        mediaPlayer = null;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction()== MotionEvent.ACTION_DOWN) {
            if(play)
            {
                play=false;
                mediaPlayer.start();
                setBackgroundResource(R.drawable.music);
            }
            else
            {
                play=true;
                mediaPlayer.stop();
                setBackgroundResource(R.drawable.musicoff);
            }

        }
    return true;
}}
