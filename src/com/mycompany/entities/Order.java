package com.mycompany.entities;

import com.mycompany.enums.OrderStatus;

public class Order {
  OrderStatus status;
  private int id;
  private int offerId;
  private int user_id;
  private int plan;
  private float total;
  private String orderDate;

  public Order() {}

  public Order(int id) {
    this.id = id;
  }

  public Order(
      int id,
      int offerId,
      int user_id,
      int plan,
      float total,
      String orderDate,
      OrderStatus status) {
    this.id = id;
    this.offerId = offerId;
    this.user_id = user_id;
    this.plan = plan;
    this.total = total;
    this.orderDate = orderDate;
    this.status = status;
  }

  public Order(
      int offerId, int user_id, int plan, float total, String orderDate, OrderStatus status) {
    this.offerId = offerId;
    this.user_id = user_id;
    this.plan = plan;
    this.total = total;
    this.orderDate = orderDate;
    this.status = status;
  }

  public int getUser_id() {
    return user_id;
  }

  public void setUser_id(int user_id) {
    this.user_id = user_id;
  }

  public int getOfferId() {
    return offerId;
  }

  public void setOfferId(int offerId) {
    this.offerId = offerId;
  }

  public int getPlan() {
    return plan;
  }

  public void setPlan(int plan) {
    this.plan = plan;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getofferId() {
    return offerId;
  }

  public float getTotal() {
    return total;
  }

  public void setTotal(float total) {
    this.total = total;
  }

  public String getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(String orderDate) {
    this.orderDate = orderDate;
  }

  public void setofferId(int offerId) {
    this.offerId = offerId;
  }

    @Override
    public String toString() {
        return "Order{" + "status=" + status + ", id=" + id + ", offerId=" + offerId + ", user_id=" + user_id + ", plan=" + plan + ", total=" + total + ", orderDate=" + orderDate + '}';
    }


}
