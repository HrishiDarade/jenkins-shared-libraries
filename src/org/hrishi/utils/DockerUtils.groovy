package org.hrishi.utils

class DockerUtils {
    
    //static build method 
    static void buildImage(def script, String project, String tag) {
        script.echo "Building the Docker Image: ${project}:${tag}"
        script.sh "docker build -t ${project}:${tag}"
    }
    //static push method
    static void pushImage (def script, String project, String tag, String credId = 'dockerCred') {
        script.withCredentials([script.usernamePassword(credentialsId: credId, usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
            script.sh '''#!/bin/bash -xe
            docker login -u ${DOCKER_USER} -p ${DOCKER_PASS}
            docker image tag ${project}:${tag} ${DOCKER_USER}/${project}:${tag}
            docker push ${DOCKER_USER}/${project}:${tag}
            '''
        }
    }

}