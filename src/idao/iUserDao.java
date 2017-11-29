/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idao;

import java.util.List;

/**
 *
 * @author Bouchriha
 */
public interface iUserDao<T> {
    
    boolean add(T t);

    boolean update(T t);

    void removeById(int id);

    List<T> findAll();

    T findById(int id);
    
    //T login(String email, String password, String type);
}
