import greenfoot.*;
import java.util.List;

public class MyWorld extends World {
    private int currentLevel = 1;
    private int score = 0;
    private int lives = 3;
    private int requiredSpecialObjects = 10;

    public MyWorld() {
        super(800, 600, 1);
        prepare();
    }

    public void act() {
        if (getObjects(ObjectSpecial.class).isEmpty()) {
            if (currentLevel < 3) {
                currentLevel++;
                requiredSpecialObjects += 5;
                addObject(new LevelComplete(currentLevel), getWidth() / 2, getHeight() / 2);
                Greenfoot.delay(100);
                nextLevel();
            } else {
                showText("You Win!", getWidth() / 2, getHeight() / 2);
                Greenfoot.stop();
            }
        }
    }

    public void prepare() {
        addObject(new Character(), getRandomX(), getRandomY());
        addObject(new ObjectSpecial(), getRandomX(), getRandomYAvoidEnemies());
        addObject(new Enemy(), getRandomX(), getRandomYAvoidEnemies());
    }

    public void nextLevel() {
        removeObjects(getObjects(ObjectSpecial.class));
        removeObjects(getObjects(LevelComplete.class));
        prepareNextLevel();
    }

    public void decreaseLives() {
        lives--;
        if (lives <= 0) {
            showText("Game Over - You Lose!", getWidth() / 2, getHeight() / 2);
            Greenfoot.stop();
        }
    }

    public int getLives() {
        return lives;
    }

    public void increaseScore() {
        score += 10;
        showText("Score: " + score, 50, 25);
    }

    public void prepareNextLevel() {
        addObject(new ObjectSpecial(), getRandomX(), getRandomYAvoidEnemies());
        addObject(new Enemy(), getRandomX(), getRandomYAvoidEnemies());
    }

    public int getRandomX() {
        return Greenfoot.getRandomNumber(getWidth());
    }

    public int getRandomY() {
        return Greenfoot.getRandomNumber(getHeight());
    }

    public int getRandomYAvoidEnemies() {
        int newY;
        do {
            newY = getRandomY();
        } while (isTooCloseToEnemies(newY));
        return newY;
    }

    public boolean isTooCloseToEnemies(int newY) {
        List<Enemy> enemies = getObjects(Enemy.class);
        for (Enemy enemy : enemies) {
            if (Math.abs(newY - enemy.getY()) < 50) {
                return true; // Jarak terlalu dekat
            }
        }
        return false; // Jarak aman
    }
}
