package sit.int690.lab_2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.stream.Stream;

import model.Sale;

public class Main {

	public static Sale getRecord(String data) {
    String record = data.replaceAll("\\'|\\(|\\)|\\;", "");
    String[] records = record.split("\\,(?!\\,|\\s)");

    Sale saleObj = new Sale(Integer.parseInt(records[0]), records[1], records[2]);
		return saleObj;
	}
	
	public static void getTime(String section, long time) {
		double convert = (double) time / 1_000_000_000;
		System.out.println(section + " => " + convert);
	}
	
	public static LinkedList<Sale> readFileStream(String filename) {
		long startTime = System.nanoTime();
		LinkedList<Sale> lkl = new LinkedList<Sale>();
		try(Stream<String> stream = Files.lines(Paths.get(filename))) {
			stream.forEach((row) -> {
	    	Sale saleObj = getRecord(row);
	    	lkl.add(saleObj);
	    });
			long endTime = System.nanoTime();

			long duration = (endTime - startTime);
			
			getTime("Stream", duration);
	  } catch(IOException e) {
	      e.printStackTrace();
	  }
		return lkl;
	}
	
	public static LinkedList<Sale> readFileBufferedReader(String filename) {
		long startTime = System.nanoTime();
		LinkedList<Sale> lkl = new LinkedList<Sale>();
		BufferedReader objReader = null;
	  try {
	   String strCurrentLine;

	   objReader = new BufferedReader(new FileReader(filename));

	   while ((strCurrentLine = objReader.readLine()) != null) {
	  	 Sale saleObj = getRecord(strCurrentLine);
	     lkl.add(saleObj);
	   }
	   
			long endTime = System.nanoTime();

			long duration = (endTime - startTime);
			
			getTime("Bufferer", duration);
	   
	  } catch (IOException e) {

	   e.printStackTrace();

	  } finally {
	  	try {
				objReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
	  return lkl;
	 }
	
	public static LinkedList<Sale> readRAF(String filename) {
		long startTime = System.nanoTime();
		LinkedList<Sale> lkl = new LinkedList<Sale>();
		try {
			RandomAccessFile raf = new RandomAccessFile(filename, "rw");
			Sale s = new Sale();
			while(raf.getFilePointer() < raf.length()) {
				int saleID = raf.readInt();
				byte[] transaction = new byte[s.SALE_TRANSECTION];
				raf.read(transaction);
				byte[] item = new byte[s.ITEM_SIZE];
				raf.read(item);
				Sale ss = new Sale(saleID, new String(transaction), new String(item));
//				ss.toString();
				lkl.add(ss);
			}
			long endTime = System.nanoTime();

			long duration = (endTime - startTime);
			
			getTime("RAF", duration);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lkl;
	}
	
	public static Sale readRAFByRecordNumber(String filename, int recordNumber) {
		long startTime = System.nanoTime();
		Sale ss = null;
		try {
			Sale s = new Sale();
			RandomAccessFile raf = new RandomAccessFile(filename, "rw");
			long position = (recordNumber - 1) * (4+s.SALE_TRANSECTION+s.ITEM_SIZE);
			raf.seek(position);
			
			int saleID = raf.readInt();
			byte[] transaction = new byte[s.SALE_TRANSECTION];
			raf.read(transaction);
			byte[] item = new byte[s.ITEM_SIZE];
			raf.read(item);
			
			ss = new Sale(saleID, new String(transaction), new String(item));
			
			long endTime = System.nanoTime();

			long duration = (endTime - startTime);
			
			getTime("RAF by record", duration);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ss;
	}
	
	public static void writeRAF(String filename, LinkedList<Sale> lkl) {
		try {
			RandomAccessFile raf = new RandomAccessFile(filename, "rw");
			for(Sale s: lkl) {
				raf.writeInt(s.getId());
				raf.write(s.getNoInBytes());
				raf.write(s.getNameInBytes());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		readFileStream("src/resources/sale.txt");
		readFileBufferedReader("src/resources/sale.txt");
		LinkedList<Sale> lkl = readRAF("src/resources/sale.raf");
		readRAFByRecordNumber("src/resources/sale.raf", 29070);

		writeRAF("src/resources/mySale.raf", lkl);
		readRAF("src/resources/mySale.raf");

	}

}
