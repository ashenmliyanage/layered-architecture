package com.example.layeredarchitecture.bo;

import com.example.layeredarchitecture.dao.Custom.DAOFactory;
import com.example.layeredarchitecture.dao.Custom.QurayDAO;
import com.example.layeredarchitecture.model.CustomerOrderDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class MainBOImpl implements MainBO {
    QurayDAO queryDAO = (QurayDAO) DAOFactory.getDaoFactory().getDAO( DAOFactory.DAOType.QUERY );

    @Override
    public ArrayList<CustomerOrderDetailsDTO> customerOrderDetails() throws SQLException, ClassNotFoundException {
        return (ArrayList<CustomerOrderDetailsDTO>) queryDAO.customerOrderDetails();
    }
}