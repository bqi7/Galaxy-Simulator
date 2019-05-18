public class Planet {
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;
    static final double g = 6.67 * 1e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
      }

      public Planet(Planet p) {
          xxPos = p.xxPos;
          yyPos = p.yyPos;
          xxVel = p.xxVel;
          yyVel = p.yyVel;
          mass = p.mass;
          imgFileName = p.imgFileName;
      }

      public double calcDistance(Planet p) {
          double xDif = xxPos - p.xxPos;
          double yDif = yyPos - p.yyPos;
          return Math.sqrt(xDif * xDif + yDif * yDif);
      }

      public double calcForceExertedBy(Planet p) {
          double difDistance = calcDistance(p);
          return (g * mass * p.mass) / (difDistance * difDistance);
      }

      public double calcForceExertedByX(Planet p) {
          double xDif = p.xxPos - xxPos;
          return calcForceExertedBy(p) * xDif / calcDistance(p);
      }

      public double calcForceExertedByY(Planet p) {
          double yDif = p.yyPos - yyPos;
          return calcForceExertedBy(p) * yDif / calcDistance(p);
      }

      public double calcNetForceExertedByX (Planet[] allPlanets) {
          double totalNetForceX = 0.0;
          for (Planet p: allPlanets) {
              if (!this.equals(p)) {
                  totalNetForceX += calcForceExertedByX(p);
              }
          }
          return totalNetForceX;
      }

      public double calcNetForceExertedByY (Planet[] allPlanets) {
          double totalNetForceY = 0.0;
          for (Planet p: allPlanets) {
              if (!this.equals(p)) {
                  totalNetForceY += calcForceExertedByY(p);
                }
          }
          return totalNetForceY;
      }

      public void update(double dt, double fX, double fY) {
          double xxNetAccel = fX / mass;
          double yyNetAccel = fY / mass;
          xxVel = xxVel + dt * xxNetAccel;
          yyVel = yyVel + dt * yyNetAccel;
          xxPos = xxPos + dt * xxVel;
          yyPos = yyPos + dt * yyVel;
      }

      public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
      }
}
