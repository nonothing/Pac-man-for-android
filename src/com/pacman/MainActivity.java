package com.pacman;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

import com.pacman.R;
import com.pacman.controller.MenuController;
import com.pacman.controller.WorldController;
import com.pacman.model.Settings;
import com.pacman.model.StateGame;

public class MainActivity extends Activity implements Runnable {

    private static MenuController menuController;
    private static WorldController controller;

    private static Builder builder;
    private static Settings settings;

    public static int WIDTH_DEVICE;
    public static int HEIGTH_DEVICE;

    private Thread thread;
    
    private Timer mainTimer;

    private int level;
    private StateGame stateGame;
    private boolean isSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initSetting();
        builder = new Builder(this);

        thread = new Thread(this);
        level = R.raw.lvl_01 + settings.get("last") - 1;
        initDisplay();
        builder.createMenu();

        initTimer();

        builder.showMenu();
    }

    public void startThread() {
        thread.start();
    }

    private boolean one = true;
    TimerTask timerMain = new TimerTask() {
        public void run() {
            if (one && getFlag()) {
                startGame();
                one = false;
            }

        }
    };

    TimerTask timerBuilder = new TimerTask() {
        public void run() {
            if (builder.isCreate()) {
                controller.nextLevel(level, settings.get("last"));
                unlockPause();
                builder.isCreate(false);
            }
        }
    };

    public void startGame() {
        stateGame = StateGame.GAME;
        controller.setSound(isSound);
        controller.startGame();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                setContentView(controller.view);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (stateGame == StateGame.MENU) {
            moveTaskToBack(true);
            finish();
            System.runFinalizersOnExit(true);
            System.exit(0);
        } else if (stateGame == StateGame.GAME) {
            stateGame = StateGame.MENU;
            controller.onPause();
            builder.showMenu();
        } else if (stateGame == StateGame.LEVEL_MENU) {
            stateGame = StateGame.MENU;
            builder.showMenu();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        System.runFinalizersOnExit(true);
        System.exit(0);
    }

    public void loading() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                builder.loadingAndReclame();
            }
        });
    }

    @Override
    public void run() {
        builder.createController();
        return;
    }

    public void unlockPause() {
        stateGame = StateGame.GAME;
        controller.setSound(isSound);
        controller.onResume();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setContentView(controller.view);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initTimer() {
        this.mainTimer = new Timer();
        mainTimer.schedule(timerMain, 1, 500);
        mainTimer.schedule(timerBuilder, 1, 100);
    }

    private void initSetting() {
        settings = new Settings(this);
        if (settings.get("1") == 0)
            settings.setConfig("1", 1);
        if (settings.get("last") == 0)
            settings.setConfig("last", 1);
    }
    
    public void refreshSetting(){
        settings = new Settings(this);
    }

    private void initDisplay() {
        Display display = getWindowManager().getDefaultDisplay();
        WIDTH_DEVICE = display.getWidth();
        HEIGTH_DEVICE = display.getHeight();
    }

    public WorldController getController() {
        return controller;
    }

    public void setController(WorldController worldController) {
        controller = worldController;
    }

    public MenuController getMenuController() {
        return menuController;
    }

    public void setMenuController(MenuController controller) {
        menuController = controller;
    }

    public Builder getBuilder() {
        return builder;
    }

    public Settings getSettings() {
        return settings;
    }

    public boolean getFlag() {
        if (controller != null)
            return true;
        return false;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int lvl) {
        level = lvl;
    }

    public StateGame getStateGame() {
        return stateGame;
    }

    public void setStateGame(StateGame state) {
        stateGame = state;
    }

    public void isSound(boolean is) {
        isSound = is;
    }

    public boolean isSound() {
        return isSound;
    }

}
