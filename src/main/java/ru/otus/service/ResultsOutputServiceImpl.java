package ru.otus.service;

import org.springframework.stereotype.Service;
import ru.otus.domain.TestResult;
import ru.otus.helpers.IOStreamHelper;

@Service
public class ResultsOutputServiceImpl implements ResultsOutputService {
    private final IOStreamHelper ioStreamHelper;

    public ResultsOutputServiceImpl(IOStreamHelper ioStreamHelper) {
        this.ioStreamHelper = ioStreamHelper;
    }

    public void printResults(TestResult testResult, int minRightAnswers) {
        ioStreamHelper.outputString("Student " + testResult.getStudent().getFullName()
                + " answered " + testResult.getRightAnswerCounter() + " questions correctly");
        if (testResult.getRightAnswerCounter() < minRightAnswers) {
            ioStreamHelper.outputString("Exam failed");
        } else {
            ioStreamHelper.outputString("Exam passed");
        }
    }
}
