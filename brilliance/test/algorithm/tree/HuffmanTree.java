package algorithm.tree;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
* @ClassName: HuffmanTree
* @Package algorithm.tree
* @Description: TODO(这里用一句话描述这个类的作用)
* @author Russell Xun Jiang
* @date 2016年12月3日 下午12:26:52
* 核心应用：n中情况，利用Huffman树。可以使得总体来看，平均到达每个元素（节点）的比较次数/路径长度最少。
	原理：要使平均到达每个元素或情境的操作步骤最少，必须把出现次数/概率最大的元素或情境，放在最前面，
	把出现次数少的元素放在后面。这样判断的时候，总体判断次数才能降低。
构造Huffman树的过程：
1.把所有元素/情境出现的次数/权重列出来，每一种元素都对应一个权重，每个元素都是单独一棵树，形成一个森林。
2.先从中选择权重最小的2个元素（权重最小的树），作为新树的左右节点，新书的根节点是这两个元素的权重之和。
3.把新树的左右元素拿掉，把新树的根节点，放回原来的森林中。（即之前的2个元素用新的元素替代）
4.再次公森林中去2个最小的元素，形成新树。
注：当取的2个最小元素，存在其中一个是初始元素，有个一个是后来生成的元素时，随便取哪个都行，WPL结果一样。这是的Huffman树就有多种形态。
5.循环上述过程，直到森林中只有一个单节点的树（只有一个元素）。
把这个过程画成树，这个数就是最优二叉树。
每个叶子节点就是初始的每个元素。
带权路径长度（WPL: weighted path length）：从根节点到每个叶子节点所经历的分支个数就是找到此元素平均判断的次数。也是这个元素的编码。从左边分支过来用0表示，右边分支过来用1表示。
当所有元素（叶子节点）的WPL总和最小时，就是最有二叉树，说明平均到达这些元素的操作需要最少。见右下方的树。

应用题：
假设一个文本文件TFile中只包含7个字符{A，B，C，D，E，F，G}，这7个字符在文本中出现的次数为{5，24，7，17，34，5，13}
利用哈夫曼树可以为文件TFile构造出符合前缀编码要求的不等长编码。
*/
public class HuffmanTree {

	public static void main(String[] args) {
		//哈夫曼树
		String[] names = {"A","B","C","D","E","F","G"};
		double[] weights = {5,24,7,17,34,5,13};
		Map<String,String> huffmanNodes = HuffmanEncode(names,weights);
		Iterator<Map.Entry<String,String>> ite = huffmanNodes.entrySet().iterator();
		while(ite.hasNext()){
			Map.Entry<String,String> item = ite.next();
			System.out.println(item+"。key="+item.getKey()+",value="+item.getValue());
		}
	}
	
	/** 
	* @Title: HuffmanEncode 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param names	原名称
	* @param @param weight	权重
	* 					
	* @param @return    设定文件 
	* @return Map<String,String>    返回类型 
	* 					key元素名称，value是Huffman编码
	* @throws 
	* 分析：
	* 	1.将初始元素，按照Huffman规则，生成Huffman树。
	* 	2.遍历树的每个叶子节点。拿到叶子的路径即为每个元素的编码。（从左分支来的取0，右分支来的取1）
	*/ 
	public static Map<String,String> HuffmanEncode(String[] names,double[] weights){
		//init 
		List<TreeNode> list = new LinkedList<TreeNode>();
		for(int i=0;i<names.length;i++){
			TreeNode node = new TreeNode(names[i],weights[i],TreeNode.ELE_TYPE_SOURCE);
			list.add(node);
		}
		int count = 0;
		//build tree
		while(list.size()!=1){
			count++;
			//sort to get minimum
			Collections.sort(list);
			for(int i=0;i<list.size();i++){
				System.out.println("第"+count+"轮排序后："+list.get(i).getEleValue());
			}
			TreeNode minimumNode1 = list.get(0);
			TreeNode minimumNode2 = list.get(1);
			TreeNode tempNode = new TreeNode(minimumNode1.getEleName()+minimumNode2.getEleName(),
					minimumNode1.getEleValue()+minimumNode2.getEleValue(),minimumNode1,minimumNode2,TreeNode.ELE_TYPE_DERIVED);
			System.out.println("第"+count+"轮排序后：临时节点信息，名称："+tempNode.getEleName()+",值："+tempNode.getEleValue()+
					"，左孩子："+tempNode.left.getEleName()+"，右孩子："+tempNode.right.getEleName()+
					",节点类型："+tempNode.getEleType());
			list.remove(0);//删除最小的元素集合升序。第一个最小的。
			list.remove(0);//删除第一个后。再删除第二个最小的。
			list.add(tempNode);
		}
		TreeNode xx = list.get(0);
		//traverse each tree leaf nodes to get encoded string for each element
		List<TreeNode> nodes = traverseTreeNodes(list.get(0),new LinkedList<TreeNode>());//whole tree
		Map<String,String> map = new HashMap<String,String>();//Huffman nodes
		for(int i=0;i<nodes.size();i++){
			System.out.println("遍历树,名称："+nodes.get(i).getEleName()+",值："+nodes.get(i).getEleValue()+
					"，左孩子："+(nodes.get(i).left!=null?nodes.get(i).left.getEleName():null)+
					"，右孩子："+(nodes.get(i).right!=null?nodes.get(i).right.getEleName():null)+
					",节点类型："+nodes.get(i).getEleType()+",Huffman编码："+nodes.get(i).getHuffmanCode());
			if(TreeNode.ELE_TYPE_SOURCE==nodes.get(i).getEleType()){
				map.put(nodes.get(i).getEleName(), nodes.get(i).getHuffmanCode());
			}
		}
		return map;
	}
	
