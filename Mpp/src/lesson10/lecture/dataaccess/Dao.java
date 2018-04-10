
package lesson10.lecture.dataaccess;

import java.sql.ResultSet;

/** 
 * This is the interface that all concrete Daos must implement.
 * Daos are responsible for providing connection access (for
 * entity classes) to the database, by way of the Data Access
 * Subsystem.
 */
public interface Dao {
	/**
	 * Builds the query that this Dao will attempt to execute
	 */
    public void buildQuery() throws DatabaseException ;
    
    /**
     * If the query executed by this Dao is some type of read operation, this
     * method is responsible for populating an appropriate entity class with the
     * data that comes back from the database in the form of a ResultSet.
     * @param resultSet - The result of some read operation. The Data Access Subsystem
     * will call this method upon completion of such an operation.
     */
    public void populateEntity(ResultSet resultSet) throws DatabaseException ;
    
    /**
     * Returns the query that needs to be executed
     */
    public String getQuery();
    
    /**
     * Returns the dburl needed to communicate with the intended DriverManager.
     * A default implementation is provided.
     */
    
    default String getDbUrl() {
    	return "jdbc:derby://localhost:1527/MPP_DB;create=true";
    }
    
    /**
     * Returns login info to provide access to the intended DBMS. First array
     * element is the username, the second is the password. A default implementation,
     * for use with JavaDB, is provided.
     */
    default String[] getLogin() {
    	return new String[] {"app", "app"};
    }
}



