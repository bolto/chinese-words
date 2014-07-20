package com.ixeron.chinese.domain;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class WordPingyingId implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Word word;
    private Pingying pingying;
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
    /**
     * @return the pingying
     */
    @ManyToOne
    public Pingying getPingying() {
        return pingying;
    }
    /**
     * @param pingying the pingying to set
     */
    public void setPingying(Pingying pingying) {
        this.pingying = pingying;
    }
    
}
