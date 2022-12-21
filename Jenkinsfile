node {  
    stage('git') { 
      git branch: 'main', credentialsId: 'virtusgit', url: 'https://gitlab.com/virtus1er/test_java.git'
    }
    stage('Build jar') {
         withMaven(jdk: 'jdk8', maven: 'mav', tempBinDir: '') {
            sh "mvn clean verify"
          }
          dir('target') { archive '*.jar' }
          stash name : 'binary', includes : 'target/*.jar'

    }
    stage('docker build images') {
      image = docker.build("virtus/mavvirtus", '-f Dockerfile .')
      sh 'docker images'
      print(image)
    }

    stage('test') {
       node {
              container = image.run('-P ')
              ip = container.port(8080)
            }
            variable = 1

            try {
                variable = input message: "Is http://${ip} ok?", ok: 'Publish'
            } finally {
                node {
                    container.stop()

                        if (variable == 1){
                            sh 'docker rmi virtus/mavvirtus:latest'
                            sh 'docker images '
                        }
                    }
            }

        }

    stage('pusk images virtus'){
           sh 'docker login -u virtus -p "bes@83HUB"'
      	   sh 'docker push virtus/mavvirtus:latest'
    }

     stage('clean images virtus ') {
        sh 'docker rmi virtus/mavvirtus:latest'
        sh 'docker images'
     }

     stage("fin deploy docker prod"){

        sh 'eval `ssh-agent -s` && ssh-add ~/.ssh/opom'
        sh 'ansible-playbook -b -i /home/ubuntu/prod.inv /home/ubuntu/cdihm.yml'

     }

}
