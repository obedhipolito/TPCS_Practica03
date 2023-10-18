/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.uv.practica3;

import org.uv.practica3.POJO.Cliente;
import org.uv.practica3.POJO.Producto;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author obed
 */
public class Practica3 {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = sesion.beginTransaction();
       
        org.uv.practica3.POJO.Producto nuevo = new Producto();
        nuevo.setCosto(200);
        nuevo.setDescripcion("reloj");
        nuevo.setExistencia(10);
        nuevo.setPrecio(300);
  
        sesion.save(nuevo);
        transaction.commit();
        sesion.clear();
        
        
    }
}
