package com.example.layeredarchitecture.bo;

import com.example.layeredarchitecture.dao.Custom.DAOFactory;
import com.example.layeredarchitecture.dao.Custom.ItemDAO;
import com.example.layeredarchitecture.dao.Custom.OrderDetailDAO;
import com.example.layeredarchitecture.dao.Custom.OrdersDAO;
import com.example.layeredarchitecture.dao.Custom.impl.ItemDAOImpl;
import com.example.layeredarchitecture.dao.Custom.impl.OrderDetailDAOImpl;
import com.example.layeredarchitecture.dao.Custom.impl.OrdersDAOImpl;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.model.OrderDTO;
import com.example.layeredarchitecture.model.OrderDetailDTO;
import com.example.layeredarchitecture.util.TransactionUtil;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class PlaceorderBoImpl implements PlaceOrderBo{

    OrdersDAO ordersDAO = (OrdersDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ORDER);
    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ORDER_DETAILS);
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ITEM);
    public boolean saveOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) {
        /*Transaction*/

        try {
            TransactionUtil.startTransaction();

            boolean isExists = ordersDAO.isExists(orderId);
            /*if order id already exist*/
            if (isExists) {

            }

            boolean isSaved = ordersDAO.saveOrder(new OrderDTO(orderId, orderDate, customerId));

            if (!isSaved) {
                TransactionUtil.rollBack();
                return false;
            }

            for (OrderDetailDTO detail : orderDetails) {

                if (!orderDetailDAO.saveOrderDetail(orderId, detail)) {
                    TransactionUtil.rollBack();
                    return false;
                }

//                //Search & Update Item
                ItemDTO item = findItem(detail.getItemCode());
                item.setQtyOnHand(item.getQtyOnHand() - detail.getQty());

                if (!itemDAO.update(item)) {
                    TransactionUtil.rollBack();
                    return false;
                }
            }

            TransactionUtil.endTransaction();
            return true;

        } catch (SQLException | ClassNotFoundException throwable) {
            throwable.printStackTrace();
        }
        return false;
    }

    public ItemDTO findItem(String code) {
        try {
            return itemDAO.search(code);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find the Item " + code, e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String generateNextOrderId() throws SQLException, ClassNotFoundException {
        return ordersDAO.generateNextOrderId();
    }
    @Override
    public boolean isExists(String orderId) throws SQLException, ClassNotFoundException {
        return ordersDAO.isExists(orderId);
    }

    @Override
    public boolean saveOrder(OrderDTO dto) throws SQLException, ClassNotFoundException {
        return ordersDAO.saveOrder(dto);
    }
}
