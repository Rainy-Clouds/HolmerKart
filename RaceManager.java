import java.awt.*;

public class RaceManager 
{
    private String trackname = "beta";
    private Car player = new Car(375, 285, 50, 30, 800, 1725, "standard"); // 0 600
    private Track track = new Track(trackname, player);
    private Clock gameClock = new Clock();
    private Score scores = new Score(trackname);
    private Countdown countdown = new Countdown();
    private boolean racing = true;
    private boolean postRace = false;

    private String afterRaceState = "none";

    public RaceManager()
    {
        gameClock.startClock();
    }

    public void setTrack(String name)
    {
        trackname = name;
    }

    public boolean getRacing()
    {
        return racing;
    }

    public void setRacing(boolean newRace)
    {
        racing = newRace;
    }

    public String getState()
    {
        return afterRaceState;
    }

    public Car getPlayer()
    {
        return player;
    }

    public Clock getClock()
    {
        return gameClock;
    }
    
    public boolean getPostRace()
    {
        return postRace;
    }

    public void setState(String newState)
    {
        afterRaceState = newState;
    }

    public void startNewRace()
    {
        track = new Track(trackname, player);
        scores = new Score(trackname);
        countdown.startCountdown();
        racing = false;
        postRace = false;
        //gameClock.startClock();

        if(trackname.equals("beta"))
        {
            player.updateStartingPos(0, 600);
        }
        else if(trackname.equals("colemanraceway"))
        {
            player.updateStartingPos(800, 1725);
        }
        player.prepare();
    }

    public void endRace()
    {
        afterRaceState = "none";
        postRace = false;
    }

    public void update(boolean[] keyMap)
    {
        countdown.update(this);
        player.update(keyMap, racing, track.getLapCount(), track.getOffroad(), track.getWall(), track.getDrop(), track.getCheckpoint());
        track.update(player);
        UserInterface.updateColors(7);
        if(player.getLap() > track.getLapCount() && racing)
        {
            postRace = true;
            racing = false;
            gameClock.endClock();
            afterRaceState = "wait";
            scores.updateBests(trackname, gameClock.overallTime());
        }
        if(!racing)
        {
            scores.update(afterRaceState);
        }
    }

    public void draw(Graphics g)
    {
        if(player.getFalling())
        {
            player.render(g);
        }
        track.render(g, player);
        if(!player.getFalling())
        {
            player.render(g);
        }
        countdown.render(g);
        if(racing)
        {
            UserInterface.drawGameUI(g, player.getLap(), track.getLapCount(), Clock.displayTime(gameClock.timeSinceStart()), afterRaceState);
        }
        else if(postRace)
        {
            UserInterface.drawGameUI(g, player.getLap(), track.getLapCount(), Clock.displayTime(gameClock.overallTime()), afterRaceState);
            scores.render(g, gameClock.overallTime());
        }
        else
        {
            UserInterface.drawGameUI(g, player.getLap(), track.getLapCount(), Clock.displayTime((long) 0), afterRaceState);
        }
    }
}
