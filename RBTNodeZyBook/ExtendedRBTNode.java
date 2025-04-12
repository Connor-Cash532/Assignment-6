public class ExtendedRBTNode extends RBTNode {
	private int subtreeKeyCount;

	public ExtendedRBTNode(int nodeKey) {
		super(nodeKey);
		subtreeKeyCount = 1;
	}

	@Override
	public int getSubtreeKeyCount() {
		return subtreeKeyCount;
	}

	// Your code here
	public void updateSubtreeKeyCount(){
		int count = 1;
		RBTNode left = (RBTNode) getLeft();
		RBTNode right = (RBTNode) getRight();
		if(left != null)
			count += left.getSubtreeKeyCount();
		if(right != null)
			count += right.getSubtreeKeyCount();

		subtreeKeyCount = count;
	}
}