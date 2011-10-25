package net.danieljurado.engine;

import org.joda.time.Period;


public interface EngineFactory {
	Engine create(Runnable owner, Period period, boolean isDaemon);
}
