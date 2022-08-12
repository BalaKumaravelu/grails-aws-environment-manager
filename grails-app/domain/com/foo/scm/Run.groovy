package com.foo.scm

class Run {
    Date startTime
    Date stopTime
    Date shutdownTime
    String startUser
    String stopUser
    String shutdownUser

    static belongsTo = [lane: Lane]

    static constraints = {
    }


    Run()
    {
        this.startTime = new Date()
        this.stopTime = new Date()
        this.shutdownTime = new Date()
        this.startUser="default"
        this.stopUser="default"
        this.shutdownUser="default"
    }

    Run(String startUser)
    {
        this()
        /*
        this.startTime = new Date()
        this.stopTime = new Date()
        this.shutdownTime = new Date()
        */
        this.startUser= startUser
        this.stopUser="default"
        this.shutdownUser="default"
    }

}
