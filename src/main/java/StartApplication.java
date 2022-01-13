import nl.saxion.app.SaxionApp;
import nl.saxion.cds.DataManager;
import nl.saxion.cds.SystemFacade;
import nl.saxion.cds.region.RegionMap;

import java.io.IOException;

public class StartApplication implements Runnable {
    public static final double SCALE = 0.5;
    public static final int MARGIN = 37;
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
        SaxionApp.start(new StartApplication(), 600, 600);
    }

    @Override
    public void run() {
//        var vans = this.facade.getAllPackages("1-12-2021");
//        int vanCount = 0;
//        for (var vanParcels : vans) {
//            SaxionApp.printLine("Van " + ++vanCount );
//            SaxionApp.setBorderColor(Color.white);
//            SaxionApp.setFill(Color.BLACK);
//            SaxionApp.drawRectangle(10, MARGIN, 580, 300);
//            for (var parcel : vanParcels) {
//                if (parcel.getData() != null) {
//                    var color = SaxionApp.getRandomColor();
//                    SaxionApp.setBorderSize(0);
//                    SaxionApp.setFill(color);
//                    SaxionApp.drawRectangle(parcel.getX() + 10, parcel.getY() + MARGIN, parcel.getData().getWidth(), parcel.getData().getLength());
//                }
//            }
//            SaxionApp.pause();
//            SaxionApp.clear();
//        }




//        int choice;
//        showMenu();
//        choice = inputOption(2);
//        System.out.println(this.facade.getParcelStatus("13580"));

//        System.out.println(this.facade.getTop10Recipients());

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
