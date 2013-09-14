package com.pacman.controller;

import java.util.Timer;
import java.util.TimerTask;

import com.pacman.MainActivity;
import com.pacman.model.Direction;
import com.pacman.model.GameLogic;
import com.pacman.model.LevelReader;
import com.pacman.model.Rectangle;
import com.pacman.model.State;
import com.pacman.model.World;
import com.pacman.view.WorldRenderer;


import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;



public class WorldController implements OnTouchListener {

    private Direction direction;
    public WorldRenderer view;
    private World world;
    private GameLogic logic;
    private Timer mainTimer;
    private SoundController soundController;
    private MainActivity mainActivity;

    public WorldController(MainActivity mainActivity, World world, WorldRenderer view) {
        direction = world.getPlayer().getDirection();
        this.world = world;
        this.view = view;
        logic = new GameLogic(world);
        view.init(world);
        this.mainActivity = mainActivity;

        this.mainTimer = new Timer();
        mainTimer.schedule(timerMain, 1, 50);
        soundController = new SoundController(view.context, false);
        logic.setPause(true);
        view.setOnTouchListener(this);
    }

    public void startGame() {
        onResume();
        world.getPlayer().setLife(3);
        world.generationPoint();
        
        if(world.getScore() > world.getRecord())
            world.setRecord(world.getScore());
        
        world.setScore(0);
        if (soundController.getSound()) {
            soundController.playBackground();
        }

        if (world.isVictory()) {
            world.newGame();
        }
    }

    public void onPause() {
        soundController.stop();
        logic.setPause(true);
    }

    public void onResume() {
        logic.setPause(false);
    }

    public void setSound(boolean isSound) {
        soundController.setSound(isSound);
    }

    private void newGame() {
        world.createSpirit();
        world.startPointPlayer();
        logic.startSpirit();
    }

    TimerTask timerMain = new TimerTask() {
        public void run() {
            actionPerformed();
        }
    };

    private void actionPerformed() {

        if (!logic.getPause()) {
            world.tryToPlayerGo(direction);

            soundController.play(world);
        }

        if (world.getPlayer().getState() == State.DEAD) {
            newGame();
        }

        
        if (world.isGameOver() || world.isVictory()) {
            onPause();
        }

    }

    private float x = 0;
    private float y = 0;

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            x = event.getX();
            y = event.getY();
            if(logic.getPause()){
 
                if(view.getRectangleNo().intersects(new Rectangle((int)x,(int) y, 10, 10))){
                    setScore(world.getScore());
                    mainActivity.onBackPressed();
                    Log.v("world controll", "start N");
                   }
                else if(view.getRectangleYes().intersects(new Rectangle((int)x,(int) y, 10, 10))){
                            if(world.isGameOver()){
                               setScore(world.getScore());
                               startGame();
                               Log.v("world controll", "start Y");
                           }   
                    if(world.isVictory()){
                        setScore(world.getScore());
                        openNextLevel();
                        mainActivity.getBuilder().nextLevel();
                    }
                }
            }
        }

        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            if (x > event.getX() && Math.abs(x - event.getX()) > 20
                    && Math.abs(y - event.getY()) < 60)
                direction = Direction.LEFT;
            if (x < event.getX() && Math.abs(x - event.getX()) > 20
                    && Math.abs(y - event.getY()) < 60)
                direction = Direction.RIGHT;
            if (y > event.getY() && Math.abs(y - event.getY()) > 20
                    && Math.abs(x - event.getX()) < 60)
                direction = Direction.UP;
            if (y < event.getY() && Math.abs(y - event.getY()) > 20
                    && Math.abs(x - event.getX()) < 60)
                direction = Direction.DOWN;
        }

        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (x > event.getX() && Math.abs(y - event.getY()) < 60)
                direction = Direction.LEFT;
            if (x < event.getX() && Math.abs(y - event.getY()) < 60)
                direction = Direction.RIGHT;
            if (y > event.getY() && Math.abs(x - event.getX()) < 60)
                direction = Direction.UP;
            if (y < event.getY() && Math.abs(x - event.getX()) < 60)
                direction = Direction.DOWN;
        }
        
        
        return true;
    }

    public void setScore(int score) {
        if (world.getScore() > world.getRecord()) {
            mainActivity.getSettings().setConfig(

            String.valueOf(mainActivity.getSettings().get("last")), score);
        }
    }
    public void openNextLevel() {
        if (world.getScore() > world.getRecord()) {
            mainActivity.getSettings().setConfig(
                    
                    String.valueOf(mainActivity.getSettings().get("last")+1), 1);
        }
    }

    public void nextLevel(int level, int record){
        world.nextLevel(new LevelReader(mainActivity.getResources().openRawResource(level)),record);
       
    }
}
