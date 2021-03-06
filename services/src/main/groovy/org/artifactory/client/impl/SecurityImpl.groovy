package org.artifactory.client.impl

import com.fasterxml.jackson.core.type.TypeReference
import groovy.json.JsonSlurper
import org.artifactory.client.Security
import org.artifactory.client.model.User
import org.artifactory.client.model.builder.SecurityBuilders
import org.artifactory.client.model.builder.impl.SecurityBuildersImpl
import org.artifactory.client.model.impl.UserImpl

/**
 *
 * Date: 10/18/12
 * Time: 9:59 AM
 * @author freds
 */
class SecurityImpl implements Security {
    private ArtifactoryImpl artifactory

    static private SecurityBuilders builders = SecurityBuildersImpl.create()

    SecurityImpl(ArtifactoryImpl artifactory) {
        this.artifactory = artifactory
    }

    @Override
    SecurityBuilders builders() {
        return builders
    }

    @Override
    Collection<String> userNames() {
        String allUsers = artifactory.getText(SECURITY_USERS_API)
        JsonSlurper slurper = new JsonSlurper()
        def users = slurper.parseText(allUsers)
        users.collect { it.name }
    }

    @Override
    User user(String name) {
        artifactory.getJson("${SECURITY_USERS_API}/$name", new TypeReference<UserImpl>() {})
    }

    @Override
    void createOrUpdate(User user) {
        artifactory.put("${SECURITY_USERS_API}/${user.name}", [:], user, [:])
    }

    @Override
    String deleteUser(String name) {
        artifactory.delete("${SECURITY_USERS_API}/$name")
    }
}
