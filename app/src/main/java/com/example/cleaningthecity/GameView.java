package com.example.cleaningthecity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View {
    //Canvas
    private int canvasWidth;
    private int canvasHeight;

    //Bird
    //private Bitmap bird;
    private Bitmap bird[] = new Bitmap[2];
    private int birdX = 10;
    private int birdY;
    private int birdSpeed;

    //Blue Ball
    private int blueX;
    private int blueY;
    private int blueSpeed = 15;
    private Paint bluePaint = new Paint();

    //Black Ball
    private int blackX;
    private int blackY;
    private int blackSpeed = 20;
    private Paint blackPaint = new Paint();

    //Background
    private Bitmap bgImage;

    //Score
    private Paint scorePaint = new Paint();
    private int score;

    //Level
    private Paint levelPaint = new Paint();

    //Life
    private Bitmap life[] = new Bitmap[2];
    private int life_count;

    //Status Check
    private boolean touch_flg = false;

    public GameView(Context context) {
        super(context);

        bird[0] = BitmapFactory.decodeResource(getResources(), R.drawable.bird1);
        bird[1] = BitmapFactory.decodeResource(getResources(), R.drawable.bird2);

        bgImage = BitmapFactory.decodeResource(getResources(), R.drawable.bg);

        bluePaint.setColor(Color.BLUE);
        bluePaint.setAntiAlias(false);

        blackPaint.setColor(Color.BLACK);
        blackPaint.setAntiAlias(false);

        scorePaint.setColor(Color.BLACK);
        scorePaint.setTextSize(32);
        scorePaint.setTypeface(Typeface.DEFAULT_BOLD);
        scorePaint.setAntiAlias(true);

        levelPaint.setColor(Color.DKGRAY);
        levelPaint.setTextSize(32);
        levelPaint.setTypeface(Typeface.DEFAULT_BOLD);
        levelPaint.setTextAlign(Paint.Align.CENTER);
        levelPaint.setAntiAlias(true);

        life[0] = BitmapFactory.decodeResource(getResources(), R.drawable.heart);
        life[1] = BitmapFactory.decodeResource(getResources(), R.drawable.heart_g);

        //First position
        birdY = 500;
        score = 0;
        life_count = 3;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        canvas.drawBitmap(bgImage,0,0,null);

        //Bird
        int minBirdY = bird[0].getHeight();
        int maxBirdY = canvasHeight - bird[0].getHeight() * 4 - bird[0].getHeight()/3;
        Log.d("MAXMIN", "minBirdY====>>> " + minBirdY);
        Log.d("MAXMIN", "maxBirdY====>>> " + maxBirdY);

        birdY += birdSpeed;
        if (birdY < minBirdY) birdY = minBirdY;
        if (birdY > maxBirdY) birdY = maxBirdY;
        birdSpeed +=2;

        if (touch_flg) {
            //Flap wings
            canvas.drawBitmap(bird[1], birdX, birdY, null);
            touch_flg = false;

        } else {
            canvas.drawBitmap(bird[0], birdX, birdY, null);
        }

        //Blue
        blueX -= blueSpeed;
        if (hitCheck(blueX, blueY)) {
            score += 10;
            blueX = -100;
        }
        if (blueX < 0) {
            blueX = canvasWidth + 20;
            blueY = (int) Math.floor(Math.random() * (maxBirdY - minBirdY)) + minBirdY;
        }
        canvas.drawCircle(blueX, blueY, 10, bluePaint);

        //Black
        blackX -= blackSpeed;
        if (hitCheck(blackX, blackY)) {
            blackX = -100;
            life_count--;
            if (life_count == 0) {
                //Game Over
                Log.v("MESSAGE", "GAME OVER");
            }
        }
        if (blackX < 0) {
            blackX = canvasWidth + 200;
            blackY = (int) Math.floor(Math.random() * (maxBirdY - minBirdY)) + minBirdY;
        }
        canvas.drawCircle(blackX, blackY, 20, blackPaint);

        //Score
        canvas.drawText("Score: " + score, 20, 60, scorePaint);

        // Level
        canvas.drawText("Lv.1", canvasWidth/3, 60, levelPaint);

        //Life
        for (int i = 0; i < 3; i++) {
            int x = (int) (560 + life[0].getWidth() * 1.5 * i);
            int y = 30;

            if (i < life_count) {
                canvas.drawBitmap(life[0], x, y, null);
            } else {
                canvas.drawBitmap(life[1], x, y, null);
            }
        }
    }

    public boolean hitCheck(int x, int y) {
        if (birdX < x && x < (birdX + bird[0].getWidth()) &&
                birdY < y && y < (birdY + bird[0].getHeight())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            touch_flg = true;
            birdSpeed = -20;
        }
        return true;
    }
}
