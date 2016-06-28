package com.osreboot.ridhvl.external;

import java.util.ArrayList;

import com.osreboot.ridhvl.action.HvlAction0;
import com.osreboot.ridhvl.action.HvlAction0r;
import com.osreboot.ridhvl.template.HvlChronology;
import com.osreboot.ridhvl.template.HvlChronologyInitialize;

public class HvlEnvironment {

	public static final int INIT_CHRONOLOGY = HvlChronology.INIT_CHRONOLOGY_EARLY - HvlChronology.INIT_CHRONOLOGY_DFLTINTVL;

	@HvlChronologyInitialize(chronology = INIT_CHRONOLOGY)
	public static final HvlAction0 INIT_ACTION = new HvlAction0(){
		@Override
		public void run(){
			current = analyze();
			initialized = true;
		}
	};

	private static ArrayList<HvlEnvironment.Variable> variables = new ArrayList<>();
	private static boolean initialized = false;
	
	public static ArrayList<HvlEnvironment.Variable> getVariables(){
		return variables;
	}

	public static HvlEnvironment analyze(){
		ArrayList<Float> values = new ArrayList<>();
		for(HvlEnvironment.Variable v : variables) values.add(v.verify());
		return new HvlEnvironment(values);
	}
	
	private static HvlEnvironment current;

	public static HvlEnvironment getCurrent(){
		return current;
	}

	private ArrayList<Float> values;
	
	public HvlEnvironment(ArrayList<Float> valuesArg){
		values = valuesArg;
	}

	public boolean meets(HvlEnvironment compareArg){
		return meetsOutliers(compareArg).size() > 0;
	}

	public ArrayList<HvlEnvironment.Variable> meetsOutliers(HvlEnvironment compareArg){
		ArrayList<HvlEnvironment.Variable> outliers = new ArrayList<HvlEnvironment.Variable>();
		for(HvlEnvironment.Variable v : variables) if(getValue(v) < compareArg.getValue(v)) outliers.add(v);
		return outliers;
	}

	public void setValues(ArrayList<Float> valuesArg){
		values = valuesArg;
	}

	public ArrayList<Float> getValues(){
		return values;
	}

	public float getValue(HvlEnvironment.Variable variableArg){
		return values.get(variables.indexOf(variableArg));
	}

	public boolean getBooleanValue(HvlEnvironment.Variable variableArg){
		return values.get(variables.indexOf(variableArg)) == 1f;
	}

	@Override
	public String toString(){
		String output = "{HvlEnvironment " + System.identityHashCode(this) + "}\n{";
		for(HvlEnvironment.Variable v : variables) output += v.toString() + "=" + values.get(variables.indexOf(v)) + (variables.indexOf(v) == variables.size() - 1 ? "" : ",\n");
		output += "}";
		return output;
	}

	public static class Variable{

		public static HvlAction0r<Float> getClassVerifyAction(final String pathArg){
			return new HvlAction0r<Float>(){
				@Override
				public Float run(){
					try{
						Class.forName(pathArg);
					}catch(Exception e){
						return 0f;
					}
					return 1f;
				}
			};
		}

		private String name;
		private HvlAction0r<Float> verify;

		public Variable(String nameArg, HvlAction0r<Float> verifyArg){
			if(initialized) throw new InitializedException();
			name = nameArg;
			verify = verifyArg;
			variables.add(this);
		}

		public float verify(){
			return verify.run();
		}

		public String getName(){
			return name;
		}

		public HvlAction0r<Float> getVerify(){
			return verify;
		}

		@Override
		public String toString(){
			return name;
		}

	}
	
	@SuppressWarnings("serial")
	static class InitializedException extends RuntimeException{}

}
