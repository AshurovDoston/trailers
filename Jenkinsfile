pipeline {
    agent any
    tools {
        maven 'maven-3.6'
        jdk 'java-11'
    }
    stages {
        stage('increment version') {
            steps{
                script{
                    echo 'incrementing app verion...'

                    sh 'mvn build-helper:parse-version versions:set \
                        -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
                        versions:commit'
                    //increment the current version
                    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
                    def version = matcher[1][1]
                    env.IMAGE_VERSION = "$version-$BUILD_NUMBER"
                }
            }
        }
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
                    echo "Building the docker image..."
                    withCredentials([usernamePassword(credentialsId: 'id_DockerHub', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                        sh "docker build -t crasoftinc/trailers:${IMAGE_VERSION} ."
                        sh "docker build -t crasoftinc/trailers:latest ."
                        sh "echo $PASS | docker login -u $USER --password-stdin"
                        sh "docker push crasoftinc/trailers:${IMAGE_VERSION}"
                        sh "docker push crasoftinc/trailers:latest"
                    }
                }
            }
        }
        stage('deploy') {
            steps {
                script {
                    echo "Deploying the application..."
                    //def IMAGE_NAME = "crasoftinc/trailers:${IMAGE_VERSION}"
                    def shellCmd = "bash ./jma-trailers-server-cmds.sh"
                    sshagent(['compose-server-key']){
                        sh "scp jma-trailers-server-cmds.sh root@137.184.20.170:/root"
                        sh "ssh -o StrictHostKeyChecking=no root@137.184.20.170 ${shellCmd}"
                    }
                }
            }
        }
        stage('commit version update') {
            steps {
                script {
                    sshagent(['cbc893d8-a634-4c7f-a2da-cb7003cb6403']) {
                        sh 'git config --global user.email "support@crasoftinc.com"'
                        sh 'git config --global user.name "crasoft"'


                        sh "git remote set-url origin git@github.com:Crasoft-Inc/jma-trailers.git"
                        sh 'git add .'
                        sh 'git commit -m "ci: version bump"'
                        sh 'git push origin HEAD:development'
                    }
                }
            }
        }

        stage('HTML report') {
            steps {
                script {
                    publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: false, reportDir: 'HTML report', reportFiles: 'index.html', reportName: 'HTML Report', reportTitles: ''])
                    }
                }
            }
        }
    }
}
