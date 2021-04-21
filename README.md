# Mini-Word-Processor
Using Java Collection - Stack to implement a mini-word processor

For this assignment you will be writing a mini-word processor. You are mainly concerned with inserting characters, deleting characters and positioning the cursor. If you think about it, that's mostly what the user does when entering or editing text. The user only adds and deletes characters next to the cursor. You can therefore get away with using two stacks. One stack holds the characters to the left of the cursor. The other stack holds the characters to the right of the cursor. This diagram shows how to represent the word elephantine with the cursor between the letters p and h:
	     LEFT STACK               RIGHT STACK
         ___ ___ ___ ___       ___ ___ ___ ___ ___ ___ ___
	|_e_|_l_|_e_|_p_|     |_h_|_a_|_n_|_t_|_i_|_n_|_e_|
	bottom        top     top                    bottom
        To move the cursor right, you would just pop the h from the right stack and push it onto the left stack:

	     LEFT STACK               RIGHT STACK
         ___ ___ ___ ___ ___       ___ ___ ___ ___ ___ ___
	|_e_|_l_|_e_|_p_|_h_|     |_a_|_n_|_t_|_i_|_n_|_e_|
	bottom            top     top                bottom
Build a small word-processor class that uses this two-stack representation. The class will include methods for inserting and deleting characters, and for moving the cursor.
To help you along, you will implement the MiniWPI interface that is linked here.
Your MiniWP constructor must be able to take in an initial string. The default will initialize an empty string.
To test your class, you will retrieve the commands from a Queue then execute them.
Make sure you use appropriate exception handling such as dealing with an empty stack, entering invalid commands into the queue, etc.
Sample output for part 2
For the following commands:
commandqueue.put("insert J");
commandqueue.put("insert K");
commandqueue.put("insert L");
commandqueue.put("move left");
commandqueue.put("move right");
commandqueue.put("move left");
commandqueue.put("move left");
commandqueue.put("search K");
This would be the output:
Starting Word processor!
----------
Initial:        [i, n, i, t, i, a, l,  , c, o, n, t, e, n, t, s]|[]
insert J:       [i, n, i, t, i, a, l,  , c, o, n, t, e, n, t, s, J]|[]
insert K:       [i, n, i, t, i, a, l,  , c, o, n, t, e, n, t, s, J, K]|[]
insert L:       [i, n, i, t, i, a, l,  , c, o, n, t, e, n, t, s, J, K, L]|[]
move left:      [i, n, i, t, i, a, l,  , c, o, n, t, e, n, t, s, J, K]|[L]
move right:     [i, n, i, t, i, a, l,  , c, o, n, t, e, n, t, s, J, K, L]|[]
move left:      [i, n, i, t, i, a, l,  , c, o, n, t, e, n, t, s, J, K]|[L]
move left:      [i, n, i, t, i, a, l,  , c, o, n, t, e, n, t, s, J]|[K, L]
search K:       [i, n, i, t, i, a, l,  , c, o, n, t, e, n, t, s, J, K]|[L]
Press any key to continue . . .
