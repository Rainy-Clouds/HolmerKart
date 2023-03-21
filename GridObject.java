import java.awt.*;

public class GridObject
{
    private int gridX, gridY, width, height;
    private Hitbox hitbox;

    public GridObject(int x, int y, int w, int h, int playerX, int playerY)
    {
        gridX = x;
        gridY = y;
        width = w;
        height = h;
        hitbox = new Hitbox(x * 100 - playerX, y * 100 - playerY, w, h);
    }

    public GridObject() {}

    public void updateHitbox(int playerX, int playerY)
    {
        hitbox = new Hitbox(gridX * 100 - playerX, gridY * 100 - playerY, width, height);
    }

    public boolean detectCollision(Hitbox h)
    {
        return hitbox.detectCollision(h);
    }

    public void debug(Graphics g)
    {
        hitbox.debug(g);
    }

    public Hitbox getHitbox()
    {
        return hitbox;
    }
}