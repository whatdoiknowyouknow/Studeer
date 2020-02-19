package course;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CourseWithSavedQuestions extends Course {

    private List<Challenge> challengeList;
    private static final Random random = new Random();

    public CourseWithSavedQuestions(String name){
        super(name);
        challengeList = new ArrayList<>();
    }

    public void loadChallenges(Challenge[] challengeTable){
        Collections.addAll(challengeList, challengeTable);
        }  // aanpassen in volgende versie, vragen opladen uit bestand

    public void addChallenge(String question, String ... answer){
        if (!question.isEmpty() && answer != null){
            challengeList.add(new Challenge(question, answer));
        }
    }

    @Override
    public Challenge getRandomChallenge() {
        int index = random.nextInt(challengeList.size());
        return challengeList.get(index);
    }
}
