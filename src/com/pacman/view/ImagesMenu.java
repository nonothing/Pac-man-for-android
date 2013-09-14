
package com.pacman.view;

import java.util.HashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.pacman.R;
import com.pacman.model.WorldObject;

public class ImagesMenu {
    HashMap<Texture,Bitmap> textures = new HashMap<Texture,Bitmap>();
    
    private int size;
    private Context context;
    
    ImagesMenu(Context context, int size){
         this.size = size;
         this.context = context;
         
        textures.put(Texture.lock,returnBitmap(R.drawable.lock));
        textures.put(Texture.silverStars, returnBitmap(R.drawable.silver_stars));
        textures.put(Texture.oneGoldStar, returnBitmap(R.drawable.one_gold_star));
        textures.put(Texture.twoGoldStars, returnBitmap(R.drawable.two_gold_star));
        textures.put(Texture.threeGoldStarts, returnBitmap(R.drawable.three_gold_star));
       
    }
     
     private Bitmap returnBitmap(int object){
         return Bitmap.createScaledBitmap(  BitmapFactory.decodeResource(context.getResources(), object),size,size,true);
     }


    public Bitmap get(WorldObject object) {
        return textures.get(object.getTexture());
    }
}
