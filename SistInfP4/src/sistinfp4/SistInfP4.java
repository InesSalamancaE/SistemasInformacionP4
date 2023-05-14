/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistinfp4;

import java.io.File;
import java.math.BigInteger;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
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
 * 
 */
public class SistInfP4 {

    /**
     * @param args the command line arguments
     */
    static Map<Integer, Trabajador> erroresTrabajadoresCuenta = new LinkedHashMap<>();
    static Map<String, Integer>emailTrabajadores = new HashMap<>();
    static Map<Integer, Integer> trienios = new HashMap<>();
    static Map<Integer, Double>retenciones = new HashMap<>();
    static Map<String, Double>costesEmpresario = new HashMap<>();
    
    public static void main(String[] args) {
        Map<Integer,Trabajador> trabajadores = new LinkedHashMap<>();
        Map<Integer,String> chequeados = new LinkedHashMap<>();
        Map<Integer, Trabajador> erroresTrabajadores = new LinkedHashMap<>();
        
        Trabajador trabajador;
        trienios = ExcelManager.getTrienios("SistemasInformacionII.xlsx");
        retenciones = ExcelManager.getRetenciones("SistemasInformacionII.xlsx");
        costesEmpresario=ExcelManager.getCostesEmpresario("SistemasInformacionII.xlsx");
        trabajadores = ExcelManager.getTrabajadores("SistemasInformacionII.xlsx");
        
        Iterator<Integer> it = trabajadores.keySet().iterator();
        while(it.hasNext()){
            Integer clave = it.next();
            trabajador= (Trabajador)trabajadores.get(clave);
            if (trabajador.getId()!=0){//No está vacío el NIF
                if (!chequeados.containsValue(trabajador.getNIF())){
                    chequeados.put(trabajador.getId(),trabajador.getNIF());
                }else{//NIF Duplicado
                    erroresTrabajadores.put(trabajador.getId(), trabajador);
                }
                validarCuenta(trabajador);
                generarEmail(trabajador);
                if (!validadorNIF(trabajador)){
                    erroresTrabajadores.put(trabajador.getId(), trabajador);
                }
                
                ExcelManager.setTrabajador("SistemasInformacionII.xlsx", trabajador);
            
            }
        }
      
        XMLManager.exportaXmlErroresNIF("errores.xml",erroresTrabajadores);
        XMLManager.exportaXmlErroresCCC("erroresCCC.xml",erroresTrabajadoresCuenta);
        //Actualizo el campo cuenta en los trabajadores con algún error
        Iterator<Integer> itE = erroresTrabajadoresCuenta.keySet().iterator();
        while(itE.hasNext()){
            Integer clave = itE.next();
            trabajador= (Trabajador) erroresTrabajadoresCuenta.get(clave);
            trabajador.setCuenta(trabajador.getIban().substring(4,24));
            ExcelManager.setTrabajador("SistemasInformacionII.xlsx", trabajador);
        }
        
        /*
        System.out.println("NIF ERRONEOS");
        Iterator<String> itErr = erroresTrabajadores.keySet().iterator();
        while(itErr.hasNext()){
            System.out.println(itErr.next());
        }
        */
        
        System.out.println("Introduce el mes del que quieres generar las nóminas de los trabajadores");
        Scanner sc = new Scanner(System.in);
        String fechaNomina = sc.nextLine();
        int mes = Integer.parseInt(fechaNomina.substring(0,2));
        int anyo = Integer.parseInt(fechaNomina.substring(4));
        // Las dejo como variables globales de la clase?
    }
    public static void generarEmail(Trabajador trabajador){
        String prefijo="", sufijo="",email = "";
        int repeticion=0;
        if (trabajador.getEmail()==""){
            
            if (trabajador.getNombre()!="")
                prefijo+=trabajador.getNombre().substring(0,1);
            
            
            if (trabajador.getPrimerApellido()!="")
                prefijo+=trabajador.getPrimerApellido().substring(0,1);
            
            
            if (trabajador.getSegundoApellido()!="")
                prefijo+=trabajador.getSegundoApellido().substring(0,1);
            
            }
        
            sufijo="@"+trabajador.getEmpresa()+".com";
            email=prefijo+sufijo;
            if (emailTrabajadores.containsKey(email)){
                repeticion = emailTrabajadores.get(email)+1;
                
            }
            emailTrabajadores.put(email,repeticion);
            trabajador.setEmail(prefijo+String.format("%02d", repeticion)+sufijo);
                
        
    }//Fin del método generarEmail
    
