import processing.core.PApplet;

import java.util.ArrayList;

public class MainClass extends PApplet {
    public static PApplet processing;

    //
    public static void main(String[] args) {
        PApplet.main("MainClass", args);
    }

    float m = random(1f);
    float b = random(1f);
    ArrayList<Point> Points;

    public void setup() {
        processing = this;
        surface.setSize(900, 900);
        Points = new ArrayList<Point>();

    }

    public void draw() {
        background(255);
        MainClass.processing.textSize(20);
        MainClass.processing.text("M = " + m, width / 2f, 20);
        MainClass.processing.text("b = " + b, width / 2f, 50);
        for (Point p : Points) {
            p.display();
        }
        drawLine();
        if (Points.size() > 1) {
            //linearRegression();
            gradientDescent();

        }

    }


    public void mousePressed() {

        Points.add(new Point(mouseX, mouseY));

    }

    public void drawLine() {
        // y = mx + b
        float x1 = 0;
        float y1 = m * x1 + b;
        float x2 = 1;
        float y2 = m * x2 + b;
        float dx1 = map(x1, 0, 1, 0, MainClass.processing.width);
        float dy1 = map(y1, 0, 1, MainClass.processing.height, 0);
        float dx2 = map(x2, 0, 1, 0, MainClass.processing.width);
        float dy2 = map(y2, 0, 1, MainClass.processing.height, 0);
        MainClass.processing.stroke(255, 0, 255);
        MainClass.processing.line(dx1, dy1, dx2, dy2);
        // MainClass.processing.println(dx1,dy1,dx2,dy2);

    }

    public void linearRegression() {

        // m = Sum of (x - aveX) (y - aveY) / Sum of (x - aveX) (x - aveX)
        // b = aveY - m * aveX

        // Calculate averages
        float sumX = 0;
        float sumY = 0;
        float x;
        float y;
        for (int i = 0; i < Points.size(); i++) {
            sumX += Points.get(i).x;
            sumY += Points.get(i).y;
        }
        float aveX = sumX / Points.size();
        float aveY = sumY / Points.size();
        // Numerator  & denominator
        float numerator = 0;
        float denominator = 0;

        for (int i = 0; i < Points.size(); i++) {
            x = Points.get(i).x;
            y = Points.get(i).y;
            numerator += (x - aveX) * (y - aveY);
            denominator += (x - aveX) * (x - aveX);
        }

        m = numerator / denominator;
        b = aveY - (m * aveX);


    }

    public void gradientDescent() {
        float learningRate = 0.51f;
        for (int i = 0; i < Points.size(); i++) {
            float x = Points.get(i).x;
            float y = Points.get(i).y;

            float guess = m * x + b;
            float error =  y- guess ;
            m = m + (error * x) * learningRate;
            b = b + (error) * learningRate;
        }
    }


}