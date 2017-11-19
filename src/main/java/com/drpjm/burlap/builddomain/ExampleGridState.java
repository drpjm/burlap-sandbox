package com.drpjm.burlap.builddomain;

import java.util.List;

import burlap.mdp.core.state.MutableState;
import burlap.mdp.core.state.*;

import static com.drpjm.burlap.builddomain.ExampleGridWorld.VAR_X;
import static com.drpjm.burlap.builddomain.ExampleGridWorld.VAR_Y;

public class ExampleGridState implements MutableState {

	private int x;
	private int y;
	
	public ExampleGridState(){
		
	}
	
	public ExampleGridState(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public State copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object get(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> variableKeys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MutableState set(Object arg0, Object arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}
