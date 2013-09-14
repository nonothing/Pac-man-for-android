package com.pacman.model;

import com.pacman.model.Rectangle;
import com.pacman.view.Texture;



import static com.pacman.MainActivity.WIDTH_DEVICE;

public abstract class WorldObject {

    public static int SIZE;

    private Texture texture;

    protected Point position;
    protected Rectangle bounds;

    public WorldObject(Point point, Texture texture) {
//??
        if(WIDTH_DEVICE > 700) SIZE =30;
        else 
            SIZE = 20;
        
        
        position = new Point(point.getX(), point.getY(), SIZE);
        bounds = new Rectangle(position.getX(), position.getY(), SIZE, SIZE);

        this.texture = texture;
    }

    public int getSize() {
        return SIZE;
    }

    public Point getPosition() {
        return position;
    }

    public Texture getTexture() {
        return texture;
    }

    public Rectangle getBounds() {
        return bounds;
    }
    
    public void setBounds(int x, int y, int size){
        bounds = new Rectangle(x, y, size, size);
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void animate() {
        // do nothing
    }

    public int inverse(int count) {
        return (-1) * count;
    }
}
