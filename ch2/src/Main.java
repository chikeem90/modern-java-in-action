import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		List<Apple> inventory = Arrays.asList(new Apple(80, Color.GREEN), new Apple(155, Color.GREEN), new Apple(120, Color.RED));
		// 직접 클래스를 구현해서 인자로 넘김
		List<Apple> heavyApples = FilteringApples.filterApples(inventory, new AppleHeavyWeightPredicate());
		List<Apple> greenApples = FilteringApples.filterApples(inventory, new AppleGreenColorPredicate());
		
		// 익명 클래스를 이용해서 즉석에서 구현
		List<Apple> redApples = FilteringApples.filterApples(inventory, new ApplePredicate() {
			@Override
			public boolean test(Apple apple) {
				return Color.RED.equals(apple.getColor());
			}
		});

		// 위 코드를 람다를 이용해서 간결하게 구현할 수 있다
		List<Apple> result = FilteringApples.filterApples(inventory, apple -> Color.RED.equals(apple.getColor()));

		// 제네릭을 적용한 프레디케이트를 이용
		List<Apple> blueApples = FilteringApples.filterApples(inventory, apple -> Color.BLUE.equals(apple.getColor()));

		// Comparator를 sorting할 때 동작파라미터로 전달해서 처리
		inventory.sort(new Comparator<Apple>() {
			@Override
			public int compare(Apple o1, Apple o2) {
				return o1.getWeight().compareTo(o2.getWeight());
			}
		});

		// 람다식으로 표현
		inventory.sort((o1, o2) -> o1.getWeight().compareTo(o2.getWeight()));


		// Thread를 수행할 때 Runnable을 동작파라미터로 전달해서 수행되도록 처리
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Hello world");
			}
		});

		// 람다식으로 표현
		Thread t2 = new Thread(() -> System.out.println("Hello world"));
	}
}