	/** 
	* @Title: getTreeNodes 
	* @Description: 遍历树——递归算法
	* @param @param node
	* 				树的某个节点
	* @param @return     
	* @return List<TreeNode>    
	* 				返回所有节点信息 
	* @throws 
	*/ 
	public static List<TreeNode> traverseTreeNodes(TreeNode node,List<TreeNode> nodes){
		if(node.left==null&&node.right==null){
			nodes.add(node);
			return null;
		}
		TreeNode leftNode = node.left;
		TreeNode rightNode = node.right;
//		if(node.left!=null||node.right!=null){
//			nodes.add(node);
//			traverseTreeNodes(leftNode,nodes);
//			traverseTreeNodes(rightNode,nodes);
//		}
		if(node.left!=null){
			nodes.add(node);
			leftNode.setHuffmanCode(node.getHuffmanCode()+"0");
			traverseTreeNodes(leftNode,nodes);
		}
		if(node.right!=null){
			nodes.add(node);
			rightNode.setHuffmanCode(node.getHuffmanCode()+"1");
			traverseTreeNodes(rightNode,nodes);
		}

		return nodes;
	}
	
	/**
	* @ClassName: TreeNode
	* @Package algorithm.tree
	* @Description: 树的节点的定义
	* @author Russell Xun Jiang
	* @date 2016年12月3日 下午3:07:36
	*/
	public static class TreeNode implements Comparable{//实现Comparable接口的compareTo方法，使用Collections.sort排序。
		private String eleName;//节点名称
		private double eleValue;//节点值
		private TreeNode left;//左孩子
		private TreeNode right;//右孩子
		private int eleType;//节点类型（0：初始元素节点；1：后生成的元素节点）
		private String huffmanCode = "";//哈夫曼编码
		
		private static final int ELE_TYPE_SOURCE= 0;//初始元素节点类型
		private static final int ELE_TYPE_DERIVED= 1;//后生成的元素节点类型
		
		@Override
		public int compareTo(Object o) {
			if(o==null){
				return -1;
			}
			TreeNode obj = (TreeNode)o;
			if(this.eleValue<obj.eleValue){
				return -1;
			}else if(this.eleValue==obj.eleValue){
				return 0;
			}else{
				return 1;
			}
		}
		
		public TreeNode(){
			super();
		}
		public TreeNode(String eleName,double eleValue){
			super();
			this.eleName = eleName;
			this.eleValue = eleValue;
		}
		public TreeNode(String eleName,double eleValue,int eleType){
			super();
			this.eleName = eleName;
			this.eleValue = eleValue;
			this.eleType = eleType;
		}
		public TreeNode(String eleName,double eleValue, TreeNode left, TreeNode right){
			super();
			this.eleName = eleName;
			this.eleValue = eleValue;
			this.left = left;
			this.right = right;
		}
		public TreeNode(String eleName,double eleValue, TreeNode left, TreeNode right,int eleType){
			super();
			this.eleName = eleName;
			this.eleValue = eleValue;
			this.left = left;
			this.right = right;
			this.eleType = eleType;
		}
		public String getEleName() {
			return eleName;
		}
		public void setEleName(String eleName) {
			this.eleName = eleName;
		}
		public double getEleValue() {
			return eleValue;
		}
		public void setEleValue(double eleValue) {
			this.eleValue = eleValue;
		}
		public TreeNode getLeft() {
			return left;
		}
		public void setLeft(TreeNode left) {
			this.left = left;
		}
		public TreeNode getRight() {
			return right;
		}
		public void setRight(TreeNode right) {
			this.right = right;
		}
		public int getEleType() {
			return eleType;
		}
		public void setEleType(int eleType) {
			this.eleType = eleType;
		}
		public String getHuffmanCode() {
			return huffmanCode;
		}
		public void setHuffmanCode(String huffmanCode) {
			this.huffmanCode = huffmanCode;
		}
	}

}