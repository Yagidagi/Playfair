public class CipherRunner
{
	public static void main(String[] args)
	{
		Cipher a = new Cipher(args[1], args[2]);
		a.decode();

	}
}