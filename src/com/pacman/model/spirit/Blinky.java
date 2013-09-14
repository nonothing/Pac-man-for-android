package com.pacman.model.spirit;

import com.pacman.model.Point;
import com.pacman.model.World;
import com.pacman.view.Texture;

public class Blinky extends Spirit {

    private static Point START_POINT;
    private static final Point DEFENCE_POINT = new Point(21, 1);

    public Blinky(Point point) {
        super(point, Texture.blinkyUp);
        START_POINT = new Point(point.getX(), point.getY());
    }

    @Override
    public void ai(World world) {
        switch (getState()) {
        case ATTACK:
            findDirection(world, world.getPlayer().getPosition(), this);
            break;
        case DEFENCE:
            findDirection(world, DEFENCE_POINT.multiply(getSize()), this);
            break;
        case DEAD:
            findDirection(world, START_POINT.multiply(getSize())
                    , this);
            break;
        }

        onMove(world);
    }

    @Override
    public Texture left() {
        return Texture.blinkyLeft;
    }

    @Override
    public Texture right() {
        return Texture.blinkyRight;
    }

    @Override
    public Texture down() {
        return Texture.blinkyDown;
    }

    @Override
    public Texture up() {
        return Texture.blinkyUp;
    }

}
