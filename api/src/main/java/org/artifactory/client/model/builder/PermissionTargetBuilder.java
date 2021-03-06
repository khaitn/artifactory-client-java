package org.artifactory.client.model.builder;

import org.artifactory.client.model.ItemPermission;
import org.artifactory.client.model.PermissionTarget;

/**
 * @author jbaruch
 * @since 26/11/12
 */
public interface PermissionTargetBuilder {

    PermissionTargetBuilder name(String name);

    PermissionTargetBuilder includesPattern(String includesPattern);

    PermissionTargetBuilder excludesPattern(String excludesPattern);

    PermissionTargetBuilder repositories(String... repositories);

    PermissionTargetBuilder itemPermissions(ItemPermission... itemPermissions);

    PermissionTarget build();
}
