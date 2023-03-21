import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;

public class KartProfLoader 
{
    private ArrayList<BufferedImage> profiles = new ArrayList<BufferedImage>();

    public KartProfLoader()
    {
        try
        {
            profiles.add(ImageIO.read(new File("assets\\standardProfile.png")));
            profiles.add(ImageIO.read(new File("assets\\cozycoupeProfile.png")));
            profiles.add(ImageIO.read(new File("assets\\fordf150Profile.png")));
            profiles.add(ImageIO.read(new File("assets\\electricitymcqueenProfile.png")));
            profiles.add(ImageIO.read(new File("assets\\dollarvanProfile.png")));
            profiles.add(ImageIO.read(new File("assets\\gorillamobileProfile.png")));
            profiles.add(ImageIO.read(new File("assets\\lachanclaProfile.png")));
            profiles.add(ImageIO.read(new File("assets\\tatesbugattiProfile.png")));
        } 
        catch (Exception e) 
        {
            System.out.println("One or more images not found!");
        }
    }

    public BufferedImage getProfile(int index)
    {
        return profiles.get(index);
    }
}
