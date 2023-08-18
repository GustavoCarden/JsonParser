/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.toJson;

import java.util.LinkedHashMap;

/**
 *
 * @author gustavo
 */
public class ExampleJsonDetail {

    private String a;
    private Integer b;
    private LinkedHashMap<Object,LinkedHashMap<Integer, Integer>> names;
    private LinkedHashMap<Integer, Integer> namesAux;

    
    public ExampleJsonDetail(String a, Integer b) {
        this.a = a;
        this.b = b;
    }

    /*public ExampleJsonDetail(String a, Integer b, LinkedHashMap<Integer, Integer> namesAux) {
        this.a = a;
        this.b = b;
        this.namesAux = namesAux;
    }*/

    public ExampleJsonDetail(String a, Integer b, LinkedHashMap<Object,LinkedHashMap<Integer, Integer>> names) {
        this.a = a;
        this.b = b;
        this.names = names;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public Integer getB() {
        return b;
    }

    public void setB(Integer b) {
        this.b = b;
    }

    public LinkedHashMap<Object,LinkedHashMap<Integer, Integer>> getNames() {
        return names;
    }

    public void setNames(LinkedHashMap<Object,LinkedHashMap<Integer, Integer>> names) {
        this.names = names;
    }

    public LinkedHashMap<Integer, Integer> getNamesAux() {
        return namesAux;
    }

    public void setNamesAux(LinkedHashMap<Integer, Integer> namesAux) {
        this.namesAux = namesAux;
    }
    
    

}
