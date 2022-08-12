package com.foo.scm

import grails.gorm.transactions.Transactional

enum LaneAction {
    Shutdown,
    WarnShutdown,
    NoAction
}

@Transactional
class RunService {
    def serviceMethod() {

    }

    public Run Create(String startUser, int duration)
    {
        log.info("Starting Run for User : ${startUser}")
        Run run = new Run(startUser)
        run.shutdownTime.hours += duration
        return run
    }

    public Run Increment(Set runs, int duration)
    {
        log.info("\nIncrement duration")
        log.info("Duration : ${duration}")

        def lastRun = runs.sort{-it.id}.toList().first()
        log.info("Current ShutdownTime : ${lastRun.shutdownTime.format( 'MM-dd-yy  HH:mm:ss')}")
        def shutdownUser = lastRun.shutdownUser

        // For some reason, name has to be modified for it to detect changes ToDO later

        lastRun.shutdownUser = "blah"
        lastRun.shutdownUser = shutdownUser

        lastRun.shutdownTime.hours += duration
        log.info("Incremented ShutdownTime : ${lastRun.shutdownTime.format( 'MM-dd-yy  HH:mm:ss')}")

        Save(lastRun)

        return lastRun
    }



    public LaneAction IsItPastShutdownTime(Set runs)
    {
        log.info("\nIsItPastShutdownTime")

        LaneAction laneAction = LaneAction.NoAction

        Run run = runs.sort{-it.id}.toList().first()

        log.info("Lane Shutdown Time : ${run.shutdownTime.format( 'MM-dd-yy  HH:mm:ss')}")
        log.info("Current Time : ${new Date().format( 'MM-dd-yy  HH:mm:ss')}")

        if(run.shutdownTime < new Date())
        {
            laneAction = LaneAction.Shutdown
            log.info "Lane is past its shutdown time, going to shut it down."
        }
        else
        {
            log.info "Lane is not past its shutdown time, skipping shutting it down."

            log.info "Checking if its time to warn."
            def currentTime = new Date()
            currentTime.minutes +=10

            if(run.shutdownTime < currentTime)
            {
                laneAction = LaneAction.WarnShutdown
            }
        }

        return laneAction
    }

    public Run Shutdown(Set runs, String shutdownUser)
    {
        log.info("Shutdown Run for User : ${shutdownUser}")

        def lastRun = runs.sort{-it.id}.toList().first()
        lastRun.shutdownUser = shutdownUser
        lastRun.shutdownTime = new Date()

        Save(lastRun)

        return lastRun
    }


    private void Save(Run run)
    {
        log.info("Saving Run instance")
        if(!run.save(flush: true))
        {
            log.error ("ERROR : Cannot Save Run instance")
            run.errors.allErrors.each {
                log.error(it)
            }
        }
        else
        {
            log.info("Saved Run instance")
        }
    }

    public void Delete(Long id)
    {
        log.info("Deleting Run instance - ID : ${id}")

        Run run = Run.get(id)
        run.delete(flush: true)
    }
}