package com.mycompany.entities;


public class Coupon {
  private int id;
  private int userID;
  private String code;
  private int reduction;
  private String used;
  private String created;

  public Coupon() {}

  public Coupon(int id, int userID, String code, int reduction, String used, String created) {
    this.id = id;
    this.userID = userID;
    this.code = code;
    this.reduction = reduction;
    this.used = used;
    this.created = created;
  }

  public Coupon(int userID, String code, int reduction, String used, String created) {
    this.userID = userID;
    this.code = code;
    this.reduction = reduction;
    this.used = used;
    this.created = created;
  }

  public String getUsed() {
    return used;
  }

  public void setUsed(String used) {
    this.used = used;
  }

  public int getReduction() {
    return reduction;
  }

  public void setReduction(int reduction) {
    this.reduction = reduction;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getUserID() {
    return userID;
  }

  public void setUserID(int userID) {
    this.userID = userID;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getCreated() {
    return created;
  }

  public void setCreated(String created) {
    this.created = created;
  }

  @Override
  public String toString() {
    return "Coupon{"
        + "id="
        + id
        + ", userID="
        + userID
        + ", code='"
        + code
        + '\''
        + ", reduction="
        + reduction
        + ", used="
        + used
        + ", created="
        + created
        + '}';
  }
}
