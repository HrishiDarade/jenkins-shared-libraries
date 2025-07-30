def call(){
  dependencyCheck additionalArguments: '--scan ./', odcInstalltion: 'OWASP'
  dependencyCheckPublisher pattern: '**/dependency-check-report.xml'
}
