pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                echo 'Building the application...'
				git (credentialsId: '6acdb56d-5885-42c7-b5f4-98c4ff4e5c1a', url: 'https://github.com/manishjain5238/testing/', branch: 'master')
            }
        }
		
		stage('mvn package') {
			steps {
				echo 'Packaging the application...'
				sh 'mvn clean package -DskipTests=true'
			}
		}
		
		stage('mvn test') {
			steps {
				echo 'Testing the application...'
				sh 'mvn test'
			}
		}
		
		stage('build docker image') {
			steps {
				echo 'Building docker image...'
				sh 'docker build -t manishjain5238/testing:2.0.0 .'
			}
		}
		
		stage('push docker image') {
			steps {
				echo 'Pushing docker image...'
				sh 'docker push manishjain5238/testing:2.0.0'
			}
		}
		
		stage('Run container on dev server') {
			steps {
				echo 'Running container on dev server...'
				sh 'docker run -d -p 9090:8080 --name testing manishjain5238/testing:2.0.0'
			}
		}
    }
}
