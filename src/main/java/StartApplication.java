import nl.saxion.app.SaxionApp;
import nl.saxion.cds.DataManager;
import nl.saxion.cds.SystemFacade;
import nl.saxion.cds.graph.UndirectedWeightedGraph;
import nl.saxion.cds.graph.Vertex;
import nl.saxion.cds.region.Coordinate;

import java.awt.*;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class StartApplication implements Runnable {
    public static final double SCALE = 0.5;
    public static final int VERTICAL_MARGIN = 37;
    public static final int HORIZONTAL_MARGIN = 10;
    private static final int MENU_OPTIONS_COUNT = 5;
    private static final int APP_WIDTH = 800;
    private static final int APP_HEIGHT = 600;
    private SystemFacade facade;
    private boolean isRunning = true;

    public StartApplication() {
        try {
            this.facade = new SystemFacade(new DataManager());
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        SaxionApp.start(new StartApplication(), APP_WIDTH, APP_HEIGHT);
    }

    @Override
    public void run() {
//        while (this.isRunning) {
//            showMenu();
//
//            int choice = inputOption(MENU_OPTIONS_COUNT);
//            handleInputOption(choice);
//            SaxionApp.printLine();
//        }

        UndirectedWeightedGraph graph = new UndirectedWeightedGraph();
        graph.addVertex(new Vertex("DC", new Coordinate(0, 0)));
        graph.addVertex(new Vertex("1", new Coordinate(10, 10)));
        graph.addVertex(new Vertex("2", new Coordinate(50, 50)));
        graph.addVertex(new Vertex("3", new Coordinate(20, 20)));
        graph.addVertex(new Vertex("4", new Coordinate(100, 100)));
        graph.addVertex(new Vertex("5", new Coordinate(5, 5)));
        var result = graph.getEdges();
        for (var edge : result) {
            System.out.println(edge.getSource().getLabel() + "--" + edge.getWeight() + "-->" + edge.getDestination().getLabel());
        }

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
        SaxionApp.printLine("3: Pack the most packages onto each van. Show each van.");
        SaxionApp.printLine("4: Get optimal delivery routes per region ");
    }

    //region user input helper methods
    private int inputOption(int max) {
        int choice;
        do {
            SaxionApp.print("Please select an option: ");
            choice = SaxionApp.readInt();
        } while (choice <= 0 || choice > max);

        return choice;
    }

    private String dateInput() {
        SaxionApp.print("Please enter a valid date in the this format (d-M-yyyy): ");
        String date = SaxionApp.readString();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d-M-yyyy");
        try {
            // check if the date is valid
            dtf.parse(date);
        } catch (DateTimeParseException e) {
            // invalid date. repeating the process
            SaxionApp.printLine("Invalid date", Color.PINK);
            dateInput();
        }

        return date;
    }
    //endregion


    private void handleInputOption(int choice) {
        switch (choice) {
            case 1 -> handleGetParcelStatus();
            case 2 -> handleGetTop10Recipients();
            case 3 -> handlePackMostPackagesPerVan();
            case 4 -> handleGetOptimalRoutesPerRegion();
            case 5 -> handleGetOptimalRouteBetween2Parcels();

            case 0 -> this.isRunning = false;
        }
    }

    private void handleGetOptimalRouteBetween2Parcels() {
        String date = dateInput();
        SaxionApp.clear();

        Vertex prevV = null;

        var ass = this.facade.getOptimalRouteBetween2Parcels(date);
        for (Vertex v : ass) {
            SaxionApp.drawPoint((int) v.getAddress().getX(), (int) v.getAddress().getY());
            if (prevV != null) {
                SaxionApp.drawLine((int) v.getAddress().getX(), (int) v.getAddress().getY(), (int) prevV.getAddress().getX(), (int) prevV.getAddress().getY());
            }
            prevV = v;
        }

        SaxionApp.pause();
    }

//    region option handler methods
    private void handleGetOptimalRoutesPerRegion() {
        String date = dateInput();
        SaxionApp.clear();
        var connections = this.facade.getOptimalRoutePerRegion(date).getEdges();
//        for (var edge : connections) {
            for (var bs : connections) {
                SaxionApp.printLine(bs.getSource().getLabel() + " ---" + bs.getWeight() + "-->" + bs.getDestination().getLabel());
            }
//        }
    }

    private void handlePackMostPackagesPerVan() {
        String date = dateInput();

        var vans = this.facade.getAllPackages(date);
        int vanCount = 0;

        SaxionApp.clear();
        // drawing the vans
        SaxionApp.setBorderColor(Color.white);

        for (var vanParcels : vans) {
            SaxionApp.printLine("Van " + ++vanCount);
            SaxionApp.setFill(Color.BLACK);
            SaxionApp.drawRectangle(HORIZONTAL_MARGIN, VERTICAL_MARGIN, 580, 300);
            // draw the parcels
            for (var parcel : vanParcels) {
                if (parcel.getData() != null) {
                    var color = SaxionApp.getRandomColor();
                    SaxionApp.setFill(color);
                    SaxionApp.drawRectangle(parcel.getX() + HORIZONTAL_MARGIN, parcel.getY() + VERTICAL_MARGIN,
                            parcel.getData().getWidth(), parcel.getData().getLength());
                }
            }
            SaxionApp.pause();
            SaxionApp.clear();
        }
    }

    private void handleGetTop10Recipients() {
        List<String> top10Recipients = this.facade.getTop10Recipients();
        for (int i = 0; i < top10Recipients.size(); i++) {
            SaxionApp.printLine(i + 1 + ". " + top10Recipients.get(i));
        }
    }

    private void handleGetParcelStatus() {
        String parcelID = SaxionApp.readString();
        SaxionApp.printLine(this.facade.getParcelStatus(parcelID));
    }
    //endregion
}
