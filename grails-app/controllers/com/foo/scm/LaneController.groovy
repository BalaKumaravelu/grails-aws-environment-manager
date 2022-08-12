package com.foo.scm

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class LaneController {

    LaneService laneService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    /*
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond laneService.list(params), model:[laneCount: laneService.count()]
    }
    */

    def index() {
        def lanes = Lane.list()
        [laneList: lanes]
    }


    def show(Long id) {
        //respond laneService.get(id)
        log.info("-----------------------------------------------------------------------------")
        log.info("Show")
        log.info("-----------------------------------------------------------------------------")

        log.info("ID : " + id.toString())
        def lane = Lane.get(id)

        log.info("Lane Name : ${lane.name}")
        log.info("Lane Alias : ${lane.alias}")
        log.info("Lane Description : ${lane.description}")
        log.info("Lane Status : ${lane.status}")

        [lane: lane]
    }

    def create() {
        respond new Lane(params)
    }

    def save(Lane lane) {
        if (lane == null) {
            notFound()
            return
        }

        try {
            lane.save(flush: true)
            //laneService.save(lane)
        } catch (ValidationException e) {
            respond lane.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'lane.label', default: 'Lane'), lane.id])
                redirect lane
            }
            '*' { respond lane, [status: CREATED] }
        }
    }

    def edit(Long id) {
        //respond laneService.get(id)
        respond Lane.findById(id)
    }

    def update(Lane lane) {
        if (lane == null) {
            notFound()
            return
        }

        try {
            //laneService.save(lane)
            lane.save(flush: true)
        } catch (ValidationException e) {
            respond lane.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'lane.label', default: 'Lane'), lane.id])
                redirect lane
            }
            '*'{ respond lane, [status: OK] }
        }
    }

    def delete(Long id) {
        log.info("Going to delete "+ id.toString())
        if (id == null) {
            notFound()
            return
        }

        //laneService.delete(id)
        Lane.get(id).delete(flush: true)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'lane.label', default: 'Lane'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    def start(Long id)
    {
        log.info("-----------------------------------------------------------------------------")
        log.info("Going to Start Lane")
        log.info("-----------------------------------------------------------------------------")
        log.info("Id : ${id}")
        log.info("Duration : ${params.duration}")

        laneService.Start(id, Integer.parseInt(params.duration))

        redirect (action: 'show', id: id, controller: "lane")
    }


    def increment()
    {
        log.info("-----------------------------------------------------------------------------")
        log.info("increment")
        log.info("-----------------------------------------------------------------------------")

        laneService.Increment(Integer.parseInt(params.id),  Integer.parseInt(params.duration))

        redirect (action: 'show', id: "${params.id}", controller: "lane")
    }

    def shutdown(Long id)
    {
        log.info("-----------------------------------------------------------------------------")
        log.info("Going to Shutdown Lane - Id : ${id}")
        log.info("-----------------------------------------------------------------------------")

        def lane = Lane.get(id)

        laneService.Shutdown(lane)

        redirect (action: 'show', id: id, controller: "lane")
     }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'lane.label', default: 'Lane'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
