package com.SaiChan.Assersions;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class APITesting001_Assertion_Types {
    //Testng Assertions
    //Expected result == Actual result


    public void hard_Asset_Example(){// fails to execute if assertion is false
        System.out.println("Start of program");
        Assert.assertTrue(false);
        System.out.println("End of program");


        Assert.assertEquals("pramod","pramod");//true
        Assert.assertEquals("pramod","pramo");//false

    }
    public void Soft_Assert_Example() {// execute even  if assertion is false
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(false);//It will not stop the Execution
        System.out.println("Start of program");
        Assert.assertTrue(false);
        softAssert.assertAll(); //THis will report all errors
    }

}
