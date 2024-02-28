package org.example.airport.util;

import java.util.Random;

public enum AircraftType {
    BigPlane, StandartPlane, LittlePlane, Helicopter;

    // МЕТОД РАНДОМНОЙ ВЫДАЧИ ОДНОГО ИЗ ЭЛЕМЕНТОВ enum'а
    public static AircraftType getRandomAircraftType() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
