public class NBody {
	public static double readRadius(String fileName) {
		In in = new In(fileName);
		int numberOfPlanets = in.readInt();
		double radius = in.readDouble();

		return radius;
	}

	public static Body[] readBodies(String fileName) {
		In in = new In(fileName);
		int numberOfPlanets = in.readInt();
		double radius = in.readDouble();
		Body[] planets = new Body[numberOfPlanets];
		for (int i = 0; i < numberOfPlanets; i++) {
			double x_pos = in.readDouble();
			double y_pos = in.readDouble();
			double x_vel = in.readDouble();
			double y_vel = in.readDouble();
			double mass = in.readDouble();
			String image = in.readString();
			planets[i] = new Body(x_pos, y_pos, x_vel, y_vel, mass, image);
		}

		return planets;
	}

	public static void drawBackground(double radius, String imageToDraw) {
		// StdDraw.enableDoubleBuffering();

		/** Sets up the universe so it goes from
		  * -100, -100 up to 100, 100 */
		// StdDraw.setScale(-radius, radius);

		/* Clears the drawing window. */
		// StdDraw.clear();

		/* Stamps three copies of advice.png in a triangular pattern. */
		StdDraw.picture(0, 0, imageToDraw);

		/* Shows the drawing to the screen, and waits 2000 milliseconds. */
		// StdDraw.show();
		// StdDraw.pause(2000);
	}

	public static void main(String[] args) {
		if (args.length < 3) {
			System.out.println("Please input three arguments");
			return;
		}

		double time = 0;
		double endTime = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		double radius = readRadius(args[2]);
		Body[] planets = readBodies(args[2]);
		double[] xForces = new double[planets.length];
		double[] yForces = new double[planets.length];
		
		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius, radius);

		while (time < endTime) {
			for (int i = 0; i < planets.length; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for (int i = 0; i < planets.length; i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
			}

			drawBackground(radius, "images/starfield.jpg");

			for (int i = 0; i < planets.length; i++) {
				planets[i].draw();
			}

			StdDraw.show();
			StdDraw.pause(10);

			time += dt;
		}

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
		}
	}
}
