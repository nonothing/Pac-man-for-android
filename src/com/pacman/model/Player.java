package com.pacman.model;

import com.pacman.model.Brick;
import com.pacman.view.Texture;




public class Player extends WorldObjectMove{
    private int life;

	public Player(Point position , Texture texture ) {
	    super(position, texture);
		setState(State.DEFENCE);
		life = 3;
	}
	
    public boolean eatPoint(Iterable<Brick> bricks) {
        for (Brick brick : bricks) {
            if(brick.tryToEat(bounds))
                return true;
        }
        return false;
    }
    
    public boolean eatBonus(Iterable<Brick> bricks) { 
        for (Brick brick : bricks) {
            if(brick.tryToBonus(bounds)){
                state = State.ATTACK;
                return true;
                }
        }
        return false;
    }
    
    public void animate() {
        boolean isOpen = 
                (getPosition().getX() % 15 == 0 && (direction == Direction.LEFT || direction == Direction.RIGHT))
                || (getPosition().getY() % 15 == 0 && (direction == Direction.UP || direction == Direction.DOWN));

        if (direction == Direction.LEFT) {
            if (isOpen) {
                setTexture(Texture.pacmanLeftOpen);
            } else {
                setTexture(Texture.pacmanLeftClose);
            }
        }
        if (direction == Direction.RIGHT) {
            if (isOpen) {
                setTexture(Texture.pacmanRightOpen);
            } else {
                setTexture(Texture.pacmanRightClose);
            }
        }
        if (direction == Direction.UP) {
            if (isOpen) {
                setTexture(Texture.pacmanUpOpen);
            } else {
                setTexture(Texture.pacmanUpClose);
            }
        }
        if (direction == Direction.DOWN) {
            if (isOpen) {
                setTexture(Texture.pacmanDownOpen);
            } else {
                setTexture(Texture.pacmanDownClose);
            }
        }
    }
    
    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

}