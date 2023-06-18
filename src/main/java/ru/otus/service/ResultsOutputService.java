package ru.otus.service;

import ru.otus.domain.TestResult;

public interface ResultsOutputService {
    public void printResults(TestResult testResult, int minRightAnswers);

}
