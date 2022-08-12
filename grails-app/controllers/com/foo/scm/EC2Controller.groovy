package com.foo.scm

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class EC2Controller {

    EC2Service EC2Service

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]


    def index() {
        def ec2s = EC2.list()
        [ec2List: ec2s]
    }

    def show(Long id) {
        //respond EC2Service.get(id)
        def ec2 = EC2.findById(id)
        [ec2: ec2]
    }

    def create() {
        respond new EC2(params)
    }

    def save(EC2 EC2) {
        if (EC2 == null) {
            notFound()
            return
        }

        try {
            EC2.save(flush: true)
            //EC2Service.save(EC2)
        } catch (ValidationException e) {
            respond EC2.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'EC2.label', default: 'EC2'), EC2.id])
                redirect EC2
            }
            '*' { respond EC2, [status: CREATED] }
        }
    }

    def edit(Long id) {
        //respond EC2Service.get(id)
        respond EC2.get(id)
    }

    def update(EC2 EC2) {
        log.info("Going to Update ")
        log.info("name "+ EC2.name)
        log.info("alias "+ EC2.alias)
        if (EC2 == null) {
            notFound()
            return
        }

        try {
            //EC2Service.save(EC2)
            EC2.save(flush: true)
        } catch (ValidationException e) {
            respond EC2.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'EC2.label', default: 'EC2'), EC2.id])
                redirect EC2
            }
            '*'{ respond EC2, [status: OK] }
        }
    }

    def delete(Long id) {
        log.info("Going to delete "+ id.toString())
        if (id == null) {
            notFound()
            return
        }

        EC2Service.Delete(id)
        /*
        def ec2 = EC2.get(id)
        log.info("name "+ ec2.name)
        log.info("alias "+ ec2.alias)

        ec2.delete(flush: true)
        log.info("Done deleting ")

        //EC2.get(id).delete(flush: true)
        */
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'EC2.label', default: 'EC2'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'EC2.label', default: 'EC2'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
