//import java.awt.*;

public class Wall extends GridObject
{
    // private int gridX;
    // private int gridY;
    // private int width;
    // private int height;
    // private Hitbox hitbox;

    public Wall(int x, int y, int w, int h, int playerX, int playerY)
    {
        super(x, y, w, h, playerX, playerY);
    }

    // public void updateHitbox(int playerX, int playerY)
    // {
    //     hitbox = new Hitbox(gridX * 100 - playerX, gridY * 100 - playerY, width, height);
    // }

    // public boolean detectCollision(Hitbox h)
    // {
    //     return hitbox.detectCollision(h);
    // }

    // public void debug(Graphics g)
    // {
    //     hitbox.debug(g);
    // }

    // public Hitbox getHitbox()
    // {
    //     return hitbox;
    // }
}
