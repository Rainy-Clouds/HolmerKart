import java.awt.*;

public class Hitbox 
{
    private int x, y, width, height;

    public Hitbox(int x, int y, int w, int h)
    {
        this.x = x;
        this.y = y;
        width = w;
        height = h;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public void debug(Graphics g)
    {
        g.setColor(Color.PINK);
        g.fillRect(x, y, width, height);
    }

    public void debug(Graphics g, Color c)
    {
        g.setColor(c);
        g.fillRect(x, y, width, height);
    }

    public void updateValues(int x, int y, int w, int h)
    {
        this.x = x;
        this.y = y;
        width = w;
        height = h;
    }

    public boolean detectCollision(Hitbox h)
    {
        return ((x > h.x && x < h.x + h.width) && (y > h.y && y < h.y + h.height)) || ((x + width > h.x && x + width < h.x + h.width) && (y > h.y && y < h.y + h.height)) || ((x > h.x && x < h.x + h.width) && (y + height > h.y && y + height < h.y + h.height)) || ((x + width > h.x && x + width < h.x + h.width) && (y + height > h.y && y + height < h.y + h.height));
    }
}
