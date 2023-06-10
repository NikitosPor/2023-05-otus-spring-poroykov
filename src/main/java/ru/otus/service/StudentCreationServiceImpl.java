package ru.otus.service;

import org.springframework.stereotype.Service;
import ru.otus.domain.Student;
import ru.otus.helpers.IOStreamHelper;

@Service
public class StudentCreationServiceImpl implements StudentCreationService {
    private final IOStreamHelper ioStreamHelper;

    public StudentCreationServiceImpl(IOStreamHelper ioStreamHelper) {
        this.ioStreamHelper = ioStreamHelper;
    }

    public Student askNameAndCreateStudent() {
        ioStreamHelper.outputString("Please enter your name and surename between a space and press Enter:");
        String inputLine = ioStreamHelper.parseString();
        String name = inputLine.substring(0, inputLine.indexOf(" "));
        String sureName = inputLine.substring(inputLine.indexOf(" ") + 1);

        return new Student(name, sureName);
    }
}
