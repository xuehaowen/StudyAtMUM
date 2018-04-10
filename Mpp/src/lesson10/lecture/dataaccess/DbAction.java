
package lesson10.lecture.dataaccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


class DbAction {
    protected String query;
    protected ResultSet resultSet;
    protected Dao concreteDao;
    protected SimpleConnectionPool pool;
    protected Connection con;
    
    DbAction(Dao c) throws DatabaseException {
        concreteDao = c;
        pool = DataAccessUtil.getPool(concreteDao);
        con = pool.getConnection(concreteDao.getDbUrl()); //new
    }
    void performRead() throws DatabaseException {
        concreteDao.buildQuery();
        ResultSet resultSet = DataAccessUtil.runQuery(pool, con, 
                                            concreteDao.getQuery());
        
        concreteDao.populateEntity(resultSet);
    }
    
    
    Integer performUpdate() throws DatabaseException {
        concreteDao.buildQuery();
        Integer generatedKey = DataAccessUtil.runUpdate(pool, con,
                				 concreteDao.getQuery());
   
        return generatedKey;
    }
    
    void performDelete() throws DatabaseException {
        concreteDao.buildQuery();
        DataAccessUtil.runUpdate(pool, con,
                				 concreteDao.getQuery());
    }
    
    void returnToPool() throws DatabaseException {
    	pool.returnToPool(con, concreteDao.getDbUrl());
    }
    
    void startTransaction() throws DatabaseException {
    	try {
			con.setAutoCommit(false);			
		} catch(SQLException e) {
			throw new DatabaseException("DbAction.startTransaction() " + 
				"encountered a SQLException " + e.getMessage());		
		}
    }
    void commit() throws DatabaseException {
    	System.out.println("\nCOMMIT \n");
    	try {
			con.commit();
		} catch(SQLException e) {
			throw new DatabaseException(e.getMessage());
		}
    }
    public void rollback() throws DatabaseException {
		try {
			con.rollback();		
		} catch(SQLException e) {
			throw new DatabaseException("rollback encountered a SQLException " + e.getMessage());		
		}
	}

}
