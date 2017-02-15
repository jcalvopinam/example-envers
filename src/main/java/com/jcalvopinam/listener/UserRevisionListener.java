/**
 * This class sets the userId attribute in the Audit table
 */
package com.jcalvopinam.listener;

import com.jcalvopinam.domain.AuditEnversInfo;
import com.jcalvopinam.utils.Utilities;
import org.hibernate.envers.RevisionListener;

/**
 * @author juanca <juan.calvopina+dev@gmail.com>
 */
public class UserRevisionListener implements RevisionListener {

    @Override
    public void newRevision(Object revisionEntity) {
        AuditEnversInfo auditEnversInfo = (AuditEnversInfo) revisionEntity;
        auditEnversInfo.setUserId(Utilities.getRandomUsers());
    }

}
