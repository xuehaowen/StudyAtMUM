package lesson9.labs.prob12;

@FunctionalInterface
public interface FourFunction<S,T,U,V,R> {
	R apply(S s, T t, U u, V v);
}
