package ru.otus.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("classpath:application.properties")
@Component
public class MinRightQuestionsDaoImpl implements MinRightQuestionsDao {
    private final int minRightQuestionsCount;

    public MinRightQuestionsDaoImpl(@Value("${right.answers}") int count) {
        this.minRightQuestionsCount = count;
    }

    public int getMinRightQuestionsCount() {
        return this.minRightQuestionsCount;
    }
}
