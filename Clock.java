import java.time.*;

public class Clock 
{
    private Instant start;
    private Instant end;

    public void startClock()
    {
        start = Instant.now();
    }

    public void endClock()
    {
        end = Instant.now();
    }

    public Long timeSinceStart()
    {
        return Duration.between(start, Instant.now()).toMillis();
    }

    public Long overallTime()
    {
        return Duration.between(start, end).toMillis();
    }

    public static String displayTime(Long milliseconds)
    {
        return (int) (milliseconds / 60000) + ":" + standardizeDigits((int) ((milliseconds % 60000) / 1000), 2) + "." + standardizeDigits((int) (milliseconds % 1000), 3);
    }
    
    public static String standardizeDigits(int time, int digits)
    {
        String str = String.valueOf(time);
        int startLen = str.length();
        for(int i = 0; i < digits - startLen; i++)
        {
            str = "0" + str;
        }
        return str;
    }
}
