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
                try {
                    gradle 'clean build'
                } finally {
                    junit '**/build/test-results/*.xml'
                }
            }
        }

        stage('Deploy Scenarioo Test-Results') {
            dir('test/build') {
                zip dir: 'scenariooDocumentation', zipFile: 'documentation.zip'
                withCredentials([usernameColonPassword(credentialsId: 'SCENARIOO_DEMO',variable: 'USERPASS')]) {
                    sh 'curl -u $USERPASS -F file=@documentation.zip http://demo.scenarioo.org/scenarioo-master/rest/builds'
                }
            }

        }
     }
}
