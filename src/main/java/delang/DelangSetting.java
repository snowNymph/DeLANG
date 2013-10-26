/* DelangSetting.java */
/* DeLANG 실행 시 setting.ini 파일을 관리하며 설정을 읽어들이거나 쓰는 역할을 한다.*/
/* 특정 명령어에 따라 설정을 변경하는 역할을 추가할 예정*/

package delang;

import java.io.*;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DelangSetting {
	private Logger logger = LoggerFactory.getLogger(DelangSetting.class);
	
	//Option List
	public static int option1;
	public static int option2;
	public static int option3;
	public static String option4;
	
	public DelangSetting()
	{
		option1 = 1;
		option2 = 2;
		option3 = 3;
		option4 = "test";
	}
	
	public boolean init()
	{
		//Check ini setting file
		boolean chkFlag = checkSetting();
		if( chkFlag == false )
		{
			logger.error("Failed to initialize setting file");
			return false;
		}
		return true;
	}
	private boolean checkSetting()
	{
		//Check ini file
		File f1 = new File("setting.ini");
		if(f1.exists() == false)
		{
			//There's no ini file
			createSetting();
		}
		else
		{
			try
			{
				Properties p = new Properties();
				//read ini file
				p.load(new FileInputStream("setting.ini"));
				
				logger.debug("option1: "+ p.getProperty("option1"));
				logger.debug("option2: "+ p.getProperty("option2"));
				logger.debug("option3: "+ p.getProperty("option3"));
				logger.debug("option4: "+ p.getProperty("option4"));
				
				option1 = Integer.parseInt(p.getProperty("option1"));
				option2 = Integer.parseInt(p.getProperty("option2"));
				option3 = Integer.parseInt(p.getProperty("option3"));
				option4 = p.getProperty("option4");
			}
			catch (Exception e)
			{
				logger.error("Error occured while reading ini file");
			}
		}
		return true;
	}
	private boolean createSetting()
	{
		try
		{
			FileOutputStream fos = null;
			Properties p = new Properties();
			//create ini file
			try
			{
				fos = new FileOutputStream("setting.ini", false);
			}
			catch(Exception e)
			{
				logger.error("Error occured while creating ini file");
			}
			finally
			{
				fos.close();
			}
			 
			//read ini file
			p.load(new FileInputStream("setting.ini"));
			
			p.setProperty("option1", Integer.toString(option1));
			p.setProperty("option2", Integer.toString(option2));
			p.setProperty("option3", Integer.toString(option3));
			p.setProperty("option4", option4);
			
			p.store(new FileOutputStream("setting.ini"), "DeLANG");
		}
		catch (Exception e)
		{
			logger.error("Error occured while reading ini file");
		}
		return true;
	}

}
