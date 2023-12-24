package com.example.layeredarchitecture.bo;

import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBo extends SuperBo{

    public ArrayList<ItemDTO> getAll() throws SQLException, ClassNotFoundException;

    public boolean delete(String id) throws SQLException, ClassNotFoundException;

    public boolean save(ItemDTO dto) throws SQLException, ClassNotFoundException;

    public boolean update(ItemDTO dto) throws SQLException, ClassNotFoundException;

    public boolean isExists(String id) throws SQLException, ClassNotFoundException;

    public String generateNewId() throws SQLException, ClassNotFoundException;

    public ItemDTO search(String newItemCode) throws SQLException, ClassNotFoundException;
}
