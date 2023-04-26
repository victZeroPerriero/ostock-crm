pipeline {
node {
  stage ('Build') {
    git url: 'https://github.com/cyrille-leclerc/multi-module-maven-project'
    withMaven {
      sh "mvn clean verify"
    } // withMaven will discover the generated Maven artifacts, JUnit Surefire & FailSafe reports and FindBugs reports
  }
}
    agent any
  environment {
    MAVEN_ARGS=" -e clean install"
    registry = ""
    dockerContainerName = 'licenseapi'
    dockerImageName = 'license-api'
  }
  stages {
    stage('Build') {
       steps {
   withMaven(maven: 'Jenkins-Maven') {
            sh "mvn ${MAVEN_ARGS}"
        }
       }
    }
 stage('clean container') {
      steps {
       sh 'docker ps -f name=${dockerContainerName} -q | xargs --no-run-if-empty docker container stop'
       sh 'docker container ls -a -fname=${dockerContainerName} -q | xargs -r docker container rm'
       sh 'docker images -q --filter=reference=${dockerImageName} | xargs --no-run-if-empty docker rmi -f'
      }
    }
  stage('docker-compose start') {
      steps {
       sh 'docker compose up -d'
      }
    }
  }
}