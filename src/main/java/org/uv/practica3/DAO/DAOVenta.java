/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.practica3.DAO;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.uv.practica3.HibernateUtil;
import org.uv.practica3.IDAOGeneral;
import org.uv.practica3.POJO.DetVenta;
import org.uv.practica3.POJO.Producto;
import org.uv.practica3.POJO.Venta;

/**
 *
 * @author obed
 */
public class DAOVenta implements IDAOGeneral<Venta, Long>{
    @Override
    public Venta create(Venta t) {
        Session session=HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();
        session.save(t);
        for(DetVenta detalle:t.getDetalles()){
            detalle.setVenta(t);
            session.save(detalle);
            Producto producto=session.get(Producto.class, detalle.getProducto().getProductoId());
            producto.setExistencia(producto.getExistencia()-detalle.getCantidad());
            session.merge(producto);
            
            /*Faltan validar reglas del negocio*/
        }
        transaction.commit();
        session.close();
        return t;
    }

    @Override
    public boolean delete(Long id) {
        Session session=HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();
        Venta venta=session.get(Venta.class, id);
        boolean pase=false;
        if(venta!=null){
            session.delete(venta);
            pase=true;
        }
        transaction.commit();
        session.close();
        return pase;
    }

    @Override
    public Venta update(Venta t, Long id) {
        Session session=HibernateUtil.getSession();
        Transaction transaction=session.beginTransaction();
        Venta venta=session.get(Venta.class, id);
        if(venta!=null){
            Producto pro;
            for(DetVenta detalle:venta.getDetalles()){
                pro=session.get(Producto.class, detalle.getProducto().getProductoId());
                pro.setExistencia(pro.getExistencia()+detalle.getCantidad());
                session.merge(pro);
            }
            for(DetVenta detalle:t.getDetalles()){
                detalle.setVenta(t);
            /*Faltan validar reglas del negocio*/
            }
            session.merge(t);
            for(DetVenta detalle:t.getDetalles()){
                pro=session.get(Producto.class, detalle.getProducto().getProductoId());
                pro.setExistencia(pro.getExistencia()-detalle.getCantidad());
                session.merge(pro);
            }
            /*for(DetVenta detalleregistrado:venta.getDetalles()){
                for(DetVenta detalle:t.getDetalles()){
                    if(detalleregistrado.getProducto().getProductoId()==detalle.getProducto().getProductoId()){
                        detalle.setVenta(t);
                        session.update(detalle);
                    }else{
                        session.delete(detalleregistrado);
                    }
                }
            }*/
            transaction.commit();
        }
        session.close();
        return t;
    }

    @Override
    public List<Venta> findAll() {
        Session session=HibernateUtil.getSession();
        List<Venta> ventas=session.createQuery("From Venta e order by e.ventaId").list();
        session.close();
        return ventas;
    }

    @Override
    public Venta findByID(Long id) {
        Session session=HibernateUtil.getSession();
        Venta venta=session.get(Venta.class, id);
        session.close();
        return venta;
    }
}
