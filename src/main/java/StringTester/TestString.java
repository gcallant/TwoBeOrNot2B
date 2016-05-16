package StringTester;

/**
 * Created by Michael on 5/6/2016.
 */
public class TestString
{
    public static String testInput(String command, String[] validInputs)
    {
        for(int x = 0; x < validInputs.length; x++)
        {
            if(command.toLowerCase().equals(validInputs[x]))
            {
                return command.toLowerCase();
            }
        }
        return "";
    }
}
