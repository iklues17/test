package net.chrisrichardson.getataxi.domain;

public class Location {

  private double lat;
  private double lon;

  public Location() {
  }

  public Location(double lat, double lon) {
    this.lat = lat;
    this.lon = lon;
  }

  public Location(double[] location) {
    this.lon = location[0];
    this.lat = location[1];
  }

  public double getLat() {
    return lat;
  }

  public double getLon() {
    return lon;
  }

  public double[] asDoubleArray() {
    return new double[]{lon, lat};
  }
}
