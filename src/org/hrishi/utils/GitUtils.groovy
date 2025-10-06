package org.hrishi.utils

class GitUtils {

    static void cloneRepo(def script, String repoUrl, String branch, String credId) {
        script.echo "Cloning repository: ${repoUrl}, branch: ${branch}"
        script.checkout([
            $class: 'GitSCM',
            branches: [[name: "*/${branch}"]],
            doGenerateSubmoduleConfigurations: false,
            extensions: [],
            userRemoteConfigs: [[
                url: repoUrl,
                credentialsId: credId
            ]]
        ])
        script.echo "Repository cloned successfully!"
    }

     static void pushChanges(def script, String commitMsg, String branch, String credId, String email) {
        script.echo "Pushing changes to branch: ${branch}"

        script.withCredentials([script.usernamePassword(credentialsId: credId,
                usernameVariable: 'GIT_USER', passwordVariable: 'GIT_PASS')]) {
            script.sh """
                git config user.email "${email}"
                git config user.name "${GIT_USER}"
                git add .
                git commit -m "${commitMsg}" || echo "No changes to commit"
                git push https://${GIT_USER}:${GIT_PASS}@\$(git remote get-url origin | sed 's/https:\\/\\///') ${branch}
            """
        }
        script.echo "Changes pushed successfully!"
    }

}
