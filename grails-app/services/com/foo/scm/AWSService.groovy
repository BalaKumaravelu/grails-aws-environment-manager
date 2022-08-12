package com.foo.scm

import grails.gorm.transactions.Transactional
@Transactional
class AWSService {
    static int i=0

    def serviceMethod() {

    }

    public Status GetLaneStatus(Lane lane)
    {
        // Temp Dev Testing only
        i++

        if(i%3==0)
        {
            return Status.Running
        }
        else if(i%2==0)
        {
            return Status.Shutdown
        }
        else
        {
            return Status.UnKnown
        }
    }

    public int Start(EC2 ec2)
    {
        log.info("-----------------------------------------------------------------------------")
        log.info("Start Instance ${ec2.name}")
        log.info("-----------------------------------------------------------------------------")
        int hasStarted = 1

        log.info("Starting EC2 Instance - Name : ${ec2.name} - Alias : ${ec2.alias} -  InstanceId : ${ec2.instanceId} -Status : ${ec2.status}")
        log.info("Starting Command line  : C:\\Program Files\\Amazon\\AWSCLI\\aws.exe ec2 start-instances --instance-ids ${ec2.instanceId}")

        def process = Runtime.getRuntime().exec("C:\\Program Files\\Amazon\\AWSCLI\\aws.exe ec2 start-instances --instance-ids ${ec2.instanceId}")

        process.waitFor()

        process.consumeProcessOutput(System.out, System.err)

        if(process.exitValue())
        {
            println "Error : "
            hasStarted=-1
            println process.errorStream.getText()
        }
        else
        {
            hasStarted = 0
            log.info( "Successfully started")
        }

        return hasStarted
    }


    public int Stop(EC2 ec2)
    {
        log.info("-----------------------------------------------------------------------------")
        log.info("Stop Instance ${ec2.name}")
        log.info("-----------------------------------------------------------------------------")

        int hasStopped = 1
        log.info("Stopping down EC2 Instance - Name : ${ec2.name} - Alias : ${ec2.alias} - Status : ${ec2.status}")
        log.info("Stopping Command line  : C:\\Program Files\\Amazon\\AWSCLI\\aws.exe ec2 stop-instances --instance-ids ${ec2.instanceId}")

        def process = Runtime.getRuntime().exec("C:\\Program Files\\Amazon\\AWSCLI\\aws.exe ec2 stop-instances --instance-ids ${ec2.instanceId}")

        process.waitFor()

        process.consumeProcessOutput(System.out, System.err)

        if(process.exitValue())
        {
            println "Error : "
            hasStopped=-1
            println process.errorStream.getText()

        }
        else
        {
            hasStopped = 0
            log.info("Successfully stopped")
        }

        return hasStopped
    }


    // Waiting for instance to start and stop, may not be ideal, especially if we have to do that for 10-20 instances. better for whoever uses it to wait 2 minutes before start using it
    private int WaitForInstanceState(String instanceId, String state)
    {
        log.info("-----------------------------------------------------------------------------")
        log.info("WaitForInstanceState ${instanceId} - State=${state}")
        log.info("-----------------------------------------------------------------------------")

        int isSuccessful = -3

        for(int i=0; i<11; i++)
        {
            String currentState = GetInstanceState(instanceId)
            if(currentState.equalsIgnoreCase(state))
            {
                log.info("EC2 Instance has reached the expected State = ${state} - Current State - ${currentState}")
                isSuccessful = 0
                break;
            }
            else
            {
                log.info("Expected State = ${state} - Current State - ${currentState}")
                log.info("Waiting for a minute ")
                sleep(30000)
            }
        }
        return isSuccessful
    }


    private String GetInstanceState(String instanceId)
    {
        log.info("-----------------------------------------------------------------------------")
        log.info("GetInstanceState ${instanceId}")
        log.info("-----------------------------------------------------------------------------")

        String instanceState = "unknown"

        log.info("Getting instance state for InstanceId : ${instanceId}")
        String commandLine = "C:\\Program Files\\Amazon\\AWSCLI\\aws.exe ec2 describe-instances --instance-ids ${instanceId} --query \"Reservations[*].Instances[*].[State.Name]\" --output text"
        log.info("Starting Command line  : ${commandLine}")

        instanceState = Runtime.getRuntime().exec(commandLine).text
        log.info("Current Instance State - InstanceId : ${instanceId} - Current State : ${instanceState}")

        return instanceState
    }

}
