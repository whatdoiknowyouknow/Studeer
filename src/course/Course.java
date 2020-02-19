package course;

import java.util.*;

public abstract class Course {

    // a course holds all questions and answers, either from file or by auto-generating them

    private String name;

    public Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract Challenge getRandomChallenge();

    // inner class
    public class Challenge {

        private String question;
        private String[] answers;


        public Challenge(String question, String ... answers) {
            if (!question.isEmpty() && answers != null) {
                this.question = question.trim();
                this.answers = new String[answers.length];
                for (int i = 0; i < answers.length; i++) {
                    this.answers[i] = answers[i].trim().toLowerCase();
                }
            }}

        public String getQuestion() {
            return question;
        }

        public boolean isCorrectAnswer(String answer) {
            return Arrays.asList(this.answers).contains(answer.trim().toLowerCase());
        }

        public String getAnswer() {
            // returns the first correct answer
            return this.answers[0];
        }
    }
}
