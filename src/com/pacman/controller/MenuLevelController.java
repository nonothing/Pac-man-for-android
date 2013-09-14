package com.pacman.controller;

import static com.pacman.MainActivity.HEIGTH_DEVICE;
import static com.pacman.MainActivity.WIDTH_DEVICE;

import java.util.ArrayList;

import com.pacman.MainActivity;
import com.pacman.model.LevelMenu;
import com.pacman.model.Point;
import com.pacman.model.Rectangle;
import com.pacman.view.MenuLevelRenderer;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class MenuLevelController implements OnTouchListener {

    private static final int MAX_PAGE = 2;
    private MenuLevelRenderer view;
    private MainActivity activity;
    public Rectangle rectangleTouch;
    public int page;

    public Rectangle rectangleArrowRight;
    public Rectangle rectangleArrowLeft;

    private ArrayList<LevelMenu> levels;

    public MenuLevelController(MainActivity activity) {
        this.activity = activity;
        page = 0;
        rectangleTouch = new Rectangle(0, 0, 10, 10);
        rectangleArrowRight = new Rectangle(WIDTH_DEVICE - 80,
                HEIGTH_DEVICE - 50, 65, 50);
        rectangleArrowLeft = new Rectangle(40, HEIGTH_DEVICE - 50, 65, 50);

        levels = new ArrayList<LevelMenu>();

        levels.add(createLevel(1, 1));
        levels.add(createLevel(7, 1));
        levels.add(createLevel(13, 1));
        levels.add(createLevel(19, 1));

        levels.add(createLevel(1, 7));
        levels.add(createLevel(7, 7));
        levels.add(createLevel(13, 7));
        levels.add(createLevel(19, 7));

        levels.add(createLevel(27, 1));
        levels.add(createLevel(33, 1));
        levels.add(createLevel(39, 1));
        levels.add(createLevel(45, 1));

        levels.add(createLevel(27, 7));
        levels.add(createLevel(33, 7));
        levels.add(createLevel(39, 7));
        levels.add(createLevel(45, 7));

        levels.add(createLevel(53, 1));
        levels.add(createLevel(59, 1));
        levels.add(createLevel(65, 1));
        levels.add(createLevel(71, 1));

        levels.add(createLevel(53, 7));
        levels.add(createLevel(59, 7));
        levels.add(createLevel(65, 7));
        levels.add(createLevel(71, 7));

        view = new MenuLevelRenderer(activity, this);
        view.setOnTouchListener(this);
    }

    private LevelMenu createLevel(int x, int y) {
        return new LevelMenu(new Point(x, y), levels.size(), activity
                .getSettings().get(String.valueOf(levels.size() + 1)));
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int X = (int) event.getX();
        int Y = (int) event.getY();

        int Action = event.getAction();
        switch (Action) {
        case MotionEvent.ACTION_DOWN:
            rectangleTouch = new Rectangle(X, Y, 10, 10);
            if (rectangleArrowRight.intersects(rectangleTouch)
                    && page < MAX_PAGE) {
                page++;
                break;
            }
            if (rectangleArrowLeft.intersects(rectangleTouch) && page > 0) {
                page--;
                break;
            }
            for (LevelMenu obj : levels) {
                if (obj.getBounds().intersects(rectangleTouch)
                        && !obj.getLock()) {
                    if (!activity.getBuilder().checkController()) {
                        activity.loading();
                        activity.startThread();
                        break;
                    } else {
                        activity.loading();
                        activity.getBuilder().createLevel(
                                obj.getLevel() + 8 * page);
                        break;
                    }
                }
            }
            break;
        case MotionEvent.ACTION_MOVE:
            rectangleTouch = new Rectangle(X, Y, 10, 10);
            break;

        }

        return true;
    }

    public MenuLevelRenderer getView() {
        return view;
    }

    public ArrayList<LevelMenu> getLevels() {
        return levels;
    }

}
