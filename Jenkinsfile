node {
   def mvnHome
   stage('CheckOut') { // for display purposes
      // Get some code from a GitHub repository
      git 'https://github.com/Rule1001/React_Java_Back_end.git'
      // Get the Maven tool.
      // ** NOTE: This 'M3' Maven tool must be configured
      // **       in the global configuration.
   }
   stage('Clean') {
      // Run the maven build
      if (isUnix()) {
         sh "mvn -Dmaven.test.failure.ignore clean"
      } else {
         bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean package/)
      }
   }
      stage('compile') {
         // Run the maven build
         if (isUnix()) {
            sh "mvn -Dmaven.test.failure.ignore compile"
         } else {
            bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean package/)
         }
      }
       stage('test') {
               // Run the maven build
          if (isUnix()) {
              sh "mvn -Dmaven.test.failure.ignore test"
          } else {
              bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean package/)
          }
       }

       stage('Docker Build') {
       if (isUnix()) {
       sh "docker build -t myproperties ."
       } else {
               bat(/"${mvnHome}\bin\mvn" -Dmaven.test.failure.ignore clean package/)
       }
    }
}
