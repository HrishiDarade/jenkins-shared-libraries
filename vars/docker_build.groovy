def call(String ProjectName, String ImageTag, String DockerHubUser){
  sh "docker build -t ${DokcerHubUser}/${ProjectName}:${ImageTag} ."
}
