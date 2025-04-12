public class RedBlackTree extends BinarySearchTree {
	private void prepareForRemoval(RBTNode node) {
		if (tryCase1(node)) {
			return;
		}

		RBTNode sibling = node.getSibling();
		if (tryCase2(node, sibling)) {
			sibling = node.getSibling();
		}
		if (tryCase3(node, sibling)) {
			return;
		}
		if (tryCase4(node, sibling)) {
			return;
		}
		if (tryCase5(node, sibling)) {
			sibling = node.getSibling();
		}
		if (tryCase6(node, sibling)) {
			sibling = node.getSibling();
		}

		RBTNode nodeParent = (RBTNode) node.getParent();
		sibling.color = nodeParent.color;
		nodeParent.color = RBTNode.Color.BLACKCOLOR;
		if (node == nodeParent.getLeft()) {
			((RBTNode) sibling.getRight()).color = RBTNode.Color.BLACKCOLOR;
			rotateLeft(nodeParent);
		} else {
			((RBTNode) sibling.getLeft()).color = RBTNode.Color.BLACKCOLOR;
			rotateRight(nodeParent);
		}
	}

	private boolean tryCase1(RBTNode node) {
		if (node.isRed() || node.isParentNull()) {
			return true;
		}
		return false; // not case 1
	}

	private boolean tryCase2(RBTNode node, RBTNode sibling) {
		if (sibling.isRed()) {
			RBTNode nodeParent = (RBTNode) node.getParent();
			nodeParent.color = RBTNode.Color.REDCOLOR;
			sibling.color = RBTNode.Color.BLACKCOLOR;
			if (node == nodeParent.getLeft()) {
				rotateLeft(nodeParent);
			} else {
				rotateRight(nodeParent);
			}
			return true;
		}
		return false; // not case 2
	}

	private boolean tryCase3(RBTNode node, RBTNode sibling) {
		RBTNode nodeParent = (RBTNode) node.getParent();
		if (nodeParent.isBlack() && sibling.areBothChildrenBlack()) {
			sibling.color = RBTNode.Color.REDCOLOR;
			prepareForRemoval(nodeParent);
			return true;
		}
		return false; // not case 3
	}

	private boolean tryCase4(RBTNode node, RBTNode sibling) {
		RBTNode nodeParent = (RBTNode) node.getParent();
		if (nodeParent.isRed() && sibling.areBothChildrenBlack()) {
			nodeParent.color = RBTNode.Color.BLACKCOLOR;
			sibling.color = RBTNode.Color.REDCOLOR;
			return true;
		}
		return false; // not case 4
	}

	private boolean tryCase5(RBTNode node, RBTNode sibling) {
		if (sibling.isLeftChildRed()) {
			if (!sibling.isRightChildRed()) {
				if (node == node.getParent().getLeft()) {
					sibling.color = RBTNode.Color.REDCOLOR;
					((RBTNode) sibling.getLeft()).color = RBTNode.Color.BLACKCOLOR;
					rotateRight(sibling);
					return true;
				}
			}
		}
		return false; // not case 5
	}

	private boolean tryCase6(RBTNode node, RBTNode sibling) {
		if (!sibling.isLeftChildRed()) {
			if (sibling.isRightChildRed()) {
				if (node == node.getParent().getRight()) {
					sibling.color = RBTNode.Color.REDCOLOR;
					((RBTNode) sibling.getRight()).color = RBTNode.Color.BLACKCOLOR;
					rotateLeft(sibling);
					return true;
				}
			}
		}
		return false; // not case 6
	}

	@Override
	protected void insertNode(BSTNode node) {
		// Red-black tree insertion starts with the standard BST insertion
		super.insertNode(node);

		// Color the node red, then balance
		((RBTNode) node).color = RBTNode.Color.REDCOLOR;
		insertionBalance((RBTNode) node);
	}

	protected void BSTInsertNode(BSTNode node) {
		super.insertNode(node);
	}

	@Override
	protected BSTNode makeNewNode(int key) {
		return new RBTNode(key);
	}

	@Override
	protected boolean removeNode(BSTNode bstNode) {
		if (bstNode == null) {
			return false;
		}

		RBTNode node = (RBTNode) bstNode;
		if ((node.getLeft() != null) && (node.getRight() != null)) {
			// get the node's predecessor
			RBTNode predecessorNode = (RBTNode) node.getLeft();
			while (predecessorNode.getRight() != null) {
				predecessorNode = (RBTNode) predecessorNode.getRight();
			}

			// get predecessor's key, then recursively remove the predecessor node
			int predecessorKey = predecessorNode.getKey();
			removeNode(predecessorNode);

			// Assign the node's key with the now-removed predecessor node's key
			node.setKey(predecessorKey);

			return true;
		}

		if (node.isBlack()) {
			prepareForRemoval(node);
		}
		super.removeNode(node);

		// One special case if the root was changed to red
		RBTNode rootNode = (RBTNode) getRoot();
		if (rootNode != null && rootNode.isRed()) {
			rootNode.color = RBTNode.Color.BLACKCOLOR;
		}

		return true;
	}

	public RedBlackTree() {
		// Note: Parent class's constructor does all needed work
	}

	public void insertionBalance(RBTNode node) {
		// If node is the tree's root, then color node black and return
		if (node.isParentNull()) {
			node.color = RBTNode.Color.BLACKCOLOR;
			return;
		}

		// Reference to parent node is needed for remaining operations
		RBTNode parent = (RBTNode) node.getParent();

		// If parent is black, then return without any changes
		if (parent.isBlack()) {
			return;
		}

		// Reference to grandparent and uncle are needed for remaining operations
		RBTNode grandparent = node.getGrandparent();
		RBTNode uncle = node.getUncle();

		// If parent and uncle are both red, then color parent and uncle black,
		// color grandparent red, recursively balance grandparent, then return
		if (uncle != null && uncle.isRed()) {
			parent.color = uncle.color = RBTNode.Color.BLACKCOLOR;
			grandparent.color = RBTNode.Color.REDCOLOR;
			insertionBalance(grandparent);
			return;
		}

		// If node is parent's right child and parent is grandparent's left
		// child, then rotate left at parent, update node and parent to reference
		// parent and grandparent, respectively
		if (node == parent.getRight() && parent == grandparent.getLeft()) {
			rotateLeft(parent);
			node = parent;
			parent = (RBTNode) node.getParent();
		}
		// Else if node is parent's left child and parent is grandparent's right
		// child, then rotate right at parent, update node and parent to reference
		// parent and grandparent, respectively
		else if (node == parent.getLeft() && parent == grandparent.getRight()) {
			rotateRight(parent);
			node = parent;
			parent = (RBTNode) node.getParent();
		}

		// Color parent black and grandparent red
		parent.color = RBTNode.Color.BLACKCOLOR;
		grandparent.color = RBTNode.Color.REDCOLOR;

		// If node is parent's left child, then rotate right at grandparent,
		// otherwise rotate left at grandparent
		if (node == parent.getLeft()) {
			rotateRight(grandparent);
		} else {
			rotateLeft(grandparent);
		}
	}

	public boolean isNullOrBlack(RBTNode node) {
		if (node == null) {
			return true;
		}
		return node.isBlack();
	}

	public boolean isNotNullAndRed(RBTNode node) {
		if (node == null) {
			return false;
		}
		return node.isRed();
	}

	@Override
	public int getNthKey(int n) {
		// Note: The ExtendedRedBlackTree.java implementation of
		// getNthKey() should override this.
		return 0;
	}
}