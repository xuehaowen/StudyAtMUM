package lesson9.labs.prob14;

import java.util.Optional;

public class MySingletonLazy {
    private static MySingletonLazy instance = null;
    private MySingletonLazy(){

    }
    public static MySingletonLazy getInstance(){
        return Optional.ofNullable(instance).orElseGet(MySingletonLazy::new);
    }

    public static void main(String[] args) {
        System.out.println(MySingletonLazy.getInstance() == null);
    }
}
