package ru.otus.domain;

import java.util.ArrayList;
import java.util.List;

public class QuestionWithAnswers {
    private final String question;
    private final List<String> answers = new ArrayList<>();
    private final String rightAnswer;

    public QuestionWithAnswers(List<String> list) {
        this.question = list.get(0);
        this.answers.add(list.get(1));
        this.answers.add(list.get(2));
        this.answers.add(list.get(3));
        this.rightAnswer = list.get(4);
    }

    public String getQuestion() {
        return this.question;
    }

    public String getAnswerX(int index) {
        return this.answers.get(index);
    }

    public String getRightAnswer() {
        return this.rightAnswer;
    }
}
