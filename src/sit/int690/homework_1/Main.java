package sit.int690.homework_1;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.TreeMap;
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
		LinkedList<Sale> lkl = new LinkedList<Sale>();
		try(Stream<String> stream = Files.lines(Paths.get(filename))) {
			stream.forEach((row) -> {
	    	Sale saleObj = getRecord(row);
	    	lkl.add(saleObj);
	    });
			
	  } catch(IOException e) {
	      e.printStackTrace();
	  }
		return lkl;
	}
	
	public static void testHashMap(LinkedList<Sale> lkl, HashMap<Integer, Sale> hmap) {
		long startTime = System.nanoTime();

		for (Sale saleObj : lkl) {
			hmap.put(saleObj.getId(), saleObj);
		}

		hmap.get(60844);
		long endTime = System.nanoTime();

		long duration = (endTime - startTime);

		getTime("Hash Map", duration);
	}
	
	public static void testLinkedHashMap(LinkedList<Sale> lkl, LinkedHashMap<Integer, Sale> lhm) {
		long startTime = System.nanoTime();

		for (Sale saleObj : lkl) {
			lhm.put(saleObj.getId(), saleObj);
		}

		lhm.get(60844);
		long endTime = System.nanoTime();

		long duration = (endTime - startTime);
		
		getTime("Linked Hash Map", duration);
	}
	
	public static void testTreeMap(LinkedList<Sale> lkl, TreeMap<Integer, Sale> tmap) {
		long startTime = System.nanoTime();

		for (Sale saleObj : lkl) {
			tmap.put(saleObj.getId(), saleObj);
		}

		tmap.get(60844);
		long endTime = System.nanoTime();

		long duration = (endTime - startTime);

		getTime("Tree Map", duration);
	}

	public static void testArrayList(LinkedList<Sale> lkl, ArrayList<Sale> arrl) {
		long startTime = System.nanoTime();

		for (Sale saleObj : lkl) {
			arrl.add(saleObj);
		}

		arrl.get(60844);
		long endTime = System.nanoTime();

		long duration = (endTime - startTime);

		getTime("Array List", duration);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList<Sale> lkl = readFileStream("src/resources/sale.txt");
		
  	HashMap<Integer, Sale> hmap = new HashMap<Integer, Sale>();
  	LinkedHashMap<Integer, Sale> lhm = new LinkedHashMap<Integer, Sale>();
  	TreeMap<Integer, Sale> tmap = new TreeMap<Integer, Sale>();
  	ArrayList<Sale> arrl = new ArrayList<Sale>(100000);

  	testHashMap(lkl, hmap);
  	testLinkedHashMap(lkl, lhm);
  	testTreeMap(lkl, tmap);
  	testArrayList(lkl, arrl);

	}
}
