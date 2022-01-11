import nl.saxion.app.SaxionApp;
import nl.saxion.cds.DataManager;
import nl.saxion.cds.SystemFacade;

import java.io.IOException;

public class StartApplication implements Runnable {
    private DataManager data;
    private SystemFacade facade;

    public StartApplication() {
        try {
            this.data = new DataManager();
            this.facade = new SystemFacade(this.data);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        SaxionApp.start(new StartApplication());
    }

    @Override
    public void run() {
//        int choice;
//        showMenu();
//        choice = inputOption(2);
//        System.out.println(this.facade.getParcelStatus("13580"));

        System.out.println(this.facade.getTop10Recipients());

//        db.sortClientsByName();
//        System.out.println(manager.getClients());

//        System.out.println("Parcel ID  ; (Client) ");
//        var route = db.getRouteForADay("1-12-2021");
//        System.out.println(route);
//        for (int i = 0; i < db.getClientsList().size(); i++) {
//            System.out.println(route.poll());
//        }
    }

    private void showMenu() {
        SaxionApp.printLine("1: Find the status of a parcel");
        SaxionApp.printLine("2: Find the top 10 recipients of a month");
    }

    private int inputOption(int max) {
        int choice;
        do {
            SaxionApp.printLine("Please select an option: ");
            choice = SaxionApp.readInt();
        } while (choice <= 0 || choice > max);

        return choice;
    }
}
