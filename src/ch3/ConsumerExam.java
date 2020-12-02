package ch3;

import java.util.List;
import java.util.function.Consumer;

public class ConsumerExam {
	public static  <T> void forEach(List<T> list, Consumer<T> c) {
		for (T t : list) {
			c.accept(t);
		}
	}
}
