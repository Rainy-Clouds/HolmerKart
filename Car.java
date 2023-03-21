import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

public class Car 
{
    private int x, y, width, height, trackX, trackY, baseWidth, baseHeight, startX, startY;
    private int topSpeed, offroading, baseTopSpeed;
    private double acceleration, traction, turnSpeed;
    private double speed, xspeed, yspeed;
    private boolean notMoving, falling;
    private int ang = 270;
    private double size = 1;
    private int checkpointNum;
    private int lap = 1;
    private KartLoader imgs = new KartLoader();
    private String name;
    private double resolution = 1;

    private boolean movable = true;

    private Hitbox hitbox;
    private Hitbox leftbox;
    private Hitbox rightbox;
    private Hitbox topbox;
    private Hitbox bottombox;

    public Car(int x, int y, int w, int h, int startX, int startY, String name)
    {
        this.x = x;
        this.y = y;
        width = w;
        height = h;
        baseWidth = w;
        baseHeight = h;
        trackX = startX;
        trackY = startY;
        this.startX = startX;
        this.startY = startY;
        hitbox = new Hitbox(x, y, w, h);
        leftbox = new Hitbox(x - 1, y, 1, h);
        rightbox = new Hitbox(x + w, y, 1, h);
        topbox = new Hitbox(x, y - 1, w, 1);
        bottombox = new Hitbox(x, y + h, w, 1);
        updateStats(name);
        this.name = name;
    }

    public void updateStats(String name)
    {
        this.name = name;
        acceleration = Stats.getStat(name, "acceleration");
        baseTopSpeed = (int) Stats.getStat(name, "top speed");
        offroading = (int) Stats.getStat(name, "offroading");
        traction = Stats.getStat(name, "traction");
        turnSpeed = Stats.getStat(name, "turn speed");
        baseWidth = imgs.getKartImg(name).getWidth();
        width = imgs.getKartImg(name).getWidth();
        baseHeight = imgs.getKartImg(name).getHeight();
        height = imgs.getKartImg(name).getHeight();
    }

    public void updateStartingPos(int x, int y)
    {
        startX = x;
        startY = y;
    }

    public void toggleRes()
    {
        if(resolution == 1)
        {
            resolution = 1.25;
        }
        else
        {
            resolution = 1;
        }
    }

    public void moveForward()
    {
        speed += acceleration;
        if(speed > topSpeed)
        {
            speed = topSpeed;
        }
        notMoving = false;
    }

    public void moveBackward()
    {
        speed -= acceleration;
        if(speed < -topSpeed)
        {
            speed = -topSpeed;
        }
        notMoving = false;
    }

    public void turnLeft()
    {
        ang -= turnSpeed;
    }

    public void turnRight()
    {
        ang += turnSpeed;
    }

    public int getTrackX()
    {
        return trackX;
    }

    public int getTrackY()
    {
        return trackY;
    }

    public boolean getFalling()
    {
        return falling;
    }

    public int getLap()
    {
        return lap;
    }

    public void prepare()
    {
        trackX = startX;
        trackY = startY;
        checkpointNum = 0;
        lap = 1;
        ang = 270;
    }

