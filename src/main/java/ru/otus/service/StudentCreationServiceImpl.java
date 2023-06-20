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

        ioStreamHelper.outputString("Please enter your name and press Enter:");
        String name = ioStreamHelper.readString();
        ioStreamHelper.outputString("Please enter your surename and press Enter:");
        String sureName = ioStreamHelper.readString();

        return new Student(name, sureName);
    }
}
