package com.restaurant.kitchen;

public enum Dish {
  Fish(25),
  Steak(30),
  Soup(15),
  Juice(5),
  Water(3);

  private final int duration;

  Dish(int duration) {
    this.duration = duration;
  }

  public int getDuration() {
    return duration;
  }

  public static String allDishesToString() {
    StringBuilder sb = new StringBuilder();
    for (Dish d : Dish.values()) {
      sb.append(d).append(", ");
    }
    sb.delete(sb.length() - 2, sb.length());
    return sb.toString();
  }
}
