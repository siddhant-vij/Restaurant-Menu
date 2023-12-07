package com.restaurant.ad;

public class Advertisement {
  private Object content;
  private String name;
  private long amountPaid;
  private int impressionsRemaining;
  private int duration;
  private long amountPerImpression;

  public Advertisement(Object content,
      String name,
      long amountPaid,
      int impressionsRemaining,
      int duration) {
    this.content = content;
    this.name = name;
    this.amountPaid = amountPaid;
    this.impressionsRemaining = impressionsRemaining;
    this.duration = duration;
    if (impressionsRemaining > 0) {
      this.amountPerImpression = this.amountPaid / this.impressionsRemaining;
    }
  }

  public Object getContent() {
    return content;
  }

  public long getAmountPaid() {
    return amountPaid;
  }

  public String getName() {
    return name;
  }

  public int getDuration() {
    return duration;
  }

  public long getAmountPerImpression() {
    return amountPerImpression;
  }

  public void revalidate() {
    if (impressionsRemaining == 0) {
      throw new UnsupportedOperationException();
    }
    impressionsRemaining--;
  }

  public boolean isActive() {
    return impressionsRemaining > 0;
  }

  public int getHits() {
    return impressionsRemaining;
  }
}
