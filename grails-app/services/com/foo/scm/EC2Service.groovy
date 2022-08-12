package com.foo.scm

import grails.gorm.transactions.Transactional
import java.util.Set

@Transactional
class EC2Service {
    AWSService awsService

    def serviceMethod() {

    }

    EC2Service(){
        awsService = new AWSService()
    }

    public int Start( Set ec2s)
    {
        log.info("-----------------------------------------------------------------------------")
        log.info("Starting All EC2 Instances")
        log.info("-----------------------------------------------------------------------------")

        int hasStarted = 0
        ec2s.each { ec2 ->
            hasStarted += Start(ec2)
        }

        return hasStarted
    }

    public int Start(EC2 ec2)
    {
        log.info("-----------------------------------------------------------------------------")
        log.info("Starting EC2 Instance : ${ec2.name}")
        log.info("-----------------------------------------------------------------------------")

        int hasStarted = 0
        hasStarted = awsService.Start(ec2)

        if(hasStarted == 0)
        {
            ec2.status = Status.Running
            log.info("Successfully started EC2 Instance : ${ec2.name}")
        }
        else
        {
            log.error("Failed to start EC2 Instance : ${ec2.name}")
        }

        if(!ec2.save(flush: true))
        {
            log.error ("ERROR : Cannot Save EC2 instance")
            lane.errors.allErrors.each {
                log.error(it)
            }
        }
        else
        {
            log.info("Saved EC2 instance")
        }

        log.info("Successfully started EC2 Instance : ${ec2.name}")

        return hasStarted
    }


    public int Shutdown( Set ec2s)
    {
        log.info("-----------------------------------------------------------------------------")
        log.info("Shutdown All EC2 Instances")
        log.info("-----------------------------------------------------------------------------")

        int hasShutdown = 0
        ec2s.each { ec2 ->
            hasShutdown += Shutdown(ec2)
        }

        return hasShutdown
    }


    public int Shutdown(EC2 ec2)
    {
        log.info("-----------------------------------------------------------------------------")
        log.info("Shutdown EC2 Instance : ${ec2.name}")
        log.info("-----------------------------------------------------------------------------")

        int hasShutdown = 0
        hasShutdown = awsService.Stop(ec2)

        if(hasShutdown == 0)
        {
            ec2.status = Status.Shutdown
            log.info("Successfully shutdown EC2 Instance : ${ec2.name}")
        }
        else
        {
            log.error("Failed to shutdown EC2 Instance : ${ec2.name}")
        }

        if(!ec2.save(flush: true))
        {
            log.error ("ERROR : Cannot Save EC2 instance")
            lane.errors.allErrors.each {
                log.error(it)
            }
        }
        else
        {
            log.info("Saved EC2 instance")
        }
        log.info("Successfully shutdown EC2 Instance : ${ec2.name}")
        return hasShutdown
    }

    public void Delete(Long id)
    {
        log.info("Deleting EC2 Instance - ID : ${id}")

        EC2 ec2 = EC2.get(id)
        ec2.delete(flush: true)
    }

}