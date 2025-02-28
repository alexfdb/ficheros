package com.alexfdb.util;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.alexfdb.model.Empleado;

public class XmlUtil {
    
    private String path;
    private File file;

    public XmlUtil() {
        path = "";
        file = new File(path);
        try {
            if(!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Set<Empleado> xmlToSet() {
        Set<Empleado> empleados = new HashSet<>();
        try {
            Document document = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .parse(new File(path));

                NodeList nodeList = document.getElementsByTagName("empleado");

                for (int i = 0; i < nodeList.getLength(); i++) {
                    Element element = (Element) nodeList.item(i);

                    String id = element.getElementsByTagName("id").item(0).getTextContent();
                    String nombre = element.getElementsByTagName("nombre").item(0).getTextContent();
                    String puesto = element.getElementsByTagName("puesto").item(0).getTextContent();
                    Float sueldo = Float.parseFloat(element.getElementsByTagName("sueldo").item(0).getTextContent());
                    Boolean alta = Boolean.parseBoolean(element.getElementsByTagName("alta").item(0).getTextContent());

                    empleados.add(new Empleado(id, nombre, puesto, sueldo, alta));
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empleados;
    }

    public boolean setToXml(Set<Empleado> empleados) {
        try {

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}