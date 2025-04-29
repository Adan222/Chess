package chess;

public class App {
    public static void main(String[] args) {
        try {
            Game g = new Game();
            g.run();
        } catch (Exception error) {
            System.out.println("Exception message: " + error.getMessage());
            System.out.println("");
            System.out.println("Stack trace: ");
            error.printStackTrace();
        }
    }
}
