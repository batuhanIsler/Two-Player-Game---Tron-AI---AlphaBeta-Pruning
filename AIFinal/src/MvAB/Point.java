package MvAB;






import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Point {

    int x, y;

    
    
    public Point() {
		this.x = -1;
		this.y = -1;
	}
    
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}