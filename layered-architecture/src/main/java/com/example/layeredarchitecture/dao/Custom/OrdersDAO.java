package com.example.layeredarchitecture.dao.Custom;

import com.example.layeredarchitecture.model.OrderDTO;

import java.sql.*;

public interface OrdersDAO extends CrudDAO<OrderDTO>{
    String generateNextOrderId() throws SQLException, ClassNotFoundException;

    boolean isExists(String orderId) throws SQLException, ClassNotFoundException;

    boolean saveOrder(OrderDTO dto) throws SQLException, ClassNotFoundException;
}
