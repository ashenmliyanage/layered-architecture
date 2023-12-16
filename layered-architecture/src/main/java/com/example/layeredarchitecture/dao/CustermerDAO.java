package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public interface CustermerDAO {
     public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException;

        public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

        public boolean update(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
        public boolean exitCustomer(String id) throws SQLException, ClassNotFoundException;

        public boolean delete(String id) throws SQLException, ClassNotFoundException;

        public String genarateId() throws SQLException, ClassNotFoundException;

}
