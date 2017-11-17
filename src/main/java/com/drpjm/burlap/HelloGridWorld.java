package com.drpjm.burlap;

import burlap.domain.singleagent.gridworld.*;
import burlap.domain.singleagent.gridworld.state.*;
import burlap.mdp.core.state.*;
import burlap.mdp.singleagent.*;
import burlap.shell.visual.VisualExplorer;
import burlap.visualizer.Visualizer;

/**
 * Class file that implements the BURLAP tutorial. This class creates
 * a manually controlled grid agent in a four room grid world.
 * burlap.cs.brown.edu
 * @author pjmartin
 *
 */
public class HelloGridWorld {

	public static void main(String[] args){
		
		// Creates the GridWorldDomain - NSEW movement
		GridWorldDomain world = new GridWorldDomain(11,11);
		world.setMapToFourRooms();
		// Dynamics have an 80% chance of success
		world.setProbSucceedTransitionDynamics(0.8);
		// Creates the domain from the world model
		SADomain domain = world.generateDomain();
		
		// Create the initial state
		// GridLocation at 10,10
		State s = new GridWorldState(new GridAgent(0,0), new GridLocation(9,8,"loc0"));
		
		// All the visualization stuff...
		Visualizer v = GridWorldVisualizer.getVisualizer(world.getMap());
		VisualExplorer exp = new VisualExplorer(domain, v, s);
		
		// Key controls - WASD!
		exp.addKeyAction("w", GridWorldDomain.ACTION_NORTH, "");
		exp.addKeyAction("a", GridWorldDomain.ACTION_WEST, "");
		exp.addKeyAction("s", GridWorldDomain.ACTION_SOUTH, "");
		exp.addKeyAction("d", GridWorldDomain.ACTION_EAST, "");
		
		exp.initGUI();
	}
	
}
