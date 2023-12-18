package com.example.layeredarchitecture.dao.impl;

import com.example.layeredarchitecture.dao.ItemDAO;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.utill.SqlUtil;

import java.sql.*;
import java.util.ArrayList;

public class ItemDAoimpl implements ItemDAO {
    public ArrayList<ItemDTO> getall() throws SQLException, ClassNotFoundException {

        ResultSet rst = SqlUtil.execute("SELECT * FROM Item");

        ArrayList<ItemDTO> dto = new ArrayList<>();
        while (rst.next()){
            ItemDTO itemDTO = new ItemDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getBigDecimal(4),
                    rst.getInt(3)
            );
            dto.add(itemDTO);
        }
        System.out.println(dto);
        return dto;
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("DELETE FROM Item WHERE code=?",id);
    }
    public boolean save(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)",itemDTO.getCode(),itemDTO.getDescription(),itemDTO.getUnitPrice(),itemDTO.getQtyOnHand());
    }

    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",dto.getDescription(),dto.getUnitPrice(),dto.getQtyOnHand(),dto.getCode());
    }

    public boolean exitCode(String code) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("SELECT code FROM Item WHERE code=?",code);
    }

    public String genarate() throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.execute("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("code");
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }
    }

    @Override
    public ItemDTO search(String code) throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.execute("SELECT * FROM Item WHERE code=?",code);
        return (rst.next()) ? new ItemDTO(code, rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand")) : null;
    }
}
