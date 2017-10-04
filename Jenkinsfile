def gradle(tasks) {
	 sh "test/gradlew --info $tasks"
}

timestamps {
    node {
	    stage('Checkout') {
            checkout scm
        }
        
        stage('Build') {
            gradle 'build'
        }
     }
}