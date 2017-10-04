def gradle(tasks) {
	 sh "./gradlew --stacktrace $tasks"
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