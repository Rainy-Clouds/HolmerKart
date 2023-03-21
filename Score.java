import java.awt.image.*;
import java.awt.*;
import java.io.*;
import javax.imageio.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Score 
{
    private BufferedImage overlay;
    private int x = -725;
    private int y = 75;
    private long[] best = new long[5];

    public Score(String trackname)
    {
        try
        {
            overlay = ImageIO.read(new File("assets\\scoreoverlay.png"));
            
            Scanner scoreScan = new Scanner(new File("savedata.txt"));
            int i = 0;
            int index = 0;
            boolean startAdding = false;
            while(scoreScan.hasNextLine())
            {
                String str = scoreScan.nextLine();
                if(startAdding)
                {
                    best[index] = Long.parseLong(str);
                    //System.out.println(i + " " + best[index]);
                    index++;
                    if(index >= 5)
                    {
                        break;
                    }
                }
                else
                {
                    if(str.equals(trackname))
                    {
                        //System.out.println(str + " == " + trackname + " " + i);
                        startAdding = true;
                    }
                }
                i++;
            }
        }
        catch(Exception e)
        {
            System.out.println("One or more files not found!");
        }
    }

    public void update(String state)
    {
        if(state.equals("enter"))
        {
            x += 25;
            if(x >= 75)
            {
                state = "stop";
                x = 75;
            }
        }
    }

    public void render(Graphics g, long playerTime)
    {
        g.drawImage(overlay, x, y, null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.ITALIC, 60));
        g.drawString("Best Times", 165 + x, 75 + y);
        g.setFont(new Font("Arial", Font.PLAIN, 36));
        for(int i = 0; i < best.length; i++)
        {
            if(best[i] == 0)
            {
                g.setColor(Color.WHITE);
                g.drawString((i + 1) + ") --:--.---", x + 50, y + (i + 1) * 50 + 85);
            }
            else
            {
                if(best[i] == playerTime)
                {
                    g.setColor(UserInterface.rainbow);
                }
                else
                {
                    g.setColor(Color.WHITE);
                }
                g.drawString((i + 1) + ") " + Clock.displayTime(best[i]), x + 50, y + (i + 1) * 50 + 85);
            }
        }
        g.setColor(UserInterface.rainbow);
        g.drawString("You: " + Clock.displayTime(playerTime), x + 210, y + 410);
    }

    public void updateBests(String trackname, long playerTime)
    {
        for(int i = 0; i < 5; i++)
        {
            if(playerTime < best[i] || best[i] == 0)
            {
                for(int j = 4; j > i; j--)
                {
                    best[j] = best[j - 1];
                }
                best[i] = playerTime;
                break;
            }
        }
        updateData(trackname);
    }

    public void updateData(String trackname)
    {
        ArrayList<String> existingFile = new ArrayList<String>();
        try
        {
            Scanner textScan = new Scanner(new File("savedata.txt"));
            while(textScan.hasNextLine())
            {
                existingFile.add(textScan.nextLine());
            }
            textScan.close();

            FileWriter writer = new FileWriter("savedata.txt");
            for(int i = 0; i < existingFile.size(); i++)
            {
                if(i > existingFile.indexOf(trackname) && i <= existingFile.indexOf(trackname) + 5)
                {
                    writer.write(String.valueOf(best[i - existingFile.indexOf(trackname) - 1]));
                    writer.write(System.lineSeparator());
                }
                else
                {
                    writer.write(existingFile.get(i));
                    writer.write(System.lineSeparator());
                }
            }
            writer.flush();
            writer.close();
        }
        catch(Exception ex)
        {
            System.out.println("An error occurred!");
        }
    }
}
