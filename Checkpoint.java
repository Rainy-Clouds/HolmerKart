import java.awt.Graphics;

public class Checkpoint
{
    private int x, y, width, height;
    private int respawnX, respawnY, respawnAng;
    private Hitbox hitbox;

    public Checkpoint(int x, int y, int w, int h, int rx, int ry, int ang)
    {
        this.x = x;
        this.y = y;
        width = w;
        height = h;
        hitbox = new Hitbox(x, y, w, h);
        respawnX = rx;
        respawnY = ry;
        respawnAng = ang;
    }

    public Hitbox getHitbox()
    {
        return hitbox;
    }

    public int getRespawnX()
    {
        return respawnX;
    }

    public int getRespawnY()
    {
        return respawnY;
    }

    public int getRespawnAngle()
    {
        return respawnAng;
    }

    public void update(int playerX, int playerY)
    {
        hitbox.updateValues(x - playerX, y - playerY, width, height);
    }

    public void debug(Graphics g)
    {
        hitbox.debug(g);
    }
}
