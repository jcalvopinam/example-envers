package com.jcalvopinam.listener;

import com.jcalvopinam.domain.AuditEnversInfo;
import org.hibernate.envers.RevisionListener;

/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */
public class UserRevisionListener implements RevisionListener {

    private final static String USER_ID = "J1987";

    @Override
    public void newRevision(Object revisionEntity) {
        AuditEnversInfo auditEnversInfo = (AuditEnversInfo) revisionEntity;
        auditEnversInfo.setUserId(USER_ID);
    }

}
