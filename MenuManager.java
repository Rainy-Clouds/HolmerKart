import java.awt.*;

public class MenuManager 
{
    private ScrollingBackground back = new ScrollingBackground();
    private ChooseScreen choose = new ChooseScreen();
    private Bar[] statBars = {new Bar("Acceleration", 0, 0, 1, 120, 420), new Bar("Top Speed", 0, 6, 15, 340, 420), new Bar("Offroad", 0, 3, 9, 560, 420), new Bar("Traction", 0, 0, 0.5, 120, 485), new Bar("Turn Speed", 0, 1.5, 4.5, 340, 485), new Bar("Tate Factor", 0, 0, 10, 560, 485)};
    private String[] karts = {"Standard", "Cozy Coupe", "Ford F150", "Electricity McQueen", "Dollar Van", "Gorillamobile", "La Chancla", "Tate's Bugatti"};
    private String[] quips = {"Starbucks on wheels", "The child version of a Lamborghini", "God bless America", "Lightning's twin brother from China", "Free Candy!", "Has that gorilla grip", "¡Cuidado!", "so alpha redpilled"};
    private int currentKart;
    private String state = "kart";
    private int xOffset;

    public void resetMenu()
    {
        state = "kart";
        xOffset = 0;
    }

    public void update()
    {
        back.update();
        choose.update();
        for(int i = 0; i < statBars.length; i++)
        {
            statBars[i].setStat(Stats.getStat(KartLoader.names.get(currentKart), i));
        }

        if(state.equals("switch"))
        {
            xOffset -= 25;
            if(xOffset == -800)
            {
                state = "track";
            }
        }
    }

    public void keyInput(String key, RaceManager race)
    {
        if(state.equals("kart"))
        {
            if(key.equals("right"))
            {
                currentKart++;
                if(currentKart >= karts.length)
                {
                    currentKart = 0;
                }
            }
            else if(key.equals("left"))
            {
                currentKart--;
                if(currentKart < 0)
                {
                    currentKart = karts.length - 1;
                }
            }
        }

        if(state.equals("track"))
        {
            choose.trackKeys(key);
        }
        
        if(key.equals("enter") && state.equals("kart"))
        {
            race.getPlayer().updateStats(KartLoader.names.get(currentKart));
            state = "switch";
        }

        if(key.equals("enter") && state.equals("track") && (choose.getSelectedTrack().equals("beta") || choose.getSelectedTrack().equals("colemanraceway")))
        {
            race.setTrack(choose.getSelectedTrack());
            Transition.switchState("race");
            state = "transition";
        }
    }

    public void draw(Graphics g)
    {
        back.render(g);
        choose.render(g, karts[currentKart], quips[currentKart], xOffset, currentKart, karts.length);
        for(Bar b : statBars)
        {
            b.render(g, xOffset);
        }
    }
}
