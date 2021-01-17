package javaApp;


public class EvenSum {

	public static void main(String[] args) {
		int num = Integer.parseInt(args[0]);
		int even[] = new int[num+1];
		int sum = 0, j = 0;
		// Insert your code here
        String evennums = "";
		for (int i = 0; i < even.length; i++) {
			even[i] = j;
			if (even[i]!=0 && even[i] % 2 == 0) {
				
				evennums = evennums+","+Integer.toString(even[i]);
				sum = sum + even[i];
			}
			j++;
		}
		evennums = evennums.replaceFirst(",", "");
		System.out.println(evennums);
		System.out.println(sum);
	}
}
