package ru.otus.service;

import ru.otus.domain.Student;

public interface ResultsOutputService {
    public void printResults(Student student, int rightAnswersCounter, int minRightAnswers);

}
