package com.pacman.model;

import com.pacman.view.Texture;

import static com.pacman.MainActivity.WIDTH_DEVICE;

public class LevelMenu extends WorldObject{

    private int size;
    private int level;
    private int score;
    @SuppressWarnings("unused")
    private boolean lock;
    public LevelMenu(Point point,int level, int score) {
        super(point, Texture.lock);
        
        if(WIDTH_DEVICE < 500){
            size = 100;
        }
        if(WIDTH_DEVICE > 700){
            size = 150;
        }
        setBounds(point.getX() * getSize(), point.getY() * getSize(), size);
        this.level = level+1;
        this.score = score;
        
        if(score > 10000) setTexture(Texture.threeGoldStarts);
        if(score < 10000) setTexture(Texture.twoGoldStars);
        if(score < 1000) setTexture(Texture.oneGoldStar);
        if(score < 100) setTexture(Texture.silverStars);
        if(score == 0) setTexture(Texture.lock);
        
    }
    
    public int getLevel(){
        return level;
    }
    
    public boolean getLock(){
        if(score == 0) return true;
        else return false;
    }
    
    public int getRecord(){
        return score;
    }
    
    public void setScore(int record){
        score = record;
    }

}
