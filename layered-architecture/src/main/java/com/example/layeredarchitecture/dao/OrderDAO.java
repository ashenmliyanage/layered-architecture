package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface OrderDAO {
    public CustomerDTO getCusData(String Id) throws SQLException;
    public ItemDTO getItemData(String code) throws SQLException;
    public boolean exitsItem(String code) throws SQLException;
    public boolean exitCustomer(String Id) throws SQLException;
    public String generateId() throws SQLException;
    public ArrayList<String> getItemData() throws SQLException;
    public ArrayList<String> getOrderCode() throws SQLException;
    public boolean getOrderId(String orderId) throws SQLException;
    public int save(String orderId, LocalDate orderDate, String customerId) throws SQLException;
    public void Roalback() throws SQLException, ClassNotFoundException;
    public boolean update(ItemDTO itemDTO) throws SQLException;

}
