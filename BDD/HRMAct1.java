package stepDefinations;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class HRMAct1 {
		
	WebDriver  driver;	
	 WebDriverWait wait;
	String ChromePath =  "C:\\Users\\KIRANCHAVAN\\Documents\\SDET TestNG\\chromedriver_win32\\chromedriver.exe";
    String VacancyName ="New Job Threess";
   
   @Given("^users Logins HRM application$")	
   public void HRMLogin() {
   	try {
		System.setProperty("webdriver.chrome.driver", ChromePath);
			driver = new ChromeDriver();	
		   wait = new WebDriverWait(driver, 20);
		   driver.manage().window().maximize();	
		   System.out.println("Maximised Window");
		   
		   driver.get("http://alchemy.hguy.co:8080/orangehrm/symfony/web/index.php/auth/login");
		   driver.findElement(By.id("txtUsername")).sendKeys("orange");
		   driver.findElement(By.id("txtPassword")).sendKeys("orangepassword123");
		   driver.findElement(By.id("btnLogin")).click();
		   wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h1[text()='Dashboard']")));
		   
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
   }
	
	
   @When("^Navigate to the Recruitment page$")
	
   public void NavigateRecruitPage() {
	   try {
		
		   driver.findElement(By.xpath("//b[text()='Recruitment']")).click();
		   Thread.sleep(1000);
		   
		   
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }


   @And("^Click on the Vacancies menu$")
	
   public void ClickVacancyMenu() throws Exception {
	   	 try {
	   		driver.findElement(By.xpath("//a[@id='menu_recruitment_viewJobVacancy']")).click();
	   		Thread.sleep(1000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   	 
	   }
   
   
   @And("^Click on the Add button to navigate to the Add Job Vacancy form$")
	
   public void ClickAddButton() throws Exception {
	   try {
		driver.findElement(By.xpath("//*[@id='btnAdd']")).click();
		   Thread.sleep(2000);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	   }
   
   @And("^Fills out the necessary details and Clicks save$")
	
   public void FillDetailsandSave() throws Exception {
	   	 
	   try { 
		  Select JobTitle = new Select(driver.findElement(By.xpath("//select[@id='addJobVacancy_jobTitle']")));
		  JobTitle.selectByVisibleText("DevOps Engineer");
		   Thread.sleep(1000);
		   driver.findElement(By.id("addJobVacancy_name")).sendKeys(VacancyName);
		   
		   driver.findElement(By.id("addJobVacancy_hiringManager")).sendKeys("orange hrm");
		   driver.findElement(By.id("btnSave")).click();
		   
		   
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
	   
	   }
   
   @Then("^Verify that the vacancy was created$")
	
 public void VerifyVacancyCreated() throws Exception {
	   
	   driver.findElement(By.xpath("//a[@id='menu_recruitment_viewJobVacancy']")).click();
  		Thread.sleep(1000);
  		Select VacancyDropDown = new Select(driver.findElement(By.xpath("//select[@id='vacancySearch_jobVacancy']")));
  		VacancyDropDown.selectByVisibleText(VacancyName);
  		driver.findElement(By.id("btnSrch")).click();
  		Thread.sleep(3000);
 	   String getStr= driver.findElement(By.xpath("//a[text()='"+VacancyName+"']")).getText();
 	   System.out.println(getStr);
 	   Assert.assertEquals(VacancyName, getStr);

   }
   
   @And("^Closes the browser$")
	
   public void CloseBrowser() throws Exception {
	   	  try {
		//	driver.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	   }
   


}
