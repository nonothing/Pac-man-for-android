package com.pacman.model;

import com.pacman.view.Texture;


public class Brick extends WorldObject{
    public Brick(Point position, Texture texture) {
        super(position, texture);
        
    }
    
    boolean tryToEat(Rectangle rectangle) {
        if (bounds.intersects(rectangle) && getTexture() == Texture.point) {
            setTexture(Texture.background);
            return true;
        }
        return false;
    }
    
    boolean tryToBonus(Rectangle rectangle) {
        if (bounds.intersects(rectangle) && getTexture() == Texture.bonus) {
            setTexture(Texture.background);
            return true;
        }
        return false;
    }
}