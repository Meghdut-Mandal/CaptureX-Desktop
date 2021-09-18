package console;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DemoConsole
{
	private static final String CONSOLE_NAME = "Console Demo";      // This is your console's name, and will
                                                                    // show up in the upper lefthand corner of
                                                                    // the window.
    
    private static final String ICON_IMAGE_FILE = "SomeImage.png";  // The filepath to the icon for the window goes here.
    
    private static final Color BACKGROUND_COLOR = Color.BLACK;      // The background color
    private static final Color FOREGROUND_COLOR = Color.GREEN;      // The text color
	
	private static final Map<String, InputProcessor> commandMap = new HashMap<>(10); // A hashmap to store commands and triggers.
    
    
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
        
    //STEP 1: Initialize and define all commands, in the form of InputProcessors.
        
        InputProcessor clearScreen = (args14, console) -> console.cls();
        
        InputProcessor terminateProgram = (args13, console) -> System.exit(0);
        
        InputProcessor echo = (args12, console) -> {
            console.write(args12[1]); // only echos the first word...
        };
        
//        InputProcessor IDontUnderstand = (args1, console) -> console.write("Sorry, I don't understand that command");
        
    // STEP 2: Link all of these command codes to a one-word String command:
        
        commandMap.put("cls",clearScreen);          //String command does not need to match variable name from above
        
        commandMap.put("close",terminateProgram);
        commandMap.put("exit",terminateProgram);    //Multiple strings can be used for the same command, but multiple 
                                                    //commands may not be referenced by the same string.
        
        commandMap.put("echo",echo);                //String command COULD be the same as the variable name, if you want.
        
//        commandMap.put("help",IDontUnderstand);
    // STEP 3: Initialize the JFrame:
        
		JFrame frame = new JFrame(CONSOLE_NAME);
        try {frame.setIconImage(ImageIO.read(new File(ICON_IMAGE_FILE)));} catch (IOException ignored) {}
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(677, 343);                    // Official Windows Command Prompt size, looks beautiful and can be resized
                                                    // after being opened if you so desire.
		
        //Init console
		Console console = new Console(Color.BLACK, Color.GREEN, 
				new Font(Font.MONOSPACED, Font.BOLD, 14), "$ ");
		console.setPreferredSize(new Dimension(677, 343)); // Same as above
		
		console.setCompletionSource(new DefaultCompletionSource("help", "echo", "cls", "close","exit")); // String commands go here as well.
		
        console.setProcessor(new InputProcessor() { // This processor breaks a statement into args and passes them to the matching
                                                    // command defined in the hashmap above (the part in step 2)
			private final int requests = 0;
            
            public void process(String[] args, Console console)
            {
                //1. Print for debugging:
//                System.out.println("Got Req. " + ++requests + ": '" + args[0] + "'");
//
//                System.out.println("asked: " + Arrays.toString(args));
                //4. Process list of arguments
                if (args.length > 0 && commandMap.containsKey(args[0].toLowerCase()))
                    commandMap.get(args[0].toLowerCase()).process(args, console);

            }
		});
		frame.add(console);
        frame.addComponentListener(console);
		frame.pack();
        console.setScreenHeight((int) frame.getContentPane().getSize().getHeight());
        console.redirectSystemStreams();
		frame.setVisible(true);
        System.out.println("Hello ");
	}
    
    public static String removeQuotes(String arg) { //Param: a quote.
        return arg.substring(1,arg.length()-1);
    }

}
