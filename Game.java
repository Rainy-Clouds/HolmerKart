import java.awt.*;

public class Game
{
    private String gameState;
    private RaceManager race = new RaceManager();
    private MenuManager menu = new MenuManager();
    private TitleManager title = new TitleManager();

    public Game(String state)
    {
        gameState = state;
    }

    public String getState()
    {
        return gameState;
    }

    public void setState(String state)
    {
        gameState = state;
        if(state.equals("race"))
        {
            race.startNewRace();
        }
        if(state.equals("menu"))
        {
            menu.resetMenu();
            race.endRace();
            race.getPlayer().prepare();
            UserInterface.reset();
        }
    }

    public RaceManager getRaceManager()
    {
        return race;
    }

    public MenuManager getMenuManager()
    {
        return menu;
    }

    public TitleManager getTitleManager()
    {
        return title;
    }

    public void tick(boolean[] keyMap)
    {
        switch(gameState)
        {
            case("race"):
                race.update(keyMap);
                break;
            case("menu"):
                menu.update();
                break;
            case("title"):
                title.update();
                break;
            default:
                System.out.println("No game state!");
                break;
        }
    }

    public void paint(Graphics g)
    {
        switch(gameState)
        {
            case("race"):
                race.draw(g);
                break;
            case("menu"):
                menu.draw(g);
                break;
            case("title"):
                title.draw(g);
                break;
            default:
                System.out.println("No game state!");
                break;
        }
        Transition.draw(g, this);
    }
}