package com.pacman.controller;

import com.pacman.MainActivity;
import com.pacman.model.MainMenu;
import com.pacman.model.Rectangle;
import com.pacman.view.MenuRenderer;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class MenuController implements OnTouchListener {

    private MenuRenderer view;
    private MainMenu menu;
    private SoundController soundController;
    private  MainActivity mainActivity;
    private boolean isSound;
    

    public MenuController(MainMenu menu, MenuRenderer view,  MainActivity mainActivity) {
        soundController = new SoundController(view.context,false);
        this.mainActivity = mainActivity;
        this.view = view;
        this.menu = menu;
        view.init(menu);
        view.setOnTouchListener(this);
    }

    Rectangle rectangleTouch;
  
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        
        int X=(int)event.getX(); 
        int Y=(int)event.getY(); 
        
                 int Action=event.getAction(); 
                 switch(Action) 
                 { 
                     case MotionEvent.ACTION_DOWN: 
                         rectangleTouch = new Rectangle(X,Y, 10, 10);
                         if(view.getRectanglePlay().intersects(rectangleTouch))menu.setPosition(0);
                         else if(view.getRectangleLevel().intersects(rectangleTouch))menu.setPosition(1);
                         else if(view.getRectangleSound().intersects(rectangleTouch))menu.setPosition(2);
                         else if(view.getRectangleRate().intersects(rectangleTouch))menu.setPosition(3);
                         else if(view.getRectangleExit().intersects(rectangleTouch))menu.setPosition(4);break;
                     case MotionEvent.ACTION_MOVE: 
                         rectangleTouch = new Rectangle(X,Y, 10, 10);
                         if(view.getRectanglePlay().intersects(rectangleTouch))menu.setPosition(0);
                         else if(view.getRectangleLevel().intersects(rectangleTouch))menu.setPosition(1);
                         else if(view.getRectangleSound().intersects(rectangleTouch))menu.setPosition(2);
                         else if(view.getRectangleRate().intersects(rectangleTouch))menu.setPosition(3);
                         else if(view.getRectangleExit().intersects(rectangleTouch))menu.setPosition(4);break;
                     case MotionEvent.ACTION_UP: 
                         switch (menu.getPosition()) {
                        case 0:
                            soundController.stop();
                            if(!mainActivity.getBuilder().checkController()){
                                    mainActivity.loading();
                                        mainActivity.startThread();
                                }else{
                                    mainActivity.unlockPause();
                             }
                            break;
                        case 1:
                            mainActivity.getBuilder().showLevels(); break;    
                        case 2: 
                           soundController.setSound(isSound = menu.isSound());
                          mainActivity.getBuilder().onSound(isSound);
                          soundController.stop();
                          soundController.playMenu();
                            break;
                        case 3:
                            mainActivity.getBuilder().showMyGame();
                            break;   
                        case 4:
                            mainActivity.onBackPressed(); 
                            break;   
                        default:
                            break;
                        }
                         break;
                 } 
        
        return true;
    }
    
    public MenuRenderer getView(){
        return view;
    }

}
