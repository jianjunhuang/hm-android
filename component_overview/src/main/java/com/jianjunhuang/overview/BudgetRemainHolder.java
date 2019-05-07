package com.jianjunhuang.overview;

class BudgetRemainHolder {

  private String type;
  private int times;

  public BudgetRemainHolder() {
  }

  public BudgetRemainHolder(String type, int times) {
    this.type = type;
    this.times = times;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getTimes() {
    return times;
  }

  public void setTimes(int times) {
    this.times = times;
  }
}
