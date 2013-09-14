package com.pacman.model;

import com.pacman.view.Texture;

public abstract class WorldObjectMove extends WorldObject{
    protected Direction direction;
    protected static final int SPEED = 5;
    protected State state;
    
    public WorldObjectMove(Point point, Texture texture) {
        super(point, texture);
        direction = Direction.UP;
    }
    
    public void onMove(Direction direction) {
        switch (direction) {
        case RIGHT:setNext(SPEED, 0);         break;
        case LEFT: setNext(inverse(SPEED), 0);break;
        case UP:   setNext(0, inverse(SPEED));break;
        case DOWN: setNext(0, SPEED);         break;
        }
    }
    
    public int getSpeed(){
        return SPEED;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
 
    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    
    public void setPosition(Rectangle rect) {
        this.position = new Point(rect);
        bounds = rect;
    }

    public void setNext(int speedX, int speedY) {
        this.bounds = new Rectangle(position.getX() + speedX, position.getY()
                + speedY, getSize(), getSize());
    }
    
    public int getPointX(){
        return position.getX() / getSize();
    }
    
    public int getPointY(){
        return position.getY() / getSize();
    }
}