    public static String generarIban(String pais, String cuenta){
        int digitosIban=0;
        String iban="";
        BigInteger cuentaNumero;
        BigInteger modo= new BigInteger("97");
        HashMap <String,String> tabla = new HashMap<>();
        tabla.put("A","10");tabla.put("B","11");tabla.put("C","12");tabla.put("D","13");tabla.put("E","14");
        tabla.put("F","15");tabla.put("G","16");tabla.put("H","17");tabla.put("I","18");tabla.put("J","19");
        tabla.put("K","20");tabla.put("L","21");tabla.put("M","22");tabla.put("N","23");tabla.put("O","24");
        tabla.put("P","25");tabla.put("Q","26");tabla.put("R","27");tabla.put("S","28");tabla.put("T","29");
        tabla.put("U","30");tabla.put("V","31");tabla.put("W","32");tabla.put("X","33");tabla.put("Y","34");
        tabla.put("Z","35");
        
        cuenta+=tabla.get(pais.substring(0, 1));
        cuenta+=tabla.get(pais.substring(1, 2));
        cuenta+="00";
        cuentaNumero=new BigInteger(cuenta);
        digitosIban= cuentaNumero.mod(modo).intValue();
        digitosIban=98-digitosIban;
        iban=pais+String.format("%02d",digitosIban)+cuenta.substring(0,20);
        
        return iban;
    }//fin de generarIban
    
    public static void validarCuenta(Trabajador trabajador){
        boolean cuentaCorrecta=true;
        String iban="";
        
        //Cálculo del primer dígito de control
        String datosBanco = "00"+trabajador.getCuenta().substring(0,8);
	int suma =  Integer.parseInt(datosBanco.substring(0,1))*1+
                    Integer.parseInt(datosBanco.substring(1,2))*2+
                    Integer.parseInt(datosBanco.substring(2,3))*4+
                    Integer.parseInt(datosBanco.substring(3,4))*8+
                    Integer.parseInt(datosBanco.substring(4,5))*5+
		    Integer.parseInt(datosBanco.substring(5,6))*10+
		    Integer.parseInt(datosBanco.substring(6,7))*9+
		    Integer.parseInt(datosBanco.substring(7,8))*7+
		    Integer.parseInt(datosBanco.substring(8,9))*3+
		    Integer.parseInt(datosBanco.substring(9,10))*6;
 	int primerDigito= 11 - (suma%11);
        if (primerDigito==10)
            primerDigito=1;
	else if (primerDigito==11)
            primerDigito=0;
        if (primerDigito!=Integer.parseInt(trabajador.getCuenta().substring(8,9)))
                cuentaCorrecta=false;
        
        //Cálculo del segundo dígito de la cuenta
        String datosCuenta=trabajador.getCuenta().substring(10,20);
       
        suma =  Integer.parseInt(datosCuenta.substring(0,1))*1+
                Integer.parseInt(datosCuenta.substring(1,2))*2+
		Integer.parseInt(datosCuenta.substring(2,3))*4+
		Integer.parseInt(datosCuenta.substring(3,4))*8+
		Integer.parseInt(datosCuenta.substring(4,5))*5+
		Integer.parseInt(datosCuenta.substring(5,6))*10+
		Integer.parseInt(datosCuenta.substring(6,7))*9+
		Integer.parseInt(datosCuenta.substring(7,8))*7+
		Integer.parseInt(datosCuenta.substring(8,9))*3+
		Integer.parseInt(datosCuenta.substring(9,10))*6;
        
 
	int segundoDigito= 11 - (suma%11);
	if (segundoDigito==10)
            segundoDigito=1;
	else if (segundoDigito==11)
            segundoDigito=0;
        if (segundoDigito!=Integer.parseInt(trabajador.getCuenta().substring(9,10)))
                cuentaCorrecta=false;
        if (!cuentaCorrecta){
            
            String cuentaCorregida=datosBanco.substring(2,10)+Integer.toString(primerDigito)+Integer.toString(segundoDigito)+datosCuenta;
            iban=generarIban(trabajador.getPaisIban(),cuentaCorregida);
            trabajador.setIban(iban);
            erroresTrabajadoresCuenta.put(trabajador.getId(), trabajador);
            
            
        }else{
            iban=generarIban(trabajador.getPaisIban(),trabajador.getCuenta());
            trabajador.setIban(iban);
        }
            
        
    }//fin validadorCuenta
    
    public static boolean isNumeric(String cadena) {

        boolean resultado;

        try {
            Integer.parseInt(cadena);
            resultado = true;
        } catch (NumberFormatException excepcion) {
            resultado = false;
        }

        return resultado;
    }
    
