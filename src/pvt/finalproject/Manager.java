package pvt.finalproject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pvt.finalproject.model.Root;
import pvt.finalproject.model.Status;
import pvt.finalproject.model.Weather;
import pvt.finalproject.parse.Json;
import pvt.finalproject.parse.ParseException;
import pvt.finalproject.parse.Parser;
import pvt.finalproject.parse.Xml;

public class Manager {
    private static final String LINK_XML = "http://kiparo.ru/t/weather.xml";
    private static final String LINK_JSON = "http://kiparo.ru/t/weather.json";
    private static final String XML = "xml";
    private static final String JSON = "json";
    private Root root;
    private static final Map<String, String> LINK_MAP = new HashMap<>();
    static {
        LINK_MAP.put(XML, LINK_XML);
        LINK_MAP.put(JSON, LINK_JSON);
    }
    private static final Map<String, Parser> PARSER_MAP = new HashMap<>();
    static {
        PARSER_MAP.put(XML, new Xml());
        PARSER_MAP.put(JSON, new Json());
    }
    private static final Map<String, Integer> MONTH_MAP = new HashMap<>();
    static{
        MONTH_MAP.put("january", 0);
        MONTH_MAP.put("february", 1);
        MONTH_MAP.put("march", 2);
        MONTH_MAP.put("april", 3);
        MONTH_MAP.put("may", 4);
        MONTH_MAP.put("june", 5);
        MONTH_MAP.put("july", 6);
        MONTH_MAP.put("august", 7);
        MONTH_MAP.put("september", 8);
        MONTH_MAP.put("october", 9);
        MONTH_MAP.put("november", 10);
        MONTH_MAP.put("december", 11);
    }

//	Thread thread = new Thread(new Runnable(){
//
//		@Override
//		public void run() {
//			loadData();
//		}
//
//
//
//

    public Status loadData(String inputData) {
        Parser parser;
        InputStream inputStream = null;
        String currentLink = LINK_MAP.get(inputData);


        try {
            URL url = new URL(currentLink);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url
                    .openConnection();

            int responseCode = httpURLConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {

                inputStream = httpURLConnection.getInputStream();

                parser = PARSER_MAP.get(inputData);
                root = parser.parse(inputStream);

            }

        }catch(ParseException e){
            return Status.PARSER_ERROR;
        }

        catch (Exception e) {
            //e.printStackTrace();
            return Status.INTERNET_ERROR;

        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }

            } catch (Exception e) {

            }
        }

        System.out.println(root.toString());
        return Status.NO_ERROR;
    }

    public String getName(){
        return root.getName();
    }

    public Date getCurrentUpdate(){
        return root.getDate();
    }

    public List<Weather> findWeatherByMonth(String month){

        GregorianCalendar calendar = new GregorianCalendar();

        List<Weather> weathers = root.getWeather();
        List<Weather> foundWeathers = new ArrayList<Weather>();

//		for(Weather weather: root.getWeather()){
//			calendar.setTime(weather.getDate());
//			if (MONTH_MAP.get(month) == calendar.get(GregorianCalendar.MONTH)){
//				foundWeathers.add(weather);
//			}
//		}

        for(int i = 0; i < weathers.size(); i++){

            calendar.setTime(weathers.get(i).getDate());
            if (MONTH_MAP.get(month) == calendar.get(GregorianCalendar.MONTH)){
                foundWeathers.add(weathers.get(i));
            }

        }
        return foundWeathers;

    }

    public List<Weather> findCity(String city){

        List<Weather> weathers = root.getWeather();
        List<Weather> cityWeathers = new ArrayList<Weather>();
        String[] showMe;
        int firstIndex = 0;

        for(int i = 0; i < weathers.size(); i++){
            for(int j = 0; j < weathers.get(i).getLocation().size(); j++){
                showMe = (weathers.get(i).getLocation().get(j).split(","));
                if (city.equals(showMe[firstIndex])){
                    cityWeathers.add(weathers.get(i));
                }
//				if (city.equals(weathers.get(i).getLocation().get(j))){
//					cityWeathers.add(weathers.get(i));
//
//				}
            }

        }


        return cityWeathers;
    }
}