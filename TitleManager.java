import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class TitleManager 
{
    private BufferedImage logo;
    private TitleBackground back = new TitleBackground();
    private Button singlePlayer = new Button("Singleplayer", 150, 300, "yellow");
    private Button twoPlayer = new Button("Multiplayer", 350, 450, "blue");
    private boolean mouseDown;
    private int mouseX, mouseY;

    public TitleManager()
    {
        try
        {
            logo = ImageIO.read(new File("assets\\logo.png"));
        }
        catch(Exception e)
        {
            System.out.println("One or more images not found!");
        }
    }

    public void update()
    {
        back.update();
        singlePlayer.update(mouseDown, mouseX, mouseY);
        twoPlayer.update(mouseDown, mouseX, mouseY);
    }

    public void mousePress(int x, int y)
    {
        mouseX = x;
        mouseY = y;
        mouseDown = true;
    }

    public void mouseRelease()
    {
        mouseDown = false;
    }

    public void draw(Graphics g)
    {
        back.render(g);
        g.drawImage(logo, 50, 50, null);
        singlePlayer.render(g);
        twoPlayer.render(g);
    }
}
