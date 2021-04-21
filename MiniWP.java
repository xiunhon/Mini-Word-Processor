import java.util.Stack;
/**
 * 
 * @author Group 3: Tien N., Victoria T., Niklas K.
 * Part 2:
 * Create a mini word processor
 */
class MiniWP implements MiniWPI {
	private static Stack<Character> left = MiniWPI.left;
	private static Stack<Character> right = MiniWPI.right;
	private char[] charArray;

	// default constructor
	public MiniWP() {
		this.left = new Stack<>();
		this.right = new Stack<>();
	}

	// initial string constructor
	public MiniWP(String word) {
		charArray = word.toCharArray();
		for (int i = 0; i<charArray.length; i++) {
			left.push(charArray[i]);
		}
	}

	@Override
	/** 
	 * Is cursor at the start of the text? 
	 * start of text means left stack is empty
	 */
	public boolean isAtStart() {
		if (left.empty()) {
			return true;
		}
		else return false;
	}

	@Override
	/** 
	 * Is cursor at the end of the text? 
	 * end of text means right stack is empty
	 */
	public boolean isAtEnd() {
		if (right.empty()) {
			return true;
		}
		else return false;
	}

	@Override
	/** Insert c into the string at the cursor. */
	public void insertChar(char c) {
		left.push(c);
	}

	@Override
	/**
	 * Move cursor left 1 character. 
	 * If we're already at the start of the string, do nothing.
	 */
	public void moveLeft() {
		if (!isAtStart()) {
			right.push(left.pop());
		}
	}

	@Override
	/**
	 * Move cursor right 1 character. 
	 * If we're already at the end of the string, do nothing.
	 */
	public void moveRight() {
		if (!isAtEnd()) {
			left.push(right.pop());
		}
	}

	@Override
	/**
	 * Delete character before the cursor. If there is none, do nothing.
	 */
	public void backspace() {
		if (!isAtStart()) {
			left.pop();
		}
	}

	@Override
	/**
	 * Delete character after the cursor. If there is none, do nothing.
	 */
	public void delete() {
		if (!isAtEnd()) {
			right.pop();
		}
	}

	@Override
	/** Move cursor to start of the text. */
	public void moveToStart() {
		if (!isAtStart()) {
			while (!left.empty()) {
				right.push(left.peek());
				left.pop();
			}
		}
	}

	@Override
	/** Move cursor to end of the text. */
	public void moveToEnd() {
		if (!isAtEnd()) {
			while (!right.empty()) {
				left.push(right.peek());
				right.pop();
			}
		}
	}
	@Override
	/**
	 * Convert to string. The cursor position is ignored.
	 * @see toStringCursor
	 */
	public String toString() {
		Stack<Character> temp = new Stack<Character>();
		while (!left.empty()) {
			temp.push(left.peek());
			left.pop();
		}
		String leftstr = "";
		String rightstr = "";
		while (!temp.empty()) {
			leftstr += temp.peek();
			left.push(temp.pop());
		}
		temp = right;
		while (!temp.empty()) {
			rightstr += temp.pop();
		}
		return leftstr + rightstr ;
	}

	@Override
	/**
	 * Like toString, but the special character | is included to mark the cursor
	 * position.
	 */
	public String toStringCursor() { // TODO Auto-generated method stub
		return "|";
	}

	@Override
	/**
	 * Search forward for the next occurrence of c that starts at the cursor or
	 * later. If found, leave the cursor immediately after that occurrence and
	 * return true. Else, leave the cursor at the end of the text and return false.
	 */
	public boolean search(char c) { 
		if (!isAtEnd()) {
			if (right.contains(c)) {
				while (right.peek() != c) {
					left.push(right.peek());
					right.pop();
				}
				left.push(right.pop());
				return true;
			}
		}
		moveToEnd();
		return false;
	}
	
	@Override
	/** Method that reads in the string that was retrieved from the top of the Queue and
	 *  executes the appropriate command. It also calls a method to print out the command
	 *  just executed and the resulting stacks
	 */
	public void processCommand(String s) { 
		if (s != null) {
			boolean valid = false;
			if (s.equalsIgnoreCase("move to end")) {
				moveToEnd();
				valid = true;
			}
			else if (s.equalsIgnoreCase("move to start")) {
				moveToStart();
				valid = true;
			}
			else if (s.equalsIgnoreCase("move left")) {
				moveLeft();
				valid = true;
			}
			else if (s.equalsIgnoreCase("move right")) {
				moveRight();
				valid = true;
			}
			else if( s.equalsIgnoreCase("backspace")) {
				backspace();
				valid = true;
			}
			else if( s.equalsIgnoreCase("delete")) {
				delete();
				valid = true;
			}
			else {
				String[] strArray = s.split("\\s+");
				if (strArray.length == 2 ) {
					char[] charArray = strArray[1].toCharArray();
					if (strArray[0].equalsIgnoreCase("insert") && charArray.length == 1) {
						insertChar(charArray[0]);
						valid = true;
					}
					else if (strArray[0].equalsIgnoreCase("search") && charArray.length == 1) {
						search(charArray[0]);
						valid = true;
					}
				}
			}
			//calls a method to print out the command just executed and the resulting stacks
			if (valid) {
				printtest(s);
			}
			else printtest(String.format("%1$-15s", s) + ":invalid command");
		}
		else printtest(String.format("%1$-15s", s) + ":invalid command");
	}

	@Override
	/* Method to print out a command and the results */
	public void printtest(String s) {
		if (s.contains("invalid")) {
			System.out.println(s);
		}
		else {
			Stack<Character> temp = new Stack<Character>();
			while (!right.empty()) {
				temp.push(right.peek());
				right.pop();
			}
			String rightstr = temp.toString();
			String leftstr = left.toString();
			while (!temp.empty()) {
				right.push(temp.pop());
			}
			s = String.format("%1$-15s", s);
			System.out.println(s + ":" + (leftstr + toStringCursor() + rightstr));
		}
	}
}//end of class