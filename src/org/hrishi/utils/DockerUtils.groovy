package org.hrishi.utils

class DockerUtils {
    
    //static build method 
    static void buildImage(def script, String project, String imagetag) {
        script.echo "Building the Docker Image: ${project}:${imagetag}"
        script.sh "docker build -t ${project}:${imagetag} ."
    }
    //static push method
    static void pushImage (def script, String project, String imagetag, String credId = 'dockerCred') {
        script.withCredentials([script.usernamePassword(credentialsId: credId, usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
            script.sh """#!/bin/bash -xe
            echo ${DOCKER_PASS} | docker login -u ${DOCKER_USER} --password-stdin
            docker image tag ${project}:${imagetag} ${DOCKER_USER}/${project}:${imagetag}
            docker push ${DOCKER_USER}/${project}:${imagetag}
            """
        }
    }

}