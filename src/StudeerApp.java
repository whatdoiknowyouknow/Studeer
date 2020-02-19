import course.Course;
import course.CourseWithSavedQuestions;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudeerApp {

    // communicates with user

    private static final Scanner scanner = new Scanner(System.in);
    private static Student student;  // dit klopt niet. Dit moet niet static zijn. Als ik dit niet static maakt werkt de laatste methode vd klasse niet


    public StudeerApp() {
        student = getStudent();    // good idea to do this here? in later iteration possibily load student from file
    }

    private Student getStudent() {
        System.out.println("Geef je naam in");
        String name = scanner.nextLine();
        return new Student(name);
    }

    public static void showMainMenu() {
        System.out.println("Wens je:");
        System.out.println("\t1. Gegevens invoeren");
        System.out.println("\t2. Test afnemen");
        System.out.println("\t3. Afsluiten");
        int choice = scanner.nextInt(); // no check on input
        scanner.nextLine(); // emptying buffer
        if (choice == 1) {
            showInputMenu();
        } else if (choice == 2) {
            showTestMenu();
        } else if (choice == 3) {
            System.exit(0);
        }
    }


    private static void showInputMenu() {
        List<Course> availableCourses = QuizMaster.getAvailableCourses();
        List<CourseWithSavedQuestions> normalCourses = new ArrayList<>();
        for (Course c : availableCourses) {
            if (c instanceof CourseWithSavedQuestions) {
                normalCourses.add((CourseWithSavedQuestions) c);
            }
        }
        System.out.println("Voor welk vak wens je gegevens in te voeren?");
        int i;
        for (i = 0; i < normalCourses.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + normalCourses.get(i).getName());
        }
        // to do: optie om terug te keren naar hoofdmenu invoeren

        int choice = scanner.nextInt();
        scanner.nextLine(); // empyting buffer
        inputChallenges(normalCourses.get(choice - 1));
    }

    public static void inputChallenges(CourseWithSavedQuestions course) {
        String question;
        do {
            System.out.println("Geef de vraag in, of druk enter om te stoppen: ");
            question = scanner.nextLine();

            if (question.isEmpty()) {
                showMainMenu();
            } else {
                System.out.println("Geef het antwoord. Als er meerdere antwoorden correct zijn, geef je ze allemaal in, gescheiden door een ';' .");
                String[] answer = scanner.nextLine().split(";");
                course.addChallenge(question, answer);
            }
        }
        while (!question.isEmpty());


    }

    public static void showTestMenu() {

        // showing menu
        List<Course> availableCourses = QuizMaster.getAvailableCourses();
        System.out.println("Voor welke vakken wens je een test af te nemen? Geef alle nummers in, gescheiden door één spatie.");
        int i;
        for (i = 0; i < availableCourses.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + availableCourses.get(i).getName());
        }
        System.out.println("\t" + (i + 1) + ". Terug naar het hoofdmenu");

        // getting user input
        String[] choices = scanner.nextLine().split(" ");   // no checks on input since next version will handle user input by GUI
        int[] courseIndices = new int[choices.length];

        if (choices[0].equals(String.valueOf(i+1))) {
            showMainMenu();
        } else {
            for (i = 0; i < choices.length; i++) {
                courseIndices[i] = Integer.parseInt(choices[i]) - 1;
            }
            System.out.println("Hoeveel vragen wil je beantwoorden?");
            int numOfQuestions = scanner.nextInt();
            scanner.nextLine();
            QuizMaster.interrogateStudent(student, numOfQuestions, courseIndices);
        }

    }


}
