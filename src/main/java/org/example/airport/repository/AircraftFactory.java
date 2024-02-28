package org.example.airport.repository;

import org.example.airport.entity.Aircraft;
import org.example.airport.entity.helicopter.Helicopter;
import org.example.airport.entity.plane.BigPlane;
import org.example.airport.entity.plane.LittlePlane;
import org.example.airport.entity.plane.StandartPlane;
import org.example.airport.util.AircraftType;

// ПРОСТАЯ ФАБРИКА НА switch expression
public class AircraftFactory {
    public Aircraft createAircraft(AircraftType aircraftType) {
       return switch (aircraftType) {
            case Helicopter -> new Helicopter();
            case BigPlane -> new BigPlane();
            case StandartPlane -> new StandartPlane();
            case LittlePlane -> new LittlePlane();
        };
    }
}
