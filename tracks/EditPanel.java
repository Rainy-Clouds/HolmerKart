package tracks;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class EditPanel extends JPanel implements Runnable, KeyListener, MouseMotionListener
{
    private static final int width = 800;
    private static final int height = 600;

    private boolean running;
    private Thread thread;
    private int fps = 60;
    private long time = 1000 / fps;

    private String[][] trackArr = new String[6][8];
    private String pen = "g0";
    private boolean asking;

    public EditPanel()
    {
        this.setPreferredSize(new Dimension(width, height));
        for(int r = 0; r < trackArr.length; r++)
        {
            for(int c = 0; c < trackArr[r].length; c++)
            {
                trackArr[r][c] = "n0";
            }
        }
        start();
    }

    public void start()
    {
        running = true;
        thread = new Thread(this);
        thread.start();
        this.addKeyListener(this);
        this.addMouseMotionListener(this);
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

    }

    public void paint(Graphics g)
    {
        super.paintComponent(g);

        for(int r = 0; r < trackArr.length; r++)
        {
            for(int c = 0; c < trackArr[r].length; c++)
            {
                switch(trackArr[r][c])
                {
                    case("g0"):
                        g.setColor(Color.GREEN);
                        g.fillRect(c * 100, r * 100, 100, 100);
                        break;

                    case("r0"):
                        g.setColor(new Color(140, 140, 140));
                        g.fillRect(c * 100, r * 100, 100, 100);
                        break;
                    case("r1"):
                        g.setColor(new Color(140, 140, 140));
                        g.fillRect(c * 100 + 10, r * 100, 90, 100);
                        break;
                    case("r2"):
                        g.setColor(new Color(140, 140, 140));
                        g.fillRect(c * 100, r * 100, 90, 100);
                        break;
                    case("r3"):
                        g.setColor(new Color(140, 140, 140));
                        g.fillRect(c * 100, r * 100, 100, 90);
                        break;
                    case("r4"):
                        g.setColor(new Color(140, 140, 140));
                        g.fillRect(c * 100, r * 100 + 10, 100, 90);
                        break;
                    case("r5"):
                        g.setColor(new Color(140, 140, 140));
                        g.fillRect(c * 100 + 10, r * 100, 90, 100);
                        g.fillRect(c * 100, r * 100 + 10, 10, 90);
                        break;
                    case("r6"):
                        g.setColor(new Color(140, 140, 140));
                        g.fillRect(c * 100, r * 100, 90, 100);
                        g.fillRect(c * 100 + 90, r * 100 + 10, 10, 90);
                        break;
                    case("r7"):
                        g.setColor(new Color(140, 140, 140));
                        g.fillRect(c * 100, r * 100, 100, 90);
                        g.fillRect(c * 100, r * 100 + 90, 90, 10);
                        break;
                    case("r8"):
                        g.setColor(new Color(140, 140, 140));
                        g.fillRect(c * 100, r * 100, 100, 90);
                        g.fillRect(c * 100 + 10, r * 100 + 90, 90, 10);
                        break;

                    case("e0"):
                        g.setColor(new Color(140, 140, 140));
                        g.fillRect(c * 100 + 10, r * 100 + 10, 90, 90);
                        break;
                    case("e1"):
                        g.setColor(new Color(140, 140, 140));
                        g.fillRect(c * 100, r * 100 + 10, 90, 90);
                        break;
                    case("e2"):
                        g.setColor(new Color(140, 140, 140));
                        g.fillRect(c * 100, r * 100, 90, 90);
                        break;
                    case("e3"):
                        g.setColor(new Color(140, 140, 140));
                        g.fillRect(c * 100 + 10, r * 100, 90, 90);
                        break;

                    case("w0"):
                        g.setColor(Color.BLACK);
                        g.fillRect(c * 100, r * 100, 50, 100);
                        break;
                    case("w1"):
                        g.setColor(Color.BLACK);
                        g.fillRect(c * 100 + 50, r * 100, 50, 100);
                        break;
                    case("w2"):
                        g.setColor(Color.BLACK);
                        g.fillRect(c * 100, r * 100, 100, 50);
                        break;
                    case("w3"):
                        g.setColor(Color.BLACK);
                        g.fillRect(c * 100, r * 100 + 50, 100, 50);
                        break;
                    case("w4"):
                        g.setColor(Color.BLACK);
                        g.fillRect(c * 100, r * 100, 50, 100);
                        g.fillRect(c * 100, r * 100, 100, 50);
                        break;
                    case("w5"):
                        g.setColor(Color.BLACK);
                        g.fillRect(c * 100 + 50, r * 100, 50, 100);
                        g.fillRect(c * 100, r * 100, 100, 50);
                        break;
                    case("w6"):
                        g.setColor(Color.BLACK);
                        g.fillRect(c * 100 + 50, r * 100, 50, 100);
                        g.fillRect(c * 100, r * 100 + 50, 100, 50);
                        break;
                    case("w7"):
                        g.setColor(Color.BLACK);
                        g.fillRect(c * 100, r * 100, 50, 100);
                        g.fillRect(c * 100, r * 100 + 50, 100, 50);
                        break;

                    case("d0"):
                        g.setColor(new Color(134, 244, 252));
                        g.fillRect(c * 100, r * 100, 100, 100);
                        break;

                    case("c0"):
                        g.setColor(new Color(125, 140, 140));
                        g.fillRect(c * 100, r * 100, 100, 100);
                        break;
                    case("c1"):
                        g.setColor(new Color(125, 140, 140));
                        g.fillRect(c * 100 + 10, r * 100, 90, 100);
                        break;
                    case("c2"):
                        g.setColor(new Color(125, 140, 140));
                        g.fillRect(c * 100, r * 100, 90, 100);
                        break;
                    case("c3"):
                        g.setColor(new Color(125, 140, 140));
                        g.fillRect(c * 100, r * 100, 100, 90);
                        break;
                    case("c4"):
                        g.setColor(new Color(125, 140, 140));
                        g.fillRect(c * 100, r * 100 + 10, 100, 90);
                        break;
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(!asking)
        {
            if("0123456789".indexOf(KeyEvent.getKeyText(e.getKeyCode())) != -1)
            {
                pen = pen.charAt(0) + KeyEvent.getKeyText(e.getKeyCode()).toLowerCase();
                System.out.println("Pen changed to " + pen);
            }
            else if(KeyEvent.getKeyText(e.getKeyCode()).length() == 1)
            {
                pen = KeyEvent.getKeyText(e.getKeyCode()).toLowerCase() + pen.charAt(1);
                System.out.println("Pen changed to " + pen);
            }
        }
        if(KeyEvent.getKeyText(e.getKeyCode()) == "Enter")
        {
            asking = true;
            Scanner input = new Scanner(System.in);
            System.out.print("X: ");
            int printX = input.nextInt();
            System.out.print("Y: ");
            int printY = input.nextInt();
            //input.close();

            ArrayList<String> existingFile = new ArrayList<String>();
            try
            {
                Scanner textScan = new Scanner(new File("tracks\\colemanraceway.txt"));
                while(textScan.hasNextLine())
                {
                    existingFile.add(textScan.nextLine());
                }
                textScan.close();
            }
            catch(Exception ex)
            {
                System.out.println("An error occurred!");
            }

            if(printX * 2 <= existingFile.get(0).length() - 16 && printY <= existingFile.size() - 6)
            {
                try
                {
                    FileWriter writer = new FileWriter("tracks\\colemanraceway.txt");
                    for(int i = 0; i < existingFile.size(); i++)
                    {
                        if(i >= printY && i <= printY + 5)
                        {
                            writer.write(existingFile.get(i).substring(0, printX * 2) + convertArrayToString(i - printY) + existingFile.get(i).substring(printX * 2 + 16));
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
                    System.out.println("Sucessfully edited track!");
                }
                catch(Exception ex)
                {
                    System.out.println("An error occurred!");
                }
            }
            else if(printX * 2 > existingFile.get(0).length())
            {
                try
                {
                    FileWriter writer = new FileWriter("tracks\\colemanraceway.txt");
                    for(int i = 0; i < existingFile.size(); i++)
                    {
                        if(i >= printY && i <= printY + 5)
                        {
                            writer.write(existingFile.get(i) + convertArrayToString(i - printY));
                            writer.write(System.lineSeparator());
                        }
                        else
                        {
                            writer.write(existingFile.get(i) + "g0g0g0g0g0g0g0g0");
                            writer.write(System.lineSeparator());
                        }
                    }
                    writer.flush();
                    writer.close();
                    System.out.println("Sucessfully edited track!");
                }
                catch(Exception ex)
                {
                    System.out.println("An error occurred!");
                }
            }
            else if(printY > existingFile.size())
            {
                try
                {
                    FileWriter writer = new FileWriter("tracks\\colemanraceway.txt");
                    for(int i = 0; i < existingFile.size(); i++)
                    {
                        writer.write(existingFile.get(i));
                        writer.write(System.lineSeparator());
                    }
                    for(int i = 0; i < 6; i++)
                    {
                        String str1 = "";
                        String str2 = "";
                        for(int j = 0; j < printX; j++)
                        {
                            str1 += "g0";
                        }
                        for(int k = 0; k < (existingFile.get(0).length() - (printX * 2 + 16)) / 2; k++)
                        {
                            str2 += "g0";
                        }
                        writer.write(str1 + convertArrayToString(i) + str2);
                        writer.write(System.lineSeparator());
                    }
                    writer.flush();
                    writer.close();
                    System.out.println("Sucessfully edited track!");
                }
                catch(Exception ex)
                {
                    System.out.println("An error occurred!");
                }
            }
            asking = false;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // nothing
    }

    public String convertArrayToString(int index)
    {
        String str = "";
        for(String s : trackArr[index])
        {
            str += s;
        }
        return str;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(e.getX() > 0 && e.getX() < 800 && e.getY() > 0 && e.getY() < 600)
        {
            trackArr[e.getY() / 100][e.getX() / 100] = pen;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // nothing
    }
}
