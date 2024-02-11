import java.util.Set;
import java.util.TreeMap;

public class PhoneBookLogic {
	private TreeMap<String, Integer> treeMap;
	
	public PhoneBookLogic() {
		treeMap = new TreeMap<>();
	}
	
	public void add(String name, String phoneNumber) throws NumberFormatException {
		treeMap.put(name, stringToInt(phoneNumber));
	}
		
	public void delete(String name, String phoneNumber) throws NumberFormatException {
		treeMap.remove(name, stringToInt(phoneNumber));
	}
	
	public String search(String name) {
		String str = treeMap.get(name) + "";
		
		if (!str.equals("null")) {
			return "Name: " + name + " Phone number: " + str;
		}
		
		return "No such contact in the phone book";
	}
	
	public Contact[] loadPage(int space, int index) {
		Contact[] contactList = new Contact[space];
		int pointer = 0;
		
		for (int i = 0; i < space; i++) {
			contactList[i] = new Contact();
		}
		
		Set<String> keySet = treeMap.keySet();
		for (String key : keySet) {
			if (pointer >= index && pointer < (index + space)) {
				contactList[pointer - index].setName(key);
				contactList[pointer - index].setPhoneNumber(treeMap.get(key) + "");
			}
			pointer++;
			if (pointer >= (index + space))
				break;
		}
		
		return contactList;
	}
		
	private int stringToInt(String str) throws NumberFormatException {
		int num = Integer.parseInt(str);
		if (num < 1)
			throw new NumberFormatException("Error: Phone can not be a negative number");
		return num;
	}
}
