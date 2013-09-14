package com.pacman.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.pacman.model.Brick;
import com.pacman.model.Player;
import com.pacman.model.spirit.*;
import com.pacman.view.Texture;



public class World {
    
    private Player player;
    
    public List<Brick> bricks;
    public List<Brick> oldBricks;
    private List<Spirit> spirits;
    private int  width;
    private int  height;
    private int countPoint;
    private int record;
    private int score;
    
    private Point pointBlinky;
    private Point pointInky;
    private Point pointPinky;
    private Point pointClyde;
    
    private Fruit fruit;

    public World(Level level, int record) {
        oldBricks = level.getBricks();
        bricks = level.getBricks();
        width = level.getWidth();
        height = level.getHeight();
        player = level.getPlayer();
        
        startPointSpirit(level);
       
        
        spirits = new ArrayList<Spirit>();
        this.record = record;
        score = 0;
        newGame();
        
        fruit = new Fruit(new Point(1, 1));
     }

    private void startPointSpirit(Level level) {
        pointBlinky = new Point(level.getPointBlinky().getX(), level.getPointBlinky().getY());
        pointClyde = new Point(level.getPointClyde().getX(), level.getPointClyde().getY());
        pointInky = new Point(level.getPointInky().getX(), level.getPointInky().getY());
        pointPinky = new Point(level.getPointPinky().getX(), level.getPointPinky().getY());
    }
    
    public void nextLevel(Level level, int record){
            oldBricks = level.getBricks();
            bricks = level.getBricks();
            width = level.getWidth();
            height = level.getHeight();
            player = level.getPlayer();
            startPointSpirit(level);
            spirits = new ArrayList<Spirit>();
            this.record = record;
            newGame();
            score = 0;
    }
    public void startPointPlayer(){
        player.setState(State.DEFENCE);
        player.setPosition(new Rectangle(12 * player.getSize(), 9 * player.getSize(), player.getSize(), player.getSize()));
    }
    
    public void createSpirit() {
        if (spirits != null) {
            spirits.clear();
        }
        
        spirits.add(new Blinky(pointBlinky));
        spirits.add(new Clyde(pointClyde));
        spirits.add(new Pinky(pointPinky));
        spirits.add(new Inky(pointInky));
    }
    
    public void generateFruit(){
        Random random = new Random();
        int n = random.nextInt(countPoint) - 1;
        int count = 0;
        for (Brick brick : bricks) {
            if (brick.getTexture() == Texture.point){
                count++;
            }
            if(count == n){
                fruit = new Fruit(new Point(brick.getPosition().getX()/brick.getSize() + 1,brick.getPosition().getY()/brick.getSize()));     
                int color = new Random().nextInt(5);
                switch (color) {
                case 0:
                    fruit.setTexture(Texture.apple_green);
                    break;
                case 1:
                    fruit.setTexture(Texture.apple_red);
                    break;
                case 2:
                    fruit.setTexture(Texture.orange);
                    break;
                case 3:
                    fruit.setTexture(Texture.cocos);
                    break;
                case 4:
                    fruit.setTexture(Texture.vinograd);
                    break;
                default:
                    fruit.setTexture(Texture.none);
                    break;
                }
            }
        }
        
    }
    
    
    public boolean collidesWithLevel(Rectangle rect) {
        for (Brick brick : bricks) {
            if (brick.getBounds().intersects(rect)
                    && brick.getTexture() != Texture.background
                    && brick.getTexture() != Texture.point
                    && brick.getTexture() != Texture.bonus
                    && brick.getTexture() != Texture.none) {
                return true;
            }
        }
        return false;
    }
    public State collidesWithRefresh(Rectangle rect) {
        for (Brick brick : bricks) {
            if (brick.getBounds().intersects(rect) && brick.getTexture() == Texture.none) {
                return State.ATTACK;
            }
        }
        return State.DEAD;
    }

    public int generationPoint() {
        int result = 0;
        for (Brick brick : bricks) {
            if (brick.getTexture() == Texture.background) {
                brick.setTexture(Texture.point);
            }
            if (brick.getTexture() == Texture.point) {
                result++;
            }
        }
        return result;
    }

    public void newGame(){
        for (int i = 0; i < oldBricks.size(); i++) {
            bricks.get(i).setTexture(oldBricks.get(i).getTexture());
        }

        createSpirit();
        startPointPlayer();
        countPoint = generationPoint(); 
    }

    public boolean eatFruit(){
        if(fruit.getBounds().intersects(player.getBounds()) && fruit.getTexture()!= Texture.none){
            fruit.setTexture(Texture.none);
            score += 500;
            return true;
        }
            return false;
    }
    
    public boolean eatPoint(){
        if (player.eatPoint(getBricks())) {
            countPoint--;
            score += 50;
            return true;
        }
        return false;
    }
    
    public boolean eatBonus(){
        if (player.eatBonus(getBricks())) {
            score += 500;
            defenceNPC();
            return true;
        }
        return false;
    }
    
    public void tryToPlayerGo(Direction direction) {
        player.onMove(direction);
   
        if (!collidesWithLevel(player.getBounds())) {
            player.setDirection(direction);
        }
        player.onMove(player.getDirection());
        
        if (!collidesWithLevel(player.getBounds())) {
            player.setPosition(player.getBounds());
        }
    }

    private void defenceNPC() {
        for (Spirit spirit : getSpirits()) {
            if (spirit.getState() == State.ATTACK) {
                spirit.setState(State.DEFENCE);
            }
        }
    }

    public void attackNPC() {
        for (Spirit spirit : getSpirits()) {
            if (spirit.getState() == State.DEFENCE) {
                spirit.setState(State.ATTACK);
            }
        }
        player.setState(State.DEFENCE);
    }


    public boolean deadPlayer (){
        for (Spirit spirit : getSpirits()) {
            if ((spirit.getBounds().intersects(player.getBounds()))) {
                if (spirit.getState() == State.ATTACK){
                    player.setState(State.DEAD);
                    player.setLife(player.getLife() - 1);
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean deadSpirit(){
        for (Spirit spirit : getSpirits()) {
            if ((spirit.getBounds().intersects(player.getBounds()))) {
                if (player.getState() == State.ATTACK && spirit.getState() != State.DEAD){
                    score += 1000;
                    spirit.setState(State.DEAD);
                    return true;
                }
            }
        }
        return false;
    }
    public boolean isVictory(){
        if(countPoint <= 0 )
            return true;
            
        return false;
    }
    
    public boolean isGameOver(){
        if(player.getLife() <= 0 )
            return true;
        
        return false;
    }
    
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Iterable<Brick> getBricks() {
        return bricks;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Spirit> getSpirits() {
        return spirits;
    }
    
    public int getRecord(){
        return record;
    }
    
    public void setRecord(int newRecord){
        record = newRecord;
    }
    
    public int getScore(){
        return score;
    }
    
    public void setScore(int score){
        this.score = score;
    }

    public Point getPointBlinky() {
        return pointBlinky;
    }

    public void setPointBlinky(Point pointBlinky) {
        this.pointBlinky = pointBlinky;
    }

    public Point getPointinky() {
        return pointInky;
    }

    public void setPointinky(Point pointinky) {
        this.pointInky = pointinky;
    }

    public Point getPointPinky() {
        return pointPinky;
    }

    public void setPointPinky(Point pointPinky) {
        this.pointPinky = pointPinky;
    }

    public Point getPointClyde() {
        return pointClyde;
    }

    public void setPointClyde(Point pointClyde) {
        this.pointClyde = pointClyde;
    }
    
    public Fruit getFruit() {
        return fruit;
    }
    
}
