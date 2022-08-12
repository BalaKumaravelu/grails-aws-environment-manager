package com.foo.scm

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class RunController {

    RunService runService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
        //params.max = Math.min(max ?: 10, 100)
        //respond runService.list(params), model:[runCount: runService.count()]
        def runs = Run.list()
        [runList: runs]
    }

    def show(Long id) {
        //respond runService.get(id)
        def run = Run.get(id)
        [run: run]

    }

    def create() {
        respond new Run(params)
    }

    def save(Run run) {
        if (run == null) {
            notFound()
            return
        }

        try {
            runService.save(run)
        } catch (ValidationException e) {
            respond run.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'run.label', default: 'Run'), run.id])
                redirect run
            }
            '*' { respond run, [status: CREATED] }
        }
    }

    def edit(Long id) {
        //respond runService.get(id)
        def run = Run.get(id)
        [run: run]
    }

    def update(Run run) {
        if (run == null) {
            notFound()
            return
        }

        try {
            runService.Save(run)
        } catch (ValidationException e) {
            respond run.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'run.label', default: 'Run'), run.id])
                redirect run
            }
            '*'{ respond run, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        runService.Delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'run.label', default: 'Run'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'run.label', default: 'Run'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
