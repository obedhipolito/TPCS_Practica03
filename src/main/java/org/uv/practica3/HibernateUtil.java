/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.practica3;

import org.uv.practica3.POJO.Cliente;
import org.uv.practica3.POJO.DetVenta;
import org.uv.practica3.POJO.Producto;
import org.uv.practica3.POJO.Venta;

import java.util.Properties;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author obed
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory;
    
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            Properties settings = new Properties();
            settings.put(Environment.DRIVER, "org.postgresql.Driver");
            settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/ventas");
            settings.put(Environment.USER, "obedhipolito");
            settings.put(Environment.PASS, "jose0710");
            
            settings.put(Environment.SHOW_SQL, "true");
            settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            settings.put(Environment.HBM2DDL_AUTO, "update");
            configuration.setProperties(settings);
            
            configuration.addAnnotatedClass(Cliente.class);
            configuration.addAnnotatedClass(DetVenta.class);
            configuration.addAnnotatedClass(Producto.class);
            configuration.addAnnotatedClass(Venta.class);


            
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }
    public static Session getSession() throws HibernateException{
        if(sessionFactory==null){
            getSessionFactory();
        }
        return sessionFactory.openSession();
    }
    
    public static void shutdown(){
        if(sessionFactory!=null){
            sessionFactory.close();
        }
    }
}
