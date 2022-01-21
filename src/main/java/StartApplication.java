import nl.saxion.app.SaxionApp;
import nl.saxion.cds.DataManager;
import nl.saxion.cds.SystemFacade;
import nl.saxion.cds.UI;
import nl.saxion.cds.exception.RecordNotLoadedException;

import java.io.IOException;

public class StartApplication implements Runnable {
    private static final int APP_WIDTH = 1000;
    private static final int APP_HEIGHT = 800;

    public StartApplication() {
    }

    public static void main(String[] args) {
        SaxionApp.start(new StartApplication(), APP_WIDTH, APP_HEIGHT);
    }

    @Override
    public void run() {
        try {
            var model = new DataManager();
            var controller = new SystemFacade(model);
            var UI = new UI(APP_WIDTH, APP_HEIGHT, controller);
            UI.start();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } catch (RecordNotLoadedException e) {
            System.err.println(e.getMessage());
        }
    }
}
