package pvt.finalproject.model;

import java.util.Date;
import java.util.List;

public class Weather {
    private int id;
    private Date date;
    private String description;
    private int humidity;
    private List<String> location;
    private int temp_max;
    private int temp_min;
    private String title;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getHumidity() {
        return humidity;
    }
    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
    public List<String> getLocation() {
        return location;
    }
    public void setLocation(List<String> location) {
        this.location = location;
    }
    public int getTemp_max() {
        return temp_max;
    }
    public void setTemp_max(int temp_max) {
        this.temp_max = temp_max;
    }
    public int getTemp_min() {
        return temp_min;
    }
    public void setTemp_min(int temp_min) {
        this.temp_min = temp_min;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result
                + ((description == null) ? 0 : description.hashCode());
        result = prime * result + humidity;
        result = prime * result + id;
        result = prime * result
                + ((location == null) ? 0 : location.hashCode());
        result = prime * result + temp_max;
        result = prime * result + temp_min;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Weather other = (Weather) obj;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (humidity != other.humidity)
            return false;
        if (id != other.id)
            return false;
        if (location == null) {
            if (other.location != null)
                return false;
        } else if (!location.equals(other.location))
            return false;
        if (temp_max != other.temp_max)
            return false;
        if (temp_min != other.temp_min)
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "Element [id=" + id + ", date=" + date + ", description="
                + description + ", humidity=" + humidity + ", location="
                + location + ", temp_max=" + temp_max + ", temp_min="
                + temp_min + ", title=" + title + "]";
    }



}
