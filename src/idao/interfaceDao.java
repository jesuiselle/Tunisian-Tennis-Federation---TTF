/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idao;

import java.util.List;

/**
 *
 * @author hichem
 */
public interface interfaceDao<T> {
    
    
    void add(T t);

    void update(T t);

    void removeById(int id);

    List<T> findAll();

    T findById(int id);

    
    
}
