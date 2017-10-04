def gradle(tasks) {
	 sh "./gradlew --info $tasks"
}

timestamps {
    node {
	    stage('Checkout') {
            checkout scm
        }
        
        stage('Build and Run Tests') {
            dir('test'){
                sh "export DISPLAY=:99"
                gradle 'clean build'
            }
        }
     }
}