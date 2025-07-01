pipeline {
    agent any

    environment {
        DOCKER_IMAGE_NAME = 'israel2403/orders'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/israel2403/saga-pattern.git'
            }
        }

        stage('Build and Test') {
            dir('orders') {
                steps {
                    sh 'mvn clean verify'
                }
            }
        }

        stage('Code Analysis (Jacoco + SpotBugs)') {
            dir('orders') {
                steps {
                    sh 'mvn spotbugs:check'
                    sh 'mvn jacoco:report'
                }
            }
        }

        stage('Build Docker Image') {
            dir('orders') {
                steps {
                    script {
                        def tag = sh(script: "git rev-parse --short HEAD", returnStdout: true).trim()
                        env.IMAGE_TAG = "${DOCKER_IMAGE_NAME}:${tag}"
                        sh "docker build -t ${IMAGE_TAG} ."
                    }
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'Dockerhub', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    sh 'echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin'
                    sh "docker push ${IMAGE_TAG}"
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

            // Optional clean-up
            script {
                if (env.IMAGE_TAG) {
                    sh "docker rmi ${IMAGE_TAG} || true"
                }
            }
        }
    }
}
