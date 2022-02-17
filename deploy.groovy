def utils

pipeline {
  agent any

  options {
    buildDiscarder logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '', numToKeepStr: '3')
  }

  parameters {
    choice choices: ['stage', 'promote', 'stage_and_promote', 'deploy_prod'], description: 'build mode', name: 'mode'
    extendedChoice(description: 'repositories to release', multiSelectDelimiter: ',', name: 'repos',
                    quoteValue: false, saveJSONParameterToFile: false, type: 'PT_MULTI_SELECT', visibleItemCount: 5,
                    value: 'udm,util,onboarding,admin,contracts,deliveries,integration,overview,settlements,approuter,ui')
    string defaultValue: '1.0.0', description: 'version to release', name: 'version'
  }

  stages {
    stage('Init') {
      steps {
        script {
          utils = load 'utils.groovy'
        }
      }
    }
  }
}
