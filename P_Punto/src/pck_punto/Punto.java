package pck_punto;

/**
 *
 * @author dieca
 */
public class Punto {

    private int x;
    private int y;

    public Punto(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Punto() {
        this.x = 0;
        this.y = 0;
    }

    public void setX(int vx) {
        x = vx;
    }

    public void setY(int vy) {
        y = vy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
