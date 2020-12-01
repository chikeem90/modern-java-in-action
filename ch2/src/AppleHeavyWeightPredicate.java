// 무거운 사과만 필터링하는 프레디케이트
public class AppleHeavyWeightPredicate implements ApplePredicate {
	@Override
	public boolean test(Apple apple) {
		return apple.getWeight() > 150;
	}
}
