/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.uv.practica3;

import java.util.List;

/**
 *
 * @author obed
 */
public interface IDAOGeneral <T, ID> {
    public T create(T t);
    public boolean delete(ID id);
    public T update(T t, ID id);
    
    public List<T> findAll();
    public T findByID(ID id);
}
