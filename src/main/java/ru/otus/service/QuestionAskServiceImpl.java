package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.dao.QuestionDao;
import ru.otus.domain.Answer;
import ru.otus.domain.Question;
import ru.otus.helpers.IOStreamHelper;

import java.util.List;

@Service
public class QuestionAskServiceImpl implements QuestionAskService {
    private final QuestionDao questionDao;

    private final IOStreamHelper ioStreamHelper;

    @Autowired
    public QuestionAskServiceImpl(QuestionDao questionDao, IOStreamHelper ioStreamHelper) {
        this.questionDao = questionDao;
        this.ioStreamHelper = ioStreamHelper;
    }

    public int askAllQuestionsAndReturnCounter() {
        int counterOfRightAnswers = 0;
        List<Question> listOfQuestions = questionDao.getAllQuestionsAndAnswers();

        for (Question question : listOfQuestions) {
            List<Answer> listOfAnswers = question.getListOfAnswers();
            StringBuilder string = new StringBuilder();

            string.append(question.getQuestion()).append(" ");
            listOfAnswers.forEach(answer -> string.append(answer.getAnswer()).append(" "));
            ioStreamHelper.outputString(string.toString());
            ioStreamHelper.outputString("Choice the right answer:");
            String enteredAnswer = ioStreamHelper.readString().toLowerCase();

            for (Answer answer : listOfAnswers) {
                String firstSymbol = answer.getAnswer().substring(0, 1);
                boolean isCorrect = answer.isCorrect();
                if (enteredAnswer.equals(firstSymbol) && isCorrect) {
                    counterOfRightAnswers++;
                }
            }
        }

        return counterOfRightAnswers;
    }
}
