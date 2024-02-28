package org.example.airport.service;

import org.example.airport.repository.AircraftFactory;
import org.example.airport.repository.AircraftRepository;
import org.example.airport.util.AircraftType;

import java.util.concurrent.locks.Lock;

// ГЕНЕРАТОР Aircraft'в - ТА САМАЯ ЧАСТЬ КОДА, КОТОРУЮ ВЫГОДНО ВЫПОЛНЯТЬ МНОГОПОТОЧНО
// Я ХОЧУ ИМЕТЬ ВОЗМОЖНОСТЬ ГЕРЕНИРОВАТЬ Aircraft'ы ИСПОЛЬЗУЯ ВСЕ ЯДРА ПРОЦЕССОРА ПАРАЛЛЕЛЬНО
// СОГЛАСИТЕСЬ - В ОБЩЕМ-ТО МНЕ МОЖЕТ БЫТЬ ВСЕ РАВНО, КАК ОНИ ГЕНЕРИРУЮТСЯ, КРОМЕ ТЕХ СЛУЧАЕВ, КОГДА
// НАД ЭТИМИ Aircraft'ами НЕ ПРОИЗВОДЯТСЯ КАКИЕ-ТО ДЕЙСТВИЯ В ТО ЖЕ САМОЕ ВРЕМЯ, ПАРАЛЛЕЛЬНО С ИХ ГЕНЕРАЦИЕЙ
public class AircraftGenerator implements Runnable {
    private Lock lock;
    private  AircraftRepository aircraftsRepository;
    private AircraftFactory aircraftFactory;

    public AircraftGenerator(Lock lock, AircraftRepository aircraftsRepository, AircraftFactory aircraftFactory) {
        this.lock = lock;
        this.aircraftsRepository = aircraftsRepository;
        this.aircraftFactory = aircraftFactory;
    }

//    @Override
//    public void run() {
//        // ДАННЫЙ х НУЖЕН ЧТОБЫ ПОДСТАВИТЬ В while ВМЕСТО true -> x != 20
//        // ИНАЧЕ ЦИКЛ РАБОТАЕТ БЕСКОНЕЧНО, lock ВСЕГДА ЗАХВАЧЕН
//        // int x = 0;
//        lock.lock();
//        try {
//            while (true) {
//                Thread.sleep(500);
//                AircraftType randomAircraftType = AircraftType.getRandomAircraftType();
//                aircraftsRepository.aircraftsDeque.addLast(aircraftFactory.createAircraft(randomAircraftType));
//                System.out.println(randomAircraftType + " создан, количество единиц в небе: " + aircraftsRepository.aircraftsDeque.size());
//                // x++;
//            }
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        } finally {
//            lock.unlock();
//        }
//    }

    @Override
    public void run() {
        lock.lock();
        try {
            AircraftType randomAircraftType = AircraftType.getRandomAircraftType();
            aircraftsRepository.aircraftsDeque.addLast(aircraftFactory.createAircraft(randomAircraftType));
            System.out.println(randomAircraftType + " создан, количество единиц в небе: " + aircraftsRepository.aircraftsDeque.size());
        } finally
        {
            lock.unlock();
        }
    }
}
