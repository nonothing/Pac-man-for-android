package com.pacman.view;


import static com.pacman.MainActivity.HEIGTH_DEVICE;
import static com.pacman.MainActivity.WIDTH_DEVICE;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.View;

import com.pacman.R;
import com.pacman.controller.MenuLevelController;
import com.pacman.model.LevelMenu;




public class MenuLevelRenderer extends View{

    private MenuLevelController controller;
    private ImagesMenu image;
    private Paint paintText;
    private Typeface mistralFont;
    private int size;
    
    private Bitmap arrowRight;
    private Bitmap arrowLeft;
    
    public MenuLevelRenderer(Context context, MenuLevelController controller) {
        super(context);
        this.controller = controller;
        
        if(WIDTH_DEVICE < 500){
            size = 100;
        }
        if(WIDTH_DEVICE > 700){
            size = 150;
        }
        
        image = new ImagesMenu(context, size);   
        arrowRight = BitmapFactory.decodeResource(getResources(), R.drawable.arrow_right);
        arrowLeft = BitmapFactory.decodeResource(getResources(), R.drawable.arrow_left);
        
        paintText = new Paint();
        paintText.setARGB(255, 210, 160, 30);
        mistralFont = Typeface.createFromAsset(context.getAssets(),
                "fonts/Mistral.ttf");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        canvas.drawRect(0, 0, getWidth(), getHeight(), new Paint());
        
        for(LevelMenu obj : controller.getLevels()){
            canvas.drawBitmap(image.get(obj), obj.getPosition().getX() - calculateWidth() ,obj.getPosition().getY(), new Paint());
          
            paintText.setTextSize(70);
            paintText.setTypeface(mistralFont);
            if(!obj.getLock()){
                if(size == 150){
                    canvas.drawText(String.valueOf(obj.getLevel()),obj.getPosition().getX() + 70 - calculateWidth(),obj.getPosition().getY()+110, paintText);
                }
                if(size == 100){
                    canvas.drawText(String.valueOf(obj.getLevel()),obj.getPosition().getX() + 35 - calculateWidth(),obj.getPosition().getY()+80, paintText);
                }
            }
        }
        
        
        if(controller.page != 2)
        canvas.drawBitmap(arrowRight, WIDTH_DEVICE - 80,HEIGTH_DEVICE - 50, new Paint());
        if(controller.page != 0)
        canvas.drawBitmap(arrowLeft,   40,HEIGTH_DEVICE - 50, new Paint());
        
        super.onDraw(canvas);
        invalidate();
    }

    private int calculateWidth() {
        if(size == 150){
            return (WIDTH_DEVICE - 20)* controller.page;
        }else return (WIDTH_DEVICE + 40)* controller.page;
    }

}
