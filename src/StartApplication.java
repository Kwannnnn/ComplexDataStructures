import nl.saxion.app.SaxionApp;

public class StartApplication implements Runnable {
    public static void main(String[] args) {
        SaxionApp.start(new StartApplication());
    }

    @Override
    public void run() {

    }
}
