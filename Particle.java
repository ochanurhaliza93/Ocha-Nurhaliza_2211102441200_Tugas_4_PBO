import greenfoot.*;

public class Particle extends Actor {
    public Particle() {
        GreenfootImage particleImage = new GreenfootImage(5, 5);
        particleImage.setColor(Color.YELLOW); // Ubah warna partikel sesuai keinginan
        particleImage.fillOval(0, 0, 5, 5);
        setImage(particleImage);
    }

    public void act() {
        if (getImage().getTransparency() > 5) {
            getImage().setTransparency(getImage().getTransparency() - 5);
        } else {
            getWorld().removeObject(this);
        }
    }
}