    public static boolean validadorNIF(Trabajador trabajador){
        String nifnie=trabajador.getNIF();
        boolean answer=false;
        int valor= Integer.parseInt(nifnie);
        int digitoControl = valor%23;
        String letraCorrecta="";
        
        HashMap <String,String> tablaNif = new HashMap<>();
                tablaNif.put("0", "T"); tablaNif.put("1", "R"); tablaNif.put("2","W");
                tablaNif.put("3", "A"); tablaNif.put("4", "G"); tablaNif.put("5","M");
                tablaNif.put("6", "Y"); tablaNif.put("7", "F"); tablaNif.put("8","P");
                tablaNif.put("9", "D"); tablaNif.put("10", "X"); tablaNif.put("11","B");
                tablaNif.put("12", "N"); tablaNif.put("13", "J"); tablaNif.put("14","Z");
                tablaNif.put("15", "S"); tablaNif.put("16", "Q"); tablaNif.put("17","V");
                tablaNif.put("18", "H"); tablaNif.put("19", "L"); tablaNif.put("20","C");
                tablaNif.put("21", "K"); tablaNif.put("22", "E"); 

        if(nifnie.length()!=9){
            //nif o nie mal almacenado, a si que va al xml
            if(nifnie.length()==8){
                if (!isNumeric(nifnie)){
                    return false;
                }
                nifnie = nifnie+tablaNif.get(String.valueOf(digitoControl));
                //System.out.println ("Nif reparado "+ nifnie);
                trabajador.setNIF(nifnie);
               //ExcelManager.setTrabajador("SistemasInformacionII.xlsx", trabajador);
            } // CIERRE IF Longitud de 8 de nifnie
            
        }else{
            String letra = nifnie.substring(8);
            String primerCaracter = nifnie.substring(0,1);
            String resto="";
            
            
            // IF-IFELSE por si es un NIE (extranjero)
            if(primerCaracter.equals("X")){
                resto = 0+nifnie.substring(1, 8);
            } else if(primerCaracter.equals("Y")){
                resto = 1+nifnie.substring(1, 8);
            } else if(primerCaracter.equals("Z")){
                resto = 2+nifnie.substring(1, 8);
            }else {
                    resto = nifnie.substring(0, 8);
                    if (!isNumeric(resto))
                        return false;
                //Hay que añadirlo al xml
            }

            
            letraCorrecta = tablaNif.get(String.valueOf(digitoControl));
            
            if (letra.equals(letraCorrecta)){
                answer=true;
            }else{
                
               //System.out.println ("Nif reparable "+ nifnie);
               //System.out.println ("Nif reparado "+ nifnie.substring(0,8)+letraCorrecta);
               trabajador.setNIF(nifnie.substring(0,8)+letraCorrecta);
               //ExcelManager libro = new ExcelManager( "SistemasInformacionII.xlsx");
               
               ExcelManager.setTrabajador("SistemasInformacionII.xlsx", trabajador);
               answer=true;
            }
        } // FIN IF tamaño del string nifnie

        return answer;
    }
    
       //Pedimos fecha al usuario
    public String pedirFecha(){
        System.out.println("Escribe la fecha para la que deseas general las nóminas");
        Scanner respuesta = new Scanner(System.in);
        String date = respuesta.nextLine();
        return date;
        
    
    }
    
      //Printear por pantalla los datos de las nóminas de los empleados ON IT
    
    public void printearNominasTrabajadores(){
        for (Nomina nominas: this.listaNominas){
            Trabajador trabaj = 
        }
    }
  
    
    //Calculo de las nominas con todos sus datos
    
    public void totalNominas(Trabajador trbj){
        Nomina nom = new Nomina();
        //Creamos una lista de nominas
        ArrayList<Nomina> listaNominas = new ArrayList<Nomina>();
        //Fecha con la que funcionamos:
        String date = pedirFecha();
        
        //Id, lo generamos nosotros
        int idD=0;
        if (listaNominas.isEmpty()){
        idD = 1;
        } else {
            idD = listaNominas.size();
        }
        nom.setId(idD);
        
        //Añadimos el mes y el año
        //Gestionar el pedir fecha y pasarlo por aqúi
        nom.setFecha(date);
        

        //Trienios numero
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
        LocalDate dateExpNomina = LocalDate.parse(date, formatter);
        LocalDate dateAltaLaboral = LocalDate.parse(trbj.fechaAltaLaboral, formatter);
        Period periodo = Period.between(dateAltaLaboral, dateExpNomina);
        int numeroTrienios = periodo.getYears() / 3;
        nom.setTrienios(numeroTrienios);
        //Una vez tenemos los trienios ay que sacar el importe mediante el getTrienios()
        
        
        //Importe salario mes
        String categ = trbj.getCategoria();
        Map<String, Double> categoriasImSalB = new HashMap<>();
        
        if (){
            
        }
        double importeCategorias = 
        
        
        
        
        
    }
    
   
    
}

