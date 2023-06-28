package ru.otus.service;

import ru.otus.domain.TestResult;

public interface ResultsOutputService {
    void printResults(TestResult testResult, int minRightAnswers);

}
