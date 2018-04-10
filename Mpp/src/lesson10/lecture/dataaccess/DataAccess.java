
package lesson10.lecture.dataaccess;

/**
 * This interface provides access to the Data Access Subsystem services, which
 * include all forms of read/write behavior. Implementations of the Dao interface
 * will provide the necessary data to configure a connection to the intended
 * database.
 *
 */
public interface DataAccess {
	/**
	 * This method starts the data access interaction and permits the use of transactions. 
	 * The DataAccessSubsystem will keep a reference to the Connection for all future uses 
	 * in the current instance so the Connection object does not need to be managed by the client.
	 * @param dao - This is the implementation of the Dao interface that carries the SQL
	 * queries and data for interaction with the data store. 
	 * @see dataaccess.Dao
	 */
	public void createConnection(Dao dao) throws DatabaseException;
	/**
	 * Releases the Connection. Typically, this will mean returning the Connection
	 * to the Connection Pool for reuse later.
	 */
	public void releaseConnection() throws DatabaseException;
	
	/**
	 * Begins a transaction. This is equivalent to setting autocommit to false.
	 * All jdbc calls made until a commit() call is made will be wrapped into a transaction.
	 */
	public void startTransaction() throws DatabaseException;
	/**
	 * After all jdbc calls have been declared, the commit method should be called,
	 * all within a try block. When commit is called, the system will try to 
	 * perform all indicated jdbc calls within a transaction. If the attempt fails,
	 * a DatabaseException will be thrown; this should be caught and should be handled
	 * by calling rollback().
	 */
	public void commit() throws DatabaseException;
	/**
	 * If a commit operation fails in a try block, rollback will undo the transaction if it
	 * is called in a catch block.
	 */
	public void rollback() throws DatabaseException;
	/**
	 * Carries out a single SELECT statement as part of a larger transaction. For reads that
	 * are not part of a transaction, use atomicRead(dao)
	 */
    public void read() throws DatabaseException;  
    /**
	 * Carries out a single INSERT or UPDATE statement as part of a larger transaction. Writes
	 * of any kind should be wrapped in a transaction. For writes that involve only a single
	 * jdbc call, use the convenience method saveWithinTransaction().
	 * 
	 * @return Integer - For inserts, the save method returns the auto-generated unique id
	 * for the row that was added, if there is one. For updates, there is no return value.
	 */
    public Integer save() throws DatabaseException;
    /**
	 * Carries out a single DELETE statement as part of a larger transaction. Deletes
	 * of any kind should be wrapped in a transaction. For deletes that involve only a single
	 * jdbc call, use the convenience method deleteWithinTransaction().
	 * 
	 * @return Integer - Returns the number of rows affected.
	 */
    public Integer delete() throws DatabaseException;
    /**
     * Convenience method to wrap a single save operation in a transaction. Takes care
     * of getting a Connection, starting the transaction, committing, rolling back if necessary,
     * and releasing the Connection.
     */
	public Integer saveWithinTransaction(Dao dao) throws DatabaseException;
	
	/**
     * Convenience method to wrap a single delete operation in a transaction. Takes care
     * of getting a Connection, starting the transaction, committing, rolling back if necessary,
     * and releasing the Connection.
     */
	public Integer deleteWithinTransaction(Dao dao) throws DatabaseException;
	
	/**
	 * Performs a single read (SELECT) operation. Takes care of getting and releasing the Connection.
	 */
	public void atomicRead(Dao dao) throws DatabaseException;
	
		
	
}
