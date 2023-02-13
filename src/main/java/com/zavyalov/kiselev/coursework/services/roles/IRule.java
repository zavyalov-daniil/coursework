package com.zavyalov.kiselev.coursework.services.roles;

/**
 * Представляет собоя экземпляр паттерна "Команда". Когда мы пытаемся совершить действие, требующее разрешение, мы пользуемся реализациями этого интерфейса
 */
public interface IRule {
    public void execute(Object[] args);
}
