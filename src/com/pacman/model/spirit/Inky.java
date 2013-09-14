package com.pacman.model.spirit;


import com.pacman.model.Direction;
import com.pacman.model.Point;
import com.pacman.model.World;
import com.pacman.view.Texture;


public class Inky extends Spirit {
    private static final int Y = 1;
    private static final int X = 0;
    private static  Point START_POINT ;
    private static final Point DEFENCE_POINT = new Point(21, 13);
    
    public Inky(Point point) {
       super(point, Texture.inkyRight);
       START_POINT = new Point(point.getX(), point.getY());
    }

    @Override
    public void ai(World world) {
        switch (getState()) {
        case ATTACK:
            findDirection(world, doubleVectorBetweenTwoPoints(findPathTwoStep(world), world.getSpirits().get(0).getPosition() ), this);
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

    private Point doubleVectorBetweenTwoPoints(Point point1, Point point2) {

        int[] aMatrix = { point2.getX() / getSize(), point2.getY() / getSize(), 1 };
        int[][] bMatrix = { { -1, 0, 0 }, { 0, -1, 0 },
                { 2 * (point1.getX() / getSize()), 2 * (point1.getY() / getSize()), 1 } };

        int[] point = new int[3];

        for (int column = 0; column < 3; column++) {
            for (int inner = 0; inner < 3; inner++) {
                point[column] += aMatrix[inner] * bMatrix[inner][column];
            }

        }

        if (point[X] >= 25) {
            point[X] = 24;
        }
        if (point[X] < 1) {
            point[X] = 1;
        }
        if (point[Y] >= 14) {
            point[Y] = 13;
        }
        if (point[Y] < 1) {
            point[Y] = 1;
        }

        return new Point(point[X], point[Y],getSize());
    }


    private Point findPathTwoStep(World world) {
        maps.potencialMap(world.getPlayer().getPosition(), this, world.getBricks());
        
        int[][] map =maps.getMap();
        int min = 0;
        int max = 999;

        Point point = new Point(0,0);
        for (int row = 0; row < world.getWidth(); row++) {
            for (int column = 0; column < world.getHeight(); column++) {
                if (map[row][column] == 3) {

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
        return Texture.inkyLeft;
    }

    @Override
    public Texture right() {
        return Texture.inkyRight;
    }

    @Override
    public Texture down() {
        return Texture.inkyDown;
    }

    @Override
    public Texture up() {
        return Texture.inkyUp;
    }

}
