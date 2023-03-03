package csv.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class myXMLReader {
    private String enabledLoad, enabledSave, enabledLog;
    private String fileNameLoad, fileNameSave, fileNameLog;
    private String formatLoad, formatSave;

    public void read() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse("shop.xml");

            NodeList nodeList = doc.getElementsByTagName("load");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (Node.ELEMENT_NODE == node.getNodeType()) {
                    Element element = (Element) node;
                    enabledLoad = element.getElementsByTagName("enabled").item(0).getTextContent();
                    fileNameLoad = element.getElementsByTagName("fileName").item(0).getTextContent();
                    formatLoad = element.getElementsByTagName("format").item(0).getTextContent();
                }
            }
            nodeList = doc.getElementsByTagName("save");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (Node.ELEMENT_NODE == node.getNodeType()) {
                    Element element = (Element) node;
                    enabledSave = element.getElementsByTagName("enabled").item(0).getTextContent();
                    fileNameSave = element.getElementsByTagName("fileName").item(0).getTextContent();
                    formatSave = element.getElementsByTagName("format").item(0).getTextContent();
                }
            }
            nodeList = doc.getElementsByTagName("log");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (Node.ELEMENT_NODE == node.getNodeType()) {
                    Element element = (Element) node;
                    enabledLog = element.getElementsByTagName("enabled").item(0).getTextContent();
                    fileNameLog = element.getElementsByTagName("fileName").item(0).getTextContent();
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.getMessage();
        }
    }

    public String getEnabledLoad() {
        return enabledLoad;
    }

    public String getEnabledSave() {
        return enabledSave;
    }

    public String getEnabledLog() {
        return enabledLog;
    }

    public String getFileNameLoad() {
        return fileNameLoad;
    }

    public String getFileNameSave() {
        return fileNameSave;
    }

    public String getFileNameLog() {
        return fileNameLog;
    }

    public String getFormatLoad() {
        return formatLoad;
    }

    public String getFormatSave() {
        return formatSave;
    }
}
