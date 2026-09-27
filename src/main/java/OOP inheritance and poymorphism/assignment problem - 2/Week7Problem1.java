public class Week7Problem1 {
    static abstract class Shape {
        private static int count = 0;
        private final String shapeId;

        Shape() {
            shapeId = "SH-" + (++count);
        }

        public abstract double calculateArea();

        void scale(double factor) {
            scaleShape(factor);
        }

        void scale(double xFactor, double yFactor) {
            scale(xFactor);
            scale(yFactor);
        }

        abstract void scaleShape(double factor);

        String getShapeId() {
            return shapeId;
        }
    }

    static class CircleShape extends Shape {
        double radius;

        CircleShape(double radius) {
            this.radius = radius;
        }

        public double calculateArea() {
            return Math.PI * radius * radius;
        }

        void scaleShape(double factor) {
            radius *= factor;
        }
    }

    static class SquareShape extends Shape {
        double side;

        SquareShape(double side) {
            this.side = side;
        }

        public double calculateArea() {
            return side * side;
        }

        void scaleShape(double factor) {
            side *= factor;
        }
    }

    static void printArea(Shape s) {
        System.out.println(s.calculateArea());
    }

    public static void main(String[] args) {
        CircleShape c = new CircleShape(5.0);
        SquareShape sq = new SquareShape(4.0);

        printArea(c);
        printArea(sq);

        sq.scale(2.0);
        printArea(sq);

        System.out.println(c.getShapeId());
        System.out.println(sq.getShapeId());
    }
}