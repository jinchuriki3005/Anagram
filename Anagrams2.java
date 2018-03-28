import java.util.*;

public class Anagrams2 {
private List<String> list;
	private Map<String, LetterInventory> dictionary;
	
	// pre: 'list' is not empty and does not contain  duplicate or non empty elements.
	// post: constructs AnagramSolver object using a dictionary of words contained in 'list'.
	public Anagrams2(List<String> list) {
		this.list = list;
		this.dictionary = new HashMap<String, LetterInventory>();
		for (String word : this.list) { 
			this.dictionary.put(word, new LetterInventory(word));
		}
	}
	
	// prints up to 'max' words that contain any or all letters contained in 's';  
	// if 'max' is 0, prints all matching words. 
	// pre: 'max' is non-negative; otherwise, throws IllegalArgumentExeption 
	// post: N/A
	public void print(String s, int max) {
		if (max < 0) {
			throw new IllegalArgumentException();
		}
		LetterInventory originalCatalog = new LetterInventory(s);
		List<String> dictionaryEdited = new ArrayList<String>();
		for (String word : list) {
			LetterInventory catalog = dictionary.get(word);
			if (originalCatalog.subtract(catalog) != null) {
				dictionaryEdited.add(word);
			}
		}
		print(max, originalCatalog, dictionaryEdited, new Stack<String>());
	}
	
	// helper method for print method
	// pre: 'catalog', 'dictionaryEdited', and 'result' are not null.
	// post: N/A
	private void print(int max, LetterInventory catalog, List<String> dictionaryEdited, 
			Stack<String> result) {
		if (catalog.isEmpty() && (max == 0 || result.size() <= max)) { 
				System.out.println(result);
		} else if (result.size() < max) {
			for (String word : dictionaryEdited) {
				LetterInventory record = dictionary.get(word);
				LetterInventory catalogModified = catalog.subtract(record);
				if (catalogModified != null) {
					result.push(word); 			
					print(max, catalogModified, dictionaryEdited, result); 
					result.pop(); 
				}
			}	
		}
	}
	
}