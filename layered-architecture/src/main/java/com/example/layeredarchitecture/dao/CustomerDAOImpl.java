package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImpl {
    public ArrayList<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Customer");

        ArrayList<CustomerDTO> dto = new ArrayList<>();
        while (rst.next()){
            CustomerDTO customerDTO = new CustomerDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            );

            dto.add(customerDTO);
        }
        return dto;
    }

    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        String sql = "INSERT INTO customer VALUES(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,customerDTO.getId());
        preparedStatement.setString(2,customerDTO.getName());
        preparedStatement.setString(3,customerDTO.getAddress());
        return preparedStatement.executeUpdate() > 0;
    }

    public boolean update(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE Customer SET name=?, address=? WHERE id=?");
        pstm.setString(1, customerDTO.getName());
        pstm.setString(2, customerDTO.getAddress());
        pstm.setString(3, customerDTO.getId());

        return pstm.executeUpdate() > 0;
    }
}
