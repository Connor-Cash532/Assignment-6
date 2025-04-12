public class ExtendedAVLNode extends AVLNode {
	private int subtreeKeyCount;

	public ExtendedAVLNode(int nodeKey) {
		super(nodeKey);
		subtreeKeyCount = 1;
	}

	@Override
	public int getSubtreeKeyCount() {
		return subtreeKeyCount;
	}

	// Your code here
// 	public ExtendedAVLNode getRight(){
// 	   return (ExtendedAVLNode) super.getRight();
// 	}
// 	public ExtendedAVLNode getLeft(){
// 	   return (ExtendedAVLNode) super.getLeft();
// 	}
// 	public ExtendedAVLNode getParent() {
//        return (ExtendedAVLNode) super.getParent();
//     }


	public void updateSubtreeKeyCount(){
		int count = 1;
		ExtendedAVLNode left = (ExtendedAVLNode) getLeft();
		ExtendedAVLNode right = (ExtendedAVLNode) getRight();

		if (left != null) count += left.getSubtreeKeyCount();
		if (right != null) count += right.getSubtreeKeyCount();

		subtreeKeyCount = count;
	}

}