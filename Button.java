import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.*;

public class Button 
{
    private String name;
    private int x, y;
    private BufferedImage image, imagepress;
    private boolean pressed;

    public Button(String name, int x, int y, String color)
    {
        this.name = name;
        this.x = x;
        this.y = y;
        try
        {
            image = ImageIO.read(new File("assets\\" + color + "button.png"));
            imagepress = ImageIO.read(new File("assets\\" + color + "buttonpress.png"));
        }
        catch(Exception e)
        {
            System.out.println("One or more images not found!");
        }
    }

    public void update(boolean mouseDown, int mx, int my)
    {
        if(mouseDown && mx >= x && mx <= x + 300 && my >= y && my <= y + 100)
        {
            pressed = true;
            if(name == "Singleplayer")
            {
                Transition.switchState("menu");
            }
        }
        else
        {
            pressed = false;
        }
    }

    public void render(Graphics g)
    {
        if(pressed)
        {
            g.drawImage(imagepress, x, y, null);
        }
        else
        {
            g.drawImage(image, x, y, null);
        }
        g.setColor(Color.WHITE);
        ChooseScreen.centerStringX(g, name, new Font("Arial", Font.PLAIN, 36), x, 300, y + 60);
    }
}
