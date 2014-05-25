package com.ixeron.chinese.domain;

import java.util.Date;

import javax.persistence.Entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "pingying")
public class Pingying {
    private Integer id;
    private Integer firstPyId;
    private Integer secondPyId;
    private Integer thirdPyId;
    private Integer toneId;
    private Date created;
    private Date updated;
//    private Set<Word> words;
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
     * @return the firstPyId
     */
    public Integer getFirstPyId() {
        return firstPyId;
    }
    /**
     * @param firstPyId the firstPyId to set
     */
    public void setFirstPyId(Integer firstPyId) {
        this.firstPyId = firstPyId;
    }
    /**
     * @return the secondPyId
     */
    public Integer getSecondPyId() {
        return secondPyId;
    }
    /**
     * @param secondPyId the secondPyId to set
     */
    public void setSecondPyId(Integer secondPyId) {
        this.secondPyId = secondPyId;
    }
    /**
     * @return the thirdPyId
     */
    public Integer getThirdPyId() {
        return thirdPyId;
    }
    /**
     * @param thirdPyId the thirdPyId to set
     */
    public void setThirdPyId(Integer thirdPyId) {
        this.thirdPyId = thirdPyId;
    }
    /**
     * @return the toneId
     */
    public Integer getToneId() {
        return toneId;
    }
    /**
     * @param toneId the toneId to set
     */
    public void setToneId(Integer toneId) {
        this.toneId = toneId;
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
