package com.example.layeredarchitecture.dao.impl;

import com.example.layeredarchitecture.controller.PlaceOrderFormController;
import com.example.layeredarchitecture.dao.ItemDAO;
import com.example.layeredarchitecture.dao.OrderDAO;
import com.example.layeredarchitecture.dao.OrderDetailsDAO;
import com.example.layeredarchitecture.dao.impl.ItemDAoimpl;
import com.example.layeredarchitecture.dao.impl.OrderDAOimpl;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;
import com.example.layeredarchitecture.utill.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailsDAOimpl implements OrderDetailsDAO {
    @Override
    public boolean save(List<OrderDetailDTO> orderDetails, String orderId) throws SQLException, ClassNotFoundException {

        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement stm = connection.prepareStatement("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)");
        PlaceOrderFormController placeOrderFormController = new PlaceOrderFormController();
        OrderDAO orderDAO = new OrderDAOimpl();
        ItemDAO itemDAO = new ItemDAoimpl();
        for (OrderDetailDTO detail : orderDetails) {
            stm.setString(1, orderId);
            stm.setString(2, detail.getItemCode());
            stm.setBigDecimal(3, detail.getUnitPrice());
            stm.setInt(4, detail.getQty());

            if (stm.executeUpdate() != 1) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

//                //Search & Update Item
            ItemDTO item = placeOrderFormController.findItem(detail.getItemCode());
            item.setQtyOnHand(item.getQtyOnHand() - detail.getQty());

            if (itemDAO.updateItem(item)) {
                Transaction.Roalback();
                return false;
            }
        }

        return false;
    }
}
