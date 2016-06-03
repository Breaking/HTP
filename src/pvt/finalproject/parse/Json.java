package pvt.finalproject.parse;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import pvt.finalproject.model.Root;
import pvt.finalproject.model.Weather;

public class Json implements Parser {

    @Override
    public Root parse(InputStream inputStream) throws pvt.finalproject.parse.ParseException {

        Root rootTag = new Root();
        JSONParser parser = new JSONParser();

        try {

            JSONObject obj = (JSONObject) parser.parse(new InputStreamReader(
                    inputStream, "UTF-8"));

            String date = (String) obj.get(TAG_DATE);
            rootTag.setDate(parseDate((date)));

            String name = (String) obj.get(TAG_NAME);
            rootTag.setName(name);

            JSONArray jsonArray = (JSONArray) obj.get(TAG_WEATHER);

            List<Weather> list = new ArrayList<Weather>();

            for (int i = 0; i < jsonArray.size(); i++) {

                Weather el = new Weather();

                JSONObject jsonObject = (JSONObject) jsonArray.get(i);

                long id = (long) jsonObject.get(TAG_ID);
                el.setId((int) id);

                String dateWeather = (String) jsonObject.get(TAG_DATE);
                el.setDate(parseDate(dateWeather));

                String title = (String) jsonObject.get(TAG_TITLE);
                el.setTitle(title);

                String description = (String) jsonObject.get(TAG_DESCRIPTION);
                el.setDescription(description);

                long humidity = (long) jsonObject.get(TAG_HUMIDITY);
                el.setHumidity((int) humidity);

                JSONArray jsonArray2 = (JSONArray) jsonObject.get(TAG_LOCATION);

                el.setLocation(parseLocations(jsonArray2));

                long temp_max = (long) jsonObject.get(TAG_TEMP_MAX);
                el.setTemp_max((int) temp_max);

                long temp_min = (long) jsonObject.get(TAG_TEMP_MIN);
                el.setTemp_min((int) temp_min);

                list.add(el);

            }

            rootTag.setWeather(list);
            return rootTag;

        } catch (Exception e) {
            throw new pvt.finalproject.parse.ParseException();
        }

    }

    private List<String> parseLocations(JSONArray locationsArray) {
        List<String> locations = new ArrayList<String>();

        for (int j = 0; j < locationsArray.size(); j++) {

            String location = (String) locationsArray.get(j);
            locations.add(location);

        }

        return locations;
    }

    private Date parseDate(String dateString) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss Z");

        return dateFormat.parse(dateString);

    }

}
