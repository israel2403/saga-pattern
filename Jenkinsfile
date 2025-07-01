pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = credentials('Dockerhub')
        IMAGE_NAME = "israel2403/orders"
    }

    stages {
        stage('Build and Test') {
            steps {
                dir('orders') {
                    sh 'mvn clean verify'
                }
            }
        }

        stage('Code Analysis (Jacoco + SpotBugs)') {
            steps {
                dir('orders') {
                    sh 'mvn spotbugs:spotbugs'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                dir('orders') {
                    script {
                        def tag = sh(script: "git rev-parse --short HEAD", returnStdout: true).trim()
                        sh "docker build -t ${IMAGE_NAME}:${tag} ."
                        sh "echo ${DOCKERHUB_CREDENTIALS_PSW} | docker login -u ${DOCKERHUB_CREDENTIALS_USR} --password-stdin"
                        sh "docker push ${IMAGE_NAME}:${tag}"
                        sh "docker rmi ${IMAGE_NAME}:${tag}"
                    }
                }
            }
        }
    }

    post {
        always {
            dir('orders') {
                junit 'target/surefire-reports/*.xml'
                jacoco execPattern: 'target/jacoco.exec'
            }
        }
    }
}
