package delang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DelangMain {
	public static void main(String args []) throws ParseException
	{
		Logger logger = LoggerFactory.getLogger(Delang.class);
		logger.info("Start DeLANG Interpreter...");
		DelangSetting setting = new DelangSetting();
		setting.init();
	    Delang parser = new Delang(System.in);
	    while(true)
	    {
	    	System.out.print("DeLANG>>");
			  try
			  {
				  Delang.statement();
			  }
			  catch (Exception e)
			  {
				  logger.error("Error occured while parsing. Program Exit");
				  System.out.println(e.getMessage());
				  break;
			  }
			  catch (Error e)
			  {
				  logger.warn("Invalid Syntax");
				  System.out.println(e.getMessage());
				  Delang.ReInit(System.in);
			  }
	    }
	}
}
