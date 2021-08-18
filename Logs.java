import java.util.Formatter;

class Logs {
	private static int 				indexArgs = -1;
	private static StringBuilder	str1 = new StringBuilder("NN,");
	private static StringBuilder	str2 = new StringBuilder("");
	private static StringBuilder	str3 = new StringBuilder("");	

	private static int sumStringForVM(int ind, StringBuilder str, int a, int flag) {
		if (flag == 1)
		{
			for (int i = 0; i < a; i++)
			{
				str.append("DN" + ind + ",");
				ind++;
			}			
		}
		else if (flag == 2)
		{
			str.append("DN" + ind + ",");
			ind++;
		}
		return ind;
	}

	private static void distributionVM(int a, int b) {
		int ind = 1;
		for (int i = 0; i < a - 1; i++)
		{
			str1.append("DN" + ind + ",");
			ind++;
		}
		if (indexArgs - 1 > 0)
		{
			ind = sumStringForVM(ind, str2, a, 1);
			if (b > 0)
				ind = sumStringForVM(ind, str2, a, 2);
			if (indexArgs - 1 > 1)
			{
				ind = sumStringForVM(ind, str3, a, 1);
				if (b == 2)
					ind = sumStringForVM(ind, str3, a, 2);
			}
		}
	}

	private static void markupTable() {
		indexArgs++;
		int a = indexArgs / 3;
		int b = indexArgs % 3;
		distributionVM(a, b);
		System.out.printf("%-20s %-35s %-35s %-35s %n", "Количество DN:", "VM1", "VM2", "VM3");
		System.out.printf("%-20d %-35s %-35s %-35s %n", indexArgs - 1, str1, str2, str3);
	}

	private static boolean isDigit(String[] argc) throws NumberFormatException {
		try {
			for (String str : argc)
			{
				int tmp = Integer.parseInt(str);
				indexArgs = tmp;
				if (tmp < 1 || tmp > 20)
				{
					System.out.println("ERROR: Вad argument(s). Please, write only 1 number from 1 to 20.");
					return false;
				}
			}
			return true;
		} catch (NumberFormatException e) {
			System.out.println("ERROR: Вad argument(s). Please, enter only number.");
			return false;
		}
	}
	
	public static void main(String[] args) {
		if(args.length != 1)
		{
			System.out.println("ERROR: Вad argument(s). Please, write only 1 number.");
			System.exit(-1);
		}
		else if (isDigit(args) == false)
			System.exit(-2);
		markupTable();
	}
}