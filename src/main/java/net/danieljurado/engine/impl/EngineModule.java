package net.danieljurado.engine.impl;

import net.danieljurado.engine.Engine;
import net.danieljurado.engine.EngineFactory;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class EngineModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new FactoryModuleBuilder()
				.implement(Engine.class, EngineImpl.class).build(
						EngineFactory.class));
	}

}
