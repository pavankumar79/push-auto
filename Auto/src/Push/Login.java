package Push;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Login{
	
	@Parameters({"url","Uname","Pswd"})
	@Test
	public void Sanity(String scoopurl, String username, String password) throws IOException, InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\pavankumar_k12\\eclipse-workspace\\Auto\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(20));
		
		driver.get(scoopurl);
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@id='i0116']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@id='idSIButton9']")).click();
		w.until(ExpectedConditions.elementToBeClickable(By.id("i0118")));
		    driver.findElement(By.xpath("//input[@id='i0118']")).sendKeys(password);
		w.until(ExpectedConditions.elementToBeClickable(By.id("idSIButton9")));
		    driver.findElement(By.id(("idSIButton9"))).click();
        w.until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[contains(text(),'Call')])[2]")));
		    driver.findElement(By.xpath("(//*[contains(text(),'Call')])[2]")).click();
		    
		    
		// Scoop Dashboard
		
        w.until(ExpectedConditions.titleIs("Dashboard"));
        driver.findElement(By.xpath("//*[@id=\"menubar-common\"]/div/ul/li[1]")).click();
		driver.findElement(By.xpath("//*[contains(text(), 'Breaking News Alert')]")).click();
		
		// PUSH window handle
		
		Set<String> tabs=driver.getWindowHandles();
		Iterator<String> it=tabs.iterator();
		String tab1=it.next();
		String tab2=it.next();
		driver.switchTo().window(tab2);
		 
		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Alert Console')]")));
		System.out.print( driver.getTitle());
		System.out.println(" loaded successfully");   
	
		
		// PUSH search
		
		driver.findElement(By.xpath("//input[contains(@name,'search')]")).sendKeys("gateway");
		WebElement search= driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[4]/div[2]/ol/div/li[1]"));
		w.until(ExpectedConditions.visibilityOf(search)).isDisplayed();
		System.out.println("Results got fetched");
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(SrcFile, new File("C:/Users/pavankumar_k12/eclipse-workspace/Auto/screenshots/results.png"));
		
		// Create and send alert
		
	    w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'New Alert')]")));
		driver.findElement(By.xpath("//a[@href=\"/ui/push/alert\"]")).click();
		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value=\"Breaking News\"]")));
		driver.findElement(By.xpath("(//button[@class=\"css-n1q2xf\"])[2]")).click();
		driver.findElement(By.xpath("//input[@name=\"url-input\"]")).sendKeys("https://www.google.com/");
		driver.findElement(By.xpath("(//textarea[@class=\"css-28z66 e1m6aiqj0\"])[2]")).sendKeys("automated push alert");
		
		driver.findElement(By.xpath("(//button[@class=\"css-n1q2xf\"])[3]")).click();
		driver.findElement(By.xpath("//textarea[@class=\"css-1tzjpbq e1m6aiqj0\"]")).sendKeys("automated email alert");
		driver.findElement(By.xpath("//div[@class=\"css-17qzwj7 ProseMirror\"]")).sendKeys("email alert body");
		
		driver.findElement(By.xpath("//div/button[@class=\"css-qvdquh\"]")).click();
		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"css-175rvmj e9ovg102\"]")));
		driver.findElement(By.xpath("//*[contains(text(),'Send all alerts')]")).click();
        w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class=\"css-1aorlql e1e6n5b77\"]")));
		System.out.println("sent automated alert");
		File SrcFile1=scrShot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(SrcFile1, new File("C:/Users/pavankumar_k12/eclipse-workspace/Auto/screenshots/alertsent.png"));
			
	
		driver.quit();
	}

}
