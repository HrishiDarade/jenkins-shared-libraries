def call(String SonarQubeAPI, String Projectname, String ProjectKey){
    withSonarQubeEnv("${SonarQubeAPI}"){
        sh """
        ${SONAR_HOME}/bin/sonar-scanner \
            -Dsonar.projectKey=${ProjectKey} \
            -Dsonar.projectName=${Projectname} \
            -Dsonar.java.binaries=target/classes \
            -X
        """
    }
}
