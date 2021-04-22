job('ejemplo-2-job-DSL') {
	description('Job DSL de ejemplo para Jenkins')
  scm {
    git('https://github.com/EveFer/jenkins-jobs.git', 'main') { node -> 
      node / gitConfigName('EveFer')
      node / gitConfigEmail('fernandapalacios.dev@gmail.com')
    }
  }
  parameters {
  	stringParam('nombre', defaultValue='Fernanda', description='Parametro de nombre')
    choiceParam('planeta', ['Marte', 'Mercurio', 'Tierra', 'Neptuno', 'Jupiter'])
    booleanParam('agente', false)
  }
  triggers {
  	cron('H/7 * * * *')
  }
  steps {
  	shell("bash jobscript.sh")
  }
  publishers {
  	mailer('fernanda@toroto.mx', true, true)
    slackNotifier {
      notifyAborted(true)
      notifyEveryFailure(true)
      notifyNotBuilt(false)
      notifyUnstable(false)
      notifyBackToNormal(true)
      notifySuccess(false)
      notifyRepeatedFailure(false)
      startNotification(false)
      includeTestSummary(false)
      includeCustomMessage(false)
      customMessage(null)
      sendAs(null)
      commitInfoChoice('NONE')
      teamDomain(null)
      authToken(null)
    }
  }
}
