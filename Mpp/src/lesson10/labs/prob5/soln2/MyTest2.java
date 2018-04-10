package lesson10.labs.prob5.soln2;

import lesson10.labs.prob5.Employee;
import lesson10.labs.prob5.Main;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class MyTest2 {

    @Test
    public void test1(){
        boolean r = Main.salaryGreaterThan100000.test( new Employee("John", "Sims", 110000));
        Assert.assertEquals(true, r);
    }

    @Test
    public void test2(){
        boolean r = Main.lastNameAfterM.test(new Employee("Thomas", "Blake", 8000));
        Assert.assertEquals(false, r);
    }

    @Test
    public void test3(){
        List<Employee> emps = Arrays.asList(
                new Employee("Steven", "Walters", 135000),
                new Employee("Thomas", "Blake", 8000));
        String str = Main.asString(emps);
        Assert.assertEquals("Steven Walters", str);
    }


}
