package com.osreboot.ridhvl.external;

import java.util.ArrayList;

import com.osreboot.ridhvl.action.HvlAction0r;

public class HvlEnvironment {

	public static HvlEnvironment analyze(){
		ArrayList<Float> values = new ArrayList<>();
		for(HvlEnvironment.Variable v : template) values.add(v.verify());
		return new HvlEnvironment(template, values);
	}

	private static ArrayList<HvlEnvironment.Variable> template;

	public static ArrayList<HvlEnvironment.Variable> getTemplate(){
		return template;
	}

	private ArrayList<HvlEnvironment.Variable> variables = new ArrayList<>();
	private ArrayList<Float> values;
	
	public HvlEnvironment(ArrayList<HvlEnvironment.Variable> variablesArg){
		variables = variablesArg;
	}
	
	public HvlEnvironment(ArrayList<HvlEnvironment.Variable> variablesArg, ArrayList<Float> valuesArg){
		variables = variablesArg;
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

	public ArrayList<HvlEnvironment.Variable> getVariables(){
		return variables;
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

	public static class Variable{

		public static HvlAction0r<Boolean> getClassVerifyAction(final String pathArg){
			return new HvlAction0r<Boolean>(){
				@Override
				public Boolean run(){
					try{
						Class.forName(pathArg);
					}catch(Exception e){
						return false;
					}
					return true;
				}
			};
		}

		private int id;
		private HvlAction0r<Float> verify;

		public Variable(int idArg, HvlAction0r<Float> verifyArg){
			id = idArg;
			verify = verifyArg;
		}

		public float verify(){
			return verify.run();
		}

		public int getId(){
			return id;
		}

		public HvlAction0r<Float> getVerify(){
			return verify;
		}

	}

}
