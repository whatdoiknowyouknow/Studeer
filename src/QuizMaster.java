import course.Course;
import course.CourseWithAutoGenQuestions;
import course.CourseWithSavedQuestions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class QuizMaster {

    // holds all courses and interrogates student

    private static List<Course> availableCourses;
    private static Random random = new Random();
    private static final int ALLOWED_GUESSES = 3;
    private static Scanner scanner = new Scanner(System.in);
    private static CourseWithSavedQuestions frans = new CourseWithSavedQuestions("Frans");
    private static CourseWithSavedQuestions engels = new CourseWithSavedQuestions("Engels");

    public static Course.Challenge[] challengeTableFrans = {

            frans.new Challenge("een kind", "un enfant"),
            frans.new Challenge("Ik moet studeren", "je dois étudier"),
            frans.new Challenge("Hoe laat is het?", "Quelle heure est-il?"),
            frans.new Challenge("mijn moeder", "ma mère"),
            frans.new Challenge("mijn vader", "mon père"),
            frans.new Challenge("mijn broer", "mon frère"),
            frans.new Challenge("mijn zus", "ma soeur"),
            frans.new Challenge("een appel", "une pomme"),
            frans.new Challenge("een man", "un homme")
    };

    public static Course.Challenge[] challengeTableEngels = {

            engels.new Challenge("een kind", "a child"),
            engels.new Challenge("Ik moet studeren", "I have to study"),
            engels.new Challenge("Hoe laat is het?", "How late is it?"),
            engels.new Challenge("mijn moeder", "my mother"),
            engels.new Challenge("mijn vader", "my father"),
            engels.new Challenge("mijn broer", "my brother"),
            engels.new Challenge("mijn zus", "my sister"),
            engels.new Challenge("een appel", "an apple"),
            engels.new Challenge("een man", "a man")
    };


    static {

        // loading challenges
        // aanpassen in latere versie, vragen uit bestand laden

        frans.loadChallenges(challengeTableFrans);
        engels.loadChallenges(challengeTableEngels);
        CourseWithAutoGenQuestions statistiek = new CourseWithAutoGenQuestions("Statistiek");

        availableCourses = new ArrayList<>();
        availableCourses.add(frans);
        availableCourses.add(engels);
        availableCourses.add(statistiek);
    }

    public QuizMaster() {
    }

    public static void interrogateStudent(Student student, int numOfQuestions, int ... courseIndex) {
        // interrogates the student, on a specified number of questions, from one or more courses

        int round = 1;
        int score = 0;

        while (round <= numOfQuestions) {

            int randomCourseIndex = courseIndex[random.nextInt(courseIndex.length)]; // picking a random course from all courses the student wants questions from
            Course.Challenge chal = availableCourses.get(randomCourseIndex).getRandomChallenge(); // picking a random challenge from that course

            System.out.println(availableCourses.get(randomCourseIndex).getName() + ": " + chal.getQuestion());
            int numOfGuesses = 0;
            boolean correctAnswer = false;
            while (!correctAnswer && numOfGuesses < ALLOWED_GUESSES) {
                String answer = scanner.nextLine();
                numOfGuesses++;
                if (chal.isCorrectAnswer(answer)) {
                    correctAnswer = true;
                    System.out.println("Correct!");
                    score++;     // give a higher score when answered from first try? e.g. ALLOWED_GUESSES - numOfGuesses + 1 ?
                } else {
                    System.out.println("Verkeerd!" + (numOfGuesses < ALLOWED_GUESSES? "Probeer het nog eens." : ""));
                }
            }
            System.out.println("Het antwoord was: " + chal.getAnswer());
            System.out.println("Huidige score: " + score + "/" + numOfQuestions + ".");
            round++;
        }
        System.out.println("Einde van deze ronde. Je scoorde " + score + "/" + numOfQuestions + ".");

        student.addToScore(score); // of dit al tijdens de ronde doen? ifv crashes of onverwacht afbreken vh spel
        StudeerApp.showTestMenu();
    }

    public static List<Course> getAvailableCourses() {
        return availableCourses;
    }

}
