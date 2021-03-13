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
public class QuestionStatusDTO implements Serializable{
    private String questionStatusId;
    private String questionStatusName;

    public QuestionStatusDTO(String questionStatusId, String questionStatusName) {
        this.questionStatusId = questionStatusId;
        this.questionStatusName = questionStatusName;
    }

    public String getQuestionStatusId() {
        return questionStatusId;
    }

    public void setQuestionStatusId(String questionStatusId) {
        this.questionStatusId = questionStatusId;
    }

    public String getQuestionStatusName() {
        return questionStatusName;
    }

    public void setQuestionStatusName(String questionStatusName) {
        this.questionStatusName = questionStatusName;
    }
    
    
}



