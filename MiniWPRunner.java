import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Tien N.
 * tester for all methods of MiniWP class
 */
public class MiniWPRunner {

	public static void main(String[] args) {
		MiniWP otherWP = new MiniWP();
		otherWP.printtest("Default");
		System.out.println("Default constructor makes 2 empty Stacks. Expected: empty String == " + otherWP.toString());
		MiniWP myWP = new MiniWP("initial contents");
		myWP.printtest("initial");
		Queue<String> commandqueue = new LinkedList<>();
		commandqueue.add("insert J"); // initial contentsJ|
		commandqueue.add("insert K"); // initial contentsJK|
		commandqueue.add("insert L"); // initial contentsJKL|
		commandqueue.add("insert LL"); // invalid command
		commandqueue.add("insert"); // invalid command
		commandqueue.add("move to start"); // |initial contentsJKL
		commandqueue.add("search a"); // initia|l contentsJKL
		commandqueue.add("search "); // invalid command
		commandqueue.add("search s"); // initial contents|JKL
		commandqueue.add("search o"); // initial contentsJKL|
		commandqueue.add("move left"); // initial contentsJK|L
		commandqueue.add("move right"); // initial contentsJKL|
		commandqueue.add("move right"); // in|itial contentsJKL|
		commandqueue.add("move left"); // initial contentsJK|L
		commandqueue.add("backspace"); // initial contentsJ|L
		commandqueue.add("delete"); // initial contentsJ|
		commandqueue.add("search a"); // initial contentsJ|
		commandqueue.add("move left"); // initial contents|J
		commandqueue.add("insert s"); // initial contentss|J
		commandqueue.add("move to end"); // initial contentssJ|
		commandqueue.add("move"); // invalid command
		commandqueue.add("move up"); // invalid command
		while (!commandqueue.isEmpty()) {
			String command = commandqueue.poll();
			myWP.processCommand(command);
		}
		System.out.println();
		System.out.println("Cursor is at start? Expected: false == " + myWP.isAtStart());
		System.out.println("Cursor is at end? Expected: true == " + myWP.isAtEnd());
		System.out.println();
		System.out.println("Print text:");
		System.out.println("Expected: initial contentssJ == " + myWP.toString());
	}//end of main
}//end of class
