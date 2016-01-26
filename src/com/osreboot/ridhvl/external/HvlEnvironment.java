package com.osreboot.ridhvl.external;

import java.util.ArrayList;

import com.osreboot.ridhvl.action.HvlAction0r;

@Deprecated
public class HvlEnvironment {

	public static void analyze(){

	}

	private static HvlEnvironment analyzed;

	public static HvlEnvironment getAnalyzed(){
		return analyzed;
	}

	private ArrayList<HvlEnvironment.Variable> variables = new ArrayList<>();
	@SuppressWarnings("unused")
	private ArrayList<Integer> values;
	
	public HvlEnvironment(ArrayList<HvlEnvironment.Variable> variablesArg){

	}

	public ArrayList<HvlEnvironment> outliers(HvlEnvironment compareArg){
		return null;
	}

	public ArrayList<HvlEnvironment.Variable> getVariables(){
		return variables;
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
		private HvlAction0r<Boolean> verify;

		public Variable(int idArg, HvlAction0r<Boolean> verifyArg){
			id = idArg;
			verify = verifyArg;
		}

		public boolean verify(){
			return verify.run();
		}

		public int getId(){
			return id;
		}

		public HvlAction0r<Boolean> getVerify(){
			return verify;
		}

	}

}
