package Randge;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class XmlFileRand {
    private String[] dataAttrs = {"MsgId", "MsgType", "OccurUnixTime", "OccurTime", "WasteID", "StaId", "LaneId",
            "TransFlag", "Msg", "MsgLen", "CapImageFilesName", "VLPImageFilesName"};
    private int[] dataTypes = {1, 1, 2, 2, 3, 7, 8, 4, 5, 6, 4, 4}; //1: int, 2: time, 3: wasteID, 4: 0, 5: msg, 6:msglen
    private int msgLen;
    private int wasteStart;
    private int stationID;
    private int laneID;
    private String wasteID;
    private String msg;
    private IntRand intrand = new IntRand(1, 5);
    private DateRand daterand;
    private StringRand stringrand;
    private String outputPath = "/Users/davidhu/Desktop/generateWaste/";

    public XmlFileRand() {
        this.msgLen = 500;
        this.wasteStart = 0;
        this.stationID = 111;
        this.laneID = 2;
    }

    public XmlFileRand(int wasteStart, int stationID, int laneID) {
        this.msgLen = 100;
        this.wasteStart = wasteStart;
        this.stationID = stationID;
        this.laneID = laneID;
    }

    public void getXmlFile() {
        this.stringrand = new StringRand(this.msgLen);
        String msg = this.stringrand.getString();
        this.getXmlFile(msg);
    }

    public void getXmlFile(String msg) {

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // root element
            Element rootElement = doc.createElement("datas");
            doc.appendChild(rootElement);

            Attr rootattr1 = doc.createAttribute("tablename");
            rootattr1.setValue("tbl_LaneWaste");
            rootElement.setAttributeNode(rootattr1);
            Attr rootattr2 = doc.createAttribute("pk");
            rootattr2.setValue("WasteID");
            rootElement.setAttributeNode(rootattr2);

            // supercars element
            Element data = doc.createElement("data");
            rootElement.appendChild(data);

            for (int i = 0; i < dataAttrs.length; i++) {
                Element attribute = doc.createElement("attribute");
                Attr attr1 = doc.createAttribute("name");
                attr1.setValue(dataAttrs[i]);
                attribute.setAttributeNode(attr1);
                Attr attr2 = doc.createAttribute("value");
                switch (dataTypes[i]) {
                    case 1:
                        attr2.setValue("" + intrand.getInt());
                        break;
                    case 2:
                        this.daterand = new DateRand();
                        attr2.setValue(daterand.getTime());
                        break;
                    case 3:
                        this.wasteID = "" + this.stationID + this.laneID + daterand.getTime() + this.wasteStart;
                        attr2.setValue(this.wasteID);
                        break;
                    case 4:
                        attr2.setValue("");
                        break;
                    case 5:
                        attr2.setValue(msg);
                        break;
                    case 6:
                        attr2.setValue("" + msg.length());
                        break;
                    case 7:
                        attr2.setValue("" + this.stationID);
                        break;
                    case 8:
                        attr2.setValue("" + this.laneID);
                        break;
                    default:
                        System.out.println("error switch");
                }
                attribute.setAttributeNode(attr2);
                data.appendChild(attribute);
            }


            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(outputPath + this.wasteID + ".xml"));
            transformer.transform(source, result);
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
            this.wasteStart++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
