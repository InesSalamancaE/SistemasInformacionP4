/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistinfp4;

/**
 *
 * @author albam
 */
public class Trabajador {
    int id;
    String nif;
    String nombre;
    String primerApellido;
    String segundoApellido;
    String cuenta;
    String paisIban;
    String Iban;
    String email;
    String fechaAltaEmpresa;
    String cifEmpresa;
    String empresa;
    String categoria;
    String prorrateaExtra;
    String fechaBajaLaboral;
    String fechaAltaLaboral;
    int salarioBase;
    int complementos;
    
    public Trabajador(){
        id=0;
        nif="";
        nombre="";
        primerApellido="";
        segundoApellido="";
        cuenta="";
        paisIban="";
        Iban="";
        email="";
        fechaAltaEmpresa="";
        empresa="";
        categoria="";
        cifEmpresa="";
        prorrateaExtra="";
        fechaBajaLaboral="";
        fechaAltaLaboral="";
        salarioBase=0;
        complementos=0;
    }
    
    public Trabajador(int id, String nif, String nombre,String primerApellido, String segundoApellido, String cuenta, String paisIban, String Iban, String email,String fechaAltaEmpresa,String empresa, String categoria,
            String cifEmpresa, String prorrateaExtra, String fechaBajaLaboral, String fechaAltaLaboral, int salarioBase, int complementos){
        this.id=id;
        this.nif=nif;
        this.nombre=nombre;
        this.primerApellido=primerApellido;
        this.segundoApellido=segundoApellido;
        this.cuenta=cuenta;
        this.paisIban=paisIban;
        this.Iban=Iban;
        this.email=email;
        this.fechaAltaEmpresa=fechaAltaEmpresa;
        this.empresa=empresa;
        this.categoria=categoria;
        this.cifEmpresa=cifEmpresa;
        this.prorrateaExtra=prorrateaExtra;
        this.fechaBajaLaboral=fechaBajaLaboral;
        this.fechaAltaLaboral=fechaAltaLaboral;
        this.salarioBase=salarioBase;
        this.complementos=complementos;
    }
    
    public void setId(int id){
        this.id=id;
    }
    public void setNIF(String nif){
        this.nif=nif;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    public void setPrimerApellido(String primerApellido){
        this.primerApellido=primerApellido;
    }
    public void setSegundoApellido(String segundoApellido){
        this.segundoApellido=segundoApellido;
    }
    public void setCuenta(String cuenta){
        this.cuenta=cuenta;
    }
    public void setPaisIban(String paisIban){
        this.paisIban=paisIban;
    }
    public void setIban(String Iban){
       this.Iban=Iban; 
    }
    public void setEmail(String email){
        this.email=email;
    }
    public void setCifEmpresa(String cifEmpresa){
        this.cifEmpresa=cifEmpresa;
    }
    public void setFechaAltaEmpresa(String fechaAltaEmpresa){
        this.fechaAltaEmpresa=fechaAltaEmpresa;
    }
    public void setEmpresa(String empresa){
        this.empresa=empresa;
    }
    public void setCategoria(String categoria){
        this.categoria=categoria;
    }
    public void setProrrateaExtra(String prorrateaExtra){
        this.prorrateaExtra=prorrateaExtra;
    }
    public void setFechaBajaLaboral(String fechaBajaLaboral){
        this.fechaBajaLaboral=fechaBajaLaboral;
    }
    public void setFechaAltaLaboral(String fechaAltaLaboral){
        this.fechaAltaLaboral=fechaAltaLaboral;
    }
    public void setSalarioBase(int salarioBase){
        this.salarioBase=salarioBase;
    }
    public void setComplementos(int complementos){
        this.complementos=complementos;
    }
    
    
    public int getId(){
        return id;
    }
    public String getNIF(){
        return nif;
    }
    public String getNombre(){
        return nombre;
    }
    public String getPrimerApellido(){
        return primerApellido;
    }
    public String getSegundoApellido(){
        return segundoApellido;
    }
    public String getCuenta(){
        return cuenta;
    }
    public String getPaisIban(){
        return paisIban;
    }
    public String getIban(){
        return Iban;
    }
    public String getEmail(){
        return email;
    }
    public String getFechaAltaEmpresa(){
        return fechaAltaEmpresa;
    }
    public String getCifEmpresa(){
        return cifEmpresa;
    }        
    public String getEmpresa(){
        return empresa;
    }
    public String getCategoria(){
        return categoria;
    }
    public String getProrrateaExtra(){
        return prorrateaExtra;
    }
    public String getFechaBajaLaboral(){
        return fechaBajaLaboral;
    }
    public String getFechaAltaLaboral(){
        return fechaAltaLaboral;
    }
    public int getSalarioBase(){
        return salarioBase;
    }
    public int getComplementos(){
        return complementos;
    }
   
    public Trabajador getTrabajador(){
        return this;
    }
}//final de la clase Trabajador
