package com.ixeron.chinese.domain;

import java.util.Date;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "assignment_mistake")
@AssociationOverrides({
    @AssociationOverride(name = "pk.assignment", 
        joinColumns = @JoinColumn(name = "assignment_id")),
    @AssociationOverride(name = "pk.word", 
        joinColumns = @JoinColumn(name = "word_id")) })
public class AssignmentMistake {
    private AssignmentMistakeId pk;
    private Date created;

    /**
     * @return the pk
     */
    @EmbeddedId
    public AssignmentMistakeId getPk() {
        return pk;
    }
    /**
     * @param pk the pk to set
     */
    public void setPk(AssignmentMistakeId pk) {
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
