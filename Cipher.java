import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Cipher
{
	File file;
	static String contents;
	static char[][] key = new char[5][5];
	static String letters = "abcdefghiklmnopqrstuvwxyz";
	
	public Cipher(String str)
	{
		file = new File(str);
		contents = "";
		try
		{
			Scanner scanner = new Scanner(file);
			while (scanner.hasNextLine()) 
			{
      				contents += scanner.nextLine();
			}	
		scanner.close();
		}

		catch (FileNotFoundException e) 
		{
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		for (int i=0;i<5;i++)
		{
			for (int j=0; j<5; j++)
			{
				key[i][j]=letters.charAt(5*i +j);
			}
		}
   	 }

	public String

	public static void main(String[] args)
	{
		System.out.println("This is the key:");

		Cipher a = new Cipher("text.txt");
		for (int i=0;i<5;i++)
		{
			for (int j=0; j<5; j++)
			{
				System.out.print(String.valueOf(a.key[i][j]));
			}
			System.out.println();
		}
		System.out.println(contents);
	}
}