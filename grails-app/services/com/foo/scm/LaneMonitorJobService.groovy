package com.foo.scm

import com.agileorbit.schwartz.SchwartzJob
import grails.transaction.Transactional
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.quartz.JobExecutionContext
import org.quartz.JobExecutionException

import java.text.SimpleDateFormat

@CompileStatic
@Slf4j
class LaneMonitorJobService implements SchwartzJob {
	LaneService laneService

	LaneMonitorJobService()
	{
		laneService = new LaneService()
	}


	@Transactional
	void execute(JobExecutionContext context) throws JobExecutionException {
		//log.info "{0}:{1}", context.trigger.key, new SimpleDateFormat("dd/M/yyyy hh:mm:ss").format(new Date())
		log.info("${context.trigger.key} - ${ new SimpleDateFormat('dd/M/yyyy hh:mm:ss').format(new Date())}")
		laneService.ShutdownLaneIfItsPassedItsDuration()
	}

	void buildTriggers() {
		triggers << factory('Simple Job every 60 seconds').intervalInSeconds(60).build()
		 //triggers << factory('cron every second').cronSchedule('0/2 * * * * ?').build()

		// triggers << factory('Repeat3TimesEvery100').intervalInMillis(100).repeatCount(3).build()

		// triggers << factory('repeat every 500ms forever').intervalInMillis(500).build()

		// triggers << factory('repeat every two days forever').intervalInDays(2).build()

		/*
		triggers << factory('trigger1')
				.intervalInMillis(100)
				.startDelay(2000).noRepeat()
				.jobData(foo: 'bar').build()
		*/

		// triggers << factory('run_once_immediately').noRepeat().build()

		// requires this static import:
		// import static com.agileorbit.schwartz.builder.MisfireHandling.NowWithExistingCount
		/*
		triggers << factory('MisfireTrigger2')
				.intervalInMillis(150)
				.misfireHandling(NowWithExistingCount)
				.build()
		*/

		// triggers << factory('trigger1').group('group1').intervalInSeconds(1).build()

		// requires this static import:
		// import static org.quartz.DateBuilder.todayAt
		// triggers << factory('run every day one second before midnight').startAt(todayAt(23,59,59)).intervalInDays(1).build()
	}
}
