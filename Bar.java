import java.awt.*;

public class Bar 
{
    private String name;
    private double max;
    private double min;
    private double stat;
    private int x;
    private int y;
    
    public Bar(String name, double stat, double min, double max, int x, int y)
    {
        this.name = name;
        this.stat = stat;
        this.min = min;
        this.max = max;
        this.x = x;
        this.y = y;
    }

    public void setStat(double newStat)
    {
        stat = newStat;
    }

    public void render(Graphics g, int offset)
    {
        g.setColor(Color.BLACK);
        g.fillRect(x + offset, y, 120, 25);
        if((stat - min) / (max - min) <= 0.25)
        {
            g.setColor(Color.RED);
        }
        else if((stat - min) / (max - min) <= 0.5)
        {
            g.setColor(Color.YELLOW);
        }
        else
        {
            g.setColor(Color.GREEN);
        }
        g.fillRect(x + 3 + offset, y + 3, (int) (((stat - min) / (max - min)) * 114), 19); // max: 114 width
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        g.drawString(name, x + 5 + offset, y + 42);
    }
}
