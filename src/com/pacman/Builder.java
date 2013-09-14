package com.pacman;

import android.content.Intent;
import android.net.Uri;
import android.widget.RelativeLayout;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.pacman.R;
import com.pacman.controller.MenuController;
import com.pacman.controller.MenuLevelController;
import com.pacman.controller.SoundController;
import com.pacman.controller.WorldController;
import com.pacman.model.LevelReader;
import com.pacman.model.MainMenu;
import com.pacman.model.StateGame;
import com.pacman.model.World;
import com.pacman.view.Loading;
import com.pacman.view.MenuRenderer;
import com.pacman.view.WorldRenderer;

public class Builder {

    private static Loading loading;
    private static SoundController soundController;
    private MainActivity activity;
    private final int MAX_LEVEL = 25;
    private boolean isCreate;

    public Builder(MainActivity activity) {
        this.activity = activity;
        soundController = new SoundController(activity, false);
        isCreate = false;
        loading = new Loading(activity);
        rlayout = new RelativeLayout(activity);
    }

    private boolean requestBool = false;
    private RelativeLayout rlayout;

    @SuppressWarnings("deprecation")
    public void loadingAndReclame() {
        activity.setContentView(rlayout);
        if (!requestBool) {
            rlayout.addView(loading, 0);

            AdView adView = new AdView(activity, AdSize.BANNER,
                    "myID");
            rlayout.addView(adView, 1);
            AdRequest request = new AdRequest();
            request.setTesting(true);

            adView.loadAd(request);
            requestBool = true;
        }
    }

    public void onSound(boolean isSound) {
        activity.isSound(isSound);
        soundController.setSound(isSound);
    }

    public boolean checkController() {
        if (activity.getController() == null)
            return false;
        return true;
    }


    public void showMyGame() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri
                .parse("http://play.google.com/store/apps/details?id=myPackageName"));
        activity.startActivity(intent);
    }

    public void setLevel(int level) {
        activity.setLevel(R.raw.lvl_01 + level - 1);
        activity.getSettings().setConfig("last", level);
    }

    public void createLevel(int level) {
        if (level >= MAX_LEVEL)
            level = 1;
        activity.setLevel(R.raw.lvl_01 + level - 1);
        activity.getSettings().setConfig("last", level);
        isCreate = true;
    }

    public void nextLevel() {
        activity.loading();
        createLevel(activity.getLevel() - R.raw.lvl_01 + 2);
    }

    public void createMenu() {
        if (activity.getMenuController() == null) {
            activity.setMenuController(new MenuController(new MainMenu(),
                    new MenuRenderer(activity), activity));

        }

    }

    public void showMenu() {
        activity.setContentView(activity.getMenuController().getView());
    }

    public void showLevels() {
        activity.setStateGame(StateGame.LEVEL_MENU);
        activity.setContentView(new MenuLevelController(activity).getView());
    }

    public void createController() {
        if (activity.getController() == null) {
            activity.setController(new WorldController(activity,
                    new World(new LevelReader(activity.getResources()
                            .openRawResource(activity.getLevel())), activity
                            .getSettings().get(
                                    String.valueOf(activity.getSettings().get(
                                            "last")))), new WorldRenderer(
                            activity)));

        }
    }

    public boolean isCreate() {
        return isCreate;
    }

    public void isCreate(boolean is) {
        isCreate = is;
    }

    public SoundController getSoundController() {
        return soundController;
    }

}
