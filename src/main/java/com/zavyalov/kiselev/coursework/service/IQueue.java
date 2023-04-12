
package com.zavyalov.kiselev.coursework.service;

import com.zavyalov.kiselev.coursework.service.Commands.ICommand;
import org.springframework.stereotype.Service;

@Service
public interface IQueue {
    void put(ICommand cmd);

    void execNext();
}