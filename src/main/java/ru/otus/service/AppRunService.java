package ru.otus.service;

import org.springframework.stereotype.Service;
import ru.otus.dao.MinRightQuestionsDao;
import ru.otus.domain.Student;

import java.io.IOException;

@Service
public class AppRunService {
    private final int minRightAnswersLimit;
    private final QuestionAskService questionAskService;
    private final ResultsOutputService resultsOutputService;
    private final StudentCreationService studentCreationService;

    public AppRunService(QuestionAskService questionAskService, MinRightQuestionsDao minRightQuestionsDao,
                         ResultsOutputService resultsOutputService, StudentCreationService studentCreationService) {
        this.questionAskService = questionAskService;
        this.minRightAnswersLimit = minRightQuestionsDao.getMinRightQuestionsCount();
        this.resultsOutputService = resultsOutputService;
        this.studentCreationService = studentCreationService;
    }

    public void run() throws IOException {
        Student student = studentCreationService.askNameAndCreateStudent();
        int rightAnswersCounter = questionAskService.askAllQuestionsAndReturnCounter();
        resultsOutputService.printResults(student, rightAnswersCounter, minRightAnswersLimit);
    }
}
