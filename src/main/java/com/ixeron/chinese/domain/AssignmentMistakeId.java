package com.ixeron.chinese.domain;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class AssignmentMistakeId implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Assignment assignment;
    private Word word;


    /**
     * @return the assignment
     */
    @ManyToOne
    public Assignment getAssignment() {
        return assignment;
    }
    /**
     * @param assignment the assignment to set
     */
    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    /**
     * @return the word
     */
    @ManyToOne
    public Word getWord() {
        return word;
    }
    /**
     * @param word the word to set
     */
    public void setWord(Word word) {
        this.word = word;
    }
    
}
