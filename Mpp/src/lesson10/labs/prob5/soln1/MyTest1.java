package lesson10.labs.prob5.soln1;

import lesson10.labs.prob5.Employee;
import lesson10.labs.prob5.Main;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class MyTest1 {

    @Test
    public void test1(){
        List<Employee> emps = Arrays.asList(
                new Employee("Steven", "Walters", 135000),
                new Employee("Thomas", "Blake", 8000));
        String str = Main.asString(emps);
        Assert.assertEquals("Steven Walters", str);

    }

}
