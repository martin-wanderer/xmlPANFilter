package ru.porozhnetalv;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class UnprocessedPANExtractor {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, TransformerException {

        PANExtractor.processFilePair(args[0], args[1]);
    }

}
