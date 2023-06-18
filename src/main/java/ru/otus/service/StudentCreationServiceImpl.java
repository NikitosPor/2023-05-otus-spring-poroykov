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
        String name = null;
        String sureName = null;
        while (name == null) {
            ioStreamHelper.outputString("Please enter your name and surename between a space and press Enter:");
            try {
                String inputLine = ioStreamHelper.readString();
                name = inputLine.substring(0, inputLine.indexOf(" "));
                sureName = inputLine.substring(inputLine.indexOf(" ") + 1);
            } catch (Exception e) {
                ioStreamHelper.outputString("Seems you entered wrong name and surename.\n" +
                        "Be sure that you placed space between name and surename");
            }
        }
        return new Student(name, sureName);
    }
}
