package com.example.layeredarchitecture.bo;

import com.example.layeredarchitecture.model.CustomerOrderDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MainBO extends SuperBo {
    ArrayList<CustomerOrderDetailsDTO> customerOrderDetails() throws SQLException, ClassNotFoundException;
}