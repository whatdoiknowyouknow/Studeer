import java.util.Objects;

public class Student {

    private String name;
    private int currentScore;

    public Student(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void addToScore(int score){
        currentScore += score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return name.equals(student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


}
