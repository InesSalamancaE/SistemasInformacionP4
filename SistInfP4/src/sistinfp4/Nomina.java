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
public class Nomina {
    double id; //Añado un id
    Trabajador trabajador;
    String fecha;
    double salarioBase;
    double prorrateo;
    double complementos;
    double antiguedad;
    double contingenciasGenerales;
    double desempleo;
    double cuotaFormacion;
    double irpf;
    int trienios; //Creo los trienios 
    double totalDeducciones;
    double totalDevengos;
    double totalNeto;
    double contingenciasEmpresario;
    double desempleoEmpresario;
    double formacionEmpresario;
    double accidentesTrabajo;
    double fogasa;
    double costeTotalEmpresario;
    double costeTotal;
    double extra;
    
    
    
    public Nomina (){
        id=0;
        trabajador=null;
        fecha="";
        salarioBase=0;
        prorrateo=0;
        complementos=0;
        antiguedad=0;
        contingenciasGenerales=0;
        desempleo=0;
        cuotaFormacion=0;
        irpf=0;
        trienios = 0;
        totalDeducciones=0;
        totalDevengos=0;
        totalNeto=0;
        contingenciasEmpresario=0;
        desempleoEmpresario=0;
        formacionEmpresario=0;
        accidentesTrabajo=0;
        fogasa=0;
        costeTotalEmpresario=0;
        costeTotal=0;
        
    } //fin del constructor
    
    public void setId(double id){
        this.id = id;
    }
    public void setFecha(String fecha){
        this.fecha=fecha;
    }
    public void setSalarioBase(double salarioBase){
        this.salarioBase=salarioBase;
    }
    public void setProrrateo(double prorrateo){
        this.prorrateo=prorrateo;
    }
    public void setComplementos(double complementos){
        this.complementos=complementos;
    }
    public void setAntiguedad(double antiguedad){
        this.antiguedad=antiguedad;
    }
    public void setContingenciasGenerales(double contingenciasGenerales){
        this.contingenciasGenerales=contingenciasGenerales;
    }
    public void setDesempleo(double desempleo){
        this.desempleo=desempleo;
    }
    public void setCuotaFormacion(double cuotaFormacion){
        this.cuotaFormacion=cuotaFormacion;
    }
    public void setIrpf(double irpf){
        this.irpf=irpf;
    }
    public void setTrienios(int trienios){
        this.trienios = trienios;
    }
    public void setTotalDeducciones(double totalDeducciones){
        this.totalDeducciones=totalDeducciones;
    }
    public void setTotalDevengos(double totalDevengos){
        this.totalDevengos=totalDevengos;
    }
    public void setTotalNeto(double totalNeto){
        this.totalNeto=totalNeto;
    }
    public void setContingenciasEmpresario(double contingenciasEmpresario){
        this.contingenciasEmpresario=contingenciasEmpresario;
    }
    public void setDesempleoEmpresario(double desempleoEmpresario){
        this.desempleoEmpresario=desempleoEmpresario;
    }
    public void setFormacionEmpresario(double formacionEmpresario){
        this.formacionEmpresario=formacionEmpresario;
    }
    public void setAccidentesTrabajo(double accidentesTrabajo){
        this.accidentesTrabajo=accidentesTrabajo;
    }
    public void setFogasa(double fogasa){
        this.fogasa=fogasa;
    }
    public void setCosteTotalEmpresario(double costeTotalEmpresario){
        this.costeTotalEmpresario=costeTotalEmpresario;
    }
    public void setCosteTotal(double costeTotal){
        this.costeTotal=costeTotal;
    }
    public void setExtra(double extra){
        this.extra = extra;
    }
    
    public double getId(){
        return id;
    }
    public String getFecha(){
        return fecha;
    }
    public double getSalarioBase(){
        return salarioBase;
    }
    public double getProrrateo(){
        return prorrateo;
    }
    public double getComplementos(){
        return complementos;
    }
    public double getAntiguedad(){
        return antiguedad;
    }
    public double getContingenciasGenerales(){
        return contingenciasGenerales;
    }
    public double getDesempleo(){
        return desempleo;
    }
    public double getCuotaFormacion(){
        return cuotaFormacion;
    } 
    public double getIrpf() {
        return irpf;
    }
    public int getTrienios(){
        return trienios;
    }
    public double getTotalDeducciones(){
        return totalDeducciones;
    }
    public double getTotalDevengos(){
        return totalDevengos;
    }
    public double getTotalNeto(){
        return totalNeto;
    }
    public double getContingenciasEmpresario(){
        return contingenciasEmpresario;
    }
    public double getDesempleoEmpresario(){
        return desempleoEmpresario;
    }
    public double getFormacionEmpresario(){
        return formacionEmpresario;
    }
    public double getAccidentesTrabajo(){
        return accidentesTrabajo;
    }
    public double getFogasa(){
        return fogasa;
    }
    public double getCosteTotalEmpresario(){
        return costeTotalEmpresario;
    }
    public double getCosteTotal(){
        return costeTotal;
    }
    public Nomina getNomina(){
        return this;
    }
    public Trabajador getTrabajador(){
        return trabajador;
    }
    public double getExtra(){
        return extra;
    }
}//final de la clase Nomina
