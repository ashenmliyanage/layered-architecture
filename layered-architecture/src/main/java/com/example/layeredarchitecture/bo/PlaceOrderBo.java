package com.example.layeredarchitecture.bo;

import com.example.layeredarchitecture.model.OrderDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface PlaceOrderBo {
    public boolean saveOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails);
    public String generateNextOrderId() throws SQLException, ClassNotFoundException;

    public boolean isExists(String orderId) throws SQLException, ClassNotFoundException;

    public boolean saveOrder(OrderDTO dto) throws SQLException, ClassNotFoundException;
}
