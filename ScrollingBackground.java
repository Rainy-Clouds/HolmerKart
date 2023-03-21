import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.*;

public class ScrollingBackground 
{
    private int y;
    private BufferedImage image;

    public ScrollingBackground()
    {
        try
        {
            image = ImageIO.read(new File("assets\\menuback.png"));
        }
        catch(Exception e)
        {
            System.out.println("One or more images not found!");
        }
    }

    public void update()
    {
        y += 3;
        if(y >= 600)
        {
            y -= 600;
        }
    }

    public void render(Graphics g)
    {
        g.drawImage(image, 0, y, null);
        g.drawImage(image, 0, y - 600, null);
    }
}
