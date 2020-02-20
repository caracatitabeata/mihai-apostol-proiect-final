package fun3;

public class Cub extends Bear {
    //String name = "Kitty";

    //void roar(){ System.out.println("I am " + name + "the cub, and I roar too!!"); }

    static void smile() {
        System.out.println("I smile like a demon");
    }

    public static void main(String[] args) {
        Bear bear = new Cub();
        bear.roar();
        //bear.smile();
        Double l = 2.02;

    }
}
