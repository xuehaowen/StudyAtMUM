package lesson9.labs.prob5;


import java.util.HashMap;
import java.util.function.Function;

public class Pair {

    HashMap<EmployeeInfoBetter.SortMethod, Function> map = new HashMap<EmployeeInfoBetter.SortMethod, Function>();

    public Pair() {
        Function<Employee, String> byName = e -> e.getName();
        Function<Employee, Integer> bySalary = e -> -e.getSalary();
        map.put(EmployeeInfoBetter.SortMethod.BYNAME, byName);
        map.put(EmployeeInfoBetter.SortMethod.BYSALARY, bySalary);
    }

    public Function get(EmployeeInfoBetter.SortMethod method){
        return map.get(method);
    }

    public Function getAnother(EmployeeInfoBetter.SortMethod method){
        if(method == EmployeeInfoBetter.SortMethod.BYNAME){
            return map.get(EmployeeInfoBetter.SortMethod.BYSALARY);
        }else{
            return map.get(EmployeeInfoBetter.SortMethod.BYNAME);
        }
    }
}
