import java.util.ArrayList;
import java.util.Arrays;

public class Stats 
{
    // it goes [acceleration, topSpeed, offroading, traction, turnSpeed, tateFactor]
    public static double[] standard = {0.6, 10, 6, 0.25, 3, 5};
    public static double[] cozycoupe = {1, 9, 8, 0.1, 4, 1};
    public static double[] fordf150 = {0.275, 12, 9, 0.3, 2, 8};
    public static double[] electricitymcqueen = {0.85, 10, 4, 0.3, 4.5, 6};
    public static double[] dollarvan = {0.12, 15, 5, 0.25, 3, 5};
    public static double[] gorillamobile = {0.5, 10, 7, 0.5, 4, 3};
    public static double[] lachancla = {0.3, 11, 7, 0.05, 3, 2};
    public static double[] tatesbugatti = {0.625, 11, 4, 0.35, 3, 10};

    public static double[][] allStats = {standard, cozycoupe, fordf150, electricitymcqueen, dollarvan, gorillamobile, lachancla, tatesbugatti};

    public static ArrayList<String> names = new ArrayList<String>(Arrays.asList("standard", "cozycoupe", "fordf150", "electricitymcqueen", "dollarvan", "gorillamobile", "lachancla", "tatesbugatti"));
    public static ArrayList<String> stats = new ArrayList<String>(Arrays.asList("acceleration", "top speed", "offroading", "traction", "turn speed"));

    public static double getStat(String name, String statToGet)
    {
        int index = names.indexOf(name);
        int stat = stats.indexOf(statToGet);
        if(index == -1 || stat == -1)
        {
            return 0;
        }
        return allStats[index][stat];
    }

    public static double getStat(String name, int statToGet)
    {
        int index = names.indexOf(name);
        if(index == -1)
        {
            return 0;
        }
        return allStats[index][statToGet];
    }
}
