package ru.otus.dao;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.domain.QuestionWithAnswers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Class LinesFromCsvFileDaoImpl must be able to:")
@ExtendWith(MockitoExtension.class)
class LinesFromCsvFileDaoImplTest {
    private static final List<QuestionWithAnswers> LIST_OF_QUESTIONS = new ArrayList<>();
    private static final QuestionWithAnswers QUESTION_1 = new QuestionWithAnswers(List.of("Who was a king?", "a)Ted", "b)Teena", "c)Kastro", "a"));
    private static final QuestionWithAnswers QUESTION_2 = new QuestionWithAnswers(List.of("Where were this done?", "a)Home", "b)Street", "c)Sky", "b"));
    public final LinesFromCsvFileDao linesFromCsvFileDao = new LinesFromCsvFileDaoImpl("/questions.csv");

    @BeforeAll
    static void setUp() {
        LIST_OF_QUESTIONS.add(QUESTION_1);
        LIST_OF_QUESTIONS.add(QUESTION_2);
    }

    @DisplayName("Extract list of questions")
    @Test
    void getAllQuestionsAndAnswersTest() throws IOException {
        var testList = linesFromCsvFileDao.getAllQuestionsAndAnswers();
        for (int i = 0; i < testList.size(); i++) {
            int finalI = i;
            assertAll("question",
                    () -> assertEquals(LIST_OF_QUESTIONS.get(finalI).getQuestion(), testList.get(finalI).getQuestion()),
                    () -> assertEquals(LIST_OF_QUESTIONS.get(finalI).getAnswerX(0), testList.get(finalI).getAnswerX(0)),
                    () -> assertEquals(LIST_OF_QUESTIONS.get(finalI).getAnswerX(1), testList.get(finalI).getAnswerX(1)),
                    () -> assertEquals(LIST_OF_QUESTIONS.get(finalI).getAnswerX(2), testList.get(finalI).getAnswerX(2)),
                    () -> assertEquals(LIST_OF_QUESTIONS.get(finalI).getRightAnswer(), testList.get(finalI).getRightAnswer())
            );
        }
    }
}