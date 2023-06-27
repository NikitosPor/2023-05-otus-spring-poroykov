package ru.otus.service;

import org.springframework.stereotype.Service;
import ru.otus.domain.TestResult;
import ru.otus.helpers.IOService;

@Service
public class ResultsOutputServiceImpl implements ResultsOutputService {
    private final IOService ioService;

    public ResultsOutputServiceImpl(IOService ioService) {
        this.ioService = ioService;
    }

    public void printResults(TestResult testResult, int minRightAnswers) {
        ioService.outputString("Student " + testResult.getStudent().getFullName()
                + " answered " + testResult.getRightAnswerCounter() + " questions correctly");
        if (testResult.getRightAnswerCounter() < minRightAnswers) {
            ioService.outputString("Exam failed");
        } else {
            ioService.outputString("Exam passed");
        }
    }
}
