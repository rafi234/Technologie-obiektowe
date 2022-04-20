import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Encoder {

    private final List<Currency> finalCurrencyList = new ArrayList<>();
    private static final String url = "https://www.nbp.pl/kursy/xml/lasta.xml";
    private static Encoder INSTANCE = null;

    private Encoder()  {
        NodeList list = getNodeListByTagName();

        for (int i = 0; i < list.getLength(); i++) {
            Node nodes = list.item(i);
            if (nodes.getNodeType() == Node.ELEMENT_NODE) {
                NodeList currencyList = nodes.getChildNodes();
                String[] tempTab = new String[4];
                int temp = 0;
                for (int j = 0; j < currencyList.getLength(); j++) {
                    Node node = currencyList.item(j);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element e = (Element) node;
                        tempTab[temp] = e.getTextContent();
                        temp++;
                    }
                }

                Currency a = new Currency(tempTab[0],
                        Double.parseDouble(tempTab[1]),
                        tempTab[2],
                        Double.parseDouble(tempTab[3].replace(",", ".")));
                finalCurrencyList.add(a);
            }
        }
    }

    public static Encoder getInstance() {
        if(INSTANCE == null)
            INSTANCE = new Encoder();
        return INSTANCE;
    }

    public void printList() {
        for (Currency a : finalCurrencyList) {
            System.out.println(a.toString());
        }
    }

    public Currency findCurrency(String currencyCode) {
        for (Currency a : finalCurrencyList) {
            if (a.checkCurrencyCode(currencyCode, a)) {
                return a;
            }
        }
        System.out.println("BŁĄD: Nie znaleziono podanej waluty. Spróbuj ponownie.");
        return null;
    }

    private NodeList getNodeListByTagName(){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document doc = null;
        try {
            assert builder != null;
            doc = builder.parse(url);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        assert doc != null;

        return doc.getElementsByTagName("pozycja");
    }

}
