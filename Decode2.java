import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Decode2
{
	
	public static void main (String[] args)
	{
		String test = "";
		try
		{
			File file = new File (args[0]);
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) 
			{
      				test += scanner.nextLine();
			}	
		scanner.close();
		}
		catch (FileNotFoundException e) 
		{
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		test=test.toLowerCase();

		for (int x=0; x<26; x++)
		{
			if (validLine (test))
			{
				System.out.println(test);
			}
			test = shift(test);
		}
	}
	public static String replace(String str, int index)
	{
		String first, last, result= "";
		char put;
		int temp;
		
		first = str.substring(0,index);
		last = str.substring(index+1, str.length());

		temp =(int)str.charAt(index)-97;

		if (temp >=0 && temp<26)
		{
			temp = (temp+1)%26;
			put = (char) (temp+97);
			result = first+put+last;
		}
		else
		{
			result = first+ " " + last;
		}


		return result;
	}


	public static String shift(String input)
	{
		for (int x=0; x< input.length(); x++)
		{
			input = replace(input, x);
			//System.out.println (input);
		}
			return input;

	}

	public static boolean validWord(String input)
	{
		StringTokenizer tokenizer;
		tokenizer = new StringTokenizer (input, "bcdfghjklmnpqrstvwxz");
		return tokenizer.hasMoreTokens();
	}

	public static boolean validLine(String line)
	{
		String word;
		StringTokenizer tokenizer;
		boolean result=true;

		tokenizer = new StringTokenizer (line);
		while (tokenizer.hasMoreTokens())
		{
			word = tokenizer.nextToken();
			if (!validWord(word))
				result = false;
		}

		return result;
	}
}