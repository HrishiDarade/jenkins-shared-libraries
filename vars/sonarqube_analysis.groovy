def call(String SonarQubeAPI, String Projectname, String ProjectKey){
    withSonarQubeEnv("${SonarQubeAPI}"){
        sh """
        cd spring-boot-server
        mvn clean install -DskipTests
        ${SONAR_HOME}/bin/sonar-scanner \
            -Dsonar.projectKey=${ProjectKey} \
            -Dsonar.projectName=${Projectname} \
            -Dsonar.java.binaries=target/classes \
            -X
        """
    }
}
