package com.pacman.view;

import static com.pacman.MainActivity.WIDTH_DEVICE;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.pacman.R;

public class Loading extends View {

    private Timer mainTimer;
    private Canvas canvas;
    private Bitmap bitmapPacmanClose;
    private Bitmap bitmapPacmanOpen;
    private Bitmap spirit;
    private boolean isOpen;
    private int posX;
    private int posY;

    private int size;

    private ArrayList<SpiritLoading> spirits;

    public Loading(Context context) {
        super(context);
        spirits = new ArrayList<SpiritLoading>();

        this.mainTimer = new Timer();
        mainTimer.schedule(timerMain, 1, 50);
        posX = 400;
        if (WIDTH_DEVICE > 700) {
            posY = 180;
            size = 60;
        }
        if (WIDTH_DEVICE < 700) {
            posY = 70;
            size = 60;
        }

        bitmapPacmanClose = Bitmap.createScaledBitmap(BitmapFactory
                .decodeResource(context.getResources(), R.drawable.pman_4_2),
                size * 2, size * 2, true);
        bitmapPacmanOpen = Bitmap.createScaledBitmap(BitmapFactory
                .decodeResource(context.getResources(), R.drawable.pman_4),
                size * 2, size * 2, true);
        spirit = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(
                context.getResources(), R.drawable.spirit_defence), size / 2,
                size / 2, true);
        spirits.add(new SpiritLoading(posX + 120, posY));
        spirits.add(new SpiritLoading(posX + 160, posY));
        spirits.add(new SpiritLoading(posX + 200, posY));
        spirits.add(new SpiritLoading(posX + 240, posY));
    }

    boolean create = false;
    boolean one = true;
    TimerTask timerMain = new TimerTask() {
        public void run() {
            onMove();
            for (SpiritLoading ghost : spirits)
                ghost.onMove();
        }
    };

    private void onMove() {
        if (posX > 800)
            posX = 0;
        posX += 5;

        if (posX % 15 == 0) {
            if (isOpen)
                isOpen = false;
            else
                isOpen = true;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        this.canvas = canvas;
        canvas.drawColor(Color.BLACK);
        canvas.drawRect(0, 0, getWidth(), getHeight(), new Paint());
        drawObj();
        super.onDraw(canvas);
        invalidate();
    }

    private void drawObj() {
        if (isOpen)
            canvas.drawBitmap(bitmapPacmanOpen, posX, posY, new Paint());
        else
            canvas.drawBitmap(bitmapPacmanClose, posX, posY, new Paint());

        for (SpiritLoading ghost : spirits)
            canvas.drawBitmap(spirit, ghost.x, ghost.y, new Paint());

    }

}

class SpiritLoading {
    public int x;
    public int y;

    SpiritLoading(int x, int y) {
        this.x = x;
        this.y = y + 55;
    }

    public void onMove() {
        if (x > 800)
            x = 0;
        x += 5;

    }

}