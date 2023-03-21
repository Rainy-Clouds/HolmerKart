import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.*;

public class TrackProfile 
{
    private BufferedImage border;
    private BufferedImage selectBorder;
    private BufferedImage betaprev;
    private BufferedImage coleprev;
    private String name;
    private boolean selecting;

    public TrackProfile(String n)
    {
        name = n;
        try
        {
            border = ImageIO.read(new File("assets\\border.png"));
            selectBorder = ImageIO.read(new File("assets\\selectborder.png"));
            betaprev = ImageIO.read(new File("assets\\betapreview.png"));
            coleprev = ImageIO.read(new File("assets\\colemanracewaypreview.png"));
        }
        catch(Exception e)
        {
            System.out.println("One or more images not found!");
        }
    }

    public void setSelect(boolean newSel)
    {
        selecting = newSel;
    }

    public boolean getSelect()
    {
        return selecting;
    }

    public String getName()
    {
        return name;
    }

    public void render(Graphics g, int x, int y)
    {
        if(name.equals("beta"))
        {
            g.drawImage(betaprev, x, y, null);
        }
        if(name.equals("colemanraceway"))
        {
            g.drawImage(coleprev, x, y, null);
        }

        if(selecting)
        {
            g.drawImage(selectBorder, x, y, null);
        }
        else
        {
            g.drawImage(border, x, y, null);
        }
        g.setColor(Color.WHITE);
        if(name.equals("beta"))
        {
            ChooseScreen.centerStringX(g, "Beta Course", new Font("Arial", Font.PLAIN, 24), x, 225, y + 175);
        }
        else if(name.equals("colemanraceway"))
        {
            ChooseScreen.centerStringX(g, "Coleman Raceway", new Font("Arial", Font.PLAIN, 24), x, 225, y + 175);
        }
        else
        {
            ChooseScreen.centerStringX(g, name, new Font("Arial", Font.PLAIN, 24), x, 225, y + 175);
        }
    }
}
