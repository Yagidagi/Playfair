import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Cipher
{

	static String contents, letters;
	static char[][] key = new char[5][5];
	static boolean mode;

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

	public static void setup()
	{
		contents=contents.toLowerCase();
		letters = letters.toLowerCase();

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

		for (int i=0;i<5;i++)
		{
			for (int j=0; j<5; j++)
			{
				key[i][j]=letters.charAt(5*i +j);
			}
		}

		if(mode==true)
		{
			System.out.println("You want to encode:");
		}
		if(mode==false)
		{
			System.out.println("You want to decode:");
		}
		System.out.println(contents.toUpperCase());
		System.out.println();

		System.out.println("This is the key:");

		for (int i=0;i<5;i++)
		{
			for (int j=0; j<5; j++)
			{
				System.out.print(String.valueOf(key[i][j]).toUpperCase());
			}
			System.out.println();
		}
		System.out.println();

	}

	public static void addX()
	{
		char temp;
		for (int k=0; k<contents.length()-1;k=k+2)
		{
			temp = contents.charAt(k+1);
			if (temp == contents.charAt(k))
			{
				contents = contents.substring(0,k+1)+ "x" + contents.substring(k+1, contents.length());
			}
		}
	}

	public static void processLetterPair(char first, char second, int x)
	{
		int[] one = findLoc(first);
		int[] two = findLoc(second);

		if (one[0]==two[0])
		{
			if (mode)
			{
				moveLetterPair("down", first, second, x);
			}
			else
			{
				moveLetterPair("up", first, second, x);
			}
		}

		if (one[1]==two[1])
		{
			if (mode)
			{
				moveLetterPair("right", first, second, x);
			}
			else
			{
				moveLetterPair("left", first, second, x);
			}
		}

		if (one[0]!=two[0] && one[1]!=two[1])
		{
			moveLetterPair("flip", first, second, x);
		}

	}

	public static void moveLetterPair(String directions, char first, char second, int x)
	{
		int[] one = findLoc(first);
		int[] two = findLoc(second);

		if (directions.equals("up"))
		{
			first = key[(one[0]+4)%5][one[1]];
			second = key[(two[0]+4)%5][two[1]];
		}

		if (directions.equals("down"))
		{
			first = key[(one[0]+1)%5][one[1]];
			second = key[(two[0]+1)%5][two[1]];
		}

		if (directions.equals("left"))
		{
			first = key[one[0]][(one[1]+4)%5];
			second = key[two[0]][(two[1]+4)%5];
		}

		if (directions.equals("right"))
		{
			first = key[one[0]][(one[1]+1)%5];
			second = key[two[0]][(two[1]+1)%5];
		}

		if (directions.equals("flip"))
		{
				first = key[one[0]][two[1]];
				second = key[two[0]][one[1]];
		}

		contents = replace(contents, x, first);
		contents = replace(contents, x+1, second);
	}

	public static void processAllLetters()
	{
		for (int x=0;x<=contents.length()-2;x=x+2)
		{
			char first = contents.charAt(x);
			char second = contents.charAt(x+1);

			processLetterPair(first,second,x);
		}
	}

	public static void run()
	{

		setup();
		System.out.println("Processing");
		addX();
		if (contents.length()%2==1) 
		{
			if(contents.charAt(contents.length()-1)==(char)122)
			{
				contents = contents+"x";
			}
			else
			{
				contents = contents+"z";
			}
		}
		processAllLetters();



		System.out.println("Done:");
		System.out.println(contents.toUpperCase());
	}
}