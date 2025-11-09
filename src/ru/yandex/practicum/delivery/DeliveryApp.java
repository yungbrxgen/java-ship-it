package ru.yandex.practicum.delivery;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();
    private static List<Trackable> trackableParcels = new ArrayList<>();
    private static ParcelBox<StandartParcel> standartParcelBox = new ParcelBox<>(50);
    private static ParcelBox<FragileParcel> fragileParcelBox = new ParcelBox<>(30);
    private static ParcelBox<PerishableParcel> perishableParcelBox = new ParcelBox<>(40);


    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    trackParcels();
                    break;
                case 5:
                    showBoxContents();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 - Обновить статус отслеживаемых посылок");
        System.out.println("5 - Показать содержимое коробки");
        System.out.println("0 — Завершить");
    }

    // реализуйте методы ниже

    private static void addParcel() {
        // Подсказка: спросите тип посылки и необходимые поля, создайте объект и добавьте в allParcels
        System.out.println("Какого типа эта посылка?: ");
        System.out.println("1 - Обычная.");
        System.out.println("2 - Хрупкая.");
        System.out.println("3 - Скоропортящаяся.");
        int cmd = Integer.parseInt(scanner.nextLine());

        System.out.println("Введите описание посылки: ");
        String description = scanner.nextLine();
        System.out.println("Введите вес посылки: ");
        int weight = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите адрес доставки: ");
        String deliveryAddress = scanner.nextLine();
        System.out.println("Введите день месяца отправки посылки: ");
        int sendDay = Integer.parseInt(scanner.nextLine());

        switch (cmd) {
            case 1:
                StandartParcel standartParcel = new StandartParcel(description, weight, deliveryAddress, sendDay);
                allParcels.add(standartParcel);
                standartParcelBox.addParcel(standartParcel);
                System.out.println("Посылка добавлена.");
                break;
            case 2:
                FragileParcel fragileParcel = new FragileParcel(description, weight, deliveryAddress, sendDay);
                allParcels.add(fragileParcel);
                trackableParcels.add(fragileParcel);
                fragileParcelBox.addParcel(fragileParcel);
                System.out.println("Посылка добавлена.");
                break;
            case 3:
                System.out.println("Укажите срок годности посылки: ");
                int timeToLive = Integer.parseInt(scanner.nextLine());
                PerishableParcel perishableParcel = new PerishableParcel(description, weight, deliveryAddress, sendDay, timeToLive);
                allParcels.add(perishableParcel);
                perishableParcelBox.addParcel(perishableParcel);
                System.out.println("Посылка добавлена.");
                break;
            default:
                System.out.println("Неверный тип посылки.");
        }
    }


    private static void sendParcels() {
        // Пройти по allParcels, вызвать packageItem() и deliver()
        for(Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
    }

    private static void calculateCosts() {
        // Посчитать общую стоимость всех доставок и вывести на экран
        int summaryCost = 0;
        for(Parcel parcel : allParcels) {
            summaryCost += parcel.calculateDeliveryCost();
        }
        System.out.println("Общая стоимость всех доставок " +  summaryCost + ".");
    }

    public static void trackParcels() {
        System.out.println("Введите новое местоположение:");
        String newLocation = scanner.nextLine();
        for (Trackable trackable : trackableParcels) {
            trackable.reportStatus(newLocation);
        }
    }

    private static void showBoxContents() {
        System.out.println("Выберите тип коробки (1 - Стандартная, 2 - Хрупкая, 3 - Скоропортящаяся");
        int type = Integer.parseInt(scanner.nextLine());

        switch (type) {
            case 1:
                standartParcelBox.getAllParcels().forEach(parcel -> System.out.println(parcel.getDescription()));
                break;
            case 2:
                fragileParcelBox.getAllParcels().forEach(parcel -> System.out.println(parcel.getDescription()));
                break;
            case 3:
                perishableParcelBox.getAllParcels().forEach(parcel -> System.out.println(parcel.getDescription()));
                break;
            default:
                System.out.println("Неверный тип коробки.");
        }
    }

}

