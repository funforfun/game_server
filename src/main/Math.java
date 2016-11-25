package main;

public class Math {
    private int a;
    private int b;

    public Math(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getSum() {
        return a + b;
    }

    public int getDiv() {
        return a / b;
    }

}
