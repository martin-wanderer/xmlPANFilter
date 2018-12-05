package ru.porozhnetalv;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class PANExtractor {

    static void processFilePair(String xlsFileName, String xmlFileName) throws IOException, ParserConfigurationException, SAXException, TransformerException {

        Set<String> xlsPans = PANExtractor.getXlsPANs(xlsFileName);

        Document originXml = PANExtractor.getDocument(xlsPans, xmlFileName);

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer t = tf.newTransformer();
        t.transform(new DOMSource(originXml), new StreamResult(new FileOutputStream(xmlFileName)));
    }

    static Document getDocument(Set<String> xlsPans, String xmlFileName) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

        Document originXml = documentBuilder.parse(xmlFileName);

        Element file = (Element) originXml.getFirstChild();
        Element requestList = (Element) file.getElementsByTagName("requestList").item(0);
        NodeList nodes = requestList.getChildNodes();
        Set<Node> nodesToRemove = new HashSet<>();

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if ("request".equals(node.getNodeName())) {
                Element request = (Element) nodes.item(i);
                Element bank = (Element) request.getElementsByTagName("bank").item(0);
                if (xlsPans.contains(bank.getElementsByTagName("pan").item(0).getTextContent())) {
                    nodesToRemove.add(node);
                }
            } else {
                if (nodesToRemove.contains(node.getPreviousSibling())) {
                    nodesToRemove.add(node);
                }
            }
        }

        for (Node node: nodesToRemove) {
            node.getParentNode().removeChild(node);
        }
        return originXml;
    }

    static Set<String> getXlsPANs(String xlsFileName) throws IOException {
        Workbook wb = WorkbookFactory.create(new File(xlsFileName));
        Sheet sheet = wb.getSheetAt(0);
        Set<String> xlsPans = new HashSet<>();

        for (Row row : sheet) {
            xlsPans.add(row.getCell(row.getLastCellNum() - 1).getStringCellValue().replaceAll("\\D", ""));
        }
        return xlsPans;
    }
}
