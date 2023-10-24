import greenfoot.*;

public class Enemy extends Actor {
    private int moveDistance = 5; // Jarak pergerakan musuh
    private int moveDirection = 1; // 1 berarti bergerak ke kanan, -1 berarti bergerak ke kiri

    public Enemy() {
        setImage("enemy.png");
    }

    public void act() {
        moveHorizontally();
        checkEdge();
    }

    public void moveHorizontally() {
        setLocation(getX() + (moveDirection * moveDistance), getY());
    }

    public void checkEdge() {
        if (isAtEdge()) {
            // Jika musuh mencapai tepi layar, balik arahnya
            moveDirection *= -1;
        }
    }
}
