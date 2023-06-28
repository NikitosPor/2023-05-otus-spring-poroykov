package ru.otus.service;

import org.springframework.stereotype.Service;
import ru.otus.domain.TestResult;
import ru.otus.helpers.IOService;
import ru.otus.exceptions.QuestionsReadingException;
import ru.otus.helpers.TestingPropertiesProvider;

import java.io.IOException;

@Service
public class AppRunService {
    private final int minRightAnswersLimit;

    private final IOService ioService;

    private final QuestionAskService questionAskService;

    private final ResultsOutputService resultsOutputService;

    private final StudentCreationService studentCreationService;

    public AppRunService(QuestionAskService questionAskService,
                         TestingPropertiesProvider testingPropertiesProvider,
                         IOService ioService,
                         ResultsOutputService resultsOutputService,
                         StudentCreationService studentCreationService) {
        this.questionAskService = questionAskService;
        this.minRightAnswersLimit = testingPropertiesProvider.getMinRightQuestionsCount();
        this.ioService = ioService;
        this.resultsOutputService = resultsOutputService;
        this.studentCreationService = studentCreationService;
    }

    public void run() {
        try {
            TestResult testResult = new TestResult();
            testResult.setStudent(studentCreationService.askNameAndCreateStudent());
            testResult.setRightAnswerCounter(questionAskService.askAllQuestionsAndReturnCounter());
            resultsOutputService.printResults(testResult, minRightAnswersLimit);
        } catch (QuestionsReadingException e) {
            Throwable cause = e.getCause();
            if (cause instanceof IOException ioException) {
                ioService.outputString("Error resource file reading!");
                ioException.printStackTrace(); // тут как я понимаю логирование вместо этого надо
            }
        }
    }
}
