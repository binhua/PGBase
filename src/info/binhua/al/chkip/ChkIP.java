package info.binhua.al.chkip;

public class ChkIP {
	
	private static final int m = 5;
	private static final int n = 4;

	public static void main(String[] args){
		
		System.out.println(chk_IP("192.168..1.111"));
	}
	
	
	public static boolean chk_IP(String ips){
		
		String[] ip = ips.split("\\.");
		if(ip.length>4) return false;
		for(int i=0; i<ip.length; i++){
			int flag = Integer.parseInt(ip[i]);
			if(flag > 256 || flag <0) return false;
		}
		return true;
	}
}
