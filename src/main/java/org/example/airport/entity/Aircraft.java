package org.example.airport.entity;

import org.example.airport.util.AircraftType;
import org.example.airport.util.State;

// КЛАСС-ПРЕДОК ДЛЯ ВСЕХ ВОЗМОЖНЫХ БУДУЩИХ АВИА-ТРАНСПОТНЫХ СРЕДСТВ
// В НЕМ Я ОПИСАЛ ОБЩИЕ ПОЛЯ, КОНСТУРКТОР И МЕТОДЫ
public class Aircraft {
    public Long id;
    public State state;

    // ВНИМАНИЕ - Я НЕ ИСПОЛЬЗУЮ ЭТО ПОЛЕ В КОНСТРУКТОРЕ
    // НО ОНО МНЕ ПОНАДОБИТСЯ В ПОТОМКАХ - ЧТОБЫ КОНКРЕТИЗИРОВАТЬ ТИП aircraftType ПО enum'у AircraftType
    protected AircraftType aircraftType;

    public Aircraft() {
        this.id = System.currentTimeMillis();
        this.state = State.ONAIR;
    }

    public boolean landing() {
        this.state = State.LANDING;
        try {
            System.err.println(aircraftType + " " + this.id + " приземлился");
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.state = State.LANDED;
        return true;
    }
}
