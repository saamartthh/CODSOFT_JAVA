import java.util.Scanner;
import java.util.concurrent.TimeUnit;

class QuizQuestion {
    private String question;
    private String[] options;
    private int correctOptionIndex;

    public QuizQuestion(String question, String[] options, int correctOptionIndex) {
        this.question = question;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public boolean isCorrect(int selectedOptionIndex) {
        return selectedOptionIndex == correctOptionIndex;
    }
}

class Quiz {
    private QuizQuestion[] questions;
    private int currentQuestionIndex;
    private int score;
    private Scanner scanner;

    public Quiz(QuizQuestion[] questions) {
        this.questions = questions;
        this.currentQuestionIndex = 0;
        this.score = 0;
        this.scanner = new Scanner(System.in);
    }

    public void startQuiz() {
        for (currentQuestionIndex = 0; currentQuestionIndex < questions.length; currentQuestionIndex++) {
            displayQuestion(questions[currentQuestionIndex]);
            int selectedOptionIndex = getSelectedOptionIndex();
            if (selectedOptionIndex != -1) {
                checkAnswer(selectedOptionIndex);
            }
        }
        displayResult();
    }

    private void displayQuestion(QuizQuestion question) {
        System.out.println(question.getQuestion());
        String[] options = question.getOptions();
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }

    private int getSelectedOptionIndex() {
        System.out.print("Enter your answer (1-" + questions[currentQuestionIndex].getOptions().length + "): ");
        int selectedOption = scanner.nextInt();
        if (selectedOption >= 1 && selectedOption <= questions[currentQuestionIndex].getOptions().length) {
            return selectedOption - 1;
        } else {
            System.out.println("Invalid option! Please try again.");
            return getSelectedOptionIndex();
        }
    }

    private void checkAnswer(int selectedOptionIndex) {
        if (questions[currentQuestionIndex].isCorrect(selectedOptionIndex)) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Incorrect!");
        }
    }

    private void displayResult() {
        System.out.println("\nQuiz Completed!");
        System.out.println("Your Score: " + score + "/" + questions.length);
    }
}

public class Main {
    public static void main(String[] args) {
        // Define quiz questions
        QuizQuestion[] questions = new QuizQuestion[3];
        questions[0] = new QuizQuestion("What is the capital of France?",
                new String[]{"Paris", "London", "Berlin", "Rome"}, 0);
        questions[1] = new QuizQuestion("What is 2+2?",
                new String[]{"3", "4", "5", "6"}, 1);
        questions[2] = new QuizQuestion("Who wrote 'Hamlet'?",
                new String[]{"William Shakespeare", "Charles Dickens", "Leo Tolstoy", "Jane Austen"}, 0);

        // Create and start the quiz
        Quiz quiz = new Quiz(questions);
        quiz.startQuiz();
    }
}
