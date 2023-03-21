import java.awt.*;

public class Countdown 
{
    private int count;
    private int frameCount;
    private boolean counting;
    private int alpha;
    private int delayFrames = 25;

    public void startCountdown()
    {
        counting = true;
        count = 4;
        frameCount = 0;
        alpha = 255;
    }

    public void update(RaceManager race)
    {
        frameCount++;
        if(counting)
        {
            if(frameCount % 60 == 0)
            {
                count--;
            }
        }

        if(count <= 0 && counting)
        {
            counting = false;
            frameCount = 0;
            race.getClock().startClock();
            race.setRacing(true);
        }

        if(!counting)
        {
            if(frameCount > delayFrames)
            {
                alpha -= 2;
                if(alpha < 0)
                {
                    alpha = 0;
                }
            }
        }
    }
    
    public void render(Graphics g)
    {
        if(count == 3)
        {
            g.setColor(Color.RED);
            ChooseScreen.centerStringX(g, String.valueOf(count), new Font("Arial", Font.BOLD, 96), 0, 300);
        }
        else if(count == 2)
        {
            g.setColor(Color.ORANGE);
            ChooseScreen.centerStringX(g, String.valueOf(count), new Font("Arial", Font.BOLD, 108), 0, 300);
        }
        else if(count == 1)
        {
            g.setColor(Color.YELLOW);
            ChooseScreen.centerStringX(g, String.valueOf(count), new Font("Arial", Font.BOLD, 120), 0, 300);
        }
        else if(count <= 0)
        {
            g.setColor(new Color(0, 255, 0, alpha));
            ChooseScreen.centerStringX(g, "GO!", new Font("Arial", Font.BOLD, 132), 0, 300);
        }
    }
}
