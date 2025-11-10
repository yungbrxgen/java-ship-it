package ru.yandex.practicum;

import ru.yandex.practicum.delivery.*;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeliveryCostTest {

    @Test
    void calculateStandartParcelCost() {
        StandartParcel parcel = new StandartParcel("Книга", 5, "Москва", 10);
        assertEquals(10, parcel.calculateDeliveryCost());
    }

    @Test
    void calculateFragileParcelCost() {
        FragileParcel parcel = new FragileParcel("Ваза", 2, "Москва", 15);
        assertEquals(8, parcel.calculateDeliveryCost());
    }

    @Test
    void calculatePerishableParcelCost() {
        PerishableParcel parcel = new PerishableParcel("Торт", 3, "Таганрог", 20, 3);
        assertEquals(9, parcel.calculateDeliveryCost());
    }

    @Test
    void isExpired() {
        PerishableParcel parcel = new PerishableParcel("Торт", 1, "Самара", 5, 2);
        assertTrue(parcel.isExpired(8));
    }

    @Test
    void isNotExpired() {
        PerishableParcel parcel = new PerishableParcel("Торт", 1, "Самара", 5, 2);
        assertFalse(parcel.isExpired(6));
    }

    @Test
    void addParcelToBoxSuccess() {
        ParcelBox<StandartParcel> box = new ParcelBox<>(10);
        StandartParcel parcel = new StandartParcel("Ручка", 1, "Уфа", 1);
        assertTrue(box.addParcel(parcel));
        assertEquals(1, box.getAllParcels().size());
    }

    @Test
    void addParcelToBoxTest() {
        ParcelBox<StandartParcel> box = new ParcelBox<>(15);
        StandartParcel parcel = new StandartParcel("Кресло", 15, "Краснодар", 10);
        assertTrue(box.addParcel(parcel));
        assertEquals(1, box.getAllParcels().size());
    }

    @Test
    void addParcelToBoxFail() {
        ParcelBox<StandartParcel> box = new ParcelBox<>(5);
        StandartParcel parcel = new StandartParcel("Книга", 6, "Омск", 2);
        assertFalse(box.addParcel(parcel));
        assertEquals(0, box.getAllParcels().size());
    }

    @Test
    void isExpiredBorderCase() {
        PerishableParcel parcel = new PerishableParcel("Фрукты", 2, "Сочи", 1, 1);
        assertTrue(parcel.isExpired(2));
    }
}
