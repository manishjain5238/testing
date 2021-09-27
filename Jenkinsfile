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
    }
}
