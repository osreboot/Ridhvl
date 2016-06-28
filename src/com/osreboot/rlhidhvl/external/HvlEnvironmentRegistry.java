package com.osreboot.rlhidhvl.external;



public class HvlEnvironmentRegistry {

	public static final String 
	PREFIX_DEPENDENCY = "DEPENDENCY|",
	PREFIX_DEPENDENCY_LWJGL2 = PREFIX_DEPENDENCY + "LWJGL2|",
	PREFIX_DEPENDENCY_SLICK2D = PREFIX_DEPENDENCY + "SLICK2D|",
	PREFIX_DEPENDENCY_JINPUT = PREFIX_DEPENDENCY + "JINPUT|",
	PREFIX_CAPABILITY = "CAPABILITY|";

	public static HvlEnvironment.Variable 
	DEPENDENCY_LWJGL2_EXISTS,
	DEPENDENCY_SLICK2D_EXISTS,
	DEPENDENCY_JINPUT_EXISTS;

	public static void registerVariables(){
		DEPENDENCY_LWJGL2_EXISTS = new HvlEnvironment.Variable(PREFIX_DEPENDENCY_LWJGL2 + "EXISTS", HvlEnvironment.Variable.getClassVerifyAction("org.lwjgl.opengl.GL11"));
		DEPENDENCY_SLICK2D_EXISTS = new HvlEnvironment.Variable(PREFIX_DEPENDENCY_SLICK2D + "EXISTS", HvlEnvironment.Variable.getClassVerifyAction("org.newdawn.slick.opengl.Texture"));
		DEPENDENCY_JINPUT_EXISTS = new HvlEnvironment.Variable(PREFIX_DEPENDENCY_JINPUT + "EXISTS", HvlEnvironment.Variable.getClassVerifyAction("net.java.games.input.Controller"));
	}

}
