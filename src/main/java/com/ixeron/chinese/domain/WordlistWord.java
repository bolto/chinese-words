package com.ixeron.chinese.domain;

import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "wordlist_word")
@AssociationOverrides({
    @AssociationOverride(name = "wordPingyingId.word", 
        joinColumns = @JoinColumn(name = "word_id")),
    @AssociationOverride(name = "wordPingyingId.pingying", 
        joinColumns = @JoinColumn(name = "pingying_id")) })
public class WordlistWord {
    private Integer id;
    private Integer listOrder;
    private String symbol;
    private Integer wordlistId;
    private WordPingyingId wordPingyingId;
    private Date created;
    private Date updated;
    private Pingying pingying;

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }


    /**
     * @return the listOrder
     */
    public Integer getListOrder() {
        return listOrder;
    }
    /**
     * @param listOrder the listOrder to set
     */
    public void setListOrder(Integer listOrder) {
        this.listOrder = listOrder;
    }
    /**
     * @return the symbol
     */
    @Column(nullable = false, length = 1)
    public String getSymbol() {
        return symbol;
    }
    /**
     * @param symbol the symbol to set
     */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /**
     * @return the wordlistId
     */
    @Column(name = "wordlist_id", nullable = false)
    public Integer getWordlistId() {
        return wordlistId;
    }
    /**
     * @param wordlistId the wordlistId to set
     */
    public void setWordlistId(Integer wordlistId) {
        this.wordlistId = wordlistId;
    }


    /**
     * @return the wordPingyingId
     */
    public WordPingyingId getWordPingyingId() {
        return wordPingyingId;
    }
    /**
     * @param wordPingyingId the wordPingyingId to set
     */
    public void setWordPingyingId(WordPingyingId wordPingyingId) {
        this.wordPingyingId = wordPingyingId;
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
    /**
     * @return the updated
     */
    @Column(columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getUpdated() {
        return updated;
    }
    /**
     * @param updated the updated to set
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }
    /**
     * @return the pingying
     */
    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "pingying_id", insertable=false, updatable=false)
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
