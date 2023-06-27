package ru.otus.helpers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("classpath:application.properties")
@Component
public class TestingPropertiesProviderImpl implements TestingPropertiesProvider {
    private final int minRightQuestionsCount;

    public TestingPropertiesProviderImpl(@Value("${right.answers}") int count) {
        this.minRightQuestionsCount = count;
    }

    public int getMinRightQuestionsCount() {
        return this.minRightQuestionsCount;
    }
}
