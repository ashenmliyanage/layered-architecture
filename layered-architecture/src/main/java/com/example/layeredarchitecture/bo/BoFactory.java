package com.example.layeredarchitecture.bo;

public class BOFactory{
    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOType {
        CUSTOMER,ITEM,PLACE_ORDER,MAIN
    }

    public SuperBo getBO(BOType boType) {
        switch (boType) {
            case CUSTOMER:
                return new CustomerBOimpl();
            case ITEM:
                return new ItemBoImpl();
            case PLACE_ORDER:
                return new PlaceorderBoImpl();
            case MAIN:
                return new MainBOImpl();
            default:
                return null;
        }
    }
}