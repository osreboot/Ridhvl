package com.osreboot.ridhvl.external;

import java.util.ArrayList;

import org.lwjgl.input.Controllers;
import org.lwjgl.opengl.GLContext;

import com.osreboot.ridhvl.action.HvlAction0;
import com.osreboot.ridhvl.template.HvlChronology;
import com.osreboot.ridhvl.template.HvlChronologyInitialize;

public abstract class HvlVerifier {

	public static final int INIT_CHRONOLOGY = HvlChronology.INIT_CHRONOLOGY_EARLY - HvlChronology.INIT_CHRONOLOGY_DFLTINTVL;

	@HvlChronologyInitialize(chronology = INIT_CHRONOLOGY)
	public static final HvlAction0 INIT_ACTION = new HvlAction0(){
		@Override
		public void run(){
			globalVerify();
		}
	};
	
	public static ArrayList<HvlVerifier> verifiers = new ArrayList<>();

	public static final HvlVerifier VFR_JINPUT = new HvlVerifier(){
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

	public static final HvlVerifier VFR_FBOENABLED = new HvlVerifier(){
		@Override
		public boolean verify(){
			return GLContext.getCapabilities().GL_EXT_framebuffer_object;
		}
	};

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
