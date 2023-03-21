import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Panel extends JPanel implements Runnable, KeyListener, MouseListener
{
    private static final int width = 800;
    private static final int height = 600;

    private boolean running;
    private Thread thread;
    private int fps = 60;
    private long time = 1000 / fps;

    private Game game = new Game("title");
    private boolean[] keyMap = new boolean[8]; // The booleans represent which keys are down in this order: Up Down Left Right W A S D
    private boolean[] commands = new boolean[2];

    public Panel()
    {
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(new Color(134, 244, 252));
        start();
    }

    public void start()
    {
        running = true;
        thread = new Thread(this);
        thread.start();
        this.addKeyListener(this);
        this.addMouseListener(this);
        setFocusable(true);
        requestFocus();
    }

    @Override
    public void run()
    {
        long start;
        long elapsed;
        long wait;
        while(running)
        {
            start = System.nanoTime();

            tick();
            repaint();

            elapsed = System.nanoTime() - start;
            wait = time - elapsed / 1000000;

            if(wait <= 0)
            {
                wait = 5;
            }

            try
            {
                Thread.sleep(wait);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void tick()
    {
        game.tick(keyMap);
    }

    public void paint(Graphics g)
    {
        super.paintComponent(g);

        game.paint(g);
    }

    @Override
    public void keyTyped(KeyEvent e) 
    {
        // nothing
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
        if(KeyEvent.getKeyText(e.getKeyCode()) == "Up")
        {
            keyMap[0] = true;
        }
        else if(KeyEvent.getKeyText(e.getKeyCode()) == "Down")
        {
            keyMap[1] = true;
        }
        else if(KeyEvent.getKeyText(e.getKeyCode()) == "Left")
        {
            keyMap[2] = true;
        }
        else if(KeyEvent.getKeyText(e.getKeyCode()) == "Right")
        {
            keyMap[3] = true;
        }

        if(game.getState() == "menu")
        {
            game.getMenuManager().keyInput(KeyEvent.getKeyText(e.getKeyCode()).toLowerCase(), game.getRaceManager());  
        }

        if(KeyEvent.getKeyText(e.getKeyCode()) == "Enter" && !game.getRaceManager().getRacing() && game.getRaceManager().getPostRace())
        {
            if(game.getRaceManager().getState().equals("wait"))
            {
                game.getRaceManager().setState("enter");
            }
            else if(game.getRaceManager().getState().equals("enter"))
            {
                Transition.switchState("title");
            }
        }

        if(KeyEvent.getKeyText(e.getKeyCode()) == "Ctrl")
        {
            commands[0] = true;
        }
        if(KeyEvent.getKeyText(e.getKeyCode()).equals("R"))
        {
            commands[1] = true;
        } 

        //System.out.println(commands[0] + " " + commands[1]);

        if(commands[0] && commands[1])
        {
            game.getRaceManager().getPlayer().toggleRes();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
        if(KeyEvent.getKeyText(e.getKeyCode()) == "Up")
        {
            keyMap[0] = false;
        }
        else if(KeyEvent.getKeyText(e.getKeyCode()) == "Down")
        {
            keyMap[1] = false;
        }
        else if(KeyEvent.getKeyText(e.getKeyCode()) == "Left")
        {
            keyMap[2] = false;
        }
        else if(KeyEvent.getKeyText(e.getKeyCode()) == "Right")
        {
            keyMap[3] = false;
        }

        if(KeyEvent.getKeyText(e.getKeyCode()) == "Ctrl")
        {
            commands[0] = false;
        }
        if(KeyEvent.getKeyText(e.getKeyCode()) == "R")
        {
            commands[1] = false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // nothing
    }

    @Override
    public void mousePressed(MouseEvent e) {
        game.getTitleManager().mousePress(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        game.getTitleManager().mouseRelease();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // nothing
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // nothing
    }
}
