package src;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public abstract class City implements IClock, Comparable {

    String name;
    String cityCode;
    int gmtDiff;

    public City(String name, String cityCode, int gmtDiff) {
        this.name = name;
        this.cityCode = cityCode;
        this.gmtDiff = gmtDiff;
    }

    @Override
    public int compareTo(Object o) {
        City city = (City)o;
        return city.name.compareTo(this.name);
    }

    @Override
    public void showTime() {
        Calendar currentGmtCalendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        currentGmtCalendar.add(Calendar.HOUR_OF_DAY, this.gmtDiff);
        System.out.println("Time in " + this.name + " " + currentGmtCalendar.getTime());
    }

    @Override
    public String toString() {
        return this.name;
    }
}
