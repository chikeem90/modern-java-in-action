package ch5;

import static ch4.Dish.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ch4.Dish;
import ch4.Type;

public class Main {
	public static void main(String[] args) {
		// 프레디케이트 필터링
		List<Dish> vegetarianMenu = menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList());

		// 고유 요소 필터링
		List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
		numbers.stream().filter(i -> i % 2 == 0).distinct().forEach(System.out::println);

		List<Dish> specialMenu = Arrays.asList(
			new Dish("seasonal fruit", true, 120, Type.OTHER),
			new Dish("prawns", false, 300, Type.FISH),
			new Dish("rice", true, 350, Type.OTHER),
			new Dish("chicken", false, 400, Type.MEAT),
			new Dish("french fries", true, 530, Type.OTHER)
		);

		List<Dish> slicedMenu1 = specialMenu.stream()
			.takeWhile(dish -> dish.getCalories() < 320)
			.collect(Collectors.toList());

		List<Dish> slicedMenu2 = specialMenu.stream()
			.dropWhile(dish -> dish.getCalories() < 320)
			.collect(Collectors.toList());

		List<Dish> dishes1 = specialMenu.stream()
			.filter(dish -> dish.getCalories() > 300)
			.limit(3)
			.collect(Collectors.toList());

		List<Dish> dishes2 = specialMenu.stream()
			.filter(dish -> dish.getCalories() > 300)
			.skip(2)
			.collect(Collectors.toList());

		List<String> dishName = menu.stream()
			.map(Dish::getName)
			.collect(Collectors.toList());

		List<String> words = Arrays.asList("Modern", "Java", "In", "Action");
		List<Integer> wordLengths = words.stream()
			.map(String::length)
			.collect(Collectors.toList());

		List<Integer> dishNameLengths = menu.stream()
			.map(Dish::getName)
			.map(String::length)
			.collect(Collectors.toList());

		String[] wordArray = {"Hello", "World"};
		List<String[]> wordList = words.stream()
			.map(word -> word.split(""))
			.distinct()
			.collect(Collectors.toList());
		System.out.println(wordList);

		List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5);
		List<Integer> resultNumberList = numberList.stream()
			.map(number -> number * number)
			.collect(Collectors.toList());
		System.out.println(resultNumberList);

		List<Integer> numbers1 = Arrays.asList(1, 2, 3);
		List<Integer> numbers2 = Arrays.asList(3, 4);
		List<int[]> pairs = numbers1.stream()
			.flatMap(i -> numbers2.stream().map(j -> new int[] {i, j}))
			.filter(ints -> (ints[0] + ints[1] % 3 == 0))
			.collect(Collectors.toList());

		if (menu.stream().anyMatch(Dish::isVegetarian)) {
			System.out.println("The menu is (somewhat) vegetarian friendly!");
		}

		boolean isHealthy1 = menu.stream().allMatch(dish -> dish.getCalories() < 1000);
		boolean isHealthy2 = menu.stream().noneMatch(dish -> dish.getCalories() >= 1000);

		menu.stream()
			.filter(Dish::isVegetarian)
			.findAny()
			.ifPresent(dish -> System.out.println(dish.getName()));

		List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
		Optional<Integer> firstSquareDivisibleByThree = someNumbers.stream()
			.map(n -> n * n)
			.filter(n -> n % 3 == 0)
			.findFirst();

		List<Integer> numbers3 = Arrays.asList(4, 5, 3, 9);
		int sum = numbers3.stream().reduce(0, Integer::sum);
		System.out.println(sum);

		Optional<Integer> sumOptional = numbers3.stream().reduce(Integer::sum);

		Optional<Integer> max = numbers3.stream().reduce(Integer::max);
		Optional<Integer> min = numbers3.stream().reduce(Integer::min);

		Integer count = menu.stream()
			.map(dish -> 1)
			.reduce(0, Integer::sum);
		System.out.println(count);
	}
}
