/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.toObject;

import java.util.Date;

/**
 *
 * @author gustavo
 */
public class Example {

    private String nombre;
    private int a;
    private float b;
    private Double c;
    private Byte d;
    private Date dateUtil;
    private java.sql.Date dateSql;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public float getB() {
        return b;
    }

    public void setB(float b) {
        this.b = b;
    }

    public Double getC() {
        return c;
    }

    public void setC(Double c) {
        this.c = c;
    }

    public Byte getD() {
        return d;
    }

    public void setD(Byte d) {
        this.d = d;
    }

    public Date getDateUtil() {
        return dateUtil;
    }

    public void setDateUtil(Date dateUtil) {
        this.dateUtil = dateUtil;
    }

    public java.sql.Date getDateSql() {
        return dateSql;
    }

    public void setDateSql(java.sql.Date dateSql) {
        this.dateSql = dateSql;
    }
}
