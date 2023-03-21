import java.io.File;
import java.util.Scanner;
import java.awt.Graphics;
//import java.awt.Color;
import java.util.ArrayList;

public class TrackConverter 
{
    public static ImageLoader img = new ImageLoader();

    public static String[][] convertTextToArray(File f)
    {
        try
        {
            Scanner valueGetter = new Scanner(f);
            int width = valueGetter.nextLine().length() / 2;
            int height = 1;
            while(valueGetter.hasNextLine())
            {
                valueGetter.nextLine();
                height++;
            }
            valueGetter.close();

            Scanner textScan = new Scanner(f);
            String[][] arr = new String[height][width];
            for(int r = 0; r < height; r++)
            {
                String str = textScan.nextLine();
                for(int c = 0; c < str.length() / 2; c++)
                {
                    arr[r][c] = str.substring(c * 2, c * 2 + 2);//String.valueOf(str.charAt(c));
                }
            }
            textScan.close();
            return arr;
        }
        catch(Exception e)
        {
            System.out.println("Deleted or corrupt file!");
            return null;
        }
    }

    public static void drawArray(String[][] arr, Graphics g, int playerX, int playerY)
    {
        for(int r = 0; r < arr.length; r++)
        {
            for(int c = 0; c < arr[r].length; c++)
            {
                if(r * 100 - playerY >= -100 && r * 100 - playerY <= 700 && c * 100 - playerX >= -100 && c * 100 - playerX <= 900)
                {
                    switch(arr[r][c].charAt(0))
                    {
                    case('g'):
                    {
                        g.drawImage(img.grass(), c * 100 - playerX, r * 100 - playerY, null);
                        break;
                    }
                    case('e'):
                        g.drawImage(img.extension(Integer.parseInt(arr[r][c].substring(1))), c * 100 - playerX, r * 100 - playerY, null);
                        break;
                    case('r'):
                        g.drawImage(img.road(Integer.parseInt(arr[r][c].substring(1))), c * 100 - playerX, r * 100 - playerY, null);
                        // g.setColor(new Color(140, 140, 140));
                        // g.fillRect(c * 100 - playerX, r * 100 - playerY, 100, 100);
                        break;
                    case('w'):
                        g.drawImage(img.wall(Integer.parseInt(arr[r][c].substring(1))), c * 100 - playerX, r * 100 - playerY, null);
                        break;
                    case('c'):
                        g.drawImage(img.finish(Integer.parseInt(arr[r][c].substring(1))), c * 100 - playerX, r * 100 - playerY, null);
                        break;
                    case('d'):
                        if(arr[r][c].charAt(1) != '0')
                        {
                            g.drawImage(img.drop(Integer.parseInt(arr[r][c].substring(1)) - 1), c * 100 - playerX, r * 100 - playerY, null);
                        }
                        break;
                    }
                }
            }
        }
    }

    public static ArrayList<Offroad> getOffroad(String[][] arr, int playerX, int playerY)
    {
        ArrayList<Offroad> returnArr = new ArrayList<Offroad>();
        for(int r = 0; r < arr.length; r++)
        {
            for(int c = 0; c < arr[r].length; c++)
            {
                if(arr[r][c].equals("g0"))
                {
                    returnArr.add(new Offroad(c, r, 100, 100, playerX, playerY));
                }
            }
        }
        return returnArr;
    }

    public static ArrayList<Wall> getWall(String[][] arr, int playerX, int playerY)
    {
        ArrayList<Wall> returnArr = new ArrayList<Wall>();
        for(int r = 0; r < arr.length; r++)
        {
            for(int c = 0; c < arr[r].length; c++)
            {
                if(arr[r][c].charAt(0) == 'w')
                {
                    returnArr.add(new Wall(c, r, 100, 100, playerX, playerY));
                }
            }
        }
        return returnArr;
    }

    public static ArrayList<Drop> getDrop(String[][] arr, int playerX, int playerY)
    {
        ArrayList<Drop> returnArr = new ArrayList<Drop>();
        for(int r = 0; r < arr.length; r++)
        {
            for(int c = 0; c < arr[r].length; c++)
            {
                if(arr[r][c].charAt(0) == 'd')
                {
                    returnArr.add(new Drop(c, r, 100, 100, playerX, playerY));
                }
            }
        }
        return returnArr;
    }
}
