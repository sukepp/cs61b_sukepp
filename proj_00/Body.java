import java.lang.Math;

public class Body {
	double xxPos;
	double yyPos;
	double xxVel;
	double yyVel;
	double mass;
	String imgFileName;
	static final double grav = 6.67e-11;

	public Body (double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Body (Body b) {
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	public double calcDistance(Body b) {
		double x = b.xxPos - xxPos;
		double y = b.yyPos - yyPos;
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}

	public double calcForceExertedBy(Body b) {
		return grav * mass * b.mass / Math.pow(calcDistance(b), 2);
	}

	public double calcForceExertedByX(Body b) {
		return calcForceExertedBy(b) * (b.xxPos - xxPos) / calcDistance(b);
	}

	public double calcForceExertedByY(Body b) {
		return calcForceExertedBy(b) * (b.yyPos - yyPos) / calcDistance(b);
	}

	public double calcNetForceExertedByX(Body[] allBodies) {
		double netForceExertedByX = 0;
		for (int index = 0; index < allBodies.length; index++) {
			if (this.equals(allBodies[index])) {
				continue;
			}
			netForceExertedByX += calcForceExertedByX(allBodies[index]);
		}
		return netForceExertedByX;
	}	

	public double calcNetForceExertedByY(Body[] allBodies) {
		double netForceExertedByY = 0;
		for (int index = 0; index < allBodies.length; index++) {
			if (this.equals(allBodies[index])) {
				continue;
			}
			netForceExertedByY += calcForceExertedByY(allBodies[index]);
		}
		return netForceExertedByY;
	}

	public void update(double dt, double fX, double fY) {
		double aX = fX / mass;
		double aY = fY / mass;
		xxVel = xxVel + dt * aX;
		yyVel = yyVel + dt * aY;
		xxPos = xxPos + dt * xxVel;
		yyPos = yyPos + dt * yyVel;
	}

	public void draw() {
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
	}
}
