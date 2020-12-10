package ch5;

import static ch4.Dish.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import ch4.Dish;

public class Test {
	public static void main(String[] args) {
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");

		List<Transaction> transactions = Arrays.asList(
			new Transaction(brian, 2011, 300),
			new Transaction(raoul, 2012, 1000),
			new Transaction(raoul, 2011, 400),
			new Transaction(mario, 2012, 710),
			new Transaction(mario, 2012, 700),
			new Transaction(alan, 2012, 950)
		);

		List<Transaction> number1 = transactions.stream()
			.filter(t -> t.getYear() == 2011)
			.sorted(Comparator.comparing(Transaction::getValue))
			.collect(Collectors.toList());
		System.out.println(number1);

		List<String> number2 = transactions.stream()
			.map(t -> t.getTrader().getCity())
			.distinct()
			.collect(Collectors.toList());
		System.out.println(number2);

		List<String> number3 = transactions.stream()
			.filter(t -> t.getTrader().getCity().equals("Cambridge"))
			.map(t -> t.getTrader().getName())
			.distinct()
			.sorted()
			.collect(
				Collectors.toList());
		System.out.println(number3);

		List<String> number4 = transactions.stream()
			.map(t -> t.getTrader().getName())
			.distinct()
			.sorted()
			.collect(Collectors.toList());
		System.out.println(number4);

		Optional<Transaction> number5 = transactions.stream()
			.filter(t -> t.getTrader().getCity().equals("Milan"))
			.findAny();
		System.out.println(number5);

		boolean number5_2 = transactions.stream().anyMatch(t -> t.getTrader().getCity().equals("Milan"));
		System.out.println(number5_2);

		List<Integer> number6 = transactions.stream()
			.filter(t -> t.getTrader().getCity().equals("Cambridge"))
			.map(Transaction::getValue)
			.collect(
				Collectors.toList());
		System.out.println(number6);

		Optional<Integer> max = transactions.stream().map(Transaction::getValue).reduce(Integer::max);
		System.out.println(max.get());
		Optional<Integer> min = transactions.stream().map(Transaction::getValue).reduce(Integer::min);
		System.out.println(min.get());

		int calories = menu.stream().mapToInt(Dish::getCalories).sum();
		OptionalInt maxCalories = menu.stream().mapToInt(Dish::getCalories).max();

		IntStream evenNumbers = IntStream.rangeClosed(1, 100).filter(n -> n % 2 == 0);
		System.out.println(evenNumbers.count());

		Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100).boxed()
			.flatMap(a ->
				IntStream.rangeClosed(1, 100)
					.filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
					.mapToObj(b -> new int[] {a, b, (int)Math.sqrt(a * a + b * b)})
			);

		pythagoreanTriples.limit(5).forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

		Stream<String> values = Stream.of("config", "home", "user").flatMap(key -> Stream.ofNullable(System.getProperty(key)));

		Stream.iterate(new int[] {0, 1}, t -> new int[] {t[1], t[0]+t[1]}).limit(20).forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));
	}
}
