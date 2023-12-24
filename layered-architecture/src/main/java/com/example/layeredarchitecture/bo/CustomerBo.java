package com.example.layeredarchitecture.bo;

import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBo extends SuperBo{
    public ArrayList<CustomerDTO> getAll() throws SQLException, ClassNotFoundException;
    public boolean save(CustomerDTO dto) throws SQLException, ClassNotFoundException;
    public boolean update(CustomerDTO dto) throws SQLException, ClassNotFoundException;
    public boolean isExists(String id) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws SQLException, ClassNotFoundException;

    public String generateNewId() throws SQLException, ClassNotFoundException;

    public CustomerDTO search(String id) throws SQLException, ClassNotFoundException;
}
