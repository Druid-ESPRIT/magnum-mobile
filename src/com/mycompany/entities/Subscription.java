package com.mycompany.entities;

import com.mycompany.enums.SubscriptionStatus;

public class Subscription {

  private int id;
  private int order_id;
  private int user_id;
  private String start_date;
  private String expire_date;
  SubscriptionStatus status;

  public Subscription() {}

  public Subscription(
      int id,
      int order_id,
      int user_id,
      String start_date,
      String expire_date,
      SubscriptionStatus status) {
    this.id = id;
    this.order_id = order_id;
    this.user_id = user_id;
    this.start_date = start_date;
    this.expire_date = expire_date;
    this.status = status;
  }

  public Subscription(
      int order_id,
      int user_id,
      String start_date,
      String expire_date,
      SubscriptionStatus status) {
    this.order_id = order_id;
    this.user_id = user_id;
    this.start_date = start_date;
    this.expire_date = expire_date;
    this.status = status;
  }

  public int getUser_id() {
    return user_id;
  }

  public void setUser_id(int user_id) {
    this.user_id = user_id;
  }

  public String getStart_date() {
    return start_date;
  }

  public void setStart_date(String start_date) {
    this.start_date = start_date;
  }

  public String getExpire_date() {
    return expire_date;
  }

  public void setExpire_date(String expire_date) {
    this.expire_date = expire_date;
  }

  public int getOrder_id() {
    return order_id;
  }

  public void setOrder_id(int order_id) {
    this.order_id = order_id;
  }

  public SubscriptionStatus getStatus() {
    return status;
  }

  public void setStatus(SubscriptionStatus status) {
    this.status = status;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getorder_id() {
    return order_id;
  }

  public void setorder_id(int order_id) {
    this.order_id = order_id;
  }

  @Override
  public String toString() {
    return "Subscription{"
        + "id="
        + id
        + ", order_id="
        + order_id
        + ", user_id="
        + user_id
        + ", start_date="
        + start_date
        + ", expire_date="
        + expire_date
        + ", status="
        + status
        + '}';
  }
}
