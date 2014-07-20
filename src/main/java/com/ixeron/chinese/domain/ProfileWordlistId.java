package com.ixeron.chinese.domain;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class ProfileWordlistId implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Profile profile;
    private Wordlist wordlist;
    /**
     * @return the profile
     */
    @ManyToOne
    public Profile getProfile() {
        return profile;
    }
    /**
     * @param profile the profile to set
     */
    public void setProfile(Profile profile) {
        this.profile = profile;
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
