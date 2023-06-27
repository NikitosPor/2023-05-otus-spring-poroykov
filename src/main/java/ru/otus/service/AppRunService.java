package ru.otus.service;

import org.springframework.stereotype.Service;
import ru.otus.domain.TestResult;
import ru.otus.helpers.IOService;
import ru.otus.helpers.ResourceFileReadingException;
import ru.otus.helpers.TestingPropertiesProvider;

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
        } catch (ResourceFileReadingException e) {
            ioService.outputString("Error resource file reading!");
        }
    }
}
