package ch3;

public class RunnableExam {
	public static void process(Runnable r) {
		r.run();
	}
	public static void main(String[] args) {
		// 람다 사용
		Runnable r1 = () -> System.out.println("Hello world 1");

		// 익명 클래스 사용
		Runnable r2 = new Runnable() {
			@Override
			public void run() {
				System.out.println("Hello world 2");
			}
		};
		process(r1);
		process(r2);
		process(() -> System.out.println("Hello world 3"));
	}
}
