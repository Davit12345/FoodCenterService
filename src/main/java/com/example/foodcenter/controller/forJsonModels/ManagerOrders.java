package com.example.foodcenter.controller.forJsonModels;

import lombok.Data;

@Data
public class ManagerOrders {

private String productName;
    private int productCount;

    private String userEmail;

    private String tablCount;
    private String sendDateTable;
    private int  tableNumber;

    private String city;
    private String strit;
    private String home;
    private String sendDateHome;


}
