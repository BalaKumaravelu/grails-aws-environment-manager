package com.foo.scm

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class LaneServiceSpec extends Specification {

    LaneService laneService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Lane(...).save(flush: true, failOnError: true)
        //new Lane(...).save(flush: true, failOnError: true)
        //Lane lane = new Lane(...).save(flush: true, failOnError: true)
        //new Lane(...).save(flush: true, failOnError: true)
        //new Lane(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //lane.id
    }

    void "test get"() {
        setupData()

        expect:
        laneService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Lane> laneList = laneService.list(max: 2, offset: 2)

        then:
        laneList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        laneService.count() == 5
    }

    void "test delete"() {
        Long laneId = setupData()

        expect:
        laneService.count() == 5

        when:
        laneService.delete(laneId)
        sessionFactory.currentSession.flush()

        then:
        laneService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Lane lane = new Lane()
        laneService.save(lane)

        then:
        lane.id != null
    }
}
