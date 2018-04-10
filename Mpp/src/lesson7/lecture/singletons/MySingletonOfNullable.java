package lesson7.lecture.singletons;
import java.util.Optional;
public class MySingletonOfNullable {
	private static MySingletonOfNullable instance = null;
	private MySingletonOfNullable() {}
	public static MySingletonOfNullable getInstance() {
		return Optional.ofNullable(instance).orElseGet(MySingletonOfNullable::new);
	}
}
