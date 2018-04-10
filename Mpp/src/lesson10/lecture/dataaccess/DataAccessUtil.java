
package lesson10.lecture.dataaccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.logging.Logger;

/**
 * @author pcorazza
 * @since Nov 10, 2004
 * Class Description:
 * 
 * 
 */
class DataAccessUtil {
	private static final Logger LOG =
		Logger.getLogger(DataAccessUtil.class.getName());
	
	public static ResultSet runQuery(SimpleConnectionPool pool, Connection con, String query) throws DatabaseException {
        LOG.info("Executing query: " + query);
        ResultSet rs = SimpleConnectionPool.doQuery(con, query);
        return rs;
    }
	protected static SimpleConnectionPool getPool(Dao dao) throws DatabaseException {
		return SimpleConnectionPool.getInstance(dao.getLogin()[0], dao.getLogin()[1],
				null, 1);
	}
    public static Integer runUpdate(SimpleConnectionPool pool, Connection con, String query) throws DatabaseException {        	
        Integer generatedKey = SimpleConnectionPool.doUpdate(con,query);  
        return generatedKey; 
    }
    
}
