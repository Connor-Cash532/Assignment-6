public class RBTNode extends BSTNode {
	public enum Color {
		REDCOLOR, BLACKCOLOR;
	}

	public Color color;

	public RBTNode(int nodeKey) {
		super(nodeKey);
		color = Color.REDCOLOR;
	}

	// Returns true if both child nodes are black. A null child is considered to
	// be black.
	public boolean areBothChildrenBlack() {
		RBTNode left = (RBTNode) getLeft();
		if (left != null && left.isRed()) {
			return false;
		}
		RBTNode right = (RBTNode) getRight();
		if (right != null && right.isRed()) {
			return false;
		}
		return true;
	}

	public RBTNode getGrandparent() {
		if (getParent() == null) {
			return null;
		}
		return (RBTNode) getParent().getParent();
	}

	// Returns this node's sibling, or null if this node does not have a sibling
	public RBTNode getSibling() {
		BSTNode parent = getParent();
		if (parent != null) {
			if (this == parent.getLeft()) {
				return (RBTNode) parent.getRight();
			}
			return (RBTNode) parent.getLeft();
		}
		return null;
	}

	// Returns the uncle of this node
	public RBTNode getUncle() {
		RBTNode grandparent = getGrandparent();
		if (grandparent == null) {
			return null;
		}
		if (grandparent.getLeft() == getParent()) {
			return (RBTNode) grandparent.getRight();
		}
		return (RBTNode) grandparent.getLeft();
	}

	// Returns true if this node is black, false otherwise
	public boolean isBlack() {
		return color == Color.BLACKCOLOR;
	}

	// Returns true if this node's left child is non-null and red. Returns false
	// otherwise.
	public boolean isLeftChildRed() {
		RBTNode leftChild = (RBTNode) getLeft();
		if (leftChild != null) {
			return leftChild.color == Color.REDCOLOR;
		}
		return false;
	}

	public boolean isParentNull() {
		return getParent() == null;
	}

	// Returns true if this node's right child is non-null and red. Returns
	// false otherwise.
	public boolean isRightChildRed() {
		RBTNode rightChild = (RBTNode) getRight();
		if (rightChild != null) {
			return rightChild.color == Color.REDCOLOR;
		}
		return false;
	}

	// Returns true if this node is red, false otherwise
	public boolean isRed() {
		return color == Color.REDCOLOR;
	}

	public String toString() {
		return String.valueOf(getKey()) + (isRed() ? " R" : " B");
	}
}