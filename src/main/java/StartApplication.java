import database.DataManager;
import nl.saxion.app.SaxionApp;

public class StartApplication implements Runnable {

    public static void main(String[] args) {
        SaxionApp.start(new StartApplication());
    }

    @Override
    public void run() {
        DataManager manager = DataManager.getInstance();
        manager.init();

//        db.sortClientsByName();
        System.out.println(manager.getClients());

//        System.out.println("Parcel ID  ; (Client) ");
//        var route = db.getRouteForADay("1-12-2021");
//        System.out.println(route);
//        for (int i = 0; i < db.getClientsList().size(); i++) {
//            System.out.println(route.poll());
//        }
    }
}
