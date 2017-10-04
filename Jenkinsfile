def gradle(tasks) {
	 sh "./gradlew --debug $tasks"
}

timestamps {
    node {
        stages {
            stage('Checkout') {
                checkout scm
            }

            stage('Build and Run Tests') {
                dir('test'){
                    gradle 'clean build'
                }
            }
        }
        post {
            always {
                junit '**/build/test-results/*.xml'
            }
        }
     }
}