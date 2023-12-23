package com.example.layeredarchitecture.bo;

import com.example.layeredarchitecture.dao.Custom.OrdersDAO;
import com.example.layeredarchitecture.dao.Custom.impl.OrdersDAOImpl;
import com.example.layeredarchitecture.model.OrderDTO;
import com.example.layeredarchitecture.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class orderBoImpl implements orderBo{

    OrdersDAO ordersDAO = new OrdersDAOImpl();
    @Override
    public String generateNextOrderId() throws SQLException, ClassNotFoundException {
        return ordersDAO.generateNextOrderId();
    }
    @Override
    public boolean isExists(String orderId) throws SQLException, ClassNotFoundException {
        return ordersDAO.isExists(orderId);
    }

    @Override
    public boolean saveOrder(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return ordersDAO.saveOrder(dto);
    }
}
