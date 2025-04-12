public class ExtendedRedBlackTree extends RedBlackTree {
	// Each node in an ExtendedRedBlackTree is an ExtendedRBTNode
	@Override
	protected BSTNode makeNewNode(int key) {
		return new ExtendedRBTNode(key);
	}

	// Your code here
	public void insertNode(RBTNode node) {
		super.insertNode(node);
		updateCountsUpward((ExtendedRBTNode) node);
	}

	public BSTNode rotateLeft(BSTNode node){
		BSTNode rotated = super.rotateLeft(node);
		updateCountsUpward((ExtendedRBTNode) node);
		((ExtendedRBTNode)rotated).updateSubtreeKeyCount();
		if(rotated != null){
			updateCountsUpward((ExtendedRBTNode) node);
		}
		return rotated;
	}

	public BSTNode rotateRight(BSTNode node){
		BSTNode rotated = super.rotateRight(node);
		updateCountsUpward((ExtendedRBTNode) node);
		((ExtendedRBTNode)rotated).updateSubtreeKeyCount();
		if(rotated != null){
			updateCountsUpward((ExtendedRBTNode) node);
		}
		return rotated;
	}



	@Override
	public void insertionBalance(RBTNode node) {
		super.insertionBalance(node);
		updateCountsUpward((ExtendedRBTNode) node);
	}

	private void updateCountsUpward(ExtendedRBTNode node) {
		while (node != null) {
			node.updateSubtreeKeyCount();
			node = (ExtendedRBTNode) node.getParent();
		}
	}


	protected boolean removeNode(BSTNode node) {
		ExtendedRBTNode parent = (ExtendedRBTNode) node.getParent();
		boolean removed = super.removeNode(node);
		if(removed){
			if(parent != null)
				updateCountsUpward(parent);
			else if(getRoot() != null)
				((ExtendedRBTNode) getRoot()).updateSubtreeKeyCount();
		}
		return removed;
	}


	@Override
	public int getNthKey(int n) {
		ExtendedRBTNode curNode = (ExtendedRBTNode) getRoot();
		int leftCount = 0;
		int rightCount = 0;
		while(curNode != null){
			ExtendedRBTNode left = (ExtendedRBTNode) curNode.getLeft();
			ExtendedRBTNode right = (ExtendedRBTNode) curNode.getRight();

			if(left != null)
				leftCount = left.getSubtreeKeyCount();
			else
				leftCount = 0;
			if(n < leftCount){
				curNode = left;
			}
			else if(n == leftCount || right == null){
				return curNode.getKey();
			}
			else{
				curNode = right;
				n = n - (leftCount + 1);
			}

		}
		return 0;
	}
}