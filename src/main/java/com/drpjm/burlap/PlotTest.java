package com.drpjm.burlap;

import burlap.behavior.singleagent.auxiliary.performance.LearningAlgorithmExperimenter;
import burlap.behavior.singleagent.auxiliary.performance.PerformanceMetric;
import burlap.behavior.singleagent.auxiliary.performance.TrialMode;
import burlap.behavior.singleagent.learning.LearningAgent;
import burlap.behavior.singleagent.learning.LearningAgentFactory;
import burlap.behavior.singleagent.learning.tdmethods.QLearning;
import burlap.domain.singleagent.gridworld.GridWorldDomain;
import burlap.domain.singleagent.gridworld.state.GridAgent;
import burlap.domain.singleagent.gridworld.state.GridLocation;
import burlap.domain.singleagent.gridworld.state.GridWorldState;
import burlap.mdp.auxiliary.common.ConstantStateGenerator;
import burlap.mdp.auxiliary.common.SinglePFTF;
import burlap.mdp.auxiliary.stateconditiontest.TFGoalCondition;
import burlap.mdp.core.TerminalFunction;
import burlap.mdp.core.oo.propositional.PropositionalFunction;
import burlap.mdp.core.state.State;
import burlap.mdp.singleagent.common.GoalBasedRF;
import burlap.mdp.singleagent.environment.SimulatedEnvironment;
import burlap.mdp.singleagent.model.RewardFunction;
import burlap.mdp.singleagent.oo.OOSADomain;
import burlap.statehashing.simple.SimpleHashableStateFactory;

/**
 * Direct implementation of the PlotTest example code on the BURLAP website.
 * Implements a basic Q-Learning algorithm deployed in a GridWorld.
 * @author pjmartin
 *
 */
public class PlotTest {

	public static void main(String[] args){

		// Core world setup - same as HelloGridWorld
		GridWorldDomain world = new GridWorldDomain(11,11);
		world.setMapToFourRooms();
		world.setProbSucceedTransitionDynamics(0.8);
		
		// Terminal function - when agent reaches the goal
		final TerminalFunction tf = new SinglePFTF(
				PropositionalFunction.findPF(world.generatePfs(), GridWorldDomain.PF_AT_LOCATION));
		
		final RewardFunction rf = new GoalBasedRF(new TFGoalCondition(tf), 5.0, -0.1);
		
		world.setTf(tf);
		world.setRf(rf);
		
		final OOSADomain domain = world.generateDomain();
		
		// Initial state and goal state
		GridWorldState s = new GridWorldState(new GridAgent(0,0), new GridLocation(10,10,"loc0"));
		
		// Init state generator for experiments
		final ConstantStateGenerator stateGen = new ConstantStateGenerator(s);
		
		final SimpleHashableStateFactory hashingFactory = new SimpleHashableStateFactory();
		
		// Generate a Q-Learning agent
		LearningAgentFactory qLearningFactory = new LearningAgentFactory(){

			public LearningAgent generateAgent() {
				// Tabular QLearning: gamma = 0.99, qInit = 0.3, learningRate = 0.1
				return new QLearning(domain, 0.8, hashingFactory, 0.3, 0.1);
			}

			public String getAgentName() {
				return "Q-Learning";
			}
			
		};
		
		// Define the learning environment for the agent
		SimulatedEnvironment env = new SimulatedEnvironment(domain, stateGen);
		
		LearningAlgorithmExperimenter exp = new LearningAlgorithmExperimenter(env, 10, 100, qLearningFactory);
		
		exp.setUpPlottingConfiguration(500, 250, 2, 1000, 
				TrialMode.MOST_RECENT_AND_AVERAGE, 
				PerformanceMetric.CUMULATIVE_STEPS_PER_EPISODE,
				PerformanceMetric.AVERAGE_EPISODE_REWARD);
		
		exp.startExperiment();
	}
	
}
