package ru.yandex.practicum.delivery;

import ru.yandex.practicum.delivery.parcels.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();
    private static List<Trackable> trackableParcels = new ArrayList<>();

    private static ParcelBox<StandardParcel> parcelBoxStandard = new ParcelBox<>(100);
    private static ParcelBox<FragileParcel> parcelBoxFragile = new ParcelBox<>(75);
    private static ParcelBox<PerishableParcel> parcelBoxPerishable = new ParcelBox<>(50);

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
                    reportTrackableStatus();
                    break;
                case 5:
                    getAllParcelsInBox();
                    break;
                case 6:
                    checkExpiredParcels();
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
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("                         🚚 SHIPIT 🚚                          ");
        System.out.println("Выберите действие:");
        System.out.println("1 — \uD83D\uDCE5 Добавить посылку");
        System.out.println("2 — \uD83D\uDE9A Отправить все посылки");
        System.out.println("3 — \uD83D\uDCB0 Посчитать стоимость доставки");
        System.out.println("4 — \uD83D\uDCCD Трекинг посылки");
        System.out.println("5 — \uD83D\uDCE6 Показать содержимое коробки");
        System.out.println("6 — \u23F0 Проверить сроки годности скоропортящихся посылок.");
        System.out.println("0 — \u274C Завершить");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }

    private static void addParcel() {
        printSeparator();
        System.out.println("Введите тип посылки: 1 - Стандартная, 2 - Хрупкая, 3 - Скоропортящаяся");
        int type = scanner.nextInt();

        scanner.nextLine();

        System.out.println("Введите описание посылки:");
        String description = scanner.nextLine();

        System.out.println("Введите вес посылки:");
        int weight = scanner.nextInt();

        scanner.nextLine();

        System.out.println("Введите адрес доставки посылки:");
        String deliveryAddress = scanner.nextLine();

        System.out.println("Введите день отправки посылки:");
        int sendDay = scanner.nextInt();
        printSeparator();

        if (type == 1) {
            StandardParcel standardParcel = new StandardParcel(description, weight, deliveryAddress, sendDay);
            allParcels.add(standardParcel);
            parcelBoxStandard.addParcel(standardParcel);
        } else if (type == 2) {
            FragileParcel fragileParcel = new FragileParcel(description, weight, deliveryAddress, sendDay);
            allParcels.add(fragileParcel);
            trackableParcels.add(fragileParcel);
            parcelBoxFragile.addParcel(fragileParcel);
        } else if (type == 3) {
            System.out.println("Введите срок годности посылки (количество дней):");
            int timeToLive = scanner.nextInt();
            PerishableParcel perishableParcel = new PerishableParcel(description, weight, deliveryAddress, sendDay,
                    timeToLive);
            allParcels.add(perishableParcel);
            parcelBoxPerishable.addParcel(perishableParcel);
        }
        scanner.nextLine();
        System.out.println("Посылка добавлена, все данные для отправки заполнены.");
        printSeparator();
    }

    private static void sendParcels() {
        printSeparator();
        for (Parcel parcel : allParcels) {
            parcel.packageItem();
            parcel.deliver();
        }
        printSeparator();
    }

    private static void calculateCosts() {
        printSeparator();
        int totalCost = 0;
        for (Parcel parcel : allParcels) {
            totalCost += parcel.calculateDeliveryCost();
        }
        System.out.println("Общая стоимость всех доставок: " + totalCost + " руб.");
        printSeparator();
    }

    private static void reportTrackableStatus() {
        printSeparator();
        if (trackableParcels.isEmpty()) {
            System.out.println("Нет отслеживаемых отправлений.");
            printSeparator();
            return;
        }
        System.out.println("Обновление статуса отслеживания.");
        System.out.println("Количество отслеживаемых отправлений: " + trackableParcels.size());
        for (Trackable item : trackableParcels) {
            Parcel parcel = (Parcel) item;
            System.out.println("Введите новое местоположение для <<" + parcel.getDescription() + ">>:");
            String newLocation = scanner.nextLine();
            item.reportStatus(newLocation);
        }
        System.out.println("Статус всех отправлений обновлен!");
        printSeparator();
    }

    private static void getAllParcelsInBox() {
        printSeparator();
        System.out.println("Выберите коробку: 1 - Стандартные посылки, 2 - Хрупкие посылки, " +
                "3 - Скоропортящиеся посылки");
        int boxType = scanner.nextInt();
        scanner.nextLine();
        switch (boxType) {
            case 1:
                if (parcelBoxStandard.getParcelsInBox().isEmpty()) {
                    System.out.println("В коробке нет посылок.");
                    printSeparator();
                    return;
                }
                parcelBoxStandard.getAllParcels();
                printSeparator();
                break;
            case 2:
                if (parcelBoxFragile.getParcelsInBox().isEmpty()) {
                    System.out.println("В коробке нет посылок.");
                    printSeparator();
                    return;
                }
                parcelBoxFragile.getAllParcels();
                printSeparator();
                break;
            case 3:
                if (parcelBoxPerishable.getParcelsInBox().isEmpty()) {
                    System.out.println("В коробке нет посылок.");
                    printSeparator();
                    return;
                }
                parcelBoxPerishable.getAllParcels();
                printSeparator();
                break;
            default:
                System.out.println("Неверный выбор.");
        }
    }

    private static void checkExpiredParcels() {
        printSeparator();
        System.out.println("Проверка сроков годности скоропортящихся посылок.");
        if (parcelBoxPerishable.getParcelsInBox().isEmpty()) {
            System.out.println("В доставке нет скоропортящихся посылок.");
            printSeparator();
            return;
        }
        System.out.println("Введите текущий день: ");
        int currentDay = scanner.nextInt();
        scanner.nextLine();
        boolean foundExpired = false;
        for (PerishableParcel perishableParcel : parcelBoxPerishable.getParcelsInBox()) {
            if (perishableParcel.isExpired(currentDay)) {
                System.out.println("Испорчена посылка: <<" + perishableParcel.getDescription() + ">>");
                foundExpired = true;
            }
        }
        if (!foundExpired) {
            System.out.println("Все скоропортящиеся посылки в норме!");
        }
        printSeparator();
    }

    private static void printSeparator() {
        System.out.println("\n────────────────────────────────────────────────────────────────\n");
    }

}


