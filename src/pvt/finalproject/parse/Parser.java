package pvt.finalproject.parse;

import java.io.InputStream;

import pvt.finalproject.model.Root;

public interface Parser {
    String TAG_NAME = "name";
    String TAG_DATE = "date";
    String TAG_WEATHER = "weather";
    String TAG_ID = "id";
    String TAG_DESCRIPTION = "description";
    String TAG_HUMIDITY = "humidity";
    String TAG_LOCATION = "location";
    String TAG_TEMP_MAX = "temp_max";
    String TAG_TEMP_MIN = "temp_min";
    String TAG_TITLE = "title";

    Root parse(InputStream inputStream) throws ParseException;

}

