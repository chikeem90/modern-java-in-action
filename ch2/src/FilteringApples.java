import java.util.ArrayList;
import java.util.List;

public class FilteringApples {
	// 1. 초록 사과를 필터링 하는 메소드
	public static List<Apple> filterGreenApples(List<Apple> inventory) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (Color.GREEN.equals(apple.getColor())) {
				result.add(apple);
			}
		}
		return result;
	}

	// 2. 컬러도 파라미터로 받아서 어떤 컬러로 필터링할 것인지 개선
	public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (apple.getColor().equals(color)) {
				result.add(apple);
			}
		}
		return result;
	}

	// 3. 무게로 사과를 필터링하는 메소드
	public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (apple.getWeight() > weight) {
				result.add(apple);
			}
		}
		return result;
	}

	// 4. 가능한 모든 속성으로 필터링하도록 수정
	public static List<Apple> filterApples(List<Apple> inventory, Color color, int weight, boolean flag) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if ((flag && apple.getColor().equals(color)) || (!flag && apple.getWeight() > weight)) {
				result.add(apple);
			}
		}
		return result;
	}

	// 5. 프레디케이트를 인자로 받음으로써 컬렉션을 반복하는 로직과 컬렉션의 각 요소에 적용할 동작을 분리할 수 있음
	public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (p.test(apple)) {
				result.add(apple);
			}
		}
		return result;
	}

	// 제네릭을 이용해서 좀 더 제너럴하게 수정
	public static <T> List<T> filter(List<T> list, Predicate<T> p) {
		List<T> result = new ArrayList<>();
		for (T e : list) {
			if (p.test(e)) {
				result.add(e);
			}
		}
		return result;
	}
}
