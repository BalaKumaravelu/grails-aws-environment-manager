package com.foo.scm

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class RunServiceSpec extends Specification {

    RunService runService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Run(...).save(flush: true, failOnError: true)
        //new Run(...).save(flush: true, failOnError: true)
        //Run run = new Run(...).save(flush: true, failOnError: true)
        //new Run(...).save(flush: true, failOnError: true)
        //new Run(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //run.id
    }

    void "test get"() {
        setupData()

        expect:
        runService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Run> runList = runService.list(max: 2, offset: 2)

        then:
        runList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        runService.count() == 5
    }

    void "test delete"() {
        Long runId = setupData()

        expect:
        runService.count() == 5

        when:
        runService.delete(runId)
        sessionFactory.currentSession.flush()

        then:
        runService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Run run = new Run()
        runService.save(run)

        then:
        run.id != null
    }
}
