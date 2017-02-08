package my.application;

/*import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
*/
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.EntityManagerFactory;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import my.jpa.Favorite;

public class IceCreamApp {
	
	private static EntityManagerFactory emfactory;
	private static EntityManager entitymanager;
	
	/*Fetch the favorite ice cream of the given name and return it*/
	public static String getFavorite( String name ) {
		
			emfactory = Persistence.createEntityManagerFactory( "Eclipselink_JPA" );
			entitymanager = emfactory.createEntityManager( );
			
			try {
				Query q = entitymanager.createQuery("Select f FROM Favorite f WHERE f.name = \"" + name + "\"");
				return toStringResultJSON(q.getSingleResult().toString());
			} catch (Exception e) {
				return "{ \"error\" : \"No results for " + name + "\" }";
			}	
	   }
	
	/*Update the database with the new name and ice cream favorite*/
	public static String newFavorite( String newName, String newIceCream ) {
	 
			String result;
	 		emfactory = Persistence.createEntityManagerFactory( "Eclipselink_JPA" );
    		entitymanager = emfactory.createEntityManager( );
			entitymanager.getTransaction( ).begin( );

			try {
				Favorite favorite = new Favorite( ); 
				favorite.setIceCream( newIceCream );
				favorite.setName( newName );
    
				entitymanager.persist( favorite );
				entitymanager.getTransaction( ).commit( );
				result = "{ \"result\" : \"Successfully added " + newName + " " + newIceCream + " to favorites\" }";
			} catch (Exception e) {
				result = "{ \"result\" : \"Exception when inserting " + newName + " " + newIceCream + " to favorites\" }";
			} finally {
				entitymanager.close( );
				emfactory.close( );
			}
    
    		return result;
 	}
	
	/*Format a returned query result string into JSON and return the result*/
	public static String toStringResultJSON( String text ) {
		   
		String split[] = text.split("=");
		
		return "{\n\t\"Favorite\" : {\n\t\t\"iceCream\": \"" + 
				split[1].substring(0,split[1].indexOf(',')) + 
				"\",\n\t\t\"name\": \"" + 
				split[2].substring(0, split[2].length() - 1) + 
				"\"\n\t}\n}";
   }
}
