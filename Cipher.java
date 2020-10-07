import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Cipher
{
	File ciphertext, keytext;
	static String contents;
	static char[][] key = new char[5][5];
	static String letters = "";
	
	public Cipher(String ciphers, String keys)
	{
		ciphertext = new File(ciphers);
		keytext = new File(keys);
		contents = "";
		try
		{
			Scanner scanner = new Scanner(ciphertext);
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

		try
		{
			Scanner scanner = new Scanner(keytext);
			while (scanner.hasNextLine()) 
			{
      				letters += scanner.nextLine();
			}	
		scanner.close();
		}

		catch (FileNotFoundException e) 
		{
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		
		contents=contents.toLowerCase();
		letters = letters.toLowerCase();

		for (int i=0;i<5;i++)
		{
			for (int j=0; j<5; j++)
			{
				key[i][j]=letters.charAt(5*i +j);
			}
		}

		System.out.println("You entered:");
		System.out.println(contents);
		System.out.println();

		System.out.println("This is the key:");

		for (int i=0;i<5;i++)
		{
			for (int j=0; j<5; j++)
			{
				System.out.print(String.valueOf(key[i][j]));
			}
			System.out.println();
		}
		System.out.println();
   	 }

	public static int[] findLoc(char a)
	{
		int[] result = {-1,-1};
		for (int i=0;i<5;i++)
		{
			for (int j=0; j<5; j++)
			{
				if(key[i][j]==a) 
				{
					result[0]=i;
					result[1]=j;
				}
			}
		}

		return result;
	}
	
	public static String replace(String str, int i, char a)
	{
		return str.substring(0,i)+ a + str.substring(i+1, str.length());
	}

	public void encode()
	{
		System.out.println("Encoding...");
		char first, second;
		int[] one, two;
		for (int x=0; x<contents.length();x++)
		{
			int num = (int)contents.charAt(x)-97;
			if (num<0 || num>=26)
			{
				contents = contents.substring(0,x) + contents.substring(x+1, contents.length());
			}

			if (contents.charAt(x)==(char)106)
			{
				contents = replace(contents, x, (char)105);
			}
		}

		char temp;
		for (int k=0; k<contents.length()-1;k++)
		{
			temp = contents.charAt(k+1);
			if (temp == contents.charAt(k))
			{
				contents = contents.substring(0,k+1)+ "x" + contents.substring(k+1, contents.length());
			}
		}

		if (contents.length()%2==1) contents = contents+"z";

		for (int x=0;x<=contents.length()-2;x=x+2)
		{
			first = contents.charAt(x);
			second = contents.charAt(x+1);

			one = findLoc(first);
			two = findLoc(second);

			if (one[0]==two[0])
			{
				first = key[(one[0]+1)%5][one[1]];
				second = key[(two[0]+1)%5][two[1]];
			}

			if (one[1]==two[1])
			{
				first = key[one[0]][(one[1]+1)%5];
				second = key[two[0]][(two[1]+1)%5];
			}

			if (one[0]!=two[0] && one[1]!=two[1])
			{
				first = key[one[0]][two[1]];
				second = key[two[0]][one[1]];
			}

			contents = replace(contents, x, first);
			contents = replace(contents, x+1, second);
		}

		System.out.println("Done:");
		System.out.println(contents);
	
	}

	public void decode()
	{
		
		System.out.println("Decoding...");
		char first, second;
		int[] one, two;

		for (int x=0;x<=contents.length()-2;x=x+2)
		{
			first = contents.charAt(x);
			second = contents.charAt(x+1);
		
			
			one = findLoc(first);
			two = findLoc(second);


			one = findLoc(first);
			two = findLoc(second);

			if (one[0]==two[0])
			{
				first = key[(one[0]+4)%5][one[1]];
				second = key[(two[0]+4)%5][two[1]];
			}

			if (one[1]==two[1])
			{
				first = key[one[0]][(one[1]+4)%5];
				second = key[two[0]][(two[1]+4)%5];
			}

			if (one[0]!=two[0] && one[1]!=two[1])
			{
				first = key[one[0]][two[1]];
				second = key[two[0]][one[1]];
			}

			contents = replace(contents, x, first);
			contents = replace(contents, x+1, second);
		}



		System.out.println("Done:");
		System.out.println(contents);
	}



	public static void main(String[] args)
	{

		Cipher a = new Cipher("cipher.txt", "key.txt");
		a.decode();

	}
}