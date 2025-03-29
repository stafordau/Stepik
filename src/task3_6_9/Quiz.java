package gui3_6_9;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Quiz {
    private static JFrame frame;
    private static JLabel questionLabel;
    private static JLabel questionNumberLabel;
    private static JButton[] answerButtons = new JButton[4];

    private static String[] questions = {
            "Кто написал роман \"Преступление и наказание\"?",
            "Как называется волшебная школа в книгах о Гарри Поттере?",
            "Какой жанр у книги \"Властелин колец\"?",
            "Кто автор романа \"1984\"?"
    };

    private static String[][] options = {
            {"Антон Чехов", "Иван Тургенев", "Фёдор Достоевский", "Лев Толстой"},
            {"Ильверморни", "Шармбатон", "Хогвартс", "Дурмстранг"},
            {"Научная фантастика", "Фэнтези", "Исторический роман", "Детектив"},
            {"Олдос Хаксли", "Джордж Оруэлл", "Рэй Брэдбери", "Курт Воннегут"}
    };

    private static int[] correctAnswers = {2, 2, 1, 1};
    private static int currentQuestion = 0;
    private static int score = 0;

    public static void main(String[] args) {
        frame = new JFrame("Викторина о книгах");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        JPanel topPanel = new JPanel(new BorderLayout());
        questionNumberLabel = new JLabel("", SwingConstants.RIGHT);
        questionNumberLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        topPanel.add(questionNumberLabel, BorderLayout.NORTH);

        questionLabel = new JLabel("", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));
        questionLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        topPanel.add(questionLabel, BorderLayout.CENTER);

        frame.add(topPanel, BorderLayout.NORTH);

        JPanel answersPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        answersPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 40, 40));
        for (int i = 0; i < 4; i++) {
            answerButtons[i] = new JButton();
            answerButtons[i].setFont(new Font("Arial", Font.PLAIN, 16));
            answersPanel.add(answerButtons[i]);
            int finalI = i;
            answerButtons[i].addActionListener(e -> checkAnswer(finalI));
        }

        frame.add(answersPanel, BorderLayout.CENTER);

        loadQuestion();
        frame.setVisible(true);
    }

    private static void loadQuestion() {
        if (currentQuestion >= questions.length) {
            showResult();
            return;
        }

        questionNumberLabel.setText("Вопрос " + (currentQuestion + 1) + "/" + questions.length);
        questionLabel.setText(questions[currentQuestion]);

        for (int i = 0; i < 4; i++) {
            answerButtons[i].setText(options[currentQuestion][i]);
            answerButtons[i].setEnabled(true);
        }
    }

    private static void checkAnswer(int selectedIndex) {
        if (selectedIndex == correctAnswers[currentQuestion]) {
            score++;
        }

        SwingUtilities.getRootPane(answerButtons[selectedIndex]).requestFocusInWindow();

        currentQuestion++;
        loadQuestion();
    }

    private static void showResult() {
        frame.getContentPane().removeAll();

        JLabel resultLabel = new JLabel("Викторина завершена! Ваш результат: " + score + " из " + questions.length, SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 20));
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setVerticalAlignment(SwingConstants.CENTER);

        frame.setLayout(new BorderLayout());
        frame.add(resultLabel, BorderLayout.CENTER);

        frame.revalidate();
        frame.repaint();
    }
}