package com.hcl.aw.service;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hcl.aw.process.Drying;
import com.hcl.aw.process.Process;
import com.hcl.aw.process.Program;
import com.hcl.aw.process.Squeaking;
import com.hcl.aw.process.Washing;
import com.hcl.aw.util.States;

/**
 * Created by Ram kurra.
 */
@Service
@Scope("singleton")
public class ProgramExecutorService {

	private final ExecutorService executor = Executors.newSingleThreadExecutor();
	private Program program;
	private StringBuffer status = new StringBuffer(States.WASHING.toString());
	private Date operationEnd;
	private Date programEnd;
	private AtomicBoolean isWashing = new AtomicBoolean(false);
	private Future<Boolean> future;
	private ReentrantLock lock = new ReentrantLock();

	public void startProgram() throws InterruptedException {
		lock.lock();

		try {
			if (!isWashing.get()) {
				isWashing.set(true);
				status = new StringBuffer(States.WASHING.toString());

				Washing washing = program.getWashing();
				Squeaking squeaking = program.getSqueaking();
				Drying drying = program.getDrying();

				programEnd = new Date(System.currentTimeMillis() + washing.getDuration() + squeaking.getDuration()
						+ drying.getDuration());

				future = executor.submit(createProcessRunner(washing, squeaking, drying), true);
			}
		} finally {
			lock.unlock();
		}
	}

	public void stopProgram() {
		lock.lock();

		try {
			if (isWashing.get()) {
				isWashing.set(false);
				future.cancel(true);
				status = new StringBuffer(States.OFF.toString());
			}
		} finally {
			lock.unlock();
		}
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	public String getStatus() {
		lock.lock();

		try {
			if (isWashing.get() && operationEnd != null && programEnd != null) {

				return status.toString() + getTimeEstimationsSting();
			}

			return status.toString();
		} finally {
			lock.unlock();
		}
	}

	private String getTimeEstimationsSting() {
		Date now = new Date();

		return ",\n\t\toperation estimation: " + getMinutesAndSecondsString(now, operationEnd)
				+ ",\n\t\tprogram estimation: " + getMinutesAndSecondsString(now, programEnd);
	}

	private String getMinutesAndSecondsString(Date now, Date end) {
		long l = end.getTime() - now.getTime();
		if (l <= 0)
			return "00:00";

		long minutes = TimeUnit.MILLISECONDS.toMinutes(l);
		long seconds = TimeUnit.MILLISECONDS.toSeconds(l);
		return minutes + ":" + seconds;
	}

	private Runnable createProcessRunner(Process washing, Squeaking squeaking, Drying drying) {
		return () -> {
			try {
				runProcess(washing);
				runProcess(squeaking);
				runProcess(drying);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (isWashing.get()) {
				isWashing.set(false);
				status = new StringBuffer(States.OFF.toString());
			}
		};
	}

	private void runProcess(Process process) throws InterruptedException {
		if (isWashing.get()) {
			lock.lock();

			try {
				status = new StringBuffer(States.WASHING.toString()).append(", ").append(process.toString());
			} finally {
				lock.unlock();
			}
		}
	}
}