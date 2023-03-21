import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.*;

public class TitleBackground 
{
    private int x = 800;
    private BufferedImage image;
    
    public TitleBackground()
    {
        try
        {
            image = ImageIO.read(new File("assets\\titleback.png"));
        }
        catch(Exception e)
        {
            System.out.println("One or more images not found!");
        }
    }

    public void update()
    {
        x -= 2;
        if(x <= 0)
        {
            x += 800;
        }
    }

    public void render(Graphics g)
    {
        g.drawImage(image, x, 0, null);
        g.drawImage(image, x - 800, 0, null);
    }
}
