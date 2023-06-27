package ru.otus.service;

import org.springframework.stereotype.Service;
import ru.otus.domain.Student;
import ru.otus.helpers.IOService;

@Service
public class StudentCreationServiceImpl implements StudentCreationService {
    private final IOService ioService;

    public StudentCreationServiceImpl(IOService ioService) {
        this.ioService = ioService;
    }

    public Student askNameAndCreateStudent() {

        ioService.outputString("Please enter your name and press Enter:");
        String name = ioService.readString();
        ioService.outputString("Please enter your surename and press Enter:");
        String sureName = ioService.readString();

        return new Student(name, sureName);
    }
}
