package org.udem.unishop.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import org.udem.unishop.models.Buyer;

import org.udem.unishop.models.Order;
import org.udem.unishop.models.User;
import org.udem.unishop.services.OrderService;

public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

  public  void confirmDelivery(Order order) {
    orderService.confirmDelivery(order);
  }

  public void confirmReturn(Order order) {
    orderService.confirmIssueReturn(order);
  }

  public List<UUID> createOrder(Buyer currentUser, boolean usePoints) {
        return orderService.createOrder(currentUser, usePoints);
    }

  public void modifyOrderStatus(Order order, User currentUser) {
    orderService.modifyOrderStatus(order, currentUser);
  }

  public void reportProblem(Order order, List<String> inputs) {
    orderService.reportProblem(order, inputs);
  }

  public void offerSolution(Order order, String s) {
    orderService.offerSolution(order, s);
  }


  public void acceptSolution(Order order) {
    orderService.acceptSolution(order);
  }

  public void cancelIssueReturn(Order order) {
    orderService.cancelIssueReturn(order);
  }

  public void confirmIssueReturn(Order order) {
    orderService.confirmIssueReturn(order);
  }


  public void createReturnOrder(Order order) {
    orderService.createReturnOrder(order);
  }

  public void returnOrder(Order order, HashMap<UUID, List<UUID>> returnItems) {

    orderService.returnOrder(order, returnItems);
  }

  public void exchangeOrder(Order order, HashMap<UUID, List<UUID>> exchangeItems, HashMap<UUID, List<UUID>> returnItems) {
    orderService.exchangeOrder(order, exchangeItems, returnItems);
  }

  public void confirmIssueDelivery(Order order) {
    orderService.confirmIssueDelivery(order);
  }
}