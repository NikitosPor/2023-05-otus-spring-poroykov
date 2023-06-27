package ru.otus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.dao.QuestionDao;
import ru.otus.domain.Answer;
import ru.otus.domain.Question;
import ru.otus.helpers.IOService;

import java.util.List;

@Service
public class QuestionAskServiceImpl implements QuestionAskService {
    private final QuestionDao questionDao;

    private final IOService ioService;

    @Autowired
    public QuestionAskServiceImpl(QuestionDao questionDao, IOService ioService) {
        this.questionDao = questionDao;
        this.ioService = ioService;
    }


    public int askAllQuestionsAndReturnCounter() {
        int counterOfRightAnswers = 0;
        List<Question> listOfQuestions = questionDao.getAllQuestions();

        for (Question question : listOfQuestions) {
            showQuestion(question);
            String enteredAnswer = ioService.readString().toLowerCase();
            counterOfRightAnswers = getCountOfRightAnswers(getListOfAnswers(question), enteredAnswer);
        }

        return counterOfRightAnswers;
    }

    private List<Answer> getListOfAnswers(Question question) {
        return question.getListOfAnswers();
    }

    private void showQuestion(Question question) {
        List<Answer> listOfAnswers = getListOfAnswers(question);
        StringBuilder string = new StringBuilder();
        string.append(question.getQuestion()).append(" ");
        listOfAnswers.forEach(answer -> string.append(answer.getAnswer()).append(" "));
        ioService.outputString(string.toString());
        ioService.outputString("Choice the right answer:");
    }

    private int getCountOfRightAnswers(List<Answer> listOfAnswers, String enteredAnswer) {
        int counterOfRightAnswers = 0;
        for (Answer answer : listOfAnswers) {
            String firstSymbol = answer.getAnswer().substring(0, 1);
            boolean isCorrect = answer.isCorrect();
            if (enteredAnswer.equals(firstSymbol) && isCorrect) {
                counterOfRightAnswers++;
            }
        }
        return counterOfRightAnswers;
    }

}
