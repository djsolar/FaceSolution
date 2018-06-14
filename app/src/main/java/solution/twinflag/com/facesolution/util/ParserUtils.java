package solution.twinflag.com.facesolution.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import solution.twinflag.com.facesolution.domain.WeatherInfo;

public class ParserUtils {

    public static WeatherInfo parserWeather(String xml) {
        WeatherInfo weatherInfo = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            weatherInfo = new WeatherInfo();
            DocumentBuilder db = factory.newDocumentBuilder();
            InputStream iStream=new ByteArrayInputStream(xml.getBytes());
            Document document = db.parse(iStream);
            Element root = document.getDocumentElement();
            NodeList nodeList = root.getElementsByTagName("wendu");
            Node wenduNode = nodeList.item(0).getChildNodes().item(0);
            System.out.println(wenduNode.getTextContent());
            weatherInfo.setCurrentTempature(wenduNode.getTextContent());

            nodeList = document.getElementsByTagName("weather").item(0).getChildNodes();
            for(int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeName().equals("high")) {
                    weatherInfo.setHigh(node.getFirstChild().getTextContent());
                } else if (node.getNodeName().equals("low")) {
                    weatherInfo.setLow(node.getFirstChild().getTextContent());
                } else if (node.getNodeName().equals("day")) {
                    weatherInfo.setType(node.getFirstChild().getFirstChild().getTextContent());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return weatherInfo;
    }
}
