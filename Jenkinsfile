pipeline {
    agent any
      tools {
        maven 'Maven_3.9.5'
      }
      stages {
        stage ('Building') {
          steps {
           echo 'Building..'
            sh 'mvn clean package'
          }
        }
        stage('Testing') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploying') {
             steps {
                 echo 'Deploying....'
                 script {
                           deploy adapters: [tomcat9(credentialsId: 'tomcat_credential', path: '', url: 'http://dayal-test.letspractice.tk:8081')], contextPath: '/pipeline', onFailure: false, war: 'webapp/target/*.war'
                         }
             }
        }
    }
}