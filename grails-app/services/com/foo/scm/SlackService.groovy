package com.foo.scm

import grails.gorm.transactions.Transactional
import grails.plugins.rest.client.RestBuilder

@Transactional
class SlackService {
    def grailsApplication

    def serviceMethod() {

    }

    public void SendMessage(Lane lane, String message){
        SendMessage(lane.slackChannels, message)
    }

    public void SendStartMessage(Lane lane){
        SendMessage(lane.slackChannels, "Lane ${lane.name} has been started")
    }

    public void SendStartErrorMessage(Lane lane){
        SendMessage(lane.slackChannels, "Cannot start Lane ${lane.name}")
    }

    public void SendShutdownMessage(Lane lane){
        SendMessage(lane.slackChannels, "Lane ${lane.name} has been shutdown")
    }

    public void SendShutdownErrorMessage(Lane lane){
        SendMessage(lane.slackChannels, "Cannot shutdown Lane ${lane.name}")
    }

    public void SendShutdownWarningMessage(Lane lane){
        SendMessage(lane.slackChannels, "Dude, Lane ${lane.name} is going to be shutdown soon")
    }

    public void SendMessage(String slackChannels, String message){
        log.info "\nSending Slack Message"
        log.info("Slack Channels : ${slackChannels}")
        log.info("Message : ${message}")

        try
        {
            slackChannels.tokenize( ',' ).each {String slackChannel ->
                log.info("Sending message to Channel : ${slackChannel}")

                RestBuilder rest = new RestBuilder()
                def resp = rest.post(grailsApplication.config.getProperty('slack.url')){
                    contentType "application/json"
                    accept "application/json"
                    json {
                        text = message
                        channel = slackChannel
                        icon_emoji=':ghost:'
                    }
                }
                log.info ("Finished sending the message")
            }
        }
        catch(Exception ex)
        {
            log.error("Oh dear, its all screwed up " + ex.message)
        }
    }




}
