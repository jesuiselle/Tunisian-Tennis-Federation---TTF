/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import dao.UtilisateurDao;
import entities.Administrateur;
import entities.Arbitre;
import entities.Fan;
import entities.Joueur;
import entities.Medecin;
import entities.Responsable;
import java.util.AbstractMap;
import java.util.Map;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Bouchriha
 */
public class Utils {
    
    public static Administrateur admin;
    public static Arbitre arbitre;
    public static Fan fan;
    public static Joueur joueur;
    public static Medecin medecin;
    public static Responsable responsable;
    
    private static int workload = 12;

    public static Map.Entry<String, String> hashPassword(String password_plaintext) {
        String salt = BCrypt.gensalt(workload);
        String hashed_password = BCrypt.hashpw(password_plaintext, salt);
        return new AbstractMap.SimpleEntry<>(salt, hashed_password);
    }
    
    public static boolean checkPassword(String password_plaintext, String stored_hash) {
		boolean password_verified = false;
                stored_hash = "$2a" + stored_hash.substring(3);
              //  System.out.println(stored_hash);
		if(null == stored_hash)
			throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

		password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

		return(password_verified);
	}

    static UtilisateurDao udao = new UtilisateurDao();

    public static boolean isLoginUsed(String username) {
        return udao.searchByLogin(username);
    }
    
    public static boolean isEmailUsed(String email) {
        return (udao.findByEmail(email) != null );
    }

}
