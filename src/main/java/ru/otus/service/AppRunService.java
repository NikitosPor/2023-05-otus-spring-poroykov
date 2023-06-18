package ru.otus.service;

import org.springframework.stereotype.Service;
import ru.otus.domain.TestResult;
import ru.otus.helpers.MinRightQuestions;

@Service
public class AppRunService {
    private final int minRightAnswersLimit;

    private final QuestionAskService questionAskService;

    private final ResultsOutputService resultsOutputService;

    private final StudentCreationService studentCreationService;

    public AppRunService(QuestionAskService questionAskService, MinRightQuestions minRightQuestions,
                         ResultsOutputService resultsOutputService, StudentCreationService studentCreationService) {
        this.questionAskService = questionAskService;
        this.minRightAnswersLimit = minRightQuestions.getMinRightQuestionsCount();
        this.resultsOutputService = resultsOutputService;
        this.studentCreationService = studentCreationService;
    }

    public void run() {
        TestResult testResult = new TestResult();
        testResult.setStudent(studentCreationService.askNameAndCreateStudent());
        testResult.setRightAnswerCounter(questionAskService.askAllQuestionsAndReturnCounter());
        resultsOutputService.printResults(testResult, minRightAnswersLimit);
    }
}
