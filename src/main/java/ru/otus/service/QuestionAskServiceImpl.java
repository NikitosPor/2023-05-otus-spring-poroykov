package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.dao.LinesFromCsvFileDao;
import ru.otus.domain.QuestionWithAnswers;
import ru.otus.helpers.IOStreamHelper;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
public class QuestionAskServiceImpl implements QuestionAskService {
    private final LinesFromCsvFileDao linesFromCsvFileDao;
    private final IOStreamHelper ioStreamHelper;

    @Autowired
    public QuestionAskServiceImpl(LinesFromCsvFileDao linesFromCsvFileDao, IOStreamHelper ioStreamHelper) {
        this.linesFromCsvFileDao = linesFromCsvFileDao;
        this.ioStreamHelper = ioStreamHelper;
    }

    public int askAllQuestionsAndReturnCounter() throws IOException {
        int counterOfRightAnswers = 0;
        List<QuestionWithAnswers> listOfQuestionsWithAnswers = linesFromCsvFileDao.getAllQuestionsAndAnswers();

        for (QuestionWithAnswers stringLine : listOfQuestionsWithAnswers) {
            String answer;
            String rightAnswer = stringLine.getRightAnswer();
            ioStreamHelper.outputString(stringLine.getQuestion() + " "
                    + stringLine.getAnswerX(0) + " "
                    + stringLine.getAnswerX(1) + " "
                    + stringLine.getAnswerX(2)
            );

            ioStreamHelper.outputString("Choice the right answer:");
            answer = ioStreamHelper.parseString();

            if (Objects.equals(answer, rightAnswer)) {
                counterOfRightAnswers++;
            }
        }

        return counterOfRightAnswers;
    }
}
