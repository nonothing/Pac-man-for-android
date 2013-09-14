package com.pacman.view;



import com.pacman.model.Brick;
import com.pacman.model.Rectangle;
import com.pacman.model.World;
import com.pacman.model.WorldObject;
import com.pacman.model.spirit.Spirit;
import com.pacman.view.Images;

import android.content.Context;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.graphics.Typeface;
import android.view.View;


import static com.pacman.model.WorldObject.SIZE;

public class WorldRenderer extends View {
    public Context context;
    private World world;
    private Images images;
    private Canvas canvas;
    private Paint paint;
    private Paint paintRect;
    private Typeface emulogicFont;
    private Rectangle rectangleYes;
    private Rectangle rectangleNo;
    private   int HEIGHT ;
    private   int WIDTH ;
    
    public WorldRenderer(Context context) {
        super(context);
        this.context = context;
        images = new Images(context, SIZE);
        emulogicFont = Typeface.createFromAsset(context.getAssets(),
                "fonts/emulogic.ttf");
                      
        paint = new Paint();
        paintRect = new Paint();
        paintRect.setColor(Color.YELLOW);
        paintRect.setStyle(Paint.Style.STROKE);
        paintRect.setStrokeWidth(5);
        
        if(SIZE == 20){
                HEIGHT = 340;//500
                WIDTH = 220;//760
                rectangleNo = new Rectangle((WIDTH/2)+200,(HEIGHT/3)+50,(WIDTH/2)+300,(HEIGHT/3)+150);
                rectangleYes = new Rectangle((WIDTH/2),(HEIGHT/3)+50,(WIDTH/2)+100,(HEIGHT/3)+150);
        }
        if(SIZE == 30){
                HEIGHT = 500;
                WIDTH = 760;
                rectangleNo = new Rectangle((WIDTH/3)+200,(HEIGHT/3)+50,(WIDTH/3)+300,(HEIGHT/3)+150);
                rectangleYes = new Rectangle((WIDTH / 3), (HEIGHT / 3) + 50,(WIDTH / 3) + 100, (HEIGHT / 3) + 150);
        }
    }
    
    public void init(World world){
        this.world = world;
        
    }

    private void drawObj(WorldObject object) {
        object.animate();
        canvas.drawBitmap(images.get(object), object.getPosition().getX(),
                object.getPosition().getY(), new Paint());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        this.canvas = canvas;
        canvas.drawColor(Color.BLACK);
        canvas.drawRect(0, 0, getWidth(), getHeight(), new Paint());
      
        for (Brick brick : world.getBricks()) {
            drawObj(brick);
        }

        drawObj(world.getFruit());
        for (Spirit spirit : world.getSpirits()) {
            drawObj(spirit);
        }

            drawObj(world.getPlayer());

          drawVictory();
          drawGameOver();
        
          drawRecordScore(); 
          drawScore();
        super.onDraw(canvas);
        
        invalidate();
    }



    private void drawRecordScore() {
        paint.setColor(Color.YELLOW);
        paint.setTypeface(emulogicFont);
        if (SIZE == 30) {
            paint.setTextSize(16);
        }
        if (SIZE == 20) {
            paint.setTextSize(12);
        }

        canvas.drawText("Record: " + String.valueOf(world.getRecord()), 50, 20,
                paint);
    }

    private void drawScore() {
        paint.setColor(Color.YELLOW);
        paint.setTypeface(emulogicFont);
        if (SIZE == 30) {
            paint.setTextSize(16);
        }
        if (SIZE == 20) {
            paint.setTextSize(12);
        }
        canvas.drawText("You: " + String.valueOf(world.getScore()), (WIDTH/2)+100, 20,
                paint);
    }
     
