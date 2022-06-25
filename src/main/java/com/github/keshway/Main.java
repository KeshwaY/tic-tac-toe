package com.github.keshway;

import com.github.keshway.databus.implementation.ConfigurationBus;
import com.github.keshway.demo.DefaultDemoFacade;
import com.github.keshway.demo.DemoFacade;
import com.github.keshway.game.DefaultGameFacade;
import com.github.keshway.game.GameFacade;
import com.github.keshway.ui.DefaultUiFacade;
import com.github.keshway.ui.UiFacade;

public class Main {
	public static void main(String[] args) {
		int demoFlag = -1;
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("--demo")) {
				demoFlag = i;
			}
		}
		GameFacade gameFacade = new DefaultGameFacade();
		gameFacade.initialize(ConfigurationBus.getInstance());
		if (demoFlag != -1) {
			DemoFacade demoFacade = new DefaultDemoFacade();
			demoFacade.initialize(ConfigurationBus.getInstance(), args);
			return;
		}
		UiFacade uiFacade = new DefaultUiFacade();
		uiFacade.initialize(ConfigurationBus.getInstance());
	}
}

