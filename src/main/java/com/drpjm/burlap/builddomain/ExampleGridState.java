package com.drpjm.burlap.builddomain;

import java.util.Arrays;
import java.util.List;

import burlap.mdp.core.state.MutableState;
import burlap.mdp.core.state.*;

import static com.drpjm.burlap.builddomain.ExampleGridWorld.VAR_X;
import static com.drpjm.burlap.builddomain.ExampleGridWorld.VAR_Y;

/**
 * Implementation of the grid state class on the BURLAP tutorial page.
 * @author pjmartin
 *
 */
public class ExampleGridState implements MutableState {

	private int x;
	private int y;

	private final static List<Object> varKeys = Arrays.<Object>asList(VAR_X,VAR_Y);
	
	public ExampleGridState(){
	}
	
	public ExampleGridState(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	
	@Override
	public String toString() {
		return StateUtilities.stateToString(this);
	}

	@Override
	public State copy() {
		/**
		 * Note on copying: there are BURLAP annotations that mark whether deep or shallow
		 * copies are needed for the grid state.
		 */
		return new ExampleGridState(this.x, this.y);
	}

	@Override
	public Object get(Object varKey) {
		if(varKey.equals(VAR_X)){
			return this.x;
		}
		else if(varKey.equals(VAR_Y)){
			return this.y;
		}
		throw new UnknownKeyException(varKey);
	}

	@Override
	public List<Object> variableKeys() {
		return varKeys;
	}

	@Override
	public MutableState set(Object varKey, Object value) {
		/**
		 *  Value could be a string or number - need to convert before storing.
		 *  If you run BURLAP from the shell, strings are one way to pass in values. Hence,
		 *  why this function checks for either type of object. 
		 */
		if(varKey.equals(VAR_X)){
			this.x = StateUtilities.stringOrNumber(value).intValue();
		}
		else if(varKey.equals(VAR_Y)){
			this.y = StateUtilities.stringOrNumber(value).intValue();
		}
		else {
			throw new UnknownKeyException(varKey);
		}
		return this;
	}

}
