package com.foo.scm

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class RunSpec extends Specification implements DomainUnitTest<Run> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}
