package ru.yandex.practicum.delivery;

public class StandartParcel extends Parcel {
    private static final int DELIVERY_COST = 2;

    public StandartParcel(String description, int weight, String deliveryAddress, int sendDay) {
        super(description, weight, deliveryAddress, sendDay);
    }

    @Override
    public void packageItem() {
        System.out.println("Посылка " + description + " доставлена по адресу " + deliveryAddress);
    }

    @Override
    public int calculateDeliveryCost() {
        return getWeight() * DELIVERY_COST;
    }
}
