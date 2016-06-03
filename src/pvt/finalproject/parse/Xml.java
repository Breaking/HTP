package pvt.finalproject.parse;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import pvt.finalproject.model.Root;
import pvt.finalproject.model.Weather;

public class Xml implements Parser {

    private static final DocumentBuilderFactory DBF = DocumentBuilderFactory
            .newInstance();

    @Override
    public Root parse(InputStream inputStream) throws pvt.finalproject.parse.ParseException {

        try {

            Document dom;

            DocumentBuilder db = DBF.newDocumentBuilder();
            dom = db.parse(inputStream);

            Root rootTag = new Root();

            Element root = dom.getDocumentElement();
            System.out.println("tag 1 = " + root.getTagName());

            NodeList dateNodeList = root.getElementsByTagName(TAG_DATE);
            Node dateNode = dateNodeList.item(0);
            System.out.println("tag 2 = " + dateNode.getNodeName());

            String date = dateNode.getFirstChild().getNodeValue();
            System.out.println("	date = " + date);

            rootTag.setDate(parseDate((date)));

            NodeList nameNodeList = root.getElementsByTagName(TAG_NAME);
            Node nameNode = nameNodeList.item(0);
            System.out.println("tag 3 = " + nameNode.getNodeName());

            String name = nameNode.getFirstChild().getNodeValue();
            System.out.println("	name = " + name);

            rootTag.setName(name);

            NodeList weatherNodeList = root.getElementsByTagName(TAG_WEATHER);
            Node weatherNode = weatherNodeList.item(0);
            System.out.println("tag 4 = " + weatherNode.getNodeName());

            NodeList elementsNodeList = weatherNode.getChildNodes();

            ArrayList<Weather> list = new ArrayList<Weather>();

            for (int i = 0; i < elementsNodeList.getLength(); i++) {


                Weather el = new Weather();

                Node node = elementsNodeList.item(i);

                if (node.getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }

                System.out
                        .println("========================================================");

                Element element = (Element) node;
                System.out.println("tag = " + node.getNodeName());

                // id
                String idWeather = element.getElementsByTagName(TAG_ID).item(0)
                        .getTextContent();
                System.out.println("	id : " + idWeather);

                el.setId(Integer.valueOf(idWeather));

                // date
                String dateWeather = element.getElementsByTagName(TAG_DATE).item(0)
                        .getTextContent();
                System.out.println("	date : " + dateWeather);

                el.setDate(parseDate(dateWeather));

                // description
                String descriptionWeather = element
                        .getElementsByTagName(TAG_DESCRIPTION).item(0)
                        .getTextContent();
                System.out.println("	description : " + descriptionWeather);

                el.setDescription(descriptionWeather);

                // humidity
                String humidityWeather = element.getElementsByTagName(TAG_HUMIDITY)
                        .item(0).getTextContent();
                System.out.println("	humidity : " + humidityWeather);

                el.setHumidity((Integer.valueOf(humidityWeather)));

                // tag : location
                NodeList locationNodeList = element
                        .getElementsByTagName(TAG_LOCATION);
                Node locationNode = locationNodeList.item(0);
                System.out.println("	tag = " + locationNode.getNodeName());

                // getting locations
                NodeList locationsNodeList = locationNode.getChildNodes();

                el.setLocation(parseLocations(locationsNodeList));

                // temp_max
                String tempMaxWeather = element.getElementsByTagName(TAG_TEMP_MAX)
                        .item(0).getTextContent();
                System.out.println("	temp max : " + tempMaxWeather);

                el.setTemp_max((Integer.valueOf(tempMaxWeather)));

                // temp_min
                String tempMinWeather = element.getElementsByTagName(TAG_TEMP_MIN)
                        .item(0).getTextContent();
                System.out.println("	temp min : " + tempMinWeather);

                el.setTemp_min((Integer.valueOf(tempMinWeather)));

                // title
                String titleWeather = element.getElementsByTagName(TAG_TITLE)
                        .item(0).getTextContent();
                System.out.println("	title : " + titleWeather);

                el.setTitle(titleWeather);

                list.add(el);
            }

            rootTag.setWeather(list);

            return rootTag;
        } catch(Exception e){
            throw new pvt.finalproject.parse.ParseException();
        }
    }

    private List<String> parseLocations(NodeList locationsNodeList) {
        List<String> locations = new ArrayList<String>();

        for (int j = 0; j < locationsNodeList.getLength(); j++) {

            Node locationsNode = locationsNodeList.item(j);

            if (locationsNode.getNodeType() != Node.ELEMENT_NODE) {
                continue;
            }

            Element elementLocation = (Element) locationsNode;
            System.out.println("		tag = " + locationsNode.getNodeName());
            System.out.println("			location : "
                    + elementLocation.getFirstChild().getNodeValue());

            locations.add(elementLocation.getFirstChild().getNodeValue());

        }
        return locations;
    }

    private Date parseDate(String dateString) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd hh:mm:ss Z");

        return dateFormat.parse(dateString);

    }

}
