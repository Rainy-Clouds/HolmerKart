import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;
import java.util.Arrays;

public class KartLoader 
{
    private ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
    public static ArrayList<String> names = new ArrayList<String>(Arrays.asList("standard", "cozycoupe", "fordf150", "electricitymcqueen", "dollarvan", "gorillamobile", "lachancla", "tatesbugatti"));

    public KartLoader()
    {
        try
        {
            for(String str : names)
            {
                images.add(ImageIO.read(new File("assets\\" + str + ".png")));
            }
        } 
        catch (Exception e) 
        {
            System.out.println("One or more images not found!");
        }
    }

    public BufferedImage getKartImg(String name)
    {
        return images.get(names.indexOf(name));
    }


}
