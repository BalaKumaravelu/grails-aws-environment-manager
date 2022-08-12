package com.foo.scm

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class EC2ServiceSpec extends Specification {

    EC2Service EC2Service
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new EC2(...).save(flush: true, failOnError: true)
        //new EC2(...).save(flush: true, failOnError: true)
        //EC2 EC2 = new EC2(...).save(flush: true, failOnError: true)
        //new EC2(...).save(flush: true, failOnError: true)
        //new EC2(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //EC2.id
    }

    void "test get"() {
        setupData()

        expect:
        EC2Service.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<EC2> EC2List = EC2Service.list(max: 2, offset: 2)

        then:
        EC2List.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        EC2Service.count() == 5
    }

    void "test delete"() {
        Long EC2Id = setupData()

        expect:
        EC2Service.count() == 5

        when:
        EC2Service.delete(EC2Id)
        sessionFactory.currentSession.flush()

        then:
        EC2Service.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        EC2 EC2 = new EC2()
        EC2Service.save(EC2)

        then:
        EC2.id != null
    }
}
