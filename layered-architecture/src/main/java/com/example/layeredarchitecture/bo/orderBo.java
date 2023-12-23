package com.example.layeredarchitecture.bo;

import com.example.layeredarchitecture.model.OrderDTO;

import java.sql.SQLException;

public interface orderBo {

    public String generateNextOrderId() throws SQLException, ClassNotFoundException;

    public boolean isExists(String orderId) throws SQLException, ClassNotFoundException;

    public boolean saveOrder(OrderDTO dto) throws SQLException, ClassNotFoundException;
}
