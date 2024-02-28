package org.example.airport.repository;

import org.example.airport.entity.Aircraft;

import java.util.ArrayDeque;
import java.util.Deque;

// РЕПОЗИТОРИЙ (ХРАНИЛИЩЕ, БАЗА ДАННЫХ) ДЛЯ entity - СУЩНОСТЕЙ
// В ДАННОМ СЛУЧАЕ ХРАНИТСЯ ВСЕ В ОБЫЧНОЙ ДВУНАПРАВЛЕННОЙ ОЧЕРЕДИ
public class AircraftRepository {
    public Deque<Aircraft> aircraftsDeque = new ArrayDeque<>();
}
