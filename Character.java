import greenfoot.*;

public class Character extends Actor {
    private GreenfootImage characterImage;
    private int speed = 5; // Kecepatan karakter
    private int lives = 3; // Jumlah nyawa karakter

    public Character() {
        characterImage = new GreenfootImage("character.png");
        setImage(characterImage);
    }

    public void act() {
        checkForCollision();
        handleKeyPress();
        addParticles(); // Panggil metode untuk menambahkan partikel
    }

    public void checkForCollision() {
        Actor object = getOneIntersectingObject(ObjectSpecial.class);
        if (object != null) {
            increaseScore();
            getWorld().removeObject(object);
        }

        // Musuh menjadi diam dan mengakibatkan karakter kehilangan nyawa saat bersentuhan
        Actor enemy = getOneIntersectingObject(Enemy.class);
        if (enemy != null) {
            takeDamage();
        }
    }

    public void increaseScore() {
        ((MyWorld) getWorld()).increaseScore();
    }

    public void handleKeyPress() {
        if (Greenfoot.isKeyDown("left")) {
            setLocation(getX() - speed, getY());
            setRotation(180); // Menghadap ke kiri
        }
        if (Greenfoot.isKeyDown("right")) {
            setLocation(getX() + speed, getY());
            setRotation(0); // Menghadap ke kanan
        }
        if (Greenfoot.isKeyDown("up")) {
            setLocation(getX(), getY() - speed);
            setRotation(270); // Menghadap ke atas
        }
        if (Greenfoot.isKeyDown("down")) {
            setLocation(getX(), getY() + speed);
            setRotation(90); // Menghadap ke bawah
        }
    }

    public void addParticles() {
        // Tambahkan partikel saat karakter bergerak
        if (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("down")) {
            World world = getWorld();
            if (world != null) {
                int particleX = getX() + Greenfoot.getRandomNumber(20) - 10;
                int particleY = getY() + Greenfoot.getRandomNumber(20) - 10;
                world.addObject(new Particle(), particleX, particleY);
            }
        }
    }

    // Metode untuk mengurangi nyawa karakter
    public void takeDamage() {
        lives--;
        if (lives <= 0) {
            World world = getWorld();
            if (world != null) {
                world.showText("Game Over - You Lose!", world.getWidth() / 2, world.getHeight() / 2);
                Greenfoot.stop();
            }
        }
    }
}
