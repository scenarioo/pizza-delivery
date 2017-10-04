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
                try {
                    gradle 'clean build'
                } finally {
                    junit '**/build/test-results/*.xml'
                }
            }
        }

        stage('Deploy Scenarioo Test-Results') {
            dir('test/build') {
                zip archive: true, dir: 'scenariooDocumentation', zipFile: 'documentation.zip'
            }

        }
     }
}
