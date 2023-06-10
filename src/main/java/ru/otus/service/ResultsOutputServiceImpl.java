package ru.otus.service;

import org.springframework.stereotype.Service;
import ru.otus.domain.Student;
import ru.otus.helpers.IOStreamHelper;

@Service
public class ResultsOutputServiceImpl implements ResultsOutputService {
    private final IOStreamHelper ioStreamHelper;

    public ResultsOutputServiceImpl(IOStreamHelper ioStreamHelper) {
        this.ioStreamHelper = ioStreamHelper;
    }

    public void printResults(Student student, int rightAnswersCounter, int minRightAnswers) {
        ioStreamHelper.outputString("Student " + student.getFullName() + " answered " + rightAnswersCounter + " questions correctly");
        if (rightAnswersCounter < minRightAnswers) {
            ioStreamHelper.outputString("Exam failed");
        } else {
            ioStreamHelper.outputString("Exam passed");
        }
    }
}
