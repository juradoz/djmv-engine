package net.danieljurado.engine.impl;

import java.util.Timer;
import java.util.TimerTask;

import net.danieljurado.engine.Engine;

import org.joda.time.Period;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

class EngineImpl extends TimerTask implements Engine {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private final Runnable owner;
	private final Timer timer;

	@Inject
	EngineImpl(@Assisted Runnable owner, @Assisted Period period,
			@Assisted boolean isDaemon) {
		this(new Timer("Engine-" + owner.getClass().getSimpleName(), isDaemon),
				owner, period);
	}

	EngineImpl(Timer timer, Runnable owner, Period period) {
		this.owner = owner;
		this.timer = timer;
		this.timer.schedule(this, 0,
				period.toStandardSeconds().getSeconds() * 1000);
		logger.debug("started engine to {}", owner.getClass().getSimpleName());
	}

	@Override
	public void run() {
		try {
			owner.run();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	public void shutdown() {
		this.timer.cancel();
		logger.debug("shutdown engine to {}", owner);
	}
}
