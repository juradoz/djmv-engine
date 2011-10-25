package net.danieljurado.engine.impl;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

import java.util.Timer;
import java.util.TimerTask;

import net.danieljurado.engine.Engine;

import org.joda.time.Period;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class EngineTest {
	private static final int INTERVAL = 1000;
	private Period interval;
	private Engine engine;

	@Mock
	private Timer timer;
	@Mock
	private Runnable owner;

	@Before
	public void setUp() {
		initMocks(this);
		interval = Period.millis(INTERVAL);
		engine = new EngineImpl(timer, owner, interval);
	}

	@Test
	public void construtorDeveriaIniciarTimer() {
		verify(timer).schedule((TimerTask) engine, 0, INTERVAL);
	}

	@Test
	public void runDeveriaChamarRunOwner() {
		((TimerTask) engine).run();
		verify(owner).run();
	}

	@Test
	public void shutdownDeveriaPararTimer() {
		engine.shutdown();
		verify(timer).cancel();
	}
}
