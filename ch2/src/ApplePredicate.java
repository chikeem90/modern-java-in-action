// 선택 조건을 결정하는 인터페이스로 따로 뺀다
// 전략 디자인 패턴
public interface ApplePredicate {
	boolean test(Apple apple);
}
