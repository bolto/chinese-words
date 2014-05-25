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
import javax.persistence.Transient;

@Entity
@Table(name = "word_pingying")
@AssociationOverrides({
    @AssociationOverride(name = "pk.word", 
        joinColumns = @JoinColumn(name = "word_id")),
    @AssociationOverride(name = "pk.pingying", 
        joinColumns = @JoinColumn(name = "pingying_id")) })
public class WordPingying {
    
    private WordPingyingId pk = new WordPingyingId();
    private Integer listOrder;
    private Date created;
    private Date updated;


    /**
     * @return the pk
     */
    @EmbeddedId
    public WordPingyingId getPk() {
        return pk;
    }
    /**
     * @param pk the pk to set
     */
    public void setPk(WordPingyingId pk) {
        this.pk = pk;
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
    @Transient
    public Word getWord(){
        return getPk().getWord();
    }
    
    public void setWord(Word word){
        getPk().setWord(word);
    }
    
    @Transient
    public Pingying getPingying(){
        return getPk().getPingying();
    }
    
    public void setPingying(Pingying pingying){
        getPk().setPingying(pingying);
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
}