    public void update(boolean[] keyMap, boolean racing, int lapCount, ArrayList<Offroad> offroad, ArrayList<Wall> wall, ArrayList<Drop> drop, ArrayList<Checkpoint> check)
    {
        width = (int) (baseWidth * size);
        height = (int) (baseHeight * size);

        movable = racing;
        if(falling)
        {
            movable = false;
        }
        if(lap > lapCount)
        {
            movable = false;
        }

        topSpeed = baseTopSpeed;
        for(Offroad o : offroad)
        {
            if(hitbox.detectCollision(o.getHitbox()))
            {
                topSpeed = offroading;
            }
        }
        notMoving = true;
        keyActions(keyMap);
        if(notMoving && speed != 0)
        {
            if(speed < 0)
            {
                speed += traction;
            }
            else if(speed > 0)
            {
                speed -= traction;
            }

            if(speed > -traction && speed < traction)
            {
                speed = 0;
            }
        }
        if(speed > 0 && speed != 0)
        {
            xspeed = Math.cos(Math.toRadians(ang)) * speed;
            yspeed = Math.sin(Math.toRadians(ang)) * speed;
        }
        else if(speed < 0 && speed != 0)
        {
            xspeed = Math.cos(Math.toRadians(ang + 180)) * Math.abs(speed);
            yspeed = Math.sin(Math.toRadians(ang + 180)) * Math.abs(speed);
        }
        else
        {
            xspeed = 0;
            yspeed = 0;
        }

        updateHitboxes();
        for(int i = 0; i < wall.size(); i++)
        {
            wall.get(i).updateHitbox(trackX, trackY);
        }
        avoidClip(wall, keyMap);

        for(int i = 0; i < check.size(); i++)
        {
            if(hitbox.detectCollision(check.get(i).getHitbox()) && checkpointNum >= i - 1)
            {
                if(i == 0 && checkpointNum == check.size() - 1)
                {
                    lap++;
                }
                checkpointNum = i;
            }
        }
        //System.out.println("Checkpoint: " + checkpointNum + " Lap: " + lap);

        if((dropCollison(leftbox, drop) && dropCollison(rightbox, drop) && dropCollison(topbox, drop) && dropCollison(bottombox, drop)) || falling)
        {
            falling = true;
            movable = false;
            size -= 0.015;

            if(size <= 0.05)
            {
                trackX = check.get(checkpointNum).getRespawnX();
                trackY = check.get(checkpointNum).getRespawnY();;
                movable = true;
                falling = false;
                size = 1;
                ang = check.get(checkpointNum).getRespawnAngle();;
            }
        }

        for(Wall w : wall)
        {
            if(rightbox.detectCollision(w.getHitbox()) && xspeed > 0)
            {
                xspeed = 0;
            }
            if(leftbox.detectCollision(w.getHitbox()) && xspeed < 0)
            {
                xspeed = 0;
            }
            if(topbox.detectCollision(w.getHitbox()) && yspeed < 0)
            {
                yspeed = 0;
            }
            if(bottombox.detectCollision(w.getHitbox()) && yspeed > 0)
            {
                yspeed = 0;
            }
        }

        trackX += (int) xspeed;
        trackY += (int) yspeed;

        updateHitboxes();
        for(int i = 0; i < wall.size(); i++)
        {
            wall.get(i).updateHitbox(trackX, trackY);
        }
        avoidClip(wall, keyMap);

        //System.out.println(trackX + ", " + trackY);
    }

    public boolean dropCollison(Hitbox h, ArrayList<Drop> objs)
    {
        for(GridObject g : objs)
        {
            if(h.detectCollision(g.getHitbox()))
            {
                return true;
            }
        }
        return false;
    }

    // god save us all
    public void avoidClip(ArrayList<Wall> wall, boolean[] keyMap)
    {
        boolean right = false;
        boolean left = false;
        boolean top = false;
        boolean bottom = false;

        boolean cutright = false;
        boolean cutleft = false;
        boolean cuttop = false;
        boolean cutbottom = false;

        for(Wall w : wall)
        {
            if(rightbox.detectCollision(w.getHitbox()))
            {
                right = true;
            }
            if(leftbox.detectCollision(w.getHitbox()))
            {
                left = true;
            }
            if(topbox.detectCollision(w.getHitbox()))
            {
                top = true;
            }
            if(bottombox.detectCollision(w.getHitbox()))
            {
                bottom = true;
            }
        }

        if(right && bottom && !top && !left)
        {
            if(ang % 360 > 45 && ang % 360 < 225)
            {
                cutright = true;
            }
            else
            {
                cutbottom = true;
            }
        }
        if(right && top && !bottom && !left)
        {
            if(ang % 360 > 130 && ang % 360 < 315)
            {
                cutright = true;
            }
            else
            {
                cuttop = true;
            }
        }
        if(left && bottom && !top && !right)
        {
            if(ang % 360 > 130 && ang % 360 < 315)
            {
                cutbottom = true;
            }
            else
            {
                cutleft = true;
            }
        }
        if(left && top && !bottom && !right)
        {
            if(ang % 360 > 45 && ang % 360 < 225)
            {
                cuttop = true;
            }
            else
            {
                cutleft = true;
            }
        }

        if(right && !left && !cutright)//(right && top && bottom && !left)
        {
            for(Wall w : wall)
            {
                if(rightbox.detectCollision(w.getHitbox()))
                {
                    trackX -= rightbox.getX() - w.getHitbox().getX();
                    break;
                }
            }
        }
        if(left && !right && !cutleft)//(left && top && bottom && !right)
        {
            for(Wall w : wall)
            {
                if(leftbox.detectCollision(w.getHitbox()))
                {
                    trackX += Math.abs((leftbox.getX() + 1) - (w.getHitbox().getX() + 100));
                    break;
                }
            }
        }
        if(bottom && !top && !cutbottom)//(bottom && right && left && !top)
        {
            for(Wall w : wall)
            {
                if(bottombox.detectCollision(w.getHitbox()))
                {
                    trackY -= bottombox.getY() - w.getHitbox().getY();
                    break;
                }
            }
        }
        if(top && !bottom && !cuttop)//(top && right && left && !bottom)
        {
            for(Wall w : wall)
            {
                if(topbox.detectCollision(w.getHitbox()))
                {
                    trackY += Math.abs((topbox.getY() + 1) - (w.getHitbox().getY() + 100));
                    break;
                }
            }
        }
        // else if(leftbox.detectCollision(w.getHitbox()) && bottombox.detectCollision(w.getHitbox()) && topbox.detectCollision(w.getHitbox()))
        // {
        //     trackX += Math.abs((leftbox.getX() + 1) - (w.getHitbox().getX() + 100));
        // }
    }

