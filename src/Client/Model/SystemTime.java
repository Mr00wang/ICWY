package Client.Model;

import java.text.SimpleDateFormat;
import java.util.SimpleTimeZone;
import java.util.Date;

public class SystemTime {
    private String date = "";
    public String getSystemTime()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM--dd HH:mm:ss");
        Date current_time = new Date();
        date = formatter.format(current_time);
        return date;
    }
}
