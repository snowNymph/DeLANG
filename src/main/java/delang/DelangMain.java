package delang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DelangMain {
	public static void main(String args []) throws ParseException
	{
		Logger logger = LoggerFactory.getLogger(Delang.class);
		logger.info("Start DeLANG Interpreter...");
	    Delang parser = new Delang(System.in);
	    while(true)
	    {
	    	System.out.println("DeLANG>>");
			  try
			  {
				  Delang.statement();
			  }
			  catch (Exception e)
			  {
				  System.out.println("NOK.");
				  System.out.println(e.getMessage());
				  Delang.ReInit(System.in);
			  }
			  catch (Error e)
			  {
				  System.out.println("Oops.");
				  System.out.println(e.getMessage());
				  break;
			  }
	    }
	}
}
