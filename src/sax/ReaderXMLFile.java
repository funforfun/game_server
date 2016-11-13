package sax;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class ReaderXMLFile {
    public static Object readXML(String xmlFile) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            //SaxHandler handler = new SaxHandler();
            SaxHandler handler = new SaxHandler();
            saxParser.parse(xmlFile, handler);

            // TODO: object -> objects
            Object object = handler.getObject();

            return object;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
