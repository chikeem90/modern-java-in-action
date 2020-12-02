package ch2;

public class Apple {
	private Integer weight = 0;
	private Color color;

	public Apple(int weight, Color color) {
		this.weight = weight;
		this.color = color;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return String.format("ch2.Apple{color=%s, weight=%d}", color, weight);
	}
}
