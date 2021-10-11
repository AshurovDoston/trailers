pipeline {
    agent any
    tools {
        maven 'maven-3.6'
        jdk 'java-11'
    }
    stages {
        stage('build jar') {
            steps {
                script {
                    echo "Building the application..."
                    sh 'mvn clean package'
                }
            }
        }
        stage("build image") {
            steps {
                script {
                    echo "Building the docker image... "
                }
            }
        }
        stage('deploy') {
            steps {
                script {
                    echo "Deploying the application... "
                }
            }
        }
        stage('commit version update') {
            steps {
                script {
                    echo "Commit Version"
                }
            }
        }

    }    
    
    
}