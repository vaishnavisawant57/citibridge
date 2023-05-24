package com.demo.clearingfeed.helper;
import java.io.InputStream;
import java.util.List;
import com.demo.clearingfeed.entity.Transaction;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



import java.util.ArrayList;
import java.util.Iterator;



public class Helper {

	
	//converts excel to list
	
	public static List<Transaction> ConvertExcelToListOftransaction(InputStream is){
		
		List<Transaction> list=new ArrayList<>();
		try {
			
			XSSFWorkbook workBook=new XSSFWorkbook(is);
			
			XSSFSheet sheet=workBook.getSheet("Sheet1");
			
			int rowNumber=0;
			
			Iterator<Row> iterator = sheet.iterator();
			while(iterator.hasNext())
			{
				Row  row =iterator.next();
				
				if(rowNumber==0)
				{
					rowNumber++;
					continue;
					
				}
				Iterator<Cell> cells= row.iterator();
				int cid=0;
				 
				Transaction t=new Transaction();
				
				while(cells.hasNext())
				{
					Cell cell=cells.next();
					 switch (cid)
					 {
					    case 0:
					    	t.setTransaction_ref_no(cell.getStringCellValue());
					        break;
					    case 1:
					    	t.setDate(cell.getStringCellValue());
					        break;
					    case 2:
					    	t.setPayer_account(cell.getStringCellValue());
						    break;
					    case 3:
					    	t.setPayer_name(cell.getStringCellValue());
					    	break;
					    case 4:
					    	t.setPayee_account(cell.getStringCellValue());
						    break;
					    case 5:
					    	t.setPayee_name(cell.getStringCellValue());
					    	break;
					    case 6:
					    	t.setAmount(cell.getStringCellValue());
					    	break;
					    	
					    default:
					    	break;
					    	
					 }
					 cid++;
					 
				}
				
				list.add(t);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
}
