pipeline {
    agent any
    stages {
        stage("build") {
            steps {
                echo 'Building..'
            }
        }
        stage("tests") {
            steps {
                echo 'Testing..'
            }
        }
        stage("deploy") {
             steps {
                 echo 'Deploying....'
             }
        }
    }
}