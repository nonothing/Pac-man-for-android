package com.pacman.view;

import com.pacman.model.MainMenu;
import com.pacman.model.Rectangle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.graphics.Typeface;
import android.view.View;


import static com.pacman.MainActivity.WIDTH_DEVICE;

public class MenuRenderer extends View {
    

    private MainMenu menu;
    private Canvas canvas;
    private Typeface pacmanFont;
    private Typeface emulogicFont;
    private Paint paint;
    
    private Rectangle rectanglePlay;
    private Rectangle rectangleLevel;
    private Rectangle rectangleSound;
    private Rectangle rectangleRate;
    private Rectangle rectangleExit;
   
    public Context context;

    public MenuRenderer(Context context) {
        super(context);
        this.context = context;

        pacmanFont = Typeface.createFromAsset(context.getAssets(),
                "fonts/PacFont.ttf");
        emulogicFont = Typeface.createFromAsset(context.getAssets(),
                "fonts/emulogic.ttf");
        paint = new Paint();
        
        if (WIDTH_DEVICE < 500) {
            rectanglePlay = new Rectangle(0, 110, WIDTH_DEVICE, 40);
            rectangleLevel = new Rectangle(0, 150, WIDTH_DEVICE, 40);
            rectangleSound = new Rectangle(0, 190, WIDTH_DEVICE, 40);
            rectangleRate = new Rectangle(0, 230, WIDTH_DEVICE, 40);
            rectangleExit = new Rectangle(0, 270, WIDTH_DEVICE, 40);
        }
        
        if (WIDTH_DEVICE > 700) {
            rectanglePlay = new Rectangle(0, 170, WIDTH_DEVICE, 40);
            rectangleLevel = new Rectangle(0, 220, WIDTH_DEVICE, 40);
            rectangleSound = new Rectangle(0, 270, WIDTH_DEVICE, 40);
            rectangleRate = new Rectangle(0, 320, WIDTH_DEVICE, 40);
            rectangleExit = new Rectangle(0, 370, WIDTH_DEVICE, 40);
        }
    }

    public void init(MainMenu menu) {
        this.menu = menu;
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        this.canvas = canvas;
   //     Log.v("size", String.valueOf(SIZE));
        drawName(WIDTH_DEVICE);
        drawLabel(menu.getPosition());
        super.onDraw(canvas);
        invalidate();
    }

    private void drawName(int size) {
        canvas.drawColor(Color.BLACK);
     if(size < 500){
        canvas.clipRect(0, 0, getWidth(), getHeight());
        paint.setTextSize(50);
        paint.setColor(Color.YELLOW);
        paint.setTypeface(pacmanFont);
        canvas.drawText("c-",getWidth()/5 + 110, 50, paint);
        paint.setTypeface(emulogicFont);
        canvas.drawText("Pa",getWidth()/5, 60, paint);
        canvas.drawText("Man",getWidth()/5 +  165, 60, paint);
    }
        if(size > 700){
            canvas.clipRect(0, 0, getWidth(), getHeight());
            paint.setTextSize(59);
            paint.setColor(Color.YELLOW);
            paint.setTypeface(pacmanFont);
            canvas.drawText("c-",getWidth()/5 + 185, 100, paint);
            paint.setTypeface(emulogicFont);
            canvas.drawText("Pa",getWidth()/5 + 55, 110, paint);
            canvas.drawText("Man",getWidth()/5 +  250, 110, paint);
        }
    }

    private void drawLabel(int menu) {
        String sound = "";
        paint.setColor(Color.YELLOW);
        paint.setTypeface(emulogicFont);
        paint.setTextSize(23);
        canvas.drawText("Play",getWidth()/3, rectanglePlay.getY(), paint);
        canvas.drawText("Select lvl",getWidth()/3, rectangleLevel.getY(), paint);
        if (this.menu.getSound()) {
            sound = "on";
        } else {
            sound = "off";
        }
        canvas.drawText("Sound " + sound,getWidth()/3, rectangleSound.getY(), paint);
        canvas.drawText("Rate",getWidth()/3, rectangleRate.getY(), paint);
        canvas.drawText("Exit",getWidth()/3, rectangleExit.getY(), paint);

        switch (menu) {
        case 0:
            paint.setColor(Color.RED);
            canvas.drawText("Play",getWidth()/3, rectanglePlay.getY(), paint);
            break;
        case 1:
            paint.setColor(Color.RED);
            canvas.drawText("Select lvl",getWidth()/3, rectangleLevel.getY(), paint);
            break;
        case 2:
            paint.setColor(Color.RED);
            canvas.drawText("Sound " + sound,getWidth()/3, rectangleSound.getY(), paint);
            break;
        case 3:
            paint.setColor(Color.RED);
            canvas.drawText("Rate",getWidth()/3, rectangleRate.getY(), paint);
            break;
        case 4:
            paint.setColor(Color.RED);
            canvas.drawText("Exit",getWidth()/3, rectangleExit.getY(), paint);
            break;
        }
    }

    public Rectangle getRectanglePlay() {
        return rectanglePlay;
    }

    public Rectangle getRectangleLevel() {
        return rectangleLevel;
    }

    public Rectangle getRectangleSound() {
        return rectangleSound;
    }

    public Rectangle getRectangleExit() {
        return rectangleExit;
    }
    public Rectangle getRectangleRate() {
        return rectangleRate;
    }

}
