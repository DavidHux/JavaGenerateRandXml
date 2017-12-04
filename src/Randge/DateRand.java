package Randge;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateRand {
    Date date;

    public DateRand() {
        date = new Date();
    }

    public String getDay() {
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
        return ft.format(date);
    }

    public String getTime() {
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddhhmmss");
        return ft.format(date);
    }
}
