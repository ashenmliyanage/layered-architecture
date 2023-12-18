package com.example.layeredarchitecture.dao.Custom.impl;

import com.example.layeredarchitecture.dao.Custom.QurayDAO;
import com.example.layeredarchitecture.model.CustomerOrderDetailsDTO;
import com.example.layeredarchitecture.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuaryDAOimpl implements QurayDAO {
    @Override
    public List<CustomerOrderDetailsDTO> customerOrderDetails() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT customer.*, orders.* FROM customer RIGHT JOIN orders ON customer.id = orders.customerID;");
        List<CustomerOrderDetailsDTO> dtoList = new ArrayList<>();
        while (resultSet.next()){
            dtoList.add(new CustomerOrderDetailsDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));
        }
        return dtoList;
    }
}
