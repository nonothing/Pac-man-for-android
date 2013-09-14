package com.pacman.model;

import java.util.List;


public interface Level {
    int getWidth();
    int getHeight();
    
    List<Brick> getBricks();

     Player getPlayer();
    
     Point getPointBlinky(); 
     Point getPointInky() ;
     Point getPointPinky();
     Point getPointClyde();
}
