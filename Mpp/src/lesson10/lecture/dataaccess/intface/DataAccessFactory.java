package lesson10.lecture.dataaccess.intface;

import lesson10.lecture.dataaccess.*;

public class DataAccessFactory {
	public static DataAccess newDataAccess() {
		return new DataAccessSubsystemFacade();
	}
}
