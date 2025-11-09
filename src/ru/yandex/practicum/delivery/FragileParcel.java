package ru.yandex.practicum.delivery;

public class FragileParcel extends Parcel implements Trackable {
    private static final int DELIVERY_COST = 4;

    public FragileParcel(String description, int weight, String deliveryAddress, int sendDay){
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    public void packageItem() {
        System.out.println("Посылка " + description + "обёрнута в защитную плёнку.");
    }

    @Override
    public int calculateDeliveryCost() {
        return getWeight() * DELIVERY_COST;
    }

    @Override
    public void reportStatus(String newLocation) {
        System.out.println("Хрупка посылка " + getDescription() + " изменила местоположение на " + newLocation);
    }
}
