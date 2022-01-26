package utils;

public final class RMIConfiguration {

	private static String HOST = "127.0.0.1";
	private static String NAME = "Chat";
	private static int PORT = 12345;
	
	public static String getHOST() {
		return HOST;
	}	

	public static String getNAME() {
		return NAME;
	}	
	
	public static int getPORT() {
		return PORT;
	}
	
	public static String getUrl() {
		return "rmi://" + HOST + ":" + PORT+ "/" + NAME;
	}
	
	
}
