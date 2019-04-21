import java.util.logging.Level;

import org.apache.commons.logging.LogFactory;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class TEDataClient {
	private WebClient WEB_CLIENT = new WebClient(BrowserVersion.CHROME);

	private final String email;
	private final String password;

	TEDataClient(String email, String password) {
		this.email = email;
		this.password = password;
		WEB_CLIENT.getCookieManager().setCookiesEnabled(true);
		WEB_CLIENT.getOptions().setThrowExceptionOnScriptError(false);
		WEB_CLIENT.getOptions().setJavaScriptEnabled(true);
		WEB_CLIENT.getOptions().setCssEnabled(true);
		
		WEB_CLIENT.setCssErrorHandler(new SilentCssErrorHandler());
        LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
        java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit.javascript.StrictErrorReporter").setLevel(Level.OFF);
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit.javascript.host.ActiveXObject").setLevel(Level.OFF);
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit.javascript.host.html.HTMLDocument").setLevel(Level.OFF);
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit.html.HtmlScript").setLevel(Level.OFF);
        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit.javascript.host.WindowProxy").setLevel(Level.OFF);
        java.util.logging.Logger.getLogger("org.apache").setLevel(Level.OFF);
	}

	public void login() {
		String loginURL = "https://mytedata.net/wps/portal";
		try {

			HtmlPage loginPage = WEB_CLIENT.getPage(loginURL);
			HtmlForm loginForm = loginPage.getFirstByXPath("//*[@id=\"viewns_Z7_49L81I02J0VS50AFQUGD3C00G4_:form1\"]");
			loginForm.getInputByName("viewns_Z7_49L81I02J0VS50AFQUGD3C00G4_:form1:inputTextEmail")
					.setValueAttribute(email);
			loginForm.getInputByName("viewns_Z7_49L81I02J0VS50AFQUGD3C00G4_:form1:inputSecretPassword")
					.setValueAttribute(password);

			loginForm.getInputByName("viewns_Z7_49L81I02J0VS50AFQUGD3C00G4_:form1:commandButtonSignIn").click();
		} catch (Exception e) {
			//System.out.println(e.getMessage());
		}
	}

	public String get(String URL) {
		try {
			return WEB_CLIENT.getPage(URL).getWebResponse().getContentAsString();
		} catch (Exception e) {
			//System.out.println(e.getMessage());
			return null;
		}
	}
}