def call(String Project, String ImageTag, String DockerUser){
  withCredentials([usernamePassword(credentialsId:"dockerCred", usernameVariable:"dockerHubUser", passwordVariable:"dockerHubPass")]){
    sh "docker login -u ${DockerUser} -p ${dockerHubPass}"  
  }
  sh "docker push ${DockerUser}/${Project}:${ImageTag}"
}
