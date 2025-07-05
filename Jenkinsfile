pipeline {
  agent any  // Use Jenkins host environment (Docker CLI is available)

  environment {
    DOCKER_IMAGE = "yourdockeruser/orders"
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
          junit 'target/surefire-reports/*.xml'
          jacoco execPattern: 'target/jacoco.exec'
        }
      }
    }

    stage('Build Docker Image') {
      steps {
        script {
          def tag = sh(script: "git rev-parse --short HEAD", returnStdout: true).trim()
          dir('orders') {
            sh "docker build -t $DOCKER_IMAGE:${tag} ."
            withCredentials([usernamePassword(credentialsId: 'Dockerhub', usernameVariable: 'DOCKERHUB_USER', passwordVariable: 'DOCKERHUB_PASSWORD')]) {
              sh "echo $DOCKERHUB_PASSWORD | docker login -u $DOCKERHUB_USER --password-stdin"
              sh "docker push $DOCKER_IMAGE:${tag}"
            }
          }
        }
      }
    }
  }

  post {
    always {
      node {
        dir('orders') {
          junit 'target/surefire-reports/*.xml'
          jacoco execPattern: 'target/jacoco.exec'
        }
      }
    }
  }
}
