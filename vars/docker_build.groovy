def call(String ProjectName, String ImageTag){
  sh "docker build -t ${ProjectName}:${ImageTag} ."
}
