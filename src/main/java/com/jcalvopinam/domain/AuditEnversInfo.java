/**
 * This entity has an additional attribute for Audit table
 */
package com.jcalvopinam.domain;

import com.jcalvopinam.listener.UserRevisionListener;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */
@Entity
@RevisionEntity(UserRevisionListener.class)
@Table(name = "AUDIT_ENVERS_INFO")
public class AuditEnversInfo extends DefaultRevisionEntity {

    @Column(name = "USER_ID")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
