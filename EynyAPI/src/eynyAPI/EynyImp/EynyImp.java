package eynyAPI.EynyImp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
/**
 * 
 * @author Long Chang Liang
 *
 */
public class EynyImp {
	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:44.0) Gecko/20100101 Firefox/44.0";
	/**
	 * Geet the response of the page of list videos that match the search
	 * @param param
	 * @return
	 */
	public String getVideoList(String param, int page) throws IOException
	{
		String encodeParam = URLEncoder.encode(param, "UTF-8");
		String url = "http://video.eyny.com/index.php?mod=search&type=video&srchtxt="+encodeParam+"&"+page;
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Connection", "Keep-Alive");
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
		String responses = "";
		if (responseCode == HttpURLConnection.HTTP_OK ) { // success
			try(InputStreamReader input = new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8);
					BufferedReader in = new BufferedReader(input))
					
			{
				responses = in.lines().collect(Collector.of
						(StringBuffer::new,(stringBuffer,str)->stringBuffer.append(str), StringBuffer::append, StringBuffer::toString));
	     	}
	     }
		//print result
		System.out.println(responses);
        return responses;
	}
	
	/**
	 * Should get the videoLink for the videos 
	 * @param video
	 * @param cookieList
	 * @return
	 * @throws IOException
	 */
	public String getVideoLink(String title, String href)
	{
		String url = "http://bbs.eyny.com/video.php?mod=video&vid="+href;
		
		//ProfilesIni allProfiles = new ProfilesIni();
		//FirefoxProfile profile = allProfiles.getProfile("default");
		//profile.setPreference("network.cookie.cookieBehavior", 0);
		//WebDriver driver = new FirefoxDriver(profile);
		//Old Account agent11340 & agent11340
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setJavascriptEnabled(true);
		dc.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "C:\\PhantomJS\\bin\\phantomjs.exe");
		WebDriver driverPhantom = new PhantomJSDriver(dc);
	    String data ="";   
		try {	
		//driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
		driverPhantom.get("http://bbs.eyny.com/member.php?mod=logging&action=login&loginsubmit=yes&handlekey=login&loginhash=LRiZH&inajax=1"+
		"&formhash=8ccbb822&referer=http%3A%2F%2Fbbs.eyny.com%2Fvideo.php&loginfield=username&username=larry12720&password=22340943&questionid=0&answer=&cookietime=2592000");
		driverPhantom.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		
		driverPhantom.get(url);
		//print result
		data = driverPhantom.getPageSource();
		}
		catch (TimeoutException timeoutException)
		{
			System.out.println("Inside of timeout");
			if(driverPhantom.findElement(By.id("mediaPlayer"))!=null)
			{
				System.out.println("Has data");
				data = driverPhantom.getPageSource();
			}
			return data;
		}
		finally
		{
		driverPhantom.quit();
		}
		return data;
		
	}
	
}
