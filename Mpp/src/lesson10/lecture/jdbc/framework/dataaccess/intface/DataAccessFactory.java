package lesson10.lecture.jdbc.framework.dataaccess.intface;

import lesson10.lecture.dataaccess.*;

public class DataAccessFactory {
	public static DataAccess getDataAccess() {
		return new DataAccessSubsystemFacade();
	}
}
