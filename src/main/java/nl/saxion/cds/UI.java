package nl.saxion.cds;

import nl.saxion.app.SaxionApp;
import nl.saxion.cds.exception.ParcelNotFoundException;
import nl.saxion.cds.region.Coordinate;
import nl.saxion.cds.region.Region;

import java.awt.*;
import java.time.Duration;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class UI {
    public static final double SCALE = 0.5;
    public static final int VERTICAL_MARGIN = 37;
    public static final int HORIZONTAL_MARGIN = 10;
    private static final int MENU_OPTIONS_COUNT = 6;

    private final int height;
    private final int width;
    private final SystemFacade controller;
    private boolean isRunning = true;

    public UI(int appWidth, int appHeight, SystemFacade controller) {
        this.width = appWidth;
        this.height = appHeight;
        this.controller = controller;
    }

    public void start() {
        while (this.isRunning) {
            showMenu();

            int choice = inputOption(MENU_OPTIONS_COUNT);
            SaxionApp.printLine();
            handleInputOption(choice);
            SaxionApp.printLine();
        }
    }

    private void showMenu() {
        SaxionApp.printLine("1: Find the status of a parcel using Hashmap");
        SaxionApp.printLine("2: Find the status of a parcel using sequential search");
        SaxionApp.printLine("3: Find the status of a parcel using binary search");
        SaxionApp.printLine("4: Find the top 10 recipients");
        SaxionApp.printLine("5: Display delivery regions");
        SaxionApp.printLine("6: Show van info (packing and route)");
    }

    // region user input helper methods
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
            case 1 -> handleGetParcelStatus(1);
            case 2 -> handleGetParcelStatus(2);
            case 3 -> handleGetParcelStatus(3);
            case 4 -> handleGetTop10Recipients();
            case 5 -> drawRegionsMap();
            case 6 -> handleShowVanInfo();
//            case 5 -> handleGetOptimalRouteBetween2Parcels();

            case 0 -> this.isRunning = false;
        }
    }

    private void handleShowVanInfo() {
        SaxionApp.print("Please select a date: ");
        String date = dateInput();
        SaxionApp.print("Please select a region: ");
        int region = SaxionApp.readInt();

        var packedVans = this.controller.getLoadedVansForADayForARegion(date, region);

        if (packedVans.size() == 0) {
            System.out.println("This region does not have any package to deliver on " + date);
            return;
        }

        for (int i = 0; i < packedVans.size(); i++) {
            SaxionApp.printLine((i + 1) + ": Van " + (i + 1));
        }

        SaxionApp.print("Please select a van: ");
        int vanIndex = SaxionApp.readInt();

        var vanParcels = packedVans.get(vanIndex - 1).getPlacement().toList();

        SaxionApp.clear();
        SaxionApp.printLine("Van: " + vanIndex + " - Region: " + region + " - Date: " + date);
        SaxionApp.setFill(Color.BLACK);
        SaxionApp.drawRectangle(HORIZONTAL_MARGIN, VERTICAL_MARGIN, 200, 80);
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

    private void drawRegionsMap() {
        SaxionApp.clear();
        var regions = this.controller.getAllRegions();
        Coordinate margin = getMarginCoordinate(this.controller.getRegionMapWidth(), this.controller.getRegionMapLength());

        // draw 4 corners of the region map
        drawRegionMapCorners(margin);

        int regionCounter = 1;
        SaxionApp.setFill(Color.BLACK);
        for (Region r : regions) {
            int topLeftX = (int) (r.getTopLeft().getX() + margin.getX());
            int topLeftY = (int) (r.getTopLeft().getY() + margin.getY());

            int width = (int) r.getWidth();
            int length = (int) r.getLength();
            SaxionApp.drawRectangle(topLeftX, topLeftY, width, length);

            // draw region number
            int numberPositionX = topLeftX + width/2 - 10;
            int numberPositionY = topLeftY + length/2 - 10;
            SaxionApp.drawText(String.valueOf(regionCounter++), numberPositionX, numberPositionY, 20);
        }
        SaxionApp.pause();
        SaxionApp.clear();
    }

    private void drawRegionMapCorners(Coordinate margin) {
        // top left corner
        SaxionApp.drawText((int) (this.controller.getRegionMapTopLeft().getX()) + ","
                        + (int) (this.controller.getRegionMapTopLeft().getY()),
                (int) (margin.getX() - 40 + this.controller.getRegionMapTopLeft().getX()),
                (int) (margin.getY() - 20 + this.controller.getRegionMapTopLeft().getY()),
                20);

        // top right corner
        SaxionApp.drawText((int) (this.controller.getRegionMapTopRight().getX()) + ","
                        + (int) (this.controller.getRegionMapTopRight().getY()),
                (int) (margin.getX() - 40 + this.controller.getRegionMapTopRight().getX()),
                (int) (margin.getY() - 20 + this.controller.getRegionMapTopRight().getY()),
                20);

        // bottom left corner
        SaxionApp.drawText((int) (this.controller.getRegionMapBottomLeft().getX()) + ","
                        + (int) (this.controller.getRegionMapBottomLeft().getY()),
                (int) (margin.getX() - 40 + this.controller.getRegionMapBottomLeft().getX()),
                (int) (margin.getY() + 10 + this.controller.getRegionMapBottomLeft().getY()),
                20);

        // top left corner
        SaxionApp.drawText((int) (this.controller.getRegionMapBottomRight().getX()) + ","
                        + (int) (this.controller.getRegionMapBottomRight().getY()),
                (int) (margin.getX() - 40 + this.controller.getRegionMapBottomRight().getX()),
                (int) (margin.getY() + 10 + this.controller.getRegionMapBottomRight().getY()),
                20);
    }
//
//    private void handleGetOptimalRouteBetween2Parcels() {
//        String date = dateInput();
//        SaxionApp.clear();
//
//        Vertex prevV = null;
//
//        var ass = this.facade.getOptimalRouteBetween2Parcels(date);
//        for (Vertex v : ass) {
//            SaxionApp.drawPoint((int) v.getAddress().getX(), (int) v.getAddress().getY());
//            if (prevV != null) {
//                SaxionApp.drawLine((int) v.getAddress().getX(), (int) v.getAddress().getY(), (int) prevV.getAddress().getX(), (int) prevV.getAddress().getY());
//            }
//            prevV = v;
//        }
//
//        SaxionApp.pause();
//    }
//
////    region option handler methods
//    private void handleGetOptimalRoutesPerRegion() {
//        String date = dateInput();
//        SaxionApp.clear();
//        var connections = this.facade.getOptimalRoutePerRegion(date).getEdges();
////        for (var edge : connections) {
//            for (var bs : connections) {
//                SaxionApp.printLine(bs.getSource().getLabel() + " ---" + bs.getWeight() + "-->" + bs.getDestination().getLabel());
//            }
////        }
//    }
//
//    private void handlePackMostPackagesPerVan() {
//        String date = dateInput();
//
//        var vans = this.facade.getAllPackages(date);
//        int vanCount = 0;
//
//        SaxionApp.clear();
//        // drawing the vans
//        SaxionApp.setBorderColor(Color.white);
//
//        for (var vanParcels : vans) {
//            SaxionApp.printLine("Van " + ++vanCount);
//            SaxionApp.setFill(Color.BLACK);
//            SaxionApp.drawRectangle(HORIZONTAL_MARGIN, VERTICAL_MARGIN, 580, 300);
//            // draw the parcels
//            for (var parcel : vanParcels) {
//                if (parcel.getData() != null) {
//                    var color = SaxionApp.getRandomColor();
//                    SaxionApp.setFill(color);
//                    SaxionApp.drawRectangle(parcel.getX() + HORIZONTAL_MARGIN, parcel.getY() + VERTICAL_MARGIN,
//                            parcel.getData().getWidth(), parcel.getData().getLength());
//                }
//            }
//            SaxionApp.pause();
//            SaxionApp.clear();
//        }
//    }

    private void handleGetTop10Recipients() {
        List<String> top10Recipients = this.controller.getTop10Recipients();
        printResponse("Top 10 Recipients (based on amount of orders):");
        for (int i = 0; i < top10Recipients.size(); i++) {
            printResponse(i + 1 + ". " + top10Recipients.get(i));
        }
    }

    private void handleGetParcelStatus(int choice) {
        try {
            SaxionApp.print("Please enter the parcel ID: ");
            String parcelID = SaxionApp.readString();

            Instant start = Instant.now();
            switch (choice) {
                case 1 -> printResponse("Status: " + this.controller.getParcelStatusUsingHashmap(parcelID));
                case 2 -> printResponse("Status: " + this.controller.getParcelStatusSequentially(parcelID));
                case 3 -> printResponse("Status: " + this.controller.getParcelStatusBinary(parcelID));
            }
            Instant end = Instant.now();
            Duration difference = Duration.between(start, end);
            SaxionApp.printLine("Total time taken for searching (in milliseconds): " + difference.toMillis());

        } catch (ParcelNotFoundException e) {
            SaxionApp.printLine(e.getMessage(), Color.RED);
        }
    }
    //endregion

    private void printResponse(String text) {
        SaxionApp.printLine(text, Color.PINK);
    }

    private Coordinate getMarginCoordinate(int itemWidth, int itemHeight) {
        int x = (width - itemWidth) / 2;
        int y = (height - itemHeight) / 2;

        return new Coordinate(x, y);
    }
}
