package com.pacman.model.spirit;

import com.pacman.model.Direction;
import com.pacman.model.Point;
import com.pacman.model.World;
import com.pacman.view.Texture;


public class Pinky extends Spirit {

    private static Point START_POINT;
    private static final Point DEFENCE_POINT = new Point(1, 2);

    public Pinky(Point point) {
        super(point, Texture.pinkyLeft);
        START_POINT = new Point(point.getX(), point.getY());
    }

    @Override
    public void ai(World world) {
        switch (getState()) {
        case ATTACK:
            findDirection(world, findPathFourStep(world), this);
            break;
        case DEFENCE:
            findDirection(world, DEFENCE_POINT.multiply(getSize()), this);
            break;
        case DEAD:
            findDirection(world, START_POINT.multiply(getSize()), this);
            break;
        }

        onMove(world);
    }

    private Point findPathFourStep(World world) {
        maps.potencialMap(world.getPlayer().getPosition(), this, world.getBricks());
        
        Point point = new Point(0,0);
        int[][] map = maps.getMap();
        int min = 0;
        int max = 999;

        for (int row = 0; row < world.getWidth(); row++) {
            for (int column = 0; column < world.getHeight(); column++) {
                if (map[row][column] == 5) {

                    if (world.getPlayer().getDirection() == Direction.LEFT) {
                        if (max > row) {
                            max = row;
                            point = new Point(row, column,getSize());
                        }
                    }

                    if (world.getPlayer().getDirection() == Direction.RIGHT) {
                        if (min < row) {
                            min = row;
                            point = new Point(row, column,getSize());
                        }
                    }

                    if (world.getPlayer().getDirection() == Direction.UP) {
                        if (max > column) {
                            max = column;
                            point = new Point(row, column,getSize());
                        }
                    }

                    if (world.getPlayer().getDirection() == Direction.DOWN) {
                        if (min < column) {
                            min = column;
                            point = new Point(row, column,getSize());
                        }
                    }

                }
            }
        }

        return point;
    }

    @Override
    public Texture left() {
        return Texture.pinkyLeft;
    }

    @Override
    public Texture right() {
        return Texture.pinkyRight;
    }

    @Override
    public Texture down() {
        return Texture.pinkyDown;
    }

    @Override
    public Texture up() {
        return Texture.pinkyUp;
    }

}
