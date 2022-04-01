
package com.crm.RaJVtiger.TestScripts;

import java.io.IOException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.RaJVtiger.GenericUtility.BaseClass;
import com.crm.RaJVtiger.ObjectElementRepository.CreateNewProductPage;
import com.crm.RaJVtiger.ObjectElementRepository.HomePage;
import com.crm.RaJVtiger.ObjectElementRepository.ProductInformationPage;
import com.crm.RaJVtiger.ObjectElementRepository.ProductPage;





@Listeners(com.crm.RaJVtiger.GenericUtility.ListenersImplementationClass.class)
public class CreateProductTest extends BaseClass {
	@Test(groups = "smoke")
	public void createProduct() throws Throwable, IOException {
		
		
		
		
	    //fetching test data from excel
	    String expectedProduct=eLib.getDataFromExcel("ProductName", 1, 3);
		System.out.println(expectedProduct);
		

        //click on productLink
        HomePage homePage=new HomePage(driver);
        homePage.products();
        
        //click on new addIconImage 
        ProductPage productPage=new ProductPage(driver);
        productPage.clickAddImageIcon();
        
        //create product with mandatoryFields  
		CreateNewProductPage createNewProductPage=new CreateNewProductPage(driver);
		createNewProductPage.createNewOrganization(expectedProduct+jLib.getrandomNum());
	
		//verification for Products
		ProductInformationPage informationProduct=new ProductInformationPage(driver);
		String actualProduct=informationProduct.getInformationMsg();
		
		SoftAssert sf=new SoftAssert();
		sf.assertEquals(actualProduct.contains(expectedProduct), true);
		sf.assertAll();
	}
}
