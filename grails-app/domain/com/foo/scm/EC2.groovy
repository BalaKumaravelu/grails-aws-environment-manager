package com.foo.scm

import com.sun.org.apache.xpath.internal.operations.Bool

enum Status{
    Running,
    Stopped,
    UnKnown,
    Shutdown
}

class EC2 {
    String name
    String alias
    String description
    String instanceId
    String ipAddress
    Status status

    static belongsTo = [lane:Lane]

    static constraints = {
        //status display: false //Disabling it in the view itself
    }

    static mapping = {
        sort name:"asc"
    }

    EC2()
    {
        name="name"
        alias="alias"
        description = "EC2 Description : "
        ipAddress="127.0.0.1"
        instanceId=""
        status = Status.Shutdown
    }





}
