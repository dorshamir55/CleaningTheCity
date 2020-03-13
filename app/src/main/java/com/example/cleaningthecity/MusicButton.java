package com.example.cleaningthecity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatButton;


public class MusicButton extends AppCompatButton {
    private SoundPlayer player;
    private boolean play = true;
    public MusicButton(Context context) {
        super(context);
        player= new SoundPlayer(context);
    }

    public MusicButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        player= new SoundPlayer(context);
    }

    public MusicButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        player= new SoundPlayer(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction()== MotionEvent.ACTION_DOWN) {
            player.playGameSound(play);
            if(play)
            {
                play=false;
                setBackgroundResource(R.drawable.music);
            }
            else
            {
                play=true;
                setBackgroundResource(R.drawable.musicoff);
            }

        }
    return true;
}}
