// 제네릭을 이용해서 좀 더 제너럴하게 개선
public interface Predicate<T> {
	boolean test(T t);
}
