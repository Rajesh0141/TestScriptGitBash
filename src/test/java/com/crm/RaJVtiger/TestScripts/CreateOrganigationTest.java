package com.crm.RaJVtiger.TestScripts;

import java.io.IOException;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.RaJVtiger.GenericUtility.BaseClass;
import com.crm.RaJVtiger.ObjectElementRepository.CreatingNewOrganizationPage;
import com.crm.RaJVtiger.ObjectElementRepository.HomePage;
import com.crm.RaJVtiger.ObjectElementRepository.InformationOfOrganizationPage;
import com.crm.RaJVtiger.ObjectElementRepository.OrganizationPage;







@Listeners(com.crm.RaJVtiger.GenericUtility.ListenersImplementationClass.class)
public class CreateOrganigationTest extends BaseClass{
	
		 @Test(groups = "smokeTest")
			public void createOrganization() throws Throwable, IOException {
			 System.out.println("rajesh");
		   
		    //fetching TestData from excel
			String ExpectedOrgName=eLib.getDataFromExcel("CreateOrg", 1, 3);
			System.out.println("organization name is "+ExpectedOrgName);
			
			//Fetching industry testData from excel
			String industry=eLib.getDataFromExcel("CreateOrg", 5, 3); 
			System.out.println("organization name is "+industry);
		
			//click On OrganizationLinkText
			HomePage homePage=new HomePage(driver);
			homePage.Organization();
			
			//click on AddImageIconclick
			OrganizationPage orgnizationPage=new OrganizationPage(driver);
			orgnizationPage.AddImageIconclick();
				
			//createNewOrganization withMandatoryFields choose IndustryType HelthCare
			CreatingNewOrganizationPage newOrgnization=new CreatingNewOrganizationPage(driver);
			newOrgnization.organizationTextField(ExpectedOrgName+jLib.getrandomNum(), industry);

			//verification for Organization
			InformationOfOrganizationPage infoOrganizationPage=new InformationOfOrganizationPage(driver);
		    String actOrgName=infoOrganizationPage.actualOrgNameText();
			
		    SoftAssert sf=new SoftAssert();
            sf.assertEquals(actOrgName.contains(ExpectedOrgName), true);
		    sf.assertAll();
		    
			
		}
}