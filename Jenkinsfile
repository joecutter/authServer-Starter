node {

    checkout scm
	def dockerImage
	def containerName = "authServer"
	def image = "mxc/authServer"
	def port = "9111"

 stage('PULLING CHANGES'){
 		checkout([$class: 'GitSCM',
 		branches: [[name: '*/dev']],
 		doGenerateSubmoduleConfigurations: false,
 		extensions: [],
 		submoduleCfg: [],
 		userRemoteConfigs: [[credentialsId: 'github', url: 'https://github.com/PrimeDev-team/mxc-auth-server.git']]])
 	}

    stage('Build app') {
 		withMaven(maven : 'maven-tool') {
                 sh 'mvn clean && mvn package'
        }
 	}

 	stage('Build image') {
 		dockerImage = docker.build("${image}:${env.BUILD_ID}")
 	}

     stage('Push image') {
 	  docker.withRegistry('https://registry.hub.docker.com', 'Docker') {
 		dockerImage.push()
 	  }
 	}

 	stage('Remove Unused docker image') {
      	sh "docker rmi $image:$BUILD_ID"
     }

     stage('Run Container') {
 		try{
 			sh "docker rm -f $containerName"
 			sh "docker run --rm --name $containerName -d -p $port:$port $image:$BUILD_ID"
 		}catch(Exception e){
 			sh "docker run --rm --name $containerName -d -p $port:$port $image:$BUILD_ID"
 		}
     }
}