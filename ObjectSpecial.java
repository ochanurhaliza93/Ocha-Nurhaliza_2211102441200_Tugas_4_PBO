import greenfoot.*;

public class ObjectSpecial extends Actor {
    private GreenfootSound collectSound = new GreenfootSound("collect.mp3"); // Ganti dengan nama file suara Anda

    public ObjectSpecial() {
        setImage("object.png");
    }

    public void act() {
        checkForCollision();
    }

    public void checkForCollision() {
        Actor character = getOneIntersectingObject(Character.class);
        if (character != null) {
            ((Character) character).increaseScore();
            getWorld().removeObject(this);
            playCollectSound(); // Memainkan efek suara
        }
    }

    public void playCollectSound() {
        collectSound.play();
    }
}
