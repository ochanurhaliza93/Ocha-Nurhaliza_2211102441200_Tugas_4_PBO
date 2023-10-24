import greenfoot.*;

public class LevelComplete extends Actor {
    public LevelComplete(int level) {
        setImage(new GreenfootImage("Level " + level + " Complete", 36, Color.WHITE, null));
    }

    public void act() {
        getWorld().removeObject(this);
    }
}
