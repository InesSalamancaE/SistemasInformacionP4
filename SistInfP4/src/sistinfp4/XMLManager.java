/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistinfp4;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 *
 * @author albam
 */
public class XMLManager {
    public static void exportaXmlErroresNIF(String archivo, Map trabajadores){
   
        try {
            // Creo una instancia de DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // Creo un documentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();
            // Creo un DOMImplementation
            DOMImplementation implementation = builder.getDOMImplementation();
 
            // Creo un documento con un elemento raiz
            Document documento = implementation.createDocument(null, archivo, null);
            documento.setXmlVersion("1.0");
            Element root = documento.createElement("Trabajadores");
             //Recorro el Map de trabajadores y los inserto en el XML
           
            Iterator<Integer> it = trabajadores.keySet().iterator();
            while(it.hasNext()){
                Integer clave = it.next();
                Trabajador t= (Trabajador)trabajadores.get(clave);
                Element trabajador = documento.createElement("Trabajador");
                trabajador.setAttribute("id", Integer.toString(t.getId()));
                
                //NIF
                Element nif = documento.createElement("NIF_NIE");
                Text textNIF = documento.createTextNode(t.getNIF());
                nif.appendChild(textNIF);
                trabajador.appendChild(nif);
                
                //Nombre
                Element nombre = documento.createElement("Nombre");
                Text textNombre = documento.createTextNode(t.getNombre());
                nombre.appendChild(textNombre);
                trabajador.appendChild(nombre);
                
                //Primer Apellido
                Element apellido1 = documento.createElement("PrimerApellido");
                Text textApellido1 = documento.createTextNode(t.getPrimerApellido());
                apellido1.appendChild(textApellido1);
                trabajador.appendChild(apellido1);
                
                //Segundo Apellido
                Element apellido2 = documento.createElement("SegundoApellido");
                Text textApellido2 = documento.createTextNode(t.getSegundoApellido());
                apellido2.appendChild(textApellido2);
                trabajador.appendChild(apellido2);
                
                //Empresa
                Element empresa = documento.createElement("Empresa");
                Text textEmpresa = documento.createTextNode(t.getEmpresa());
                empresa.appendChild(textEmpresa);
                trabajador.appendChild(empresa);
                
                //Categoría
                Element categoria = documento.createElement("Categoria");
                Text textCategoria = documento.createTextNode(t.getCategoria());
                categoria.appendChild(textCategoria);
                trabajador.appendChild(categoria);
                
                
                root.appendChild(trabajador);
            }
                        
            documento.getDocumentElement().appendChild(root);
 
           
            
            
            // Asocio el source con el Document
            Source source = new DOMSource(documento);
            // Creo el Result, indicado que fichero se va a crear
            Result result = new StreamResult(new File(archivo));
 
            // Creo un transformer, se crea el fichero XML
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            transformer.transform(source, result);
 
        } catch (ParserConfigurationException | TransformerException ex) {
            System.out.println(ex.getMessage());
        }
 
    } //fin del método exportalXML
    
    
    public static void exportaXmlErroresCCC(String archivo,Map trabajadores){
   
        try {
            // Creo una instancia de DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // Creo un documentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();
            // Creo un DOMImplementation
            DOMImplementation implementation = builder.getDOMImplementation();
 
            // Creo un documento con un elemento raiz
            Document documento = implementation.createDocument(null, archivo, null);
            documento.setXmlVersion("1.0");
            Element root = documento.createElement("Cuentas");
             //Recorro el Map de trabajadores y los inserto en el XML
           
            Iterator<Integer> it = trabajadores.keySet().iterator();
            while(it.hasNext()){
                Integer clave = it.next();
                Trabajador t= (Trabajador)trabajadores.get(clave);
                Element cuenta= documento.createElement("cuenta");
                cuenta.setAttribute("id", Integer.toString(t.getId()));
                
                //Nombre
                Element nombre = documento.createElement("Nombre");
                Text textNombre = documento.createTextNode(t.getNombre());
                nombre.appendChild(textNombre);
                cuenta.appendChild(nombre);
                
                //Primer Apellido
                Element apellido1 = documento.createElement("PrimerApellido");
                Text textApellido1 = documento.createTextNode(t.getPrimerApellido());
                apellido1.appendChild(textApellido1);
                cuenta.appendChild(apellido1);
                
                //Segundo Apellido
                Element apellido2 = documento.createElement("SegundoApellido");
                Text textApellido2 = documento.createTextNode(t.getSegundoApellido());
                apellido2.appendChild(textApellido2);
                cuenta.appendChild(apellido2);
                
                //Empresa
                Element empresa = documento.createElement("Empresa");
                Text textEmpresa = documento.createTextNode(t.getEmpresa());
                empresa.appendChild(textEmpresa);
                cuenta.appendChild(empresa);
                
                //Cuenta
                Element categoria = documento.createElement("Cuenta");
                Text textCategoria = documento.createTextNode(t.getCuenta());
                categoria.appendChild(textCategoria);
                cuenta.appendChild(categoria);
                
                //IBAN
                Element iban = documento.createElement("IBAN");
                Text textIban = documento.createTextNode(t.getIban());
                iban.appendChild(textIban);
                cuenta.appendChild(iban);
                
                root.appendChild(cuenta);
            }
                        
            documento.getDocumentElement().appendChild(root);
 
           
            
            
            // Asocio el source con el Document
            Source source = new DOMSource(documento);
            // Creo el Result, indicado que fichero se va a crear
            Result result = new StreamResult(new File(archivo));
 
            // Creo un transformer, se crea el fichero XML
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            transformer.transform(source, result);
 
        } catch (ParserConfigurationException | TransformerException ex) {
            System.out.println(ex.getMessage());
        }
 
    } //fin del método exportalXML
    
