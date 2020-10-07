public class CipherRunner
{
	public static void main(String[] args)
	{
		Cipher a = new Cipher(args[1], args[2]);
		if (args[0].equals("encode"))
		{
			a.encode();
		}

		if (args[0].equals("decode"))
		{
			a.decode();
		}

	}
}