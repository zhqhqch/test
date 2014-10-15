package server.service;

import java.util.concurrent.TimeUnit;

import com.hqch.simple.server.ScheduledTask;

public class PKRunnable extends ScheduledTask {
	
	public PKRunnable(int initialDelay, int repeat, TimeUnit timeUnit) {
		super(initialDelay, repeat, timeUnit);
	}

	@Override
	public void runTask() {
		System.out.println("#@@@@@@@@@@@@@@@#####");
		if(getRunTimes() == 10){
			cancelTask(true);
		}
	}
}
