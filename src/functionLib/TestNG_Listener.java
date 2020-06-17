package functionLib;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNG_Listener implements ITestListener{

    @Override		
    public void onFinish(ITestContext Result) 					
    {		
                		
    }		

    @Override		
    public void onStart(ITestContext Result)					
    {		
            		
    }		

    @Override		
    public void onTestFailedButWithinSuccessPercentage(ITestResult Result)					
    {		
    		
    }		

    @Override		
    public void onTestFailure(ITestResult Result) 					
    {		
    System.out.println("Failed TC - "+Result.getName());					
    }		
	
    @Override		
    public void onTestSkipped(ITestResult Result)					
    {		
				
    }		

    @Override		
    public void onTestStart(ITestResult Result)					
    {		
    System.out.println("Start Test Case - " + Result.getName());					
    }		
		
    @Override		
    public void onTestSuccess(ITestResult Result)					
    {		
    System.out.println("Passed TC - "+Result.getName());					
    }
	
	
	
}
