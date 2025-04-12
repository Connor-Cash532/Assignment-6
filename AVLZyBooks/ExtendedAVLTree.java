public class ExtendedAVLTree extends AVLTree {
	// Each node in an ExtendedAVLTree is an ExtendedAVLNode
	@Override
	protected BSTNode makeNewNode(int key) {
		return new ExtendedAVLNode(key);
	}

	// Your code here
	public void insertNode(AVLNode node) {
		super.insertNode(node);
		updateCountsUpward((ExtendedAVLNode) node);
	}

	@Override
	protected AVLNode rebalance(AVLNode node) {
		AVLNode newRoot = super.rebalance(node);
		if (newRoot != null) {
			updateSubtree((ExtendedAVLNode) newRoot);
		}
		return newRoot;
	}

	private void updateSubtree(ExtendedAVLNode node) {
		if (node.getLeft() != null) {
			updateSubtree((ExtendedAVLNode) node.getLeft());
		}
		if (node.getRight() != null) {
			updateSubtree((ExtendedAVLNode) node.getRight());
		}
		node.updateSubtreeKeyCount();
	}


	private void updateCountsUpward(ExtendedAVLNode node) {
		while (node != null) {
			node.updateSubtreeKeyCount();
			node = (ExtendedAVLNode) node.getParent();
		}
	}

	public boolean removeNode(AVLNode node) {
		ExtendedAVLNode parent = (ExtendedAVLNode) node.getParent();
		boolean result = super.removeNode(node);
		if (result && parent != null) {
			updateCountsUpward(parent);
		}
		return result;
	}





	@Override
	public int getNthKey(int n) {
		ExtendedAVLNode curNode = (ExtendedAVLNode) getRoot();
		int leftCount = 0;
		int rightCount = 0;
		while(curNode != null){
			ExtendedAVLNode left = (ExtendedAVLNode) curNode.getLeft();
			ExtendedAVLNode right = (ExtendedAVLNode) curNode.getRight();

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
