def gradle(tasks) {
	 sh "./gradlew --info -s --no-daemon $tasks"
}

def getEncodedBranchName() {
     // just to make sure that slashes in branch names cause no problems
	 String branchName = "${env.BRANCH_NAME}"
	 return branchName.replace('/', '_')
}

/**
 * Output summary message to jenkins build page
 * with nice Scenarioo logo icon and styling
 * using https://wiki.jenkins.io/display/JENKINS/Summary+Display+Plugin
 */
def reportJenkinsSummary(summaryFile, contentHtml) {
    def scenariooIconUrl = "https://raw.githubusercontent.com/scenarioo/scenarioo/develop/scenarioo-client/resources/LogoScenariooBlackQuadraticSmall.png"
    def scenariooIconHtml = "<img src=\"${scenariooIconUrl}\" style=\"width: 48px; height: 48px; \" class=\"icon-scenarioo icon-xlg\">"
    def contentHtmlWithIcon = "<table><tbody><tr><td>${scenariooIconHtml}</td><td style=\"vertical-align:middle\">${contentHtml}</td></tr></tbody></table>"
    def contentCss = ""
    def overruleUglyPluginStyleCss = ".summary_report_table {border:none;border-spacing:0px;} .summary_report_table td {border:none;border-spacing:0px;}"
    def htmlSnippet = "<style>${overruleUglyPluginStyleCss} ${contentCss}</style> ${contentHtmlWithIcon}"
    sh "echo '<section><table><tr><td width=\"; margin:0px; padding:0px;\"><![CDATA[ ${htmlSnippet} ]]></td></tr></table></section>' > ${summaryFile}"
    archive summaryFile
    step([$class: 'ACIPluginPublisher', name: summaryFile, shownOnProjectPage: true])
}

/**
 * Output summary message on jenkins build page
 * with the link to scenarioo reports (self docu)
 * for current build run
 */
def reportJenkinsSummaryScenariooReports(scenariooUrl, branchId, buildId) {
    def scenariooReportUrl = "${scenariooUrl}/#/?branch=${branchId}&build=${buildId}"
    echo "See Scenarioo Test Reports for this build: ${scenariooReportUrl}"
    def title = "<h2>Scenarioo Reports</h2>"
    def summary = "<a target=\"_blank\" href=\"${scenariooReportUrl}\">Scenarioo Test Reports for this build</a>"
    reportJenkinsSummary("scenarioo-reports.jenkins-summary.xml", "${title} ${summary}")
}

properties([
		  pipelineTriggers([
					 [$class: 'GitHubPushTrigger']
		  ])
])

timestamps {

    node {

	    stage('Clean Checkout') {
            checkout scm
            // Clean build: ensure no unversioned files disturbing the build
            sh "git clean -x -d -f"
        }

        def encodedBranchName = getEncodedBranchName()

        stage('Build, Test & Deploy Reports') {

            try {

                gradle 'clean build'

            } finally {

                // Junit Test Reports
                junit '**/build/test-results/*.xml'

                // Deploy scenarioo test reports in any case, also in case of test failures (inside finally!)
                dir('build') {
                    zip dir: 'scenariooDocumentation', zipFile: 'documentation.zip'
                    withCredentials([usernameColonPassword(credentialsId: 'SCENARIOO_DEMO',variable: 'USERPASS')]) {
                        // Only for the master branch the self docu is deployed to scenarioo-master demo
                        // for all others: to scenarioo-develop demo
                        def docuDeploymentScenariooInstance = encodedBranchName == "master" ? "master" : "develop"
                        def scenariooUrl = "http://demo.scenarioo.org/scenarioo-${docuDeploymentScenariooInstance}"
                        sh "curl -u $USERPASS -F file=@documentation.zip $scenariooUrl/rest/builds"
                        reportJenkinsSummaryScenariooReports(scenariooUrl, "pizza-delivery-${encodedBranchName}", "build-${env.BUILD_NUMBER}")
                    }
                }

                archiveArtifacts artifacts: 'build/documentation.zip'

            }
        }

        stage('Cleanup Scenarioo Report Data') {
            // Remove old scenarioo data (orphaned branches & old builds)
            sh './ci/cleanupScenariooReports.sh'
        }

    }
}
