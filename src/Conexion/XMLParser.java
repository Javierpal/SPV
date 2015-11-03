/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Javier
 */
public class XMLParser {
    
    private Productos prod;
    
    String FileName;
    
    Path current = Paths.get("");
    String inf ="";
    
    public XMLParser(String FileName){
        this.FileName = FileName;
        inf = current.toAbsolutePath().toString()+"\\Ventas\\"+FileName+".xml";
    }
    
    public ArrayList<Productos> Parse(){
        ArrayList<Productos> a = new ArrayList<>();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder;
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new File(inf));
            
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("Producto");
            for(int i = 0; i < nList.getLength(); i++){
                Node nNode = nList.item(i);
                if(nNode.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) nNode;
                    prod = new Productos();
                    prod.setNombre(element.getElementsByTagName("nombre").item(0).getTextContent());
                    prod.setCantidad(Integer.parseInt(element.getElementsByTagName("cantidad").item(0).getTextContent()));
                    prod.setPrecio(Float.parseFloat(element.getElementsByTagName("precio").item(0).getTextContent()));
                    prod.setTotal(Float.parseFloat(element.getElementsByTagName("total").item(0).getTextContent()));
                    a.add(prod);
                }
            }
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
    
}
