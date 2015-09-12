package com.osreboot.ridhvl.external;

import java.util.ArrayList;

import org.lwjgl.input.Controllers;

public abstract class HvlVerifier {

	public static final HvlVerifier JINPUT = new HvlVerifier(){
		@Override
		public boolean verify(){
			try{
				Controllers.create();
				return true;
			}catch(Exception e){
				return false;
			}
		}
	};

	public static ArrayList<HvlVerifier> verifiers = new ArrayList<>();

	public static void globalVerify(){
		for(HvlVerifier v : verifiers) v.metaVerify();
	}

	private boolean isValid = false;

	public HvlVerifier(){
		verifiers.add(this);
	}

	public final void metaVerify(){
		isValid = verify();
	}

	public abstract boolean verify();

	public boolean isValid(){
		return isValid;
	}


}
