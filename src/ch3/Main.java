package ch3;

import static java.util.Comparator.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class Main {
	public static void main(String[] args) throws IOException {
		ProcessExam processTest = new ProcessExam();
		String oneLine = processTest.processFile((BufferedReader br) -> br.readLine());
		String twoLines = processTest.processFile((BufferedReader br) -> br.readLine() + br.readLine());

		List<String> listOfStrings = new ArrayList<>();
		listOfStrings.add("");
		listOfStrings.add("abc");
		listOfStrings.add("bbbb");

		// java.util.function에서 기본적으로 제공하는 새로운 함수형 인터페이스
Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
List<String> nonEmpty = FilterExam.filter(listOfStrings, nonEmptyStringPredicate);
		System.out.println(nonEmpty);

ConsumerExam.forEach(Arrays.asList(1,2,3,4,5), (Integer i) -> System.out.println(i));

List<Integer> l = FunctionExam.map(Arrays.asList("lamdas", "in", "action"), (String s) -> s.length());
		System.out.println(l);

		// 기본형을 입출력으로 사용하는 상황에서 오토박싱 동작을 피할 수 있도록 특별한 함수형 인터페이스를 제공
		// 박싱 X
		IntPredicate evenNumbers = (int i) -> i % 2 == 0;
		System.out.println(evenNumbers.test(1000));

		// 박싱 O
		Predicate<Integer> oddNumbers = (Integer i) -> i % 2 != 0;
		System.out.println(oddNumbers.test(1000));

		List<Apple> inventory = new ArrayList<>();
		inventory.addAll(Arrays.asList(
			new Apple(80, Color.GREEN),
			new Apple(155, Color.GREEN),
			new Apple(120, Color.RED)
		));

// 형식 추론 X
Comparator<Apple> c = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
// 형식 추론
// Comparator<Apple> c = (a1, a2) -> a1.getWeight().compareTo(a2.getWeight());

// 1단계 Comparator 객체를 인수로 받아 두 사과를 비교
inventory.sort(new AppleComparator());

// 2단계 익명 클래스를 이용해서 Comparator를 인수로 넘김
inventory.sort(new Comparator<Apple>() {
	@Override
	public int compare(Apple o1, Apple o2) {
		return o1.getWeight().compareTo(o2.getWeight());
	}
});

// 3단계 람다 표현식 사용
inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));
// Comparator<Apple> c = comparing((Apple a) -> a.getWeight());
inventory.sort(comparing(apple -> apple.getWeight()));
		
// 4단계 메서드 참조 사용
inventory.sort(comparing(Apple::getWeight));

// Comparator 조합
// 디폴트 메서드인 reversed를 사용
inventory.sort(comparing(Apple::getWeight)
	.reversed()
	.thenComparing(Apple::getCountry));

Predicate<Apple> redApple = apple -> Color.RED.equals(apple.getColor());
Predicate<Apple> notRedApple = redApple.negate();
// 프레디케이트를 조합해서 사용 가능
Predicate<Apple> redAndHeavyApple = redApple.and(apple -> apple.getWeight() > 150);
Predicate<Apple> redAndHeavyAppleOrGreen = redApple.and(apple -> apple.getWeight() > 150)
													.or(apple -> Color.GREEN.equals(apple.getColor()));

// Function 인터페이스도 조합 가능
Function<Integer, Integer> f = x -> x + 1;
Function<Integer, Integer> g = x -> x * 2;
Function<Integer, Integer> h1 = f.andThen(g);
Function<Integer, Integer> h2 = f.compose(g);
int result1 = h1.apply(1); // 4
int result2 = h2.apply(1); // 3
		System.out.println(result1);
		System.out.println(result2);

// 다양한 변환 파이프라인 만들 수 있음
Function<String, String> addHeader = Letter::addHeader;
Function<String, String> transformationPipeline1 = addHeader.andThen(Letter::checkSpelling)
															.andThen(Letter::addFooter);
Function<String, String> transformationPipeline2 = addHeader.andThen(Letter::addFooter);
	}
}
