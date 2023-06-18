package ru.otus.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.domain.QuestionWithAnswers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;


@Component
public class LinesFromCsvFileDaoImpl implements LinesFromCsvFileDao {
    private final List<QuestionWithAnswers> listOfQuestionsWithAnswers = new ArrayList<>();
    private final String filePathDest;

    public LinesFromCsvFileDaoImpl(@Value("${file.path}") String filePath) {
        this.filePathDest = filePath;
    }

    public List<QuestionWithAnswers> getAllQuestionsAndAnswers() throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(this.getClass().getResourceAsStream(filePathDest))))) {
            String line;
            while ((line = reader.readLine()) != null) {
                List<String> listOfStrings = new ArrayList<String>();
                Scanner scanner = new Scanner(line);
                scanner.useDelimiter(";");
                while (scanner.hasNext()) {
                    String data = scanner.next();
                    listOfStrings.add(data);
                }
                this.listOfQuestionsWithAnswers.add(new QuestionWithAnswers(listOfStrings));
                scanner.close();
            }

            return this.listOfQuestionsWithAnswers;
        }//try
    }
}