package lesson11.labs.prob7.mystream_lab;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class MyStream<T> {
	private List<T> elements;
	public static <T> MyStream<T> of(List<T> aList) {
		//implement
		return new MyStream<>(aList);
	}
	private MyStream(List<T> aList) {
		//implement
		this.elements = new ArrayList<>(aList);
	}
	
	public static <S> MyStream<S> concat(MyStream<S> s1, MyStream<S> s2) {
		//implement
		MyStream<S> myStream = new MyStream<>(s1.elements);
		myStream.elements.addAll(s2.elements);
		return myStream;
	}
	
	public <R> MyStream<R> flatMap(Function<T, MyStream<R>> mapper) {
		//implement
		MyStream<R> myStream = new MyStream<>(new ArrayList<>());
		for(T t : elements){
			MyStream<R> tmp = mapper.apply(t);
			myStream.elements.addAll(tmp.elements);
		}
		return myStream;
	}
	
	public List<T> asList() {
		//implement
		return this.elements;
	}
	
	public <R> MyStream<R> map(Function<T,R> mapper) {
		//implement
		MyStream<R> myStream = new MyStream<>(new ArrayList<>());
		for(T t : elements){
			myStream.elements.add(mapper.apply(t));
		}
		return myStream;
	}
	
	public MyStream<T> filter(Predicate<T> predicate) {
		//implement
		MyStream<T> myStream = new MyStream<>(new ArrayList<>());
		for(T t : elements){
			if(predicate.test(t))
				myStream.elements.add(t);
		}
		return myStream;
	}
}
