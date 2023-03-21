import java.awt.*;
import java.awt.image.BufferedImage;

public class ChooseScreen 
{
    private TrackProfile[][] profiles = {{new TrackProfile("colemanraceway"), new TrackProfile("beta")}, {new TrackProfile("???"), new TrackProfile("???")}};
    private int selectX;
    private int selectY;
    private KartProfLoader kartImg = new KartProfLoader();

    public void update()
    {
        for(int i = 0; i < 2; i++)
        {
            for(int j = 0; j < 2; j++)
            {
                profiles[i][j].setSelect(selectX == j && selectY == i);
            }
        }
    }

    public void render(Graphics g, String kart, String quip, int x, int currentKart, int kartsLength)
    {
        g.setColor(Color.WHITE);
        centerStringX(g, kart, new Font("Arial", Font.PLAIN, 48), x, 325);
        centerStringX(g, quip, new Font("Arial", Font.PLAIN, 24), x, 365);
        g.setColor(Color.YELLOW);
        centerStringX(g, "Press ENTER to select kart", new Font("Arial", Font.PLAIN, 24), x, 575);

        g.drawImage(kartImg.getProfile(currentKart), (400 - kartImg.getProfile(currentKart).getWidth() / 2) + x, 150 - kartImg.getProfile(currentKart).getHeight() / 2, null);
        BufferedImage prev = kartImg.getProfile(loopCurrentKart(currentKart, -1, kartsLength));
        BufferedImage post = kartImg.getProfile(loopCurrentKart(currentKart, 1, kartsLength));
        g.drawImage(prev.getScaledInstance(prev.getWidth() / 2, prev.getHeight() / 2, Image.SCALE_DEFAULT), (100 - prev.getWidth() / 4) + x, 150 - prev.getHeight() / 4, null);
        g.drawImage(post.getScaledInstance(post.getWidth() / 2, post.getHeight() / 2, Image.SCALE_DEFAULT), (700 - post.getWidth() / 4) + x, 150 - post.getHeight() / 4, null);

        for(int i = 0; i < 2; i++)
        {
            for(int j = 0; j < 2; j++)
            {
                profiles[i][j].render(g, 138 + (300 * j) + 800 + x, 105 + (240 * i));
            }
        }
        g.setColor(Color.YELLOW);
        centerStringX(g, "Press ENTER to start the race!", new Font("Arial", Font.PLAIN, 24), x + 800, 575);
    }

    public String getSelectedTrack()
    {
        for(TrackProfile[] sub : profiles)
        {
            for(TrackProfile p : sub)
            {
                if(p.getSelect())
                {
                    return p.getName();
                }
            }
        }
        return null;
    }

    public void trackKeys(String key)
    {
        if(key.equals("left"))
        {
            selectX--;
            if(selectX < 0)
            {
                selectX = 1;
            }
        }
        if(key.equals("right"))
        {
            selectX++;
            if(selectX > 1)
            {
                selectX = 0;
            }
        }
        if(key.equals("up"))
        {
            selectY--;
            if(selectY < 0)
            {
                selectY = 1;
            }
        }
        if(key.equals("down"))
        {
            selectY++;
            if(selectY > 1)
            {
                selectY = 0;
            }
        }
    }

    public int loopCurrentKart(int currentKart, int add, int len)
    {
        if(currentKart + add < 0)
        {
            return len - 1;
        }
        else if(currentKart + add >= len)
        {
            return 0;
        }
        return currentKart + add;
    }

    public static void centerStringX(Graphics g, String text, Font f, int offset, int y)
    {
        FontMetrics metrics = g.getFontMetrics(f);
        int x = 400 - metrics.stringWidth(text) / 2;
        g.setFont(f);
        g.drawString(text, x + offset, y);
    }

    public static void centerStringX(Graphics g, String text, Font f, int offset, int width, int y)
    {
        FontMetrics metrics = g.getFontMetrics(f);
        int x = (width / 2) - metrics.stringWidth(text) / 2;
        g.setFont(f);
        g.drawString(text, x + offset, y);
    }
}
