package ch2;

// 빨간사과와 무게로 필터링하는 프레디케이트
public class AppleRedAndHeavyPredicate implements ApplePredicate{
	@Override
	public boolean test(Apple apple) {
		return Color.RED.equals(apple.getColor()) && apple.getWeight() > 150;
	}
}
