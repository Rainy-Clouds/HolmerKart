import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class Track 
{
    private String name;
    private int lapCount;
    private String[][] trackArray;
    private ArrayList<Offroad> offroad;
    private ArrayList<Wall> wall;
    private ArrayList<Drop> drop;
    private ArrayList<Checkpoint> checkpoint;

    public Track(String name, Car player)
    {
        this.name = name;
        trackArray = TrackConverter.convertTextToArray(new File("tracks\\" + name + ".txt"));
        offroad = TrackConverter.getOffroad(trackArray, 0, 0);
        wall = TrackConverter.getWall(trackArray, 0, 0);
        drop = TrackConverter.getDrop(trackArray, 0, 0);
        checkpoint = CheckpointGenerator.generate(this.name);
        if(name.equals("colemanraceway"))
        {
            lapCount = 3;
        }
        else if(name.equals("beta"))
        {
            lapCount = 5;
        }
    }

    public ArrayList<Offroad> getOffroad()
    {
        return offroad;
    }
    
    public ArrayList<Wall> getWall()
    {
        return wall;
    }

    public ArrayList<Drop> getDrop()
    {
        return drop;
    }

    public ArrayList<Checkpoint> getCheckpoint()
    {
        return checkpoint;
    }

    public int getLapCount()
    {
        return lapCount;
    }

    public void update(Car player)
    {
        offroad = TrackConverter.getOffroad(trackArray, player.getTrackX(), player.getTrackY());
        wall = TrackConverter.getWall(trackArray, player.getTrackX(), player.getTrackY());
        drop = TrackConverter.getDrop(trackArray, player.getTrackX(), player.getTrackY());
        for(Checkpoint c : checkpoint)
        {
            c.update(player.getTrackX(), player.getTrackY());
        }
        //player.detectOffroad(offroad);
    }

    public void render(Graphics g, Car player)
    {
        // offroad = TrackConverter.getOffroad(trackArray, player.getTrackX(), player.getTrackY());
        // wall = TrackConverter.getWall(trackArray, player.getTrackX(), player.getTrackY());
        TrackConverter.drawArray(trackArray, g, player.getTrackX(), player.getTrackY());
        // for(Checkpoint c : checkpoint)
        // {
        //     c.debug(g);
        // }
        // for(Wall w : wall)
        // {
        //     w.debug(g);
        // }
    }
}
