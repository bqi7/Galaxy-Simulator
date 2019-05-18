public class NBody {

    public static double readRadius(String filename) {
        In in = new In(filename);
        in.readLine();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int num = in.readInt();
        Planet[] pl = new Planet[num];
        int count = 0;
        double radius = in.readDouble();

        while (!in.isEmpty() && count != num) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();

            pl[count++] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }

        return pl;
    }

    public static void main(String[] args) {
        StdDraw.enableDoubleBuffering();

        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);

        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        StdDraw.setScale(-radius, radius);

        for(int t = 0; t <= T; t += dt) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];

            for(int j = 0; j < planets.length; ++j) {
                xForces[j] = planets[j].calcNetForceExertedByX(planets);
                yForces[j] = planets[j].calcNetForceExertedByY(planets);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");

            for(int j = 0; j < planets.length; ++j) {
                planets[j].update(t, xForces[j], yForces[j]);
                planets[j].draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i += 1) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }

    }
}
