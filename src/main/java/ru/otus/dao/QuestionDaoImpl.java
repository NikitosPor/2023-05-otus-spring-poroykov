package ru.otus.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.domain.Answer;
import ru.otus.domain.Question;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;


@Component
public class QuestionDaoImpl implements QuestionDao {
    private final List<Question> listOfQuestionsWithAnswers = new ArrayList<>();

    private final String filePathDest;

    public QuestionDaoImpl(@Value("${file.path}") String filePath) {
        this.filePathDest = filePath;
    }

    public List<Question> getAllQuestionsAndAnswers() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(this.getClass().getResourceAsStream(filePathDest))))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                List<Answer> lstOfAnswrs = new ArrayList<>();
                List<String> listOfStrngs = new ArrayList<String>();
                Scanner scanner = new Scanner(line);
                scanner.useDelimiter(";");
                while (scanner.hasNext()) {
                    listOfStrngs.add(scanner.next());
                }
                scanner.close();
                for (int i = 1; i <= 3; i++) {
                    lstOfAnswrs.add(new Answer(listOfStrngs.get(i),
                            ((listOfStrngs.get(i)).substring(0, 1).equals(listOfStrngs.get(4)))));
                }
                this.listOfQuestionsWithAnswers.add(new Question(listOfStrngs.get(0), lstOfAnswrs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.listOfQuestionsWithAnswers;
    }
}