    public static void exportaXmlNominas(String archivo, Map nominas){
        //ESTO AÚN NO FUNCIONA, POR ESO ESTÁ COMENTADO
    //Acceder al execel:
    //FileInputStream archivoExcelNombre = new FileInputStream(new File("ruta_del_archivo_excel"));
    //HSSFWorkbook libroExcelNombre = new HSSFWorkbook(archivoExcel);
    
    //Creamos una instanciade la clase HSSFSheet, que representa el excel
    //HSSFSheet hojaExcel = libroExcel.getSheet("nombre_de_la_hoja");

    //Lo mismo para la fila
    //HSSFRow filaExcel = hojaExcel.getRow(numero_de_fila);

    //Celada
    //HSSFCell celdaExcel = filaExcel.getCell(numero_de_columna);

    //Obtenemos el valor de la celda
    //String valorCelda = celdaExcel.getStringCellValue();


        try {
            // Creo una instancia de DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // Creo un documentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();
            // Creo un DOMImplementation
            DOMImplementation implementation = builder.getDOMImplementation();
 
            // Creo un documento con un elemento raiz
            Document documento = implementation.createDocument(null, archivo, null);
            documento.setXmlVersion("1.0");
            Element root = documento.createElement("Nominas");
            root.setAttribute("fechaNomina", archivo); //Falta ver como paso la fecha de la consola
             //Recorro el Map de nominas y los inserto en el XML
           
            Iterator<Integer> it = nominas.keySet().iterator();
            while(it.hasNext()){
                Integer clave = it.next();
                Nomina n = (Nomina)nominas.get(clave);
                Element nomina = documento.createElement("Nomina");
                //nomina.setAttribute("idNomina", Integer.toString(n.getId())); 
                
                //Extra
                //Magda, he creado en nóminas el get y el set de extra, con su variable. Hay que ver si esta bien hecho
                Element extra = documento.createElement("Extra");
                Text textExtra = documento.createTextNode(String.valueOf(n.getExtra()));
                extra.appendChild(textExtra);
                nomina.appendChild(extra);

                //Id Fila Excel
                Element idFilaExcel = documento.createElement("idFilaExcel");
                Text textIdFilaExcel = documento.createTextNode(String.valueOf(n.getTrabajador().getId()));
                idFilaExcel.appendChild(textIdFilaExcel);
                nomina.appendChild(idFilaExcel);

                //Nombre
                Element nombre = documento.createElement("Nombre");
                Text textNombre = documento.createTextNode(n.getTrabajador().getNombre());
                nombre.appendChild(textNombre);
                nomina.appendChild(nombre);

                //NIF
                Element nif = documento.createElement("NIF");
                Text textNIF = documento.createTextNode(n.getTrabajador().getNIF());
                nif.appendChild(textNIF);
                nomina.appendChild(nif);

                //IBAN
                Element iban = documento.createElement("IBAN");
                Text textIban = documento.createTextNode(n.getTrabajador().getIban());
                iban.appendChild(textIban);
                nomina.appendChild(iban);
                
                //Categoría
                Element categoria = documento.createElement("Categoria");
                Text textCategoria = documento.createTextNode(n.getTrabajador().getCategoria());
                categoria.appendChild(textCategoria);
                nomina.appendChild(categoria);
                
                //Bruto Anual
                Element brutoAnual = documento.createElement("BrutoAnual");
                Text textBrutoAnual = documento.createTextNode(); //CALCULAR CON LA INFORMACIÓN QUE OBTENEMOS DEL EXCEL CON LO COMENTADO
                brutoAnual.appendChild(textBrutoAnual);
                nomina.appendChild(brutoAnual);

                //Importe IRPF
                Element importeIrpf = documento.createElement("ImporteIrpf");
                Text textImporteIrpf = documento.createTextNode(String.valueOf(n.getIrpf()));
                importeIrpf.appendChild(textImporteIrpf);
                nomina.appendChild(importeIrpf); 
                
                //Base Empresario
                Element baseEmpresario = documento.createElement("BaseEmpresario");
                Text textBaseEmpresario = documento.createTextNode(); //CALCULAR
                baseEmpresario.appendChild(textBaseEmpresario);
                nomina.appendChild(baseEmpresario); 

                //Bruto Nómina
                Element brutoNomina = documento.createElement("BrutoNomina");
                Text textBrutoNomina = documento.createTextNode(); //CALCULAR
                brutoAnual.appendChild(textBrutoNomina);
                nomina.appendChild(brutoAnual); 

                //Líquido Nómina
                Element liquidoNomina = documento.createElement("LiquidoNomina");
                Text textLiquidoNomina = documento.createTextNode(n.getLiquidoNomina()); //CALCULAR o es totalNeto?
                liquidoNomina.appendChild(textLiquidoNomina);
                nomina.appendChild(liquidoNomina); 

                //Coste Total del Empresario
                Element costeTotalEmpresario = documento.createElement("CosteTotalEmpresario");
                Text textCosteTotalEmpresario = documento.createTextNode(String.valueOf(n.getCosteTotalEmpresario()));
                costeTotalEmpresario.appendChild(textCosteTotalEmpresario);
                nomina.appendChild(costeTotalEmpresario);
                
                
                root.appendChild(nomina);
            }
                        
            documento.getDocumentElement().appendChild(root);
 
           
            
            
            // Asocio el source con el Document
            Source source = new DOMSource(documento);
            // Creo el Result, indicado que fichero se va a crear
            Result result = new StreamResult(new File(archivo));
 
            // Creo un transformer, se crea el fichero XML
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            transformer.transform(source, result);
 
        } catch (ParserConfigurationException | TransformerException ex) {
            System.out.println(ex.getMessage());
        }
 
    }//fin del método exportaNominas
 
}//final de la clase XMLManager

