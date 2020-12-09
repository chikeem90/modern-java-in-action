package ch4;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Main {
	public static void main(String[] args) {
		// 자바 7 기준
		// List<Dish> lowCaloricDishes = new ArrayList<>();
		// for (Dish dish : Dish.menu) {
		// 	if (dish.getCalories() < 400) {
		// 		lowCaloricDishes.add(dish);
		// 	}
		// }
		// // 칼로리 적은 순으로 정렬
		// Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
		// 	@Override
		// 	public int compare(Dish o1, Dish o2) {
		// 		return Integer.compare(o1.getCalories(), o2.getCalories());
		// 	}
		// });
		// List<String> lowCaloricDishesName = new ArrayList<>();
		// for (Dish dish : lowCaloricDishes) {
		// 	lowCaloricDishesName.add(dish.getName());
		// }

		List<String> lowCaloricDishesName = Dish.menu.parallelStream()
													.filter(dish -> dish.getCalories() < 400)
													.sorted(comparing(Dish::getCalories))
													.map(Dish::getName)
													.collect(toList());
		System.out.println(lowCaloricDishesName);

		Map<Type, List<Dish>> dishesByType = Dish.menu.stream().collect(groupingBy(Dish::getType));

		List<String> threeHighCaloricDishNames = Dish.menu.stream()
														.filter(dish -> dish.getCalories() > 300)
														.map(Dish::getName)
														.limit(3)
														.collect(toList());
		System.out.println(threeHighCaloricDishNames);
		List<String> names = Dish.menu.stream()
									.filter(dish -> {
										System.out.println("filtering: " + dish.getName());
										return dish.getCalories() > 300;
									}).map(dish -> {
										System.out.println("mapping: " + dish.getName());
										return dish.getName();
									})
									.limit(3)
									.collect(toList());
		System.out.println(names);
		Dish.menu.stream().forEach(System.out::println);
	}
}
