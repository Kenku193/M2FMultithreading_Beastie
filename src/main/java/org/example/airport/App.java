package org.example.airport;

import org.example.airport.repository.AircraftFactory;
import org.example.airport.repository.AircraftRepository;
import org.example.airport.service.AircraftGenerator;
import org.example.airport.service.AircraftLander;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class App {

    public static void main(String[] args) {

        // ЕДИНАЯ  ТОЧКА ВХОДА И "СБОРКИ" ПРИЛОЖЕНИЯ
        // Я СОЗДАЮ ПО ОДНОМУ ЭКЗЕМПЛЯРУ НУЖНЫХ МНЕ КЛАССОВ
        // ОБРАЗУЯ ТАК НАЗЫВАЕМЫЙ "КОНТЕКСТ" МОЕГО ПРИЛОЖЕНИЯ

        // МНЕ НУЖЕН ИМЕННО И ТОЛЬКО ОДИН ОБЪЕТ БЛОКИРОВЩИКА
        // ЧТОБЫ НА НЕМ БЛОКИРОВАТЬ НУЖНЫЕ МНЕ УЧАСТИ МНОГОПОТОЧНОГО КОДА
        Lock lock = new ReentrantLock();

        // МНЕ НУЖЕН ТОЛЬКО ОДИН РЕПОЗИТОРИЙ (БАЗА ДАННЫХ) ДЛЯ МОИХ СУЩНОСТЕЙ - Aircraft
        AircraftRepository aircraftRepository = new AircraftRepository();

        // МНЕ НУЖНА ТОЛЬКО ОДНА ФАБРИКА И ТОЛЬКО ОДИН ГЕНЕРАТОР, ДЛЯ СОЗДАНИЯ Aircraft
        AircraftFactory aircraftFactory = new AircraftFactory();

        // КОНТЕКСТ - НУЖНЫЙ НАБОР КЛАССОВ ДЛЯ РАБОТЫ ПРИЛОЖЕНИЯ ГОТОВ

        // ТЕПЕРЬ Я СОЗДАЮ ПУЛ ПОТОКОВ, В КОТОРОМ БУДУТ РАБОТАТЬ МОИ ЗАДАЧИ
        // ПОСТАВЩИКОМ ЗАДАЧ - ТО ЕСТЬ УЧАСТКОВ МНОГОПОТОЧНОГО КОДА ЯВЛЯЮТСЯ aircraftGenerator И aircraftLander
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(4);
//        scheduledExecutorService.schedule(aircraftLander, 1, TimeUnit.SECONDS);
//        ExecutorService executorService = Executors.newFixedThreadPool(4);

        // В СВОЮ ОЧЕРЕДЬ AircraftGenerator'у В КОНСТРУКТОР НУЖНО ПОДАВАТЬ СОЗДАННЫЕ ВЫШЕ lock, repository И factory
        // ТАКЖЕ КАК И aircraftLander'у НУЖНЫ ИМЕННО ТОТ ЖЕ (НЕ ТАКОЙ ЖЕ, А ТОТ ЖЕ!) БЛОКИРОВЩИК И РЕПОЗИТОРИЙ

        // А ЗНАЧИТ Я ИХ ПОДАЮ НА ВЫПОЛНЕНИЕ ПУЛУ ПОТОКОВ
        // ОН САМ РАЗБЕРЕТСЯ КАК ИХ ВЫПОЛНЯТЬ, В КАКОМ ПОРЯДКЕ, С КАКИМ ПРИОРИТЕТОМ И ТАК ДАЛЕЕ
        while (true) {
            executorService.submit(new AircraftGenerator(lock, aircraftRepository, aircraftFactory));
            executorService.submit(new AircraftLander(lock, aircraftRepository));
        }
    }
}