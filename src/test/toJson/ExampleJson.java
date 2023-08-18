/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.toJson;

import com.gustavo.annotations.Show;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author gustavo
 */
public class ExampleJson {

    private String nombre;
    private Integer num;
    @Show
    private Date fecNac;
    @Show
    private java.sql.Date fecha;
    private Timestamp fecTime;
    private ExampleJsonDetail detail;

    private Collection<ExampleJsonDetail> lst;
    private HashMap<Integer, ExampleJsonDetail> map;
    private ExampleJsonDetail[] arr;
    //private int[] intArr;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getFecNac() {
        return fecNac;
    }

    public void setFecNac(Date fecNac) {
        this.fecNac = fecNac;
    }

    public java.sql.Date getFecha() {
        return fecha;
    }

    public void setFecha(java.sql.Date fecha) {
        this.fecha = fecha;
    }

    public Timestamp getFecTime() {
        return fecTime;
    }

    public void setFecTime(Timestamp fecTime) {
        this.fecTime = fecTime;
    }

    public ExampleJsonDetail getDetail() {
        return detail;
    }

    public void setDetail(ExampleJsonDetail detail) {
        this.detail = detail;
    }

    public Collection<ExampleJsonDetail> getLst() {
        return lst;
    }

    public void setLst(Collection<ExampleJsonDetail> lst) {
        this.lst = lst;
    }

    public HashMap<Integer, ExampleJsonDetail> getMap() {
        return map;
    }

    public void setMap(HashMap<Integer, ExampleJsonDetail> map) {
        this.map = map;
    }

    public ExampleJsonDetail[] getArr() {
        return arr;
    }

    public void setArr(ExampleJsonDetail[] arr) {
        this.arr = arr;
    }

    /*public int[] getIntArr() {
        return intArr;
    }

    public void setIntArr(int[] intArr) {
        this.intArr = intArr;
    }*/
}
