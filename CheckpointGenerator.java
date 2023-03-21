import java.util.ArrayList;

public class CheckpointGenerator 
{
    public static ArrayList<Checkpoint> generate(String trackname)
    {
        ArrayList<Checkpoint> checks = new ArrayList<Checkpoint>();
        // ORDER MATTERS!!!!
        if(trackname.equals("beta"))
        {
            checks.add(new Checkpoint(100, 825, 600, 50, 0, 610, 270));
            checks.add(new Checkpoint(1325, 0, 50, 600, 880, 0, 0));
            checks.add(new Checkpoint(2500, 925, 600, 50, 2400, 580, 90));
            checks.add(new Checkpoint(1825, 1200, 50, 600, 1520, 1200, 180));
        }
        if(trackname.equals("colemanraceway"))
        {
            checks.add(new Checkpoint(800, 1925, 800, 50, 800, 1725, 270));
            checks.add(new Checkpoint(2850, 1400, 50, 600, 880, 0, 0));
            checks.add(new Checkpoint(5600, 1300, 600, 50, 2400, 580, 90));
            checks.add(new Checkpoint(2750, 2400, 50, 600, 1520, 1200, 180));
        }
        return checks;
    }
}
