public class CipherRunner
{
	public static void main(String[] args)
	{
		Cipher.contents=args[1];
		Cipher.letters= args[2];
		if (args[0].equals("encode"))
		{
			Cipher.mode=true;
		}
		if (args[0].equals("decode"))
		{
			Cipher.mode=false;
		}
	
		Cipher.run();
	}
}