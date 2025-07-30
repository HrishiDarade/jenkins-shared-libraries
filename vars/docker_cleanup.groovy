def call(String Project, String ImageTag){
  withCredentials([usernamePassword(
    credentialsId: "dockerCred",
    usernameVariable: "dockerHubUser",
    passwordVariable: "dockerHubPass"
  )]){
    sh "docker rmi ${dockerHubUser}/${Project}:${ImageTag}"
  }
}
