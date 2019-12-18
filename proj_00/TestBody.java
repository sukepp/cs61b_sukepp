public class TestBody {
	public static void main(String[] args) {
		Body b0 = new Body(1, 0, 0, 0, 10, "b0");
		Body b1 = new Body(3, 3, 0, 0, 5, "b1");
		Body b2 = new Body(5, -3, 0, 0, 50, "b2");

		Body[] allBodys = {b0, b1, b2};
		System.out.println("b0 -> X:" + b0.calcNetForceExertedByX(allBodys) + " Y:" + b0.calcNetForceExertedByY(allBodys));
	}
}