package com.example.layeredarchitecture.dao.impl;

import com.example.layeredarchitecture.dao.OrderDAO;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class OrderDAOimpl implements OrderDAO {

    Connection connection = DBConnection.getDbConnection().getConnection();

    public OrderDAOimpl() throws SQLException, ClassNotFoundException {
    }

    @Override
    public CustomerDTO getCusData(String Id) throws SQLException {

        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Customer WHERE id=?");
        pstm.setString(1, Id + "");
        ResultSet rst = pstm.executeQuery();
        rst.next();
        CustomerDTO customerDTO = null;
        return customerDTO = new CustomerDTO(Id + "", rst.getString("name"), rst.getString("address"));

    }

    @Override
    public ItemDTO getItemData(String code) throws SQLException {

        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE code=?");
        pstm.setString(1, code + "");
        ResultSet rst = pstm.executeQuery();
        rst.next();
        return new ItemDTO(code + "", rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));

    }

    @Override
    public boolean exitsItem(String code) throws SQLException {

        PreparedStatement pstm = connection.prepareStatement("SELECT code FROM Item WHERE code=?");
        pstm.setString(1, code);
        return pstm.executeQuery().next();
    }

    @Override
    public boolean exitCustomer(String Id) throws SQLException {

        PreparedStatement pstm = connection.prepareStatement("SELECT id FROM Customer WHERE id=?");
        pstm.setString(1, Id);
        return pstm.executeQuery().next();

    }

    @Override
    public String generateId() throws SQLException {

        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");
        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001";

    }

    @Override
    public ArrayList<String> getItemData() throws SQLException {

        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Customer");
        ArrayList<String> dto = new ArrayList<>();
        while (rst.next()) {
            dto.add(rst.getString("id"));
        }
        return dto;

    }

    @Override
    public ArrayList<String> getOrderCode() throws SQLException {

        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Item");
        ArrayList<String> dto = new ArrayList<>();
        while (rst.next()) {
            dto.add(rst.getString("code"));
        }
        return dto;

    }

    @Override
    public boolean getOrderId(String orderId) throws SQLException {

        PreparedStatement stm = connection.prepareStatement("SELECT oid FROM `Orders` WHERE oid=?");
        stm.setString(1, orderId);
        return stm.executeQuery().next();
    }

    @Override
    public int save(String orderId, LocalDate orderDate, String customerId) throws SQLException {
        connection.setAutoCommit(false);
        PreparedStatement stm = connection.prepareStatement("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)");
        stm.setString(1, orderId);
        stm.setDate(2, Date.valueOf(orderDate));
        stm.setString(3, customerId);
        return stm.executeUpdate();
    }

    @Override
    public void Roalback() throws SQLException, ClassNotFoundException {

        connection = DBConnection.getDbConnection().getConnection();
        connection.rollback();
        connection.setAutoCommit(true);

    }

    @Override
    public boolean update(ItemDTO itemDTO) throws SQLException {

        PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");
        pstm.setString(1, itemDTO.getDescription());
        pstm.setBigDecimal(2, itemDTO.getUnitPrice());
        pstm.setInt(3, itemDTO.getQtyOnHand());
        pstm.setString(4, itemDTO.getCode());
        return pstm.executeUpdate() > 0;

    }
}
