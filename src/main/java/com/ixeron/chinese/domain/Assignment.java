package com.ixeron.chinese.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "assignment")
public class Assignment {
    private Integer id;
    private Integer testId;
    private Integer profileId;
    private Date assigned;
    private Date completed;
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
     * @return the testId
     */
    @NotNull
    @Column(nullable = false)
    public Integer getTestId() {
        return testId;
    }
    /**
     * @param testId the testId to set
     */
    public void setTestId(Integer testId) {
        this.testId = testId;
    }
    /**
     * @return the profileId
     */
    @NotNull
    @Column(nullable = false)
    public Integer getProfileId() {
        return profileId;
    }
    /**
     * @param profileId the profileId to set
     */
    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
    }
    /**
     * @return the assigned
     */
    @Column(columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getAssigned() {
        return assigned;
    }
    /**
     * @param assigned the assigned to set
     */
    public void setAssigned(Date assigned) {
        this.assigned = assigned;
    }
    /**
     * @return the completed
     */
    @Column(columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCompleted() {
        return completed;
    }
    /**
     * @param completed the completed to set
     */
    public void setCompleted(Date completed) {
        this.completed = completed;
    }
}
