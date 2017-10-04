def gradle(tasks) {
	 sh './gradlew --info $tasks'
}

timestamps {
    node {
	    stage('Checkout') {
            checkout scm
        }
        
        stage('Build') {
            dir('test'){
                gradle 'build'
            }
        }
     }
}