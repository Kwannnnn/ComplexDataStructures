import nl.saxion.app.SaxionApp;
import resource.DataParser;

public class StartApplication implements Runnable {

    public static void main(String[] args) {
        SaxionApp.start(new StartApplication());
    }

    @Override
    public void run() {
        DataParser db = new DataParser();
        db.readData();

        db.sortClientsByName();
        System.out.println(db.getClientsList());

//        System.out.println("Parcel ID  ; (Client) Distance from DC ");
//        for (int i = 0; i < db.getParcels().size(); i++) {
//            System.out.println(db.getParcels().poll());
//        }
    }
}
