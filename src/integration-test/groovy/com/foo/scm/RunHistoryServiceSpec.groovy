package com.foo.scm

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class RunHistoryServiceSpec extends Specification {

    RunHistoryService runHistoryService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new RunHistory(...).save(flush: true, failOnError: true)
        //new RunHistory(...).save(flush: true, failOnError: true)
        //RunHistory runHistory = new RunHistory(...).save(flush: true, failOnError: true)
        //new RunHistory(...).save(flush: true, failOnError: true)
        //new RunHistory(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //runHistory.id
    }

    void "test get"() {
        setupData()

        expect:
        runHistoryService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<RunHistory> runHistoryList = runHistoryService.list(max: 2, offset: 2)

        then:
        runHistoryList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        runHistoryService.count() == 5
    }

    void "test delete"() {
        Long runHistoryId = setupData()

        expect:
        runHistoryService.count() == 5

        when:
        runHistoryService.delete(runHistoryId)
        sessionFactory.currentSession.flush()

        then:
        runHistoryService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        RunHistory runHistory = new RunHistory()
        runHistoryService.save(runHistory)

        then:
        runHistory.id != null
    }
}
