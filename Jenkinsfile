node {
   def mvnHome
   stage('CheckOut') { // for display purposes
      // Get some code from a GitHub repository
      git 'https://github.com/Rule1001/React_Java_Back_end.git'
      // Get the Maven tool.
      // ** NOTE: This 'M3' Maven tool must be configured
      // **       in the global configuration.
   }
   stage('Lets Go') {
      // Run the maven build
      if (isUnix()) {
         sh "mvn -Dmaven.test.failure.ignore clean package"
      } else {
         bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean package/)
      }
   }
   stage('Results') {
      junit '**/target/surefire-reports/TEST-*.xml'
      archive 'target/*.jar'
   }
}