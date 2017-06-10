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
@Table(name = "env_audit_envers_info")
public class AuditEnversInfo extends DefaultRevisionEntity {

    private static final long serialVersionUID = -7604731515258123883L;

    @Column(name = "user_id")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
