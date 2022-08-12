# grails-aws-environment-manager
Grails Application for starting an environment of EC2 instances for just the specified duration and then shutting them down


You can specify, however long the systems should be online, when the time is due it warns that the environment (all the EC2 instances attached to that env)  are going to be shut down, if the duration is not incremented, it will shut down all the instances.

Lane here is equivalent to Environment.

This version doesn't support authentication. It should be in next version

It has slack integration, currently it justs notifies on Slack. and one can interact with the app through slack to start, increment and stop environment
