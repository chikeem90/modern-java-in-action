// 초록색 사과만 필터링하는 프레디케이트
public class AppleGreenColorPredicate implements ApplePredicate {
	@Override
	public boolean test(Apple apple) {
		return Color.GREEN.equals(apple.getColor());
	}
}
