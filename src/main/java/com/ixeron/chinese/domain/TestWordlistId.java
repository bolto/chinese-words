package com.ixeron.chinese.domain;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class TestWordlistId implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Test test;
    private Wordlist wordlist;
    /**
     * @return the test
     */
    @ManyToOne
    public Test getTest() {
        return test;
    }
    /**
     * @param test the test to set
     */
    public void setTest(Test test) {
        this.test = test;
    }
    /**
     * @return the wordlist
     */
    @ManyToOne
    public Wordlist getWordlist() {
        return wordlist;
    }
    /**
     * @param wordlist the wordlist to set
     */
    public void setWordlist(Wordlist wordlist) {
        this.wordlist = wordlist;
    }
    
}
