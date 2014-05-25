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
@Table(name = "profile_wordlist")
@AssociationOverrides({
    @AssociationOverride(name = "pk.profile", 
        joinColumns = @JoinColumn(name = "profile_id")),
    @AssociationOverride(name = "pk.wordlist", 
        joinColumns = @JoinColumn(name = "wordlist_id")) })
public class ProfileWordlist {
    
    private ProfileWordlistId pk = new ProfileWordlistId();
    private Date created;

    /**
     * @return the pk
     */
    @EmbeddedId
    public ProfileWordlistId getPk() {
        return pk;
    }
    /**
     * @param pk the pk to set
     */
    public void setPk(ProfileWordlistId pk) {
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
