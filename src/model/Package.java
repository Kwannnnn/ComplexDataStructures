package model;

public record Package(Long id, int length, int breadth, int height, double weight, String entryDate,
                      String clientID) {
}
