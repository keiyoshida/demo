package com.techscore.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
public class MainController {

    private UserDao userDao;

    private ScoreDao scoreDao;

    private QuestionDao questionDao;

    public MainController(
            final UserDao userDao,
            final ScoreDao scoreDao,
            final QuestionDao questionDao) {
        this.userDao = userDao;
        this.scoreDao = scoreDao;
        this.questionDao = questionDao;
    }

    @GetMapping("/top")
    public String top() {
        return "top";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    // TODO: : -> /
    @GetMapping("/index:{userName}")
    public String index(@PathVariable String userName, RedirectAttributes attr) {
        User user = userDao.findUserByName(userName);

        printHistory(scoreDao.findRecentlyScores(userName), attr);

        attr.addFlashAttribute("userId", user.getId());
        attr.addFlashAttribute("userName", user.getName());

        return "redirect:index";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @PostMapping("/scoring")
    public String scoring(int[] sentenceId, String[] answer, String[] sentenceFirst, String[] sentenceSecond, String[] sentenceMain, String genre, int userId, Timestamp start_time, Model model) {
        List<Question> list = questionDao.findQuestions();
        List<Sentence> data = new ArrayList<>();
        boolean result = false;
        float score = 0;
        for (int i = 0; i < sentenceId.length; i++) {
            String ans = answer[i];
            ans = ans.replaceAll(" ", "");
            if (list.get(sentenceId[i] - 1).getGenre().equals("PostgreSQL")) {
                if (list.get(sentenceId[i] - 1).getAnswer().toLowerCase().replaceAll(" ", "").equals(ans.toLowerCase())) {
                    result = true;
                } else {
                    result = false;
                }
            } else if (list.get(sentenceId[i] - 1).getGenre().equals("Java")) {
                ans = ans.replaceAll("inti", "int i");
                if (list.get(sentenceId[i] - 1).getAnswer().equals(ans)) {
                    result = true;
                } else {
                    result = false;
                }
            } else if (list.get(sentenceId[i] - 1).getGenre().equals("Python")) {
                ans = ans.replace("inrange", "in range");
                if (list.get(sentenceId[i] - 1).getAnswer().equals(ans)) {
                    result = true;
                } else {
                    result = false;
                }
            }
            if (result) {
                score += 100.0 / sentenceId.length;
            }
            data.add(new Sentence(sentenceId[i], sentenceMain[i], genre, sentenceFirst[i], sentenceSecond[i], answer[i], result, list.get(sentenceId[i] - 1).getAnswer()));
        }
        model.addAttribute("sentences", data);
        model.addAttribute("userId", userId);
        model.addAttribute("genre", genre);
        model.addAttribute("score", (int) score);
        model.addAttribute("start_time", start_time);

        return "result";
    }

    @PostMapping("/result")
    public String result(int userId, String genre, int score, Timestamp start_time, Model model) {
        User user = userDao.findUserById(userId);

        scoreDao.insertScoreData(userId, genre, score, start_time);

        return "redirect:/index:" + user.getName();
    }

    // 確認
    @PostMapping("/test_conf")
    public String test_conf(int userId, Model model) {
        model.addAttribute("userId", userId);
        return "test_conf";
    }

    @PostMapping("/examination")
    public String test(String genre, int userId, Model model) {
        if (genre.equals("random")) {
            printQuestion(questionDao.findQuestionsAtRandom(), model);
        } else {
            printQuestion(questionDao.findQuestionsByGenreAtRandom(genre), model);
        }
        Date dt = new Date();
        Timestamp ts = new Timestamp((long) dt.getTime());
        model.addAttribute("userId", userId);
        model.addAttribute("genre", genre);
        model.addAttribute("start_time", ts);
        return "examination";
    }

    @PostMapping("/login")
    public String login(String userName, String password, RedirectAttributes attr) {
        final User user = userDao.findUserByName(userName);
        if (user != null && user.getPassword().equals(password)) {
            attr.addFlashAttribute("userName", userName);

            return "redirect:/index:" + userName;
        } else {
            attr.addFlashAttribute("alert", "ユーザー名もしくはパスワードが違います。");

            return "redirect:/top";
        }
    }

    @PostMapping("/regist")
    public String regist(String userName, String password, RedirectAttributes attr) {
        if (userDao.findUserByName(userName) == null) {
            userDao.insertUser(userName, password);
            return "reg-complete";
        } else {
            attr.addFlashAttribute("alert", "このユーザー名は既に使用されています。");
            return "redirect:/registration";
        }
    }

    public void printHistory(List<Score> list, RedirectAttributes attr) {
        List<UserData> scores = new ArrayList<UserData>();
        for (int i = 0; i < list.size(); i++) {
            String genre = list.get(i).getGenre();
            int score = list.get(i).getScore();
            Timestamp start_time = list.get(i).getStartDateTime();
            scores.add(new UserData(score, genre, start_time));
        }

        // Stream
        // scores.stream().map(score -> new UserData(score.getScore(), score.getGenre(), score.getStartDatetime()));
        // ForEach構文
        // scores.forEach(score -> {
        //     scores.add(new UserData(score.getScore(), score.getGenre(), score.getStartDatetime()));
        // });
        // 拡張For文
        // for (final UserData score : scores) {
        //     scores.add(new UserData(score.getScore(), score.getGenre(), score.getStartDatetime()));
        // }

        attr.addFlashAttribute("dataList", scores);
    }

    public void printQuestion(List<Question> list, Model model) {

        List<Sentence> question = new ArrayList<Sentence>();
        for (int i = 0; i < list.size(); i++) {
            int id = list.get(i).getId();
            String genre = list.get(i).getGenre();
            String main_text = list.get(i).getMainText();
            String first_text = list.get(i).getFirstText();
            String second_text = list.get(i).getSecondText();
            question.add(new Sentence(id, main_text, genre, first_text, second_text, "", false, ""));
        }

        model.addAttribute("sentences", question);
        return;
    }
}

