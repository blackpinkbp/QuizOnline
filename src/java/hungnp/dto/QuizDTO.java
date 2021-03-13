/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnp.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Win 10
 */
public class QuizDTO implements Serializable{
    private String quizId;
    private String subjectName;
    private Timestamp startTime;
    private Timestamp endTime;
    private float totalScore;
    private List<QuestionDTO> listQuestion;

    public QuizDTO() {
    }

    public QuizDTO(String quizId, Timestamp startTime, Timestamp endTime, float totalScore) {
        this.quizId = quizId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalScore = totalScore;
    }

    
    public QuizDTO(String quizId, String subjectName, Timestamp startTime, Timestamp endTime, float totalScore) {
        this.quizId = quizId;
        this.subjectName = subjectName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalScore = totalScore;
    }
    
    
        
    public QuizDTO(String quizId, String subjectName, List<QuestionDTO> listQuestion, float totalScore, Timestamp startTime, Timestamp endTime) {
        this.quizId = quizId;
        this.subjectName = subjectName;
        this.listQuestion = listQuestion;
        this.totalScore = totalScore;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public List<QuestionDTO> getListQuestion() {
        return listQuestion;
    }

    public void setListQuestion(List<QuestionDTO> listQuestion) {
        this.listQuestion = listQuestion;
    }

    public float getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(float totalScore) {
        this.totalScore = totalScore;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    
    
}





















