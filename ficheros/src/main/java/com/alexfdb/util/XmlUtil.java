package com.alexfdb.util;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.alexfdb.model.Empleado;

public class XmlUtil {
    
    private String path;
    private File file;

    public XmlUtil() {
        path = "src/main/resources/empleados.xml";
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
                .parse(file);

                NodeList nodeList = document.getElementsByTagName("empleado");

                for (int i = 0; i < nodeList.getLength(); i++) {
                    Element element = (Element) nodeList.item(i);

                    String id = element.getElementsByTagName("id").item(0).getTextContent();
                    String nombre = element.getElementsByTagName("nombre").item(0).getTextContent();
                    String puesto = element.getElementsByTagName("puesto").item(0).getTextContent();
                    Float salario = Float.parseFloat(element.getElementsByTagName("salario").item(0).getTextContent());
                    Boolean alta = Boolean.parseBoolean(element.getElementsByTagName("alta").item(0).getTextContent());

                    empleados.add(new Empleado(id, nombre, puesto, salario, alta));
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empleados;
    }

    public boolean setToXml(Set<Empleado> empleados) {
        try {
            Document document = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .newDocument();

                Element root = document.createElement("empleados");
                document.appendChild(root);

            for (Empleado empleado : empleados) {
                Element empleadoElement = document.createElement("empleado");

                Element id = document.createElement("id");
                id.appendChild(document.createTextNode(String.valueOf(empleado.getId())));
                empleadoElement.appendChild(id);

                Element nombre = document.createElement("nombre");
                nombre.appendChild(document.createTextNode(String.valueOf(empleado.getNombre())));
                empleadoElement.appendChild(nombre);

                Element puesto = document.createElement("puesto");
                puesto.appendChild(document.createTextNode(String.valueOf(empleado.getPuesto())));
                empleadoElement.appendChild(puesto);

                Element salario = document.createElement("salario");
                salario.appendChild(document.createTextNode(String.valueOf(empleado.getSalario())));
                empleadoElement.appendChild(salario);

                Element alta = document.createElement("alta");
                alta.appendChild(document.createTextNode(String.valueOf(empleado.getAlta())));
                empleadoElement.appendChild(alta);

                root.appendChild(empleadoElement);

            }
            TransformerFactory transformerFactory = TransformerFactory.newDefaultInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            File outputFile = file;
            transformer.transform(new DOMSource(document), new StreamResult(outputFile));

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}