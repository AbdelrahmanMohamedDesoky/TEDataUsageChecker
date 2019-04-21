

public class OPThread implements Runnable {
	private String email,password,usage;
	public OPThread(String email, String password) {
		this.email = email;
		this.password = password;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		TEDataClient teclient = new TEDataClient(email, password);
		teclient.login();
		
		String page = teclient.get("https://mytedata.net/wps/portal");
		
		if(page.indexOf("window.onload=calUsage(") == -1) {
			this.usage = "ERROR : Wrong Email or Password";
			Thread.currentThread().stop();
		}

		String usage = page.substring(page.indexOf("window.onload=calUsage(")+23, page.indexOf("window.onload=calUsage(")+35);
		
		this.setUsage(usage);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsage() {
		return this.usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

}
