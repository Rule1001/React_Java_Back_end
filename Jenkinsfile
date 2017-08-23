node {
   stage('Preparation') {
      git 'https://github.com/Rule1001/React_Java_Back_end.git'
   }
   stage('Clean') {
      sh "mvn -Dmaven.test.failure.ignore clean"
   }
   stage('Package') {
      sh "mvn -Dmaven.test.failure.ignore package"
   }

   stage('Docker Build') {
      sh "docker build -t myapp ."
   }
}
