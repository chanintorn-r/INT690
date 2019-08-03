package sit.int690.first;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.stream.Stream;



public class Main {
	
	public static Sale getRecord(String data) {
    String record = data.replaceAll("\\'|\\(|\\)|\\;", "");
    String[] records = record.split("\\,(?!\\,|\\s)");

    Sale saleObj = new Sale(Integer.parseInt(records[0]), records[1], records[2]);
		return saleObj;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
  	HashMap<Integer, Sale> hmap = new HashMap<Integer, Sale>();
  	LinkedHashMap<Integer, Sale> lhm = new LinkedHashMap<Integer, Sale>();
  	TreeMap<Integer, Sale> tmap = new TreeMap<Integer, Sale>();

		try(Stream<String> stream = Files.lines(Paths.get("src/sit/int690/first/sale.txt"))) {
				long startTime = System.nanoTime();
				stream.forEach((row) -> {
		    	Sale saleObj = getRecord(row);
		    	hmap.put(saleObj.getId(), saleObj);
		    });
	
		    hmap.get(60844);
				long endTime = System.nanoTime();
	
				long duration = (endTime - startTime);
				System.out.println(duration);
	  } catch(IOException e) {
	      e.printStackTrace();
	  }

		try(Stream<String> stream = Files.lines(Paths.get("src/sit/int690/first/sale.txt"))) {
			long startTime = System.nanoTime();
			stream.forEach((row) -> {
      	Sale saleObj = getRecord(row);
      	lhm.put(saleObj.getId(), saleObj);
      });

			lhm.get(60844);
			long endTime = System.nanoTime();

			long duration = (endTime - startTime);
			System.out.println(duration);
	  } catch(IOException e) {
	      e.printStackTrace();
	  }

		try(Stream<String> stream = Files.lines(Paths.get("src/sit/int690/first/sale.txt"))) {
			long startTime = System.nanoTime();
			stream.forEach((row) -> {
      	Sale saleObj = getRecord(row);
      	tmap.put(saleObj.getId(), saleObj);
      });

			tmap.get(60844);
			long endTime = System.nanoTime();

			long duration = (endTime - startTime);
			System.out.println(duration);
	  } catch(IOException e) {
	      e.printStackTrace();
	  }

	}
}
