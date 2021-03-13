/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnp.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Win 10
 */
public class QuestionDTO implements Serializable{
    private String questionId;
    private String questionContent;
    private String questionStatus;
    private Date createdDate;
    private List<AnswerDTO> listAnswer;
    private String selectedAnswerId;

    public QuestionDTO() {
    }

    public QuestionDTO(String questionContent, List<AnswerDTO> listAnswer) {
        this.questionContent = questionContent;
        this.listAnswer = listAnswer;
    }

    public QuestionDTO(String questionId, String questionContent, String questionStatus, List<AnswerDTO> listAnswer) {
        this.questionId = questionId;
        this.questionContent = questionContent;
        this.questionStatus = questionStatus;
        this.listAnswer = listAnswer;
    }

    public QuestionDTO(String questionId, String questionContent, String questionStatus, Date createdDate, List<AnswerDTO> listAnswer) {
        this.questionId = questionId;
        this.questionContent = questionContent;
        this.questionStatus = questionStatus;
        this.createdDate = createdDate;
        this.listAnswer = listAnswer;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getQuestionStatus() {
        return questionStatus;
    }

    public void setQuestionStatus(String questionStatus) {
        this.questionStatus = questionStatus;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public List<AnswerDTO> getListAnswer() {
        return listAnswer;
    }

    public void setListAnswer(List<AnswerDTO> listAnswer) {
        this.listAnswer = listAnswer;
    }

    public String getSelectedAnswerId() {
        return selectedAnswerId;
    }

    public void setSelectedAnswerId(String selectedAnswerId) {
        this.selectedAnswerId = selectedAnswerId;
    }
    
    
}





























