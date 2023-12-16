package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.*;
import java.util.ArrayList;

public interface ItemDAO {

    public ArrayList<ItemDTO> getall() throws SQLException, ClassNotFoundException;

    public boolean delete(String id) throws SQLException, ClassNotFoundException;
    public boolean save(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException;

    public boolean exitCode(String code) throws SQLException, ClassNotFoundException;

    public String genarate() throws SQLException, ClassNotFoundException;

}
