package com.foo.scm

class Lane {
    String name
    String alias
    String description
    String slackChannels
    Status status

    static hasMany = [ec2s:EC2,runs:Run]

    static mapping = {
        sort name:"asc"
    }

    static constraints = {
        id display: false
    }

    Lane()
    {
        name="name"
        alias="alias"
        description = "Lane Description : "
        slackChannels="bothome"
        status = Status.Shutdown
    }
}
