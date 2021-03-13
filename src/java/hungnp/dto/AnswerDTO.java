/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnp.dto;

import java.io.Serializable;

/**
 *
 * @author Win 10
 */
public class AnswerDTO implements Serializable{
    private String answerId;
    private String answerContent;
    private boolean correcting;
    

    public AnswerDTO() {
    }

    public AnswerDTO(String answerContent, boolean correcting) {
        this.answerContent = answerContent;
        this.correcting = correcting;
    }

    public AnswerDTO(String answerId, String answerContent, boolean correcting) {
        this.answerId = answerId;
        this.answerContent = answerContent;
        this.correcting = correcting;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public boolean isCorrecting() {
        return correcting;
    }

    public void setCorrecting(boolean correcting) {
        this.correcting = correcting;
    }
    
    
}





