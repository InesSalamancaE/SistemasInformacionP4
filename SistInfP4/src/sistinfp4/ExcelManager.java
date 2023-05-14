/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistinfp4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.*;


/**
 *
 * @author albam
 */
public class ExcelManager {
    
    //La fecha que creo ahora es para ir haciendo, hay que preguntar al usuario
    String date;
    
    static final int COL_PAISIBAN=0;
    static final int COL_CUENTA=1;
    static final int COL_IBAN=2;
    static final int COL_EMAIL=3;
    static final int COL_FECHAALTAEMPRESA=4;
    static final int COL_CIFEMPRESA=5;
    static final int COL_NOMEMPRESA=6;
    static final int COL_CATEGORIA=7;
    static final int COL_APELLIDO1=8;
    static final int COL_APELLIDO2=9;
    static final int COL_NOMBRE=10;
    static final int COL_NIF=11;
    static final int COL_PRORRATEAEXTRA=12;
    static final int COL_FECHABAJALABORAL=13;
    static final int COL_FECHAALTALABORAL=14;
    static final int COL_SALARIOBASE=0;
    static final int COL_COMPLEMENTOS=1;
    static final int COL_CATEGORIAHOJA2=2;
    static final int COL_TRIENIO=0;
    static final int COL_IMPORTETRIENIO=1;
    static final int COL_BRUTOANUAL=0;
    static final int COL_RETENCION=1;
    static final int COL_CONCEPTOSEMPRESARIO=5;
    static final int COL_RETENCIONESEMPRESARIO=6;
    
    
    public static Map getTrienios(String archivo){
        Map<Integer,Integer> trienios = new HashMap<>();
        try {
            FileInputStream ficheroExcel = new FileInputStream(new File(archivo));
            XSSFWorkbook libro = new XSSFWorkbook(ficheroExcel);
            XSSFSheet hoja = libro.getSheetAt(2); 
            Row row;
            for (int i = 1; i < hoja.getLastRowNum() + 1; i++) {
                row = hoja.getRow(i);
                if (row!=null){
                    Cell cell = row.getCell(COL_TRIENIO);
                    Cell cell2 = row.getCell(COL_IMPORTETRIENIO);
                    trienios.put((int)cell.getNumericCellValue(),(int)cell2.getNumericCellValue());
                }
            }//final del for que recorre el Excel
            libro.close();
            ficheroExcel.close();
            } catch (Exception ex) {
                Logger.getLogger(ExcelManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return trienios;
    }//fin del método getRetenciones
    
    //Categorias Salario Base
    public static Map getCategoriasSalarioBase(String archivo){
        Map<String, Double> categoriasI = new HashMap<>();
        try {
            FileInputStream ficheroExcel = new FileInputStream(new File(archivo));
            XSSFWorkbook libro = new XSSFWorkbook(ficheroExcel);
            XSSFSheet hoja = libro.getSheetAt(1); 
            Row row;
            
            for (int i = 1; i < hoja.getLastRowNum() + 1; i++){
                row = hoja.getRow(i);
                if (row!=null){
                    Cell cell = row.getCell(COL_CATEGORIAHOJA2);
                    Cell cell2 = row.getCell(COL_SALARIOBASE);
                    categoriasI.put((String)cell.getNumericCellValue(),(double)cell2.getNumericCellValue());
                }
            }
            libro.close();
            ficheroExcel.close();
            
        } catch (Exception ex) {
                Logger.getLogger(ExcelManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        return categoriasI;
        
    }
    
    //Categorias Complementos
    public static Map getCategoriasComplementos(String archivo){
        Map<String, Double> categoriasCompl = new HashMap<>();
        try {
            FileInputStream ficheroExcel = new FileInputStream(new File(archivo));
            XSSFWorkbook libro = new XSSFWorkbook(ficheroExcel);
            XSSFSheet hoja = libro.getSheetAt(1); 
            Row row;
            
            for (int i = 1; i < hoja.getLastRowNum() + 1; i++){
                row = hoja.getRow(i);
                if (row!=null){
                    Cell cell = row.getCell(COL_CATEGORIAHOJA2);
                    Cell cell2 = row.getCell(COL_COMPLEMENTOS);
                    categoriasCompl.put((String)cell.getNumericCellValue(),(double)cell2.getNumericCellValue());
                }
            }
            libro.close();
            ficheroExcel.close();
            
        } catch (Exception ex) {
                Logger.getLogger(ExcelManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        return categoriasCompl;
        
    }
    
    public static Map getRetenciones(String archivo){
        Map<Integer,Double> retenciones = new HashMap<>();
        try {
            FileInputStream ficheroExcel = new FileInputStream(new File(archivo));
            XSSFWorkbook libro = new XSSFWorkbook(ficheroExcel);
            XSSFSheet hoja = libro.getSheetAt(3);
            Row row;
            for (int i = 1; i < hoja.getLastRowNum() + 1; i++) {
                row = hoja.getRow(i);
                if (row!=null){
                    Cell cell = row.getCell(COL_BRUTOANUAL);
                    Cell cell2 = row.getCell(COL_RETENCION);
                    retenciones.put((int)cell.getNumericCellValue(),cell2.getNumericCellValue());
                }
            }//final del for que recorre el Excel
            libro.close();
            ficheroExcel.close();
            } catch (Exception ex) {
                Logger.getLogger(ExcelManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return retenciones;
    }//fin del método getRetenciones
    
    public static Map getCostesEmpresario(String archivo){
         Map<String,Double> costesEmpresario = new HashMap<>();
        try {
            FileInputStream ficheroExcel = new FileInputStream(new File(archivo));
            XSSFWorkbook libro = new XSSFWorkbook(ficheroExcel);
            XSSFSheet hoja = libro.getSheetAt(3);
            Row row;
            
            
            for (int i = 0; i < hoja.getLastRowNum() + 1; i++) {
                row = hoja.getRow(i);
                if (row!=null){
                    
                    Cell cell = row.getCell(COL_CONCEPTOSEMPRESARIO);
                    Cell cell2 = row.getCell(COL_RETENCIONESEMPRESARIO);
                    if (cell!=null){
                        costesEmpresario.put(cell.getStringCellValue(),cell2.getNumericCellValue());
                    }
                                        
                       
                }
            }//final del for que recorre el Excel
            libro.close();
            ficheroExcel.close();
            } catch (Exception ex) {
                Logger.getLogger(ExcelManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        return costesEmpresario;
    }//fin del método getCostesEmpresario
    
    
    public static Map getTrabajadores(String archivo){
        Map<Integer,Trabajador> trabajadores = new LinkedHashMap<>();
        try {
            FileInputStream ficheroExcel = new FileInputStream(new File(archivo));
            XSSFWorkbook libro = new XSSFWorkbook(ficheroExcel);
            XSSFSheet hoja = libro.getSheetAt(0);
            XSSFSheet hoja2 = libro.getSheetAt(1);
            Row row,row2;
            int id;
            String cuenta;
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy"); 
            
            
            for (int i = 1; i < hoja.getLastRowNum() + 1; i++) {
                row = hoja.getRow(i);
                if (row!=null){
                    id=row.getRowNum()+1;
                    if (row.getCell(COL_NOMBRE)!=null){ //Si tiene rellena la celda del nombre, relleno ficha de trabajador
                        Trabajador trabajador= new Trabajador();
                        trabajador.setId(id);
                        trabajador.setNombre(row.getCell(COL_NOMBRE).toString());
                        if (row.getCell(COL_PAISIBAN)!=null)
                            trabajador.setPaisIban(row.getCell(COL_PAISIBAN).toString());
                        if (row.getCell(COL_CUENTA)!=null)
                            trabajador.setCuenta(row.getCell(COL_CUENTA).toString());
                        if (row.getCell(COL_IBAN)!=null)
                            trabajador.setIban(row.getCell(COL_IBAN).toString());
                        if (row.getCell(COL_EMAIL)!=null)
                            trabajador.setEmail(row.getCell(COL_EMAIL).toString());
                        if (row.getCell(COL_NIF)!=null)
                            trabajador.setNIF(row.getCell(COL_NIF).toString());
                        if (row.getCell(COL_APELLIDO1)!=null)
                            trabajador.setPrimerApellido(row.getCell(COL_APELLIDO1).toString());
                        if (row.getCell(COL_APELLIDO2)!=null)
                            trabajador.setSegundoApellido(row.getCell(COL_APELLIDO2).toString());
                        if (row.getCell(COL_CIFEMPRESA)!=null)
                            trabajador.setCifEmpresa(row.getCell(COL_CIFEMPRESA).toString());
                        if (row.getCell(COL_FECHAALTAEMPRESA)!=null){
                            Cell cell = row.getCell(COL_FECHAALTAEMPRESA);
                            trabajador.setFechaAltaEmpresa(formato.format(cell.getDateCellValue()));
                        }
                        if (row.getCell(COL_NOMEMPRESA)!=null)
                            trabajador.setEmpresa(row.getCell(COL_NOMEMPRESA).toString());
                        
                        if (row.getCell(COL_PRORRATEAEXTRA)!=null){
                            trabajador.setProrrateaExtra(row.getCell(COL_PRORRATEAEXTRA).toString());
                        }
                        if (row.getCell(COL_FECHABAJALABORAL)!=null){
                            Cell cell = row.getCell(COL_FECHABAJALABORAL);
                            trabajador.setFechaBajaLaboral(formato.format(cell.getDateCellValue()));
                        }
                        if (row.getCell(COL_FECHAALTALABORAL)!=null){
                            Cell cell = row.getCell(COL_FECHAALTALABORAL);
                            trabajador.setFechaAltaLaboral(formato.format(cell.getDateCellValue()));
                        }
                        if (row.getCell(COL_FECHAALTAEMPRESA)!=null){
                            Cell cell = row.getCell(COL_FECHAALTAEMPRESA);
                            trabajador.setFechaAltaEmpresa(formato.format(cell.getDateCellValue()));
                        }
                        
                        if (row.getCell(COL_CATEGORIA)!=null){
                            trabajador.setCategoria(row.getCell(COL_CATEGORIA).toString());
                            boolean encontrado=false;
                            for (int j = 1; j < hoja2.getLastRowNum() + 1 && !encontrado ; j++) {
                                row2 = hoja2.getRow(j);
                                if (row!=null){
                                    if (row.getCell(COL_CATEGORIA).toString().equals(row2.getCell(COL_CATEGORIAHOJA2).toString())){
                                        encontrado=true;
                                        Cell cell = row2.getCell(COL_SALARIOBASE);
                                        Cell cell2 = row2.getCell(COL_COMPLEMENTOS);
                                        trabajador.setSalarioBase((int)cell.getNumericCellValue());
                                        trabajador.setComplementos((int)cell2.getNumericCellValue());
                                    }
                                }
                            }
                        }
                        trabajadores.put(id,trabajador);
                        
                    }
                }
                
            }//final del for que recorre el Excel
            libro.close();
            ficheroExcel.close();
            } catch (Exception ex) {
                Logger.getLogger(ExcelManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        return trabajadores;
    }//fin del método getTrabajadores
    
    
    public static void setTrabajador(String archivo,Trabajador trabajador) {
       
        
        try {
            FileInputStream ficheroExcel = new FileInputStream(new File(archivo));
            XSSFWorkbook libro = new XSSFWorkbook(ficheroExcel);
            XSSFSheet hoja = libro.getSheetAt(0);
            Row row;
            Cell cel1,cel2,cel3;
            row = hoja.getRow( trabajador.getId()-1);
            
            cel1=row.getCell(COL_NIF);
            if (cel1==null)
                cel1=row.createCell(COL_NIF);
            cel1.setCellValue(trabajador.getNIF());
            
            cel2=row.getCell(COL_IBAN);
            if (cel2==null)
                cel2=row.createCell(COL_IBAN);
            cel2.setCellValue(trabajador.getIban());
            
            cel3=row.getCell(COL_EMAIL);
            if (cel3==null)
                cel3=row.createCell(COL_EMAIL);
            cel3.setCellValue(trabajador.getEmail());
            
            ficheroExcel.close();
            FileOutputStream fos = null;
            File file;

            file = new File(archivo);
            fos = new FileOutputStream(file);

            libro.write(fos);
            libro.close();
            
            fos.close();
            
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
    }//Fin del método setTrabajador
    //Calculo de las nominas co todos sus datos
    
    public void totalNominas(Trabajador trbj){
        Nomina nom = new Nomina();
        //Creamos una lista de nominas
        ArrayList<Nomina> listaNominas = new ArrayList<Nomina>();
        
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
        nom.setFecha(this.date);
        

        //Trienios numero
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
        LocalDate dateExpNomina = LocalDate.parse(this.date, formatter);
        LocalDate dateAltaLaboral = LocalDate.parse(trbj.fechaAltaLaboral, formatter);
        Period periodo = Period.between(dateAltaLaboral, dateExpNomina);
        int numeroTrienios = periodo.getYears() / 3;
        nom.setTrienios(numeroTrienios);
        //Una vez tenemos los trienios ay que sacar el importe mediante el getTrienios()
        
        
        //Importe salario mes
        String categ = trbj.getCategoria();
        bouble importeCategorias = categ
        
        
        
        
        
    }
   
    
    
    //Printear por pantalla los datos de las nóminas de los empleados ON IT
    
    public void printearNominasTrabajadores(){
        for (Nomina nominas: this.listaNominas){
            Trabajador trabaj = 
        }
    }
  
    

    
}//fin de la clase ExcelManager
