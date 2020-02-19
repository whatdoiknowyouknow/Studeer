import java.util.Arrays;

//public class Challenge {
//
//    private String question;
//    private String[] answers;
//
//
//    public Challenge(String question, String ... answers) {
//        if (!question.isEmpty() && answers != null) {
//            this.question = question.trim();
//            this.answers = new String[answers.length];
//            for (int i = 0; i < answers.length; i++) {
//                this.answers[i] = answers[i].trim().toLowerCase();
//            }
//        }}
//
//    public String getQuestion() {
//        return question;
//    }
//
//    public boolean isCorrectAnswer(String answer) {
//        return Arrays.asList(this.answers).contains(answer.trim().toLowerCase());
//    }
//
//    public String getAnswer() {
//        // returns the first correct answer
//        return this.answers[0];
//    }
//
//
//
//}
