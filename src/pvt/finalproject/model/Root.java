package pvt.finalproject.model;

import java.util.Date;
import java.util.List;

public class Root {
    private Date date;
    private String name;
    private List<Weather> weather;

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Weather> getWeather() {
        return weather;
    }
    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        return "Root [date=" + date + ", name=" + name + ", weather=" + weather
                + "]";
    }




}

