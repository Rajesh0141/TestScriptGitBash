package com.crm.RaJVtiger.TestScripts;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.RaJVtiger.GenericUtility.BaseClass;
import com.crm.RaJVtiger.ObjectElementRepository.ContactInformationPage;
import com.crm.RaJVtiger.ObjectElementRepository.ContactPage;
import com.crm.RaJVtiger.ObjectElementRepository.CreatingNewContactPage;
import com.crm.RaJVtiger.ObjectElementRepository.CreatingNewOrganizationPage;
import com.crm.RaJVtiger.ObjectElementRepository.HomePage;
import com.crm.RaJVtiger.ObjectElementRepository.InformationOfOrganizationPage;
import com.crm.RaJVtiger.ObjectElementRepository.OrganizationPage;
import com.crm.RaJVtiger.ObjectElementRepository.OrganizationWindowPage;

@Listeners(com.crm.RaJVtiger.GenericUtility.ListenersImplementationClass.class)
public class OrgTest extends BaseClass{
	 @Test(groups={"smoke","IntegrationTest"})
		public void createContactChooseOrganization() throws Throwable, IOException {
	  //fetch organization name in excel sheet
		String expectedOrgName=eLib.getDataFromExcel("CreateCon", 4, 3)+jLib.getrandomNum();

		System.out.println("Rajesh");
		System.out.println("organization name is "+expectedOrgName);
		
		String industry=eLib.getDataFromExcel("CreateCon", 8, 3);
		
		System.out.println("organization name is "+industry);
		
		String expectedContactName=eLib.getDataFromExcel("CreateCon", 1, 3);
		
		System.out.println("organization name is "+expectedContactName);
		
		//click on organization link
		HomePage homePage=new HomePage(driver);
		homePage.Organization();
	   //click on "+" image
		OrganizationPage orgnizationPage=new OrganizationPage(driver);
		orgnizationPage.AddImageIconclick();
	   //enter orgname
		
	   //select industry type
	    CreatingNewOrganizationPage newOrgnizationname=new CreatingNewOrganizationPage(driver);
	    newOrgnizationname.organizationTextField(expectedOrgName, industry);
		//validation organization
	    InformationOfOrganizationPage infoOrganizationPage=new InformationOfOrganizationPage(driver);
	    String actualOrgname=infoOrganizationPage.actualOrgNameText();
	    
	    Assert.assertEquals(actualOrgname.contains(expectedOrgName), true);
	    
		//element will be clickAble 
		
		WebElement contacts = homePage.getContactsLink();
		
		wLib.waitForElementToBeClickAble(driver, contacts);
		
	    //Create contact
		homePage.Contacts(driver);
		//Create new contact
		ContactPage contactPage=new ContactPage(driver);
		contactPage.clickAddIcon();
		//creating new Contact with MandatoryFields and Choose organization
		CreatingNewContactPage createNewContact=new CreatingNewContactPage(driver);
		createNewContact.contactLastName(expectedContactName, actualOrgname, driver);
		//Organization Window Page Open
		OrganizationWindowPage selectOrgName=new OrganizationWindowPage(driver);
		selectOrgName.chooseOrganizationIn0Window(driver, expectedOrgName);
		createNewContact.contactSave(driver, actualOrgname);
		//verification contact InforMantion
		ContactInformationPage contactInfoPage=new ContactInformationPage(driver);
		String actualContactName=contactInfoPage.ActualContacxtInfo();
		
		Assert.assertEquals(actualContactName.contains(expectedContactName), true);
		}
		


}
