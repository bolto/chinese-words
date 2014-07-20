package com.ixeron.chinese.domain;

import java.util.Date;

import javax.persistence.AssociationOverrides;
import javax.persistence.AssociationOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "test_wordlist_word")
@AssociationOverrides({
    @AssociationOverride(name = "pk.test", 
        joinColumns = @JoinColumn(name = "test_id")),
    @AssociationOverride(name = "pk.wordlist", 
        joinColumns = @JoinColumn(name = "wordlist_id")),
    @AssociationOverride(name = "pk.word", 
        joinColumns = @JoinColumn(name = "word_id")) })
public class TestWordlistWord {
    
    private TestWordlistWordId pk = new TestWordlistWordId();
    private Date created;

    /**
     * @return the pk
     */
    @EmbeddedId
    public TestWordlistWordId getPk() {
        return pk;
    }
    /**
     * @param pk the pk to set
     */
    public void setPk(TestWordlistWordId pk) {
        this.pk = pk;
    }

    /**
     * @return the created
     */
    @Column(columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreated() {
        return created;
    }
    /**
     * @param created the created to set
     */
    public void setCreated(Date created) {
        this.created = created;
    }
}
