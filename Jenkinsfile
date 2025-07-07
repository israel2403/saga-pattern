pipeline {
  agent none

  environment {
    DOCKER_IMAGE = "israelhf24/orders"
  }

  stages {
    stage('Build and Test (All Modules)') {
      agent {
        docker {
          image 'maven:3.9.6-eclipse-temurin-17'
        }
      }
      steps {
        sh 'mvn clean install'
      }
    }

    stage('Code Analysis (Jacoco + SpotBugs)') {
      agent {
        docker {
          image 'maven:3.9.6-eclipse-temurin-17'
        }
      }
      steps {
        dir('orders') {
          junit 'target/surefire-reports/*.xml'
          jacoco execPattern: 'target/jacoco.exec'
        }
      }
    }

    stage('Build Docker Image') {
      agent any
      steps {
        script {
          def tag = sh(script: "git rev-parse --short HEAD", returnStdout: true).trim()
          dir('orders') {
            sh "docker build -t $DOCKER_IMAGE:${tag} ."

            // 🟢 Inject secrets from Vault dynamically
            withVault([vaultSecrets: [[path: 'secret/dockerhub', secretValues: [
              [envVar: 'DOCKERHUB_USER', vaultKey: 'username'],
              [envVar: 'DOCKERHUB_PASSWORD', vaultKey: 'password']
            ]]]]) {
              sh "echo $DOCKERHUB_PASSWORD | docker login -u $DOCKERHUB_USER --password-stdin"
              sh "docker push $DOCKER_IMAGE:${tag}"
            }
          }
        }
      }
    }

    stage('Post Actions') {
      agent {
        docker {
          image 'maven:3.9.6-eclipse-temurin-17'
        }
      }
      steps {
        dir('orders') {
          junit 'target/surefire-reports/*.xml'
          jacoco execPattern: 'target/jacoco.exec'
        }
      }
    }
  }
}
