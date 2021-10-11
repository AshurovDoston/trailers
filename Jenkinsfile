pipeline {
    agent any
    tools {
        maven 'maven-3.6'
        jdk 'java-11'
    }
    
        stage('build jar') {
            steps {
                script {
                    echo "Building the application..."
                    sh 'mvn clean package'
                }
            }
        }
        
        stage('HTML report') {
            steps {
                script {
                    publishHTML([allowMissing: false, 
                    alwaysLinkToLastBuild: true, 
                    keepAll: true, 
                    reportDir: '/var/lib/jenkins/workspace/jma-trailers', 
                    reportFiles: 'index.html', 
                    reportName: 'HTML Report', 
                    reportTitles: ''])
                    }
                }
            }
        }
    
}
