import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		Scanner input = new Scanner(System.in);
		System.out.println("Please Enter Your MyTEData Email Address ");
		String email = input.nextLine();
		System.out.println("Please Enter Your MyTEData Password ");
		String password = input.nextLine();
		
		OPThread tedataRunnable = new OPThread(email, password);
		Thread tedataThread = new Thread(tedataRunnable);
		tedataThread.start();
		
		System.out.println("Connecting with TEDATA Server ");
		while(tedataRunnable.getUsage() == null) {
			System.out.print(".");
			Thread.sleep(1000);
		}
		if(tedataRunnable.getUsage().equals("ERROR : Wrong Email or Password")) {
			System.out.print("\nDISCONNECTED\n");
			System.out.println(tedataRunnable.getUsage());
		} else {
			System.out.print("\nCONNECTED\n");
			System.out.println("Your current Usage is " + tedataRunnable.getUsage());
		}
		
	
	}

}
