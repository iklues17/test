package net.chrisrichardson.getataxi.testutil.domain;

import net.chrisrichardson.getataxi.domain.Location;

public class LocationMother {
  public static Location makeLocation() {
    return new Location(1.2, 5.6);
  }
}
