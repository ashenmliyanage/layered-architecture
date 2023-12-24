package com.example.layeredarchitecture.dao.Custom;

import com.example.layeredarchitecture.dao.Custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}
    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOType{
        CUSTOMER,ITEM,ORDER_DETAILS,ORDER,QUERY
    }

    public SuperDAO getDAO(DAOType daoType) {
        switch (daoType) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case ORDER:
                return new OrdersDAOImpl();
            case ORDER_DETAILS:
                return new OrderDetailDAOImpl();
            case QUERY:
                return new QuaryDAOimpl();
            default:
                return null;
        }
    }
}
