package ru.otus.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.domain.Answer;
import ru.otus.domain.Question;
import ru.otus.exceptions.QuestionsReadingException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;


@Component
public class QuestionDaoImpl implements QuestionDao {

    private final String filePathDest;

    public QuestionDaoImpl(@Value("${file.path}") String filePath) {
        this.filePathDest = filePath;
    }

    private void parseStringLine(String stringLine, List<Question> listOfQuestionsWithAnswers) {
        List<Answer> listOfAnswers = new ArrayList<>();
        List<String> listOfStrings = new ArrayList<String>();
        try (Scanner scanner = new Scanner(stringLine)) {
            scanner.useDelimiter(";");
            while (scanner.hasNext()) {
                listOfStrings.add(scanner.next());
            }
        }
        for (int i = 1; i <= 3; i++) {
            listOfAnswers.add(new Answer(listOfStrings.get(i),
                    ((listOfStrings.get(i)).substring(0, 1).equals(listOfStrings.get(4)))));
        }
        listOfQuestionsWithAnswers.add(new Question(listOfStrings.get(0), listOfAnswers));
    }

    public List<Question> getAllQuestions() {
        List<Question> listOfQuestionsWithAnswers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(this.getClass().getResourceAsStream(filePathDest))))) {
            String line;
            while ((line = reader.readLine()) != null) {
                parseStringLine(line, listOfQuestionsWithAnswers);
            }
        } catch (IOException e) {
            throw new QuestionsReadingException(e);
        }
        return listOfQuestionsWithAnswers;
    }
}