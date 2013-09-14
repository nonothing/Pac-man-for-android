package com.pacman.view;

import java.util.HashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.pacman.R;
import com.pacman.model.WorldObject;

public class Images {
    HashMap<Texture,Bitmap> textures = new HashMap<Texture,Bitmap>();
    
    private int size;
    private Context context;
    
     Images(Context context, int size){
         this.size = size;
         this.context = context;
         
         textures.put(Texture.angleLeftDown,returnBitmap(R.drawable.angle_ld));
         textures.put(Texture.angleLeftUp,returnBitmap(R.drawable.angle_lv));
         textures.put(Texture.angleRightDown,returnBitmap(R.drawable.angle_rd));
         textures.put(Texture.angleRightUp,returnBitmap(R.drawable.angle_rv));
         
         textures.put(Texture.point,returnBitmap( R.drawable.point));
         textures.put(Texture.horizontal,returnBitmap(R.drawable.horizontal));
         textures.put(Texture.vertical,returnBitmap( R.drawable.vertical));
         textures.put(Texture.background,returnBitmap( R.drawable.background));
         
         textures.put(Texture.arcDown,returnBitmap( R.drawable.arc_down));
         textures.put(Texture.arcUp,returnBitmap(R.drawable.arc_up));
         textures.put(Texture.arcLeft,returnBitmap(R.drawable.arc_left));
         textures.put(Texture.arcRight,returnBitmap( R.drawable.arc_right));

         textures.put(Texture.arc2Down,returnBitmap( R.drawable.arc2_down));
         textures.put(Texture.arc2Up,returnBitmap( R.drawable.arc2_up));
         textures.put(Texture.arc2Left, returnBitmap( R.drawable.arc2_left));
         textures.put(Texture.arc2Right,returnBitmap(R.drawable.arc2_right));
         
         textures.put(Texture.pacmanLeftOpen,returnBitmap( R.drawable.pman_3_2));
         textures.put(Texture.pacmanRightOpen,returnBitmap( R.drawable.pman_4_2));
         textures.put(Texture.pacmanDownOpen,returnBitmap( R.drawable.pman_2_2));
         textures.put(Texture.pacmanUpOpen, returnBitmap( R.drawable.pman_1_2));
         
         textures.put(Texture.pacmanLeftClose,returnBitmap( R.drawable.pman_3));
         textures.put(Texture.pacmanRightClose,returnBitmap( R.drawable.pman_4));
         textures.put(Texture.pacmanDownClose,returnBitmap(R.drawable.pman_2));
         textures.put(Texture.pacmanUpClose,returnBitmap( R.drawable.pman_1));
         
         textures.put(Texture.point, returnBitmap( R.drawable.point));
         textures.put(Texture.bonus,returnBitmap(R.drawable.bonus));
         textures.put(Texture.spiritDefence,returnBitmap( R.drawable.spirit_defence));
         textures.put(Texture.spiritDefenceWhite,returnBitmap(R.drawable.spirit_defence_2));
         textures.put(Texture.none,returnBitmap(R.drawable.background));
         
         textures.put(Texture.blinkyDown,returnBitmap(R.drawable.blinky_2));
         textures.put(Texture.blinkyLeft,returnBitmap(R.drawable.blinky_3));
         textures.put(Texture.blinkyRight,returnBitmap(R.drawable.blinky_4));
         textures.put(Texture.blinkyUp,returnBitmap(R.drawable.blinky_1));
         
         textures.put(Texture.pinkyDown,returnBitmap(R.drawable.pinky_2));
         textures.put(Texture.pinkyLeft,returnBitmap(R.drawable.pinky_3));
         textures.put(Texture.pinkyRight,returnBitmap(R.drawable.pinky_4));
         textures.put(Texture.pinkyUp,returnBitmap(R.drawable.pinky_1));
         
         textures.put(Texture.clydeDown,returnBitmap(R.drawable.clyde_2));
         textures.put(Texture.clydeLeft,returnBitmap(R.drawable.clyde_3));
         textures.put(Texture.clydeRight,returnBitmap(R.drawable.clyde_4));
         textures.put(Texture.clydeUp,returnBitmap(R.drawable.clyde_1));
         
         textures.put(Texture.inkyDown,returnBitmap(R.drawable.inky_2));
         textures.put(Texture.inkyLeft,returnBitmap(R.drawable.inky_3));
         textures.put(Texture.inkyRight,returnBitmap(R.drawable.inky_4));
         textures.put(Texture.inkyUp,returnBitmap(R.drawable.inky_1));
         
         textures.put(Texture.orbDown,returnBitmap(R.drawable.orb_2));
         textures.put(Texture.orbLeft,returnBitmap(R.drawable.orb_3));
         textures.put(Texture.orbRight,returnBitmap(R.drawable.orb_4));
         textures.put(Texture.orbUp,returnBitmap(R.drawable.orb_1));
         
         textures.put(Texture.apple_green,returnBitmap(R.drawable.apple));
         textures.put(Texture.apple_red,returnBitmap(R.drawable.apple_red));
         textures.put(Texture.cocos,returnBitmap(R.drawable.cocos));
         textures.put(Texture.orange,returnBitmap(R.drawable.orange));
         textures.put(Texture.vinograd,returnBitmap(R.drawable.vinograd));
        
    }
     
     private Bitmap returnBitmap(int object){
         return Bitmap.createScaledBitmap(  BitmapFactory.decodeResource(context.getResources(), object),size,size,true);
     }

    
     
    public Bitmap get(WorldObject object) {
       
        
        return textures.get(object.getTexture());
    }
}
