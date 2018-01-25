package processor;

import org.json.simple.JSONObject;

public class EquationTree {
	EquationNode head;

	class EquationNode {
		long data;
		String variable;
		String operator;
		EquationNode left;
		EquationNode right;

		public EquationNode(long data, String operator, String variable) {
			this.data = data;
			this.operator = operator;
			this.left = null;
			this.right = null;
		}

	}

	public void constructEquationTree(Object object, EquationNode mover) {
		if (mover == null)
			return;
		Object lhs = new Object();
		Object rhs = new Object();
		String op = "";

		if (object instanceof JSONObject) {
			// System.out.println("instance of jsonobject");
			JSONObject a = (JSONObject) object;
			lhs = a.get("lhs");
			rhs = a.get("rhs");
			op = (String) a.get("op");

			EquationNode mover1 = new EquationNode(1000, op, null);

			if (lhs instanceof JSONObject) {
				mover.left = mover1;
				constructEquationTree((Object) lhs, mover);
			} else if (lhs instanceof Long) {
				// EquationNode operator = new EquationNode(1000, op, null);
				mover.left = mover1;

				EquationNode temp = new EquationNode((long) lhs, null, null);
				mover1.left = temp;
				System.out.println((Long) lhs);
			} else if (lhs instanceof String) {
				// EquationNode operator = new EquationNode(1000, op, null);
				mover.left = mover1;

				EquationNode temp = new EquationNode(1000, null, (String) lhs);
				mover1.left = temp;
				System.out.println((String) lhs);
			}

			if (rhs instanceof JSONObject) {
				//mover.right = mover1;
				constructEquationTree((Object) rhs, mover);
			} else if (rhs instanceof Long) {

				//mover.right = mover1;

				EquationNode temp = new EquationNode((Long) rhs, null, null);
				mover1.right = temp;
				System.out.println((Long) rhs);
			} else if (rhs instanceof String) {

				//mover.right = mover1;

				EquationNode temp = new EquationNode(1000, null, (String) rhs);
				mover1.right = temp;
				System.out.println((String) rhs);
			}
		} else if (object instanceof Long) {

			if (mover.left == null) {
				EquationNode temp = new EquationNode((Long) object, null, null);
				mover.left = temp;
				System.out.println((Long) object);
			} else {
				EquationNode temp = new EquationNode((Long) object, null, null);
				mover.right = temp;
				System.out.println((Long) object);

			}

		} else if (object instanceof String) {

			if (mover.left == null) {
				EquationNode temp = new EquationNode(1000, null, (String) object);
				mover.left = temp;
				System.out.println((String) object);
			} else {
				EquationNode temp = new EquationNode(1000, null, (String) object);
				mover.right = temp;
				System.out.println((String) object);

			}

		}

	}

	public void tree(JSONObject jsonObject) {
		Object lhs = jsonObject.get("lhs");
		Object rhs = jsonObject.get("rhs");
		String op = (String) jsonObject.get("op");
		if (op.equals("equal")) {
			EquationNode mover = new EquationNode(1000, op, null);
			head = mover;
			System.out.println("Calling from tree  class");
			constructEquationTree(lhs, mover);
			constructEquationTree(rhs, mover);
			// mover.right = new EquationNode((Long) rhs, null, null);

		}
		System.out.println("--------------");
		preetyprint(head);
	}

	public void preetyprint(EquationNode head) {

		if (head == null)
			return;
		preetyprint(head.left);
		if (head.data != 1000)
			System.out.println(head.data);
		if (head.variable != null)
			System.out.println(head.variable);
		if (head.operator != null)
			System.out.println(head.operator);
		preetyprint(head.right);

	}
}
