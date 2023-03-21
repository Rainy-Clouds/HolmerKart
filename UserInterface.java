import java.awt.*;

public class UserInterface 
{
    private static int r = 255;
    private static int g, b;
    private static boolean redUp = true; 
    private static boolean greenUp, blueUp;
    private static int fly = -50; // final lap y
    public static double flyvel = 10;
    public static Color rainbow = new Color(r, g, b);
    public static int x;

    public static void drawGameUI(Graphics g, int lap, int lapCount, String time, String state)
    {
        g.setFont(new Font("Arial", Font.PLAIN, 36));
        if(lap >= lapCount)
        {
            g.setColor(rainbow);
        }
        else
        {
            g.setColor(new Color(140, 140, 140));
        }

        if(lap <= lapCount)
        {
            g.drawString("Lap: " + lap + "/" + lapCount, 30 + x, 550);
        }
        else
        {
            g.drawString("Lap: FINISH!!", 30 + x, 550);
        }

        g.drawString(time, 328 + x, 50);

        if(lap == lapCount)
        {
            g.setFont(new Font("Arial", Font.BOLD, 64));
            g.setColor(Color.ORANGE);
            g.drawString("FINAL LAP!", 225, fly);
            flyvel -= 0.2;
            fly += flyvel;
        }

        if(lap > lapCount)
        {
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Arial", Font.PLAIN, 24));
            g.drawString("Press ENTER to continue", 500, 575);
        }

        if(state.equals("enter"))
        {
            x += 25;
        }
    }

    public static void reset()
    {
        x = 0;
        fly = -50;
        flyvel = 10;
    }

    public static void updateColors(int speed)
    {
        if(redUp)
        {
            r += speed;
            if(r > 255)
            {
                r = 255;
                blueUp = false;
            }
        }
        if(!redUp)
        {
            r -= speed;
            if(r < 0)
            {
                r = 0;
                blueUp = true;
            }
        }
        if(greenUp)
        {
            g += speed;
            if(g > 255)
            {
                g = 255;
                redUp = false;
            }
        }
        if(!greenUp)
        {
            g -= speed;
            if(g < 0)
            {
                g = 0;
                redUp = true;
            }
        }
        if(blueUp)
        {
            b += speed;
            if(b > 255)
            {
                b = 255;
                greenUp = false;
            }
        }
        if(!blueUp)
        {
            b -= speed;
            if(b < 0)
            {
                b = 0;
                greenUp = true;
            }
        }

        rainbow = new Color(r, g, b);
    }
}