    public void detectOffroad(ArrayList<Offroad> offroad)
    {
        for(Offroad o : offroad)
        {
            if(hitbox.detectCollision(o.getHitbox()))
            {
                topSpeed = 5;
                return;
            }
        }
        topSpeed = 10;
    }

    public void updateHitboxes()
    {
        int newWidth = (int) (Math.abs(Math.sin(Math.toRadians(ang)) * height) + Math.abs(Math.cos(Math.toRadians(ang)) * width));
        int newHeight = (int) (Math.abs(Math.cos(Math.toRadians(ang)) * height) + Math.abs(Math.sin(Math.toRadians(ang)) * width));
        hitbox.updateValues(400 - (newWidth / 2), 300 - (newHeight / 2), newWidth, newHeight);
        leftbox.updateValues(hitbox.getX() - 1, hitbox.getY(), 1, hitbox.getHeight());
        rightbox.updateValues(hitbox.getX() + hitbox.getWidth(), hitbox.getY(), 1, hitbox.getHeight());
        topbox.updateValues(hitbox.getX(), hitbox.getY() - 1, hitbox.getWidth(), 1);
        bottombox.updateValues(hitbox.getX(), hitbox.getY() + hitbox.getHeight(), hitbox.getWidth(), 1);
    }

    public void render(Graphics g)
    {
        // Graphics2D g2d = (Graphics2D) g.create();
        // Rectangle r = new Rectangle(x, y, width, height);
        // Path2D.Double path = new Path2D.Double();
        // path.append(r, false);

        // AffineTransform t = new AffineTransform();
        // t.rotate(Math.toRadians(45));
        // path.transform(t);
        // g2d.draw(path);

        //hitbox.debug(g, Color.PINK);

        x = (int) (400 - (width / 2));
        y = (int) (300 - (height / 2));

        Graphics2D g2d = (Graphics2D)g.create();
        AffineTransform at = AffineTransform.getTranslateInstance(0, 0);
        at.rotate(Math.toRadians(ang), x * resolution + width * resolution / 2, y * resolution + height * resolution / 2);
        g2d.setTransform(at);
        // if(topSpeed == 5)
        // {
        //     g2d.setColor(Color.BLUE);
        // }
        // else
        // {
        //     g2d.setColor(Color.RED);
        // }
        g2d.drawImage(imgs.getKartImg(name).getScaledInstance((int) (width * resolution), (int) (height * resolution), Image.SCALE_DEFAULT), (int) (x * resolution), (int) (y * resolution), null);
        //g2d.fillRect((int) (x * 1.25), (int) (y * 1.25), (int) (width * 1.25), (int) (height * 1.25));
        g2d.dispose();
        
        // g.setColor(Color.RED);
        // g.fillRect(x, y, width, height);
    }

    public void keyActions(boolean[] keyMap)
    {
        if(movable)
        {
            if(keyMap[0])
            {
                moveForward();
            }
            if(keyMap[1])
            {
                moveBackward();
            }
            if(keyMap[2])
            {
                turnLeft();
            }
            if(keyMap[3])
            {
                turnRight();
            }
        }
    }

    public int simplestAng(int ang)
    {
        while(!(ang >= 0 && ang <= 360))
        {
            if(ang < 0)
            {
                ang += 360;
            }
            if(ang > 360)
            {
                ang -= 360;
            }
        }
        return ang;
    }
}
