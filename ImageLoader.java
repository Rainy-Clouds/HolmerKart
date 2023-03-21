import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class ImageLoader 
{
    private BufferedImage roadRaw;
    private BufferedImage[] road = new BufferedImage[9];
    private BufferedImage extensionRaw;
    private BufferedImage[] extension = new BufferedImage[4];
    private BufferedImage finishRaw;
    private BufferedImage[] finish = new BufferedImage[2];
    private BufferedImage grass;
    private BufferedImage wallRaw;
    private BufferedImage[] wall = new BufferedImage[8];
    private BufferedImage dropRaw;
    private BufferedImage[] drop = new BufferedImage[4];

    public ImageLoader()
    {
        try
        {
            roadRaw = ImageIO.read(new File("assets\\road.png"));
            for(int i = 0; i < 9; i++)
            {
                road[i] = roadRaw.getSubimage(i * 100, 0, 100, 100);
            }
            extensionRaw = ImageIO.read(new File("assets\\extension.png"));
            for(int i = 0; i < 4; i++)
            {
                extension[i] = extensionRaw.getSubimage(i * 100, 0, 100, 100);
            }
            finishRaw = ImageIO.read(new File("assets\\finish.png"));
            for(int i = 0; i < 2; i++)
            {
                finish[i] = finishRaw.getSubimage(i * 100, 0, 100, 100);
            }
            grass = ImageIO.read(new File("assets\\grass.png"));
            wallRaw = ImageIO.read(new File("assets\\wall.png"));
            for(int i = 0; i < 8; i++)
            {
                wall[i] = wallRaw.getSubimage(i * 100, 0, 100, 100);
            }
            dropRaw = ImageIO.read(new File("assets\\drop.png"));
            for(int i = 0; i < 4; i++)
            {
                drop[i] = dropRaw.getSubimage(i * 100, 0, 100, 100);
            }
        }
        catch(Exception e)
        {
            System.out.println("One or more images not found!");
        }
    }

    public BufferedImage road(int index)
    {
        return road[index];
    }

    public BufferedImage extension(int index)
    {
        return extension[index];
    }

    public BufferedImage finish(int index)
    {
        return finish[index];
    }

    public BufferedImage grass()
    {
        return grass;
    }

    public BufferedImage wall(int index)
    {
        return wall[index];
    }

    public BufferedImage drop(int index)
    {
        return drop[index];
    }
}
