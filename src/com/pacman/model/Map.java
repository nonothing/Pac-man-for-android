package com.pacman.model;

import static com.pacman.model.WorldObject.SIZE;

import com.pacman.model.spirit.Spirit;
import com.pacman.view.Texture;

public class Map {
    private static final int WALL = 200;
    private int[][] map;
    private int step;
    private int width;
    private int height;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        map = new int[width][height];
    }

    public void potencialMap(Point point, Spirit spirit, Iterable<? extends WorldObject> object) {
        inverseMap(object);
        int count = 0;
        step = 2;
        
        map[point.getX() / SIZE][point.getY() / SIZE] = 1;

        if (spirit.getState() != State.DEAD) {
            changeMap(spirit);
        }

        while (count < 40) {
            for (int row = 0; row < width; row++) {
                for (int column = 0; column < height; column++) {
                    if (map[row][column] == step - 1) {
                        if (row > 1) {
                            if (map[row - 1][column] == 0) {
                                map[row - 1][column] = step;
                            }
                        }
                        if (row < width - 1) {
                            if (map[row + 1][column] == 0) {
                                map[row + 1][column] = step;
                            }
                        }
                        if (column > 1) {
                            if (map[row][column - 1] == 0) {
                                map[row][column - 1] = step;
                            }
                        }
                        if (column < height - 1) {
                            if (map[row][column + 1] == 0) {
                                map[row][column + 1] = step;
                            }
                        }
                    }
                }
            }
            step++;
            count++;
        }

    }

    private void changeMap(Spirit spirit) {
        if (spirit.getDirection() == Direction.LEFT) {
            map[(spirit.getPointX()) + 1][spirit.getPointY()] = WALL;
        }

        if (spirit.getDirection() == Direction.RIGHT) {
            map[(spirit.getPointX()) - 1][spirit.getPointY()] = WALL;
        }

        if (spirit.getDirection() == Direction.UP) {
            map[spirit.getPointX()][(spirit.getPointY()) + 1] = WALL;
        }

        if (spirit.getDirection() == Direction.DOWN) {
            map[spirit.getPointX()][(spirit.getPointY()) - 1] = WALL;
        }
    }

    private void inverseMap(Iterable<? extends WorldObject> bricks) {
        int row = 0;
        int column = 0;
        for (WorldObject object : bricks) {
            if (object.getTexture() == Texture.background
                    || object.getTexture() == Texture.point
                    || object.getTexture() == Texture.none
                    || object.getTexture() == Texture.bonus) {
                map[row][column] = 0;
            } else {
                map[row][column] = WALL;
            }
            row++;
            if (row == width) {
                row = 0;
                column++;
            }
            if (column == height) {
                column = 0;
            }
        }
    }

    public int[][] getMap() {
        return map;
    }
}
