package com.pacman.model;

public class MainMenu {

    private static final int POSITION_MAX = 5;
    private int position;
    private boolean isSound;

    public MainMenu() {
        position = 1;
    }

    public void onDown() {
        position++;
        if (position > POSITION_MAX) {
            position = 0;
        }
    }

    public void onUp() {
        position--;
        if (position < 0) {
            position = POSITION_MAX;
        }
    }

    public int getPosition() {
        return position;
    }
    
    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isSound() {
        if (isSound) {
            return isSound = false;
        } else {
            return isSound = true;
        }
    }

    public boolean getSound() {
        return isSound;
    }
}
