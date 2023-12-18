package com.example.layeredarchitecture.model;

public class CustomerOrderDetailsDTO {
    private String Id;
    private String name;
    private String address;
    private String orderId;
    private String data;

    public CustomerOrderDetailsDTO(String id, String name, String address, String orderId, String data) {
        Id = id;
        this.name = name;
        this.address = address;
        this.orderId = orderId;
        this.data = data;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
