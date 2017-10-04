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
                sh "Xvfb :98 &"
                sh "export DISPLAY=:98"
                gradle 'clean build'
            }
        }
     }
}