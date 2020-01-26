public class Point {
    float x, y;

    Point(float x, float y) {
        this.x = MainClass.processing.map(x, 0, MainClass.processing.width, 0, 1);
        this.y = MainClass.processing.map(y, 0, MainClass.processing.height, 1, 0);

    }

    public void display() {
        float dx = MainClass.processing.map(x, 0, 1, 0, MainClass.processing.width);
        float dy = MainClass.processing.map(y, 0, 1, MainClass.processing.height, 0);
        MainClass.processing.noStroke();
        MainClass.processing.fill(0);
        MainClass.processing.ellipse(dx, dy, 5, 5);

    }
}