    private void drawVictory() {
        if (world.isVictory()) {
            if (SIZE == 20) {
                paint.setColor(Color.YELLOW);
                paint.setTypeface(emulogicFont);
                paint.setTextSize(48);
                canvas.drawText("You win!", WIDTH / 6, HEIGHT / 4, paint);
                paint.setTextSize(25);
                canvas.drawText("next level?", WIDTH / 2, HEIGHT / 3, paint);

                paint.setTextSize(72);
                canvas.drawRect((WIDTH / 2) + 200, (HEIGHT / 3) + 50,
                        (WIDTH / 2) + 300, (HEIGHT / 3) + 150, paintRect);
                canvas.drawText("N", (WIDTH / 2) + 210, (HEIGHT / 3) + 125,
                        paint);

                canvas.drawRect((WIDTH / 2), (HEIGHT / 3) + 50,
                        (WIDTH / 2) + 100, (HEIGHT / 3) + 150, paintRect);
                canvas.drawText("Y", (WIDTH / 2) + 10, (HEIGHT / 3) + 125,
                        paint);
            }
            if (SIZE == 30) {
                paint.setColor(Color.YELLOW);
                paint.setTypeface(emulogicFont);
                paint.setTextSize(48);
                canvas.drawText("You win!", WIDTH / 6, HEIGHT / 4, paint);
                paint.setTextSize(25);
                canvas.drawText("next level?", WIDTH / 4, HEIGHT / 3, paint);

                paint.setTextSize(72);
                canvas.drawRect((WIDTH / 3) + 200, (HEIGHT / 3) + 50, (WIDTH / 3) + 300, (HEIGHT / 3) + 150, paintRect);
                canvas.drawText("N", (WIDTH / 3) + 210, (HEIGHT / 3) + 125,paint);

                canvas.drawRect((WIDTH / 3), (HEIGHT / 3) + 50,(WIDTH / 3) + 100, (HEIGHT / 3) + 150, paintRect);
                canvas.drawText("Y", (WIDTH / 3) + 10, (HEIGHT / 3) + 125,
                        paint);
            }
        }
    }
        
    private void drawGameOver() {
        if (world.isGameOver()) {
            if (SIZE == 20) {
                paint.setColor(Color.YELLOW);
                paint.setTypeface(emulogicFont);
                paint.setTextSize(48);
                canvas.drawText("Game Over!", WIDTH / 6, HEIGHT / 4, paint);
                paint.setTextSize(25);
                canvas.drawText("play again?", WIDTH / 2, HEIGHT / 3, paint);

                paint.setTextSize(72);
                canvas.drawRect((WIDTH / 2) + 200, (HEIGHT / 3) + 50,
                        (WIDTH / 2) + 300, (HEIGHT / 3) + 150, paintRect);
                canvas.drawText("N", (WIDTH / 2) + 210, (HEIGHT / 3) + 125,
                        paint);

                canvas.drawRect((WIDTH / 2), (HEIGHT / 3) + 50,
                        (WIDTH / 2) + 100, (HEIGHT / 3) + 150, paintRect);
                canvas.drawText("Y", (WIDTH / 2) + 10, (HEIGHT / 3) + 125,
                        paint);
            }
            if (SIZE == 30) {
                paint.setColor(Color.YELLOW);
                paint.setTypeface(emulogicFont);
                paint.setTextSize(48);
                canvas.drawText("Game Over!", WIDTH / 6, HEIGHT / 4, paint);
                paint.setTextSize(25);
                canvas.drawText("play again?", WIDTH / 4, HEIGHT / 3, paint);

                paint.setTextSize(72);
                canvas.drawRect((WIDTH / 3) + 200, (HEIGHT / 3) + 50, (WIDTH / 3) + 300, (HEIGHT / 3) + 150, paintRect);
                canvas.drawText("N", (WIDTH / 3) + 210, (HEIGHT / 3) + 125,paint);

                canvas.drawRect((WIDTH / 3), (HEIGHT / 3) + 50,(WIDTH / 3) + 100, (HEIGHT / 3) + 150, paintRect);
                canvas.drawText("Y", (WIDTH / 3) + 10, (HEIGHT / 3) + 125,
                        paint);
            }
        }
    }

    public Rectangle getRectangleYes() {
        return rectangleYes;
    }

    public Rectangle getRectangleNo() {
        return rectangleNo;
    }

}
