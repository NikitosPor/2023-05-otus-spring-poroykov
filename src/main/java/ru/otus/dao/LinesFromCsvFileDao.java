package ru.otus.dao;

import ru.otus.domain.QuestionWithAnswers;

import java.io.IOException;
import java.util.List;

public interface LinesFromCsvFileDao {

    public List<QuestionWithAnswers> getAllQuestionsAndAnswers() throws IOException;

}
