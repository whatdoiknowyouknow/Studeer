package course;

import java.util.Arrays;
import java.util.Random;

public class CourseWithAutoGenQuestions extends Course {

    // deze klasse wordt alleen geinstantieerd als statistiek. Is het geen oneigenlijk gebruik van abstracte klasse
    // singleton pattern gebruiken ?


    private static final Random random = new Random();
    private static final int BOUND = 100;

    public CourseWithAutoGenQuestions(String name) {
        super(name);
    }

    @Override
    public Challenge getRandomChallenge() {
        int randomChallenge = random.nextInt(3);
        switch (randomChallenge) {
            case 0:
                return computeStdDeviation();
            case 1:
                return whichIsHighest();
            default: // randomChallenge == 2
                return computeAverage();
        }
    }


    public Challenge computeStdDeviation() {
        final int NUM_OF_NUMBERS = 5;
        int[] numbers = new int[NUM_OF_NUMBERS];
        int average = 0;

        // generating and storing numbers, computing total sum
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(BOUND);
            average += numbers[i];
        }

        // we want our average to be an int
        while (average % NUM_OF_NUMBERS != 0) {
            numbers[numbers.length - 1]++;
            average++;
        }
        average /= NUM_OF_NUMBERS;
        double answer = 0;

        // computing the squared differences from the average and adding them, dividing by number and taking sqrt
        for (int temp : numbers) {
            answer += (average - temp) * (average - temp) + 0.0d;
        }
        answer /= NUM_OF_NUMBERS;
        answer = Math.sqrt(answer);

        String strAnswer = String.format("%.1f", answer);

        StringBuilder sb = new StringBuilder("Wat is de standaarddeviatie van de volgende getallen? Geef het antwoord tot op één cijfer na de komma: ");
        for (int temp : numbers) {
            sb.append(temp).append(" ");
        }
        sb.append("?");

        // if e.g. the answer is 28.0, we also want to approve of 28 .
        if (strAnswer.charAt(strAnswer.length() - 1) == '0') {
            return new Challenge(sb.toString(), strAnswer, strAnswer.replace(',', '.'), strAnswer.substring(0, strAnswer.length() - 2));
        }

        // e.g. 28,3 will be stored as a correct answer, just as 28.3
        return new Challenge(sb.toString(), strAnswer, strAnswer.replace(',', '.'));
    }


    public Challenge whichIsHighest() {

        int numOfNumbers = random.nextInt(2) + 2;  // we want 2 or 3 numbers to compare
        int[] numbers = new int[numOfNumbers];
        int answer = Integer.MIN_VALUE;

        for (int i = 0; i < numOfNumbers; i++) {
            numbers[i] = random.nextInt(BOUND);
            if (numbers[i] > answer) {
                answer = numbers[i];
            }
        }

        StringBuilder sb = new StringBuilder("Welke van deze getallen is het grootst: ");
        for (int temp : numbers) {
            sb.append(temp).append(" ");
        }
        sb.append("?");

        // check to see if we don't have two equal numbers
        Arrays.sort(numbers);
        for (int i = 0; i < numOfNumbers - 1; i++) {
            if (numbers[i] == numbers[i + 1]) {
                return whichIsHighest();
            }
        }
        return new Challenge(sb.toString(), String.valueOf(answer));

    }

    public Challenge computeAverage() {

        int numOfNumbers = random.nextInt(2) + 2;  // we want 2 or 3 numbers to compute the average for
        int[] numbers = new int[numOfNumbers];
        int answer = 0;

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(BOUND);
            answer += numbers[i];
        }

        while (answer % numOfNumbers != 0) {  // we add 1 to the last number until the average is an integer
            answer++;
            numbers[numbers.length - 1]++;
        }
        answer /= numOfNumbers;

        StringBuilder sb = new StringBuilder("Wat is het gemiddelde van deze getallen: ");
        for (int temp : numbers) {
            sb.append(temp).append(" ");
        }
        sb.append("?");

        return new Challenge(sb.toString(), String.valueOf(answer));
    }
}
