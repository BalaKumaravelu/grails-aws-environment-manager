package com.foo.scm

import grails.gorm.transactions.Transactional
import grails.plugins.rest.client.RestBuilder

@Transactional
class LaneService {

    EC2Service ec2Service
    RunService runService
    SlackService slackService
    def grailsApplication

    private String slackUrl = "https://hooks.slack.com/services/SomeGuid/SomeGuid"

    /*
    Lane get(Serializable id)

    List<Lane> list(Map args)

    Long count()

    void delete(Serializable id)

    Lane save(Lane lane)
    */

    static int i=0

    def serviceMethod() {

    }

    LaneService()
    {
        ec2Service = new EC2Service()
        runService = new RunService()
        slackService = new SlackService()
    }

    def GetAllLanes()
    {
        UpdateLaneStatuses()
    }

    def UpdateLaneStatuses()
    {
        log.info "Updating lane statuses"

        Lane.list().each {lane ->

            log.info "Getting Lane status ${lane.name} ${lane.instanceId} ${lane.status}"
        }
    }

    def UpdateLaneStatus(Lane lane, int i)
    {
        log.info "Updating Status for Lane : ${lane.name}"
    }

    def Start(Long id, int duration) {
        log.info("-----------------------------------------------------------------------------")
        log.info("Starting Lane - Id : ${id}")
        log.info("-----------------------------------------------------------------------------")

        Lane lane = Lane.get(id)
        Start(lane, duration)
    }

    def Start(Lane lane, int duration) {
        log.info("-----------------------------------------------------------------------------")
        log.info("Starting Lane")
        log.info("-----------------------------------------------------------------------------")

        log.info("Lane Name : ${lane.name}")
        log.info("Lane Alias : ${lane.alias}")
        log.info("Lane Description : ${lane.description}")
        log.info("Lane Status : ${lane.status}")

        Run run = runService.Create("Bala", duration)

        run.lane=lane
        run.save(flush: true)

        lane.addToRuns(run)

        log.info("Run startTime : ${run.startTime}")
        log.info("Run stopTime : ${run.stopTime}")
        log.info("Run startUser : ${run.startUser}")

        /* Starting individual EC2 instances */
        int result = 0

        result = ec2Service.Start(lane.ec2s)

        if(result == 0)
        {
            lane.status = Status.Running
            slackService.SendStartMessage(lane)
        }
        else
        {
            slackService.SendStartErrorMessage(lane)
            log.error("There were errors in starting the lane : Result= ${result}")
            lane.status = Status.UnKnown
        }

        Save(lane)

        log.info("Started Lane successfully")
    }


    def Increment(Long id, int duration) {
        log.info("-----------------------------------------------------------------------------")
        log.info("Increment Lane")
        log.info("-----------------------------------------------------------------------------")
        log.info("Lane Id : ${id}")
        log.info("Duration : ${duration}")

        Lane lane = Lane.get(id)
        Increment(lane, duration)
    }

    def Increment(Lane lane, int duration) {
        log.info("Lane Name : ${lane.name}")
        log.info("Lane Alias : ${lane.alias}")
        log.info("Lane Description : ${lane.description}")
        log.info("Lane Status : ${lane.status}")

        runService.Increment(lane.runs,  duration)
        Save(lane)

        log.info("Incremented Lane shutdown time successfully")
    }


    public void ShutdownLaneIfItsPassedItsDuration(){
        log.info("-----------------------------------------------------------------------------")
        log.info("Shutdown Lane if its passed its duration")
        log.info("-----------------------------------------------------------------------------")

        Lane.list().each{ Lane lane->
            log.info("Processing Lane : ${lane.name}")
            log.info("Status : ${lane.status}")

            if(lane.status != Status.Shutdown)
            {
                LaneAction laneAction = runService.IsItPastShutdownTime(lane.runs)

                if(laneAction == LaneAction.Shutdown)
                {
                    Shutdown(lane)
                }
                else if (laneAction == LaneAction.WarnShutdown)
                {
                    slackService.SendShutdownWarningMessage(lane)
                }
            }
            else
            {
                log.info("Its shutdown already")
            }

        }

    }

    def void Shutdown(Long id)
    {
        log.info("-----------------------------------------------------------------------------")
        log.info("Shutdown Lane - Id : ${id}")
        log.info("-----------------------------------------------------------------------------")

        Lane lane = Lane.get(id)
        Shutdown(lane)
    }

    def void Shutdown(Lane lane)
    {
        log.info("-----------------------------------------------------------------------------")
        log.info("Shutdown Lane")
        log.info("-----------------------------------------------------------------------------")

        log.info("Lane Name : ${lane.name}")
        log.info("Lane Alias : ${lane.alias}")
        log.info("Lane Description : ${lane.description}")
        log.info("Lane Status : ${lane.status}")

        /* Stopping individual EC2 instances */
        int result = 0

        result = ec2Service.Shutdown(lane.ec2s)

        if(result == 0)
        {
            lane.status = Status.Shutdown
            runService.Shutdown(lane.runs, "Raja")
            slackService.SendShutdownMessage(lane)
        }
        else
        {
            log.error("There were errors in shutting down the lane : Result= ${result}")
            lane.status = Status.UnKnown
            slackService.SendShutdownErrorMessage(lane)
        }

        Save(lane)

        log.info("Shutdown Lane successfully")
    }


    private void Save(Lane lane)
    {
        log.info ("Saving Lane instance ")
        if(!lane.save(flush: true))
        {
            log.error ("ERROR : Cannot Save Lane instance")
            lane.errors.allErrors.each {
                log.error(it)
            }
        }
        else
        {
            log.info("Successfully Saved Lane instance ")
        }
    }




}