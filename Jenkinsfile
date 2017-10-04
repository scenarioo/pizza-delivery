def gradle(tasks) {
	 sh "./gradlew --debug $tasks"
}

timestamps {
    node {
	    stage('Checkout') {
            checkout scm
        }
        
        stage('Build and Run Tests') {
            dir('test'){
                gradle 'clean build'
            }
        }
     }
}