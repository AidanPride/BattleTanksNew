package game;

public class Launcher {
    public static void main(String[] args) throws Exception {
        StartGUI start = new StartGUI();
        Thread.sleep(5000);
        ActionField af = new ActionField(start.getTank());
        af.runTheGame();
    }
}
