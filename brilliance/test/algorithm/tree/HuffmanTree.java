/**
 * 遍历就是查找每一个节点。
	插入，删除就是先找到要操作的节点，再把前驱后继重新设置下。
	遍历可以找出树的节点数，树高，层数的任意关于树的信息，所以遍历是树的各种操作的关键。
 */
package algorithm.tree;

import java.io.FileNotFoundException;
import java.util.*;

import org.apache.commons.lang3.ArrayUtils;

import corejava.Final.Student;

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
	
	private static TreeNode curNode;

	public TreeNode getCurNode() {
		return curNode;
	}
	public void setCurNode(TreeNode curNode) {
		this.curNode = curNode;
	}
	static int diguiCount=0;
	static int feidiguiCount=0;

	public static void main(String[] args){
		
//		//先序递归创建树 - 直接输入字符串方式
//		String[] sourceNames = {"a","b","d","#","e","#","#","f","g","#","#","#","c","#","#"};
//		TreeNode node1 = createTreeFirstRecursive(null,sourceNames);
//		traverseTreeNodesPreorder(node1,new LinkedList<TreeNode>());
//		//打印结果 - 读取文件方式
//		TreeNode node2 = createTreeFirstRecursive2(null,new Scanner(new File("test/algorithm/tree/tree_input.txt")));
//		traverseTreeNodes(node2,new LinkedList<TreeNode>());
		
		
		
		//递归遍历树。即/brilliance/test/algorithm/tree/tree.png
		/**
		 *       a
		 *      / \
		 *     b   c
		 *    / \
		 *   d   f
		 *    \  / 
		 *     e g
		 */
		TreeNode a = new TreeNode("a",1);
		TreeNode b = new TreeNode("b",1);
		TreeNode c = new TreeNode("c",1);
		TreeNode d = new TreeNode("d",1);
		TreeNode e = new TreeNode("e",1);
		TreeNode f = new TreeNode("f",1);
		TreeNode g = new TreeNode("g",1);
		a.setLeft(b);
		a.setRight(c);
		b.setLeft(d);
		b.setRight(f);
		c.setLeft(null);
		c.setRight(null);
		d.setLeft(null);
		d.setRight(e);
		e.setLeft(null);
		e.setRight(null);
		f.setLeft(g);
		f.setRight(null);
		g.setLeft(null);
		g.setRight(null);

		TreeNode a1 = new TreeNode("a1",1);
		TreeNode b1 = new TreeNode("b1",1);
		TreeNode c1 = new TreeNode("c1",1);
		TreeNode d1 = new TreeNode("d1",1);
		TreeNode e1 = new TreeNode("e1",1);
		TreeNode f1 = new TreeNode("f1",1);
		TreeNode g1 = new TreeNode("g1",1);
		TreeNode h1 = new TreeNode("h1",1);
		TreeNode i1 = new TreeNode("i1",1);
		TreeNode j1 = new TreeNode("j1",1);
		TreeNode k1 = new TreeNode("k1",1);
		TreeNode l1 = new TreeNode("l1",1);
		TreeNode m1 = new TreeNode("m1",1);
		TreeNode n1 = new TreeNode("n1",1);
		TreeNode o1 = new TreeNode("o1",1);
		a1.setLeft(b1);
		a1.setRight(c1);
		b1.setLeft(d1);
		b1.setRight(e1);
		c1.setLeft(f1);
		c1.setRight(g1);
		d1.setLeft(h1);
		d1.setRight(i1);
		e1.setLeft(j1);
		e1.setRight(k1);
		f1.setLeft(l1);
		f1.setRight(m1);
		g1.setLeft(n1);
		g1.setRight(o1);


//		//先序遍历
//		List<TreeNode> nodes = traverseTreeNodesPreorder(a,new LinkedList<TreeNode>());
//		System.out.println("tree node size: "+nodes.size()+",先序递归遍历节点总数："+diguiCount);
		List<TreeNode> nodes1 = traverseTreeNodesPreorder(a1,new LinkedList<TreeNode>());
		System.out.println("tree node size: "+nodes1.size()+",先序递归遍历节点总数："+diguiCount);
//		//中序遍历1
//		traverseTreeNodesInorder1(a);
//		//中序遍历2
//		traverseTreeNodesInorder2(a);
//		//后序遍历
//		traverseTreeNodesPostorder(a);
		/**output:
		 先序当前节点： a, 节点类型： 中。左孩子：b右孩子：c
		 先序当前节点： b, 节点类型： 中。左孩子：d右孩子：f
		 先序当前节点： d, 节点类型： 中。左孩子：null右孩子：e
		 先序当前节点： e, 节点类型： 中。左孩子：null右孩子：null
		 先序当前节点： f, 节点类型： 中。左孩子：g右孩子：null
		 先序当前节点： g, 节点类型： 中。左孩子：null右孩子：null
		 先序当前节点： c, 节点类型： 中。左孩子：null右孩子：null
		 tree node size: 7
		 中序1。当前节点： d, 节点类型： 中。左孩子：null右孩子：e
		 中序1。当前节点： e, 节点类型： 中。左孩子：null右孩子：null
		 中序1。当前节点： b, 节点类型： 中。左孩子：d右孩子：f
		 中序1。当前节点： g, 节点类型： 中。左孩子：null右孩子：null
		 中序1。当前节点： f, 节点类型： 中。左孩子：g右孩子：null
		 中序1。当前节点： a, 节点类型： 中。左孩子：b右孩子：c
		 中序1。当前节点： c, 节点类型： 中。左孩子：null右孩子：null
		 中序2。当前节点： d, 节点类型： 中。左孩子：null右孩子：e
		 中序2。当前节点： e, 节点类型： 中。左孩子：null右孩子：null
		 中序2。当前节点： b, 节点类型： 中。左孩子：d右孩子：f
		 中序2。当前节点： g, 节点类型： 中。左孩子：null右孩子：null
		 中序2。当前节点： f, 节点类型： 中。左孩子：g右孩子：null
		 中序2。当前节点： a, 节点类型： 中。左孩子：b右孩子：c
		 中序2。当前节点： c, 节点类型： 中。左孩子：null右孩子：null
		 后序。当前节点： e, 节点类型： 中。左孩子：null右孩子：null
		 后序。当前节点： d, 节点类型： 中。左孩子：null右孩子：e
		 后序。当前节点： g, 节点类型： 中。左孩子：null右孩子：null
		 后序。当前节点： f, 节点类型： 中。左孩子：g右孩子：null
		 后序。当前节点： b, 节点类型： 中。左孩子：d右孩子：f
		 后序。当前节点： c, 节点类型： 中。左孩子：null右孩子：null
		 后序。当前节点： a, 节点类型： 中。左孩子：b右孩子：c
		 */
		//非递归遍历。List实现。
//		traverseTreeNodes(a);
		/**
		 非递归。根节点是： a
		 非递归。当前节点是： b
		 非递归。当前节点是： c
		 非递归。当前节点是： d
		 非递归。当前节点是： f
		 非递归。当前节点是： g
		 非递归。当前节点是： e
		 */

		//非递归遍历。Stack实现
//		stackPreOrder(a);
//		System.out.println("先序非递归遍历节点总数："+diguiCount);
		/**
		 * output:
		 * 非递归先序。stack.a b d e f g c
		 * 顺序
		 * 出	进	Stack剩余
		 * a	cb	c,b
		 * b	fd	cfd
		 * d	e	cfe
		 * e	无	cf
		 * f	g	cg
		 * g	无	c
		 * c	无	无
		 */
		stackPreOrder(a1);
		System.out.println("先序非递归遍历节点总数："+diguiCount);


		//查找节点.注测试返回多个匹配的节点时，修改初始的树中节点名称多个相同的进行测试
//		searchNodeByName("b",a,false,null);
		//查找节点的前驱
//		try{
//			searchPrecedence("f",a);
//		}catch(Exception e1){
//			System.out.println("找前驱满足的第一个节点。"+curNode.toString());
//		}
		/**
		 * output
		 * debug二叉树查找。当前节点是：节点名:a,左孩子：b,右孩子：c
		 debug二叉树查找。当前节点是：节点名:b,左孩子：d,右孩子：f
		 二叉树查找。已经找到:节点名:b,左孩子：d,右孩子：f
		 debug二叉树查找。当前节点是：节点名:c,左孩子：null,右孩子：null
		 二叉树查找前驱。找到。是此节点的右孩子。节点名:b,左孩子：d,右孩子：f
		 找前驱满足的第一个节点。节点名:b,左孩子：d,右孩子：f


		 */
		
		
		
		
//		TreeNode node2 = createTreeFirstRecursive2(new Scanner(System.in));
//		System.out.println("创建树2....");
		
		//哈夫曼树
//		String[] names = {"A","B","C","D","E","F","G"};
//		double[] weights = {5,24,7,17,34,5,13};
//		Map<String,String> huffmanNodes = HuffmanEncode(names,weights);
//		Iterator<Map.Entry<String,String>> ite = huffmanNodes.entrySet().iterator();
//		while(ite.hasNext()){
//			Map.Entry<String,String> item = ite.next();
//			System.out.println(item+"。key="+item.getKey()+",value="+item.getValue());
//		}
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
		List<TreeNode> nodes = traverseTreeNodesPreorder(list.get(0),new LinkedList<TreeNode>());//whole tree
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
	* @Title: traverseTreeNodes 
	* @Description: 非递归遍历（按层遍历） 
	* @param @param node    根节点
	* @return void    返回类型 
	* @throws 
	* 按层遍历二叉树。
	* 结束条件；当当前层都是叶子节点时（这一层没有孩子）。
	* 每次价差当前层（作为父亲层的那一层）和下一层节点。下一层如果都是叶子则到达树的边缘；否则吧当前下一层作为下一层循环的父层，再次产生新的父层的下一层。
	*/ 
	public static void traverseTreeNodes(TreeNode node){
		boolean leafFlag = false;
		Set<TreeNode> pNodes = new HashSet<TreeNode>();
		Set<TreeNode> cNodes = new HashSet<TreeNode>();
		pNodes.add(node);
		System.out.println("非递归。根节点是： "+node.eleName);
		while(!leafFlag){
			System.out.println("cNodes.size()="+cNodes.size());
			for(TreeNode pNode : pNodes){
				if(pNode.getLeft() != null){
					cNodes.add(pNode.getLeft());
				}
				if(pNode.getRight() != null){
					cNodes.add(pNode.getRight());
				}
			}
			pNodes = cNodes;
			Set<TreeNode> temp = new HashSet<TreeNode>();
			boolean allLeaf = true;
			for(TreeNode cNode : cNodes){//检查下一层是否都是叶子。是:遍历结束；否：把当前下一层节点作为下次外循环的父节点
				System.out.println("非递归。当前节点是： "+cNode.eleName);
				if(cNode.getLeft() != null){
					temp.add(cNode.getLeft());
				}
				if(cNode.getRight() != null){
					temp.add(cNode.getRight());
				}
				if(cNode.getLeft() != null || cNode.getRight() != null ){//***这里判断子节点是否是叶子。好处是不用全局检测每个子节点是否都是叶子，避免增加一个if判断。
					allLeaf = false;
				}
			}
			leafFlag = allLeaf;
			
			cNodes = temp;
		}
	}

	/**
	 * 非递归先序-stack实现
	 * 符合动态规划。
	 * @param Root
	 */
	public static void stackPreOrder(TreeNode Root) {
		feidiguiCount++;
		System.out.print(" 非递归先序。stack.");
		if(Root==null) {
			System.out.println("空树");
			return;
		}
		TreeNode tmp=Root;
		//DP动态规划。每一层节点的下层节点都放在stack中，防止重复计算。空间换时间。
		Stack<TreeNode> s=new Stack<TreeNode>();
		s.push(tmp); //根节点入栈
		while(!s.empty()) {
			//1.访问根节点
			TreeNode p=s.pop();
			System.out.print(p.eleName+" ");
			//2.如果根节点存在右孩子，则将右孩子入栈
			if(p.right!=null) {
				s.push(p.right);
			}
			//3.如果根节点存在左孩子，则将左孩子入栈
			if(p.left!=null) {
				s.push(p.left);
			}
		}
		System.out.println();
	}

	/**
	 * 非递归先序-stack实现
	 * @param Root
	 */
	public void stackInOrder(TreeNode Root) {
		if(Root==null) {
			System.out.println("空树");
			return;
		}
		TreeNode tmp=Root;
		Stack<TreeNode> s=new Stack<TreeNode>();
		while(tmp!=null || !s.empty()) {
			//1.将根节点入栈
			//2.将所有左孩子入栈
			while(tmp!=null) {
				s.push(tmp);
				tmp=tmp.left;
			}
			//3.访问栈顶元素
			tmp=s.pop();
			System.out.print(tmp.eleName+" ");
			//4.如果栈顶元素存在右孩子，则将右孩子赋值给tmp，也就是将右孩子入栈
			if(tmp.right!=null) {
				tmp=tmp.right;
			}
			//否则，将tmp置为null，表示下次要访问的是栈顶元素
			else {
				tmp=null;
			}
		}
		System.out.println();
	}

	/**
	 * 非递归后序-stack实现
	 * @param Root
	 */
	public void stackPostOrder(TreeNode Root) {
		if(Root==null) {
			System.out.println("空树");
			return;
		}
		TreeNode tmp=Root; //当前节点
		TreeNode prev=null; //上一次访问的节点
		Stack<TreeNode> s=new Stack<TreeNode>();
		while(tmp!=null || !s.empty()) {
			//1.将根节点及其左孩子入栈
			while(tmp!=null) {
				s.push(tmp);
				tmp=tmp.left;
			}

			if(!s.empty()) {
				//2.获取栈顶元素值
				tmp=s.peek();
				//3.没有右孩子，或者右孩子已经被访问过
				if(tmp.right==null || tmp.right==prev) {
					//则可以访问栈顶元素
					tmp=s.pop();
					System.out.print(tmp.eleName+" ");
					//标记上一次访问的节点
					prev=tmp;
					tmp=null;
				}
				//4.存在没有被访问的右孩子
				else {
					tmp=tmp.right;
				}
			}
		}
		System.out.println();
	}
	
	/** 
	* @Title: searchNodeByName 
	* @Description: 树的查找 （先序）
	* @param @param name	要找的节点名
	* @param @param node	树的根节点
	* @param @param all		如果有多个匹配的是否返回所有匹配的节点
	* @param @return    设定文件 
	* @return TreeNode   要找的节点 
	* @throws 
	* @date 2017年07月20日 上午8:53:00
	* @modified 2017年07月21日 上午9:02:00
	* 根据节点名称和根节点查找节点.
	* 
	* 注：快速结束递归的方法：
一般不能立即跳出递归，只能做到在找到的那一溜递归中不再向下递归，而和满足条件（找到节点，即return那层）同一层的递归会继续向下执行。
return只是返回给上一个调用者---本函数,如果上一个调用者还有未执行的代发将继续执行，同理上一个调用者返回的return还有经过上上个调用者，直到回到最开始的调用者代码都执行完毕。顺序是由深到浅返回给自己的调用者。再由自己的调用者的代码逻辑决定下一步执行什么。
当函数不带返回值或者内部没有return时，会按照递归的由浅到深顺序向下调用，直到最后的被调用者运行结束，或者遇到return返回给上个调用者，或者遇到异常直接终止后面程序的运行。当然调用者的方法里可以罗列有多个递归方法，这些递归方法会按照每一个递归方法运行结束再执行下一个递归方法的顺序执行。
如：查找节点b,首先递归遍历a,b。找到b之后return，b下面的d,e,f,g则不会在遍历到，和b同一级的c那一溜将继续向下遍历，直到这一溜遇到return语句停止。
	* 问：那如何快速结束递归？
	* 答：在代码中抛出异常。可以快速结束递归，
	* 追问：但怎么在抛出异常的情况下，返回之前递归找到的数据？
	* 追答：在抛出异常之前把结果赋值给其他变量存起来。（如存放在类的属性中等）
但通过抛出异常来结束递归，不太符合JAVA规范，毕竟抛出异常也要一层一层向上抛出。
	* 递归快速结束例子见searchPrecedence
	*/ 
	public static List<TreeNode> searchNodeByName(String name, TreeNode node, boolean all,List<TreeNode> nodes){
		if(nodes == null){
			nodes = new ArrayList<TreeNode>();
		}
		if(node !=null){
			System.out.println("debug二叉树查找。当前节点是："+node.toString());
			if(name.equals(node.getEleName())){
				System.out.println("二叉树查找。已经找到:"+node.toString());
				nodes.add(node);
				if(!all){
					return nodes;
				}
			}
//			else{//此处不能用else。因为当根节点和下面节点名字相同时，匹配所有，只能找到根节点，下面匹配的就不往下遍历了。
				searchNodeByName(name,node.getLeft(),all,nodes);
				searchNodeByName(name,node.getRight(),all,nodes);
//			}
		}
		return nodes;
	}
	
	/** 
	* @Title: searchPrecedence 
	* @Description: 查找节点的前驱 （先序）快速跳出递归
	* @param @param name	节点名
	* @param @param node	树的根节点
	* @param @return    
	* @return TreeNode      节点名的前驱 
	 * @throws Exception 
	* @throws 
	* @date 2017年07月20日 上午8:58:00
	* 查找这样的节点：
	* 	此节点的左孩子或右孩子是入参的节点名。
	*/ 
	public static TreeNode searchPrecedence(String name, TreeNode node) throws Exception{
		if(node !=null){
			if(node.getLeft() != null){
				if(name.equals(node.getLeft().getEleName())){
					System.out.println("二叉树查找前驱。找到。是此节点的左孩子。"+node.toString());
					curNode = node;
					throw new Exception("二叉树查找前驱。找到。是此节点的左孩子。"+node.toString());
				}
			}
			if(node.getRight() != null){
				if(name.equals(node.getRight().getEleName())){
					System.out.println("二叉树查找前驱。找到。是此节点的右孩子。"+node.toString());
					curNode = node;
					throw new Exception("二叉树查找前驱。找到。是此节点的右孩子。"+node.toString());
				}
			}
			
			searchPrecedence(name,node.getLeft());
			searchPrecedence(name,node.getRight());
		}
		System.out.println("二叉树查找。树中无此节点。");
		return null;
	}
	
	/** 
	* @Title: getTreeNodes
	* @Description: 遍历树——先序递归算法.  把所有几点放在集合里（也可以看树的大小）
	* @param @param node
	* 				树的根节点
	* @param @return     
	* @return List<TreeNode>    
	* 				返回所有节点信息 
	* @throws 
每个节点都遵循以下过程：
        中左右。
       1. 中：看此节点是否存在，不存在则直接返回空节点。
        2.和3存在则看左边是否有孩子，有则返回他的左孩子，再看是否有右孩子，有则返回他的右孩子。
       4. 对于操作2和3里的左孩子和右孩子，这也是递归函数的。有也同样的上述操作过程：对于2中的左孩子是否存在，无则返回空；有则看其是否有自己的左孩子，有则返回其左孩子，再看其是否有右孩子，有则返回其右孩子。
             对于3中的右孩子也一样。
4.中总结的便是递归的核心重复过程。
所以递归函数的结构就有了，如下：
	* 入参：一个节点（包含子节点信息）
	* 返回值: 无。或者一个不断容纳遍历节点的集合。
	* 递归方法：遍历当前节点（一般打印此节点等操作）、获取节点的左右孩子节点
	* 结束条件：节点没有左右孩子
	*/ 
	public static List<TreeNode> traverseTreeNodesPreorder(TreeNode node,List<TreeNode> nodes){
		diguiCount++;
		if(node == null){
//			nodes.add(node);
			return null;
		}
		TreeNode leftNode = node.left;
		TreeNode rightNode = node.right;
		System.out.println("先序当前节点： "+node.getEleName()+", 节点类型： 中。"+
				"左孩子："+((node.getLeft()==null)?null:node.getLeft().getEleName())+
				"右孩子："+((node.getRight()==null)?null:node.getRight().getEleName()));
		nodes.add(node);
		if(node.left!=null){
			leftNode.setHuffmanCode(node.getHuffmanCode()+"0");
			traverseTreeNodesPreorder(leftNode,nodes);
		}
		if(node.right!=null){
			rightNode.setHuffmanCode(node.getHuffmanCode()+"1");
			traverseTreeNodesPreorder(rightNode,nodes);
		}
		return nodes;
	}
	
	/** 
	* @Title: traverseTreeNodeInorder 
	* @Description: 递归 - 中序遍历 
	* @param @param node    根节点 
	* @return void    返回类型 
	* @throws 
	* 只要当前节点不为空，则继续遍历
	*/ 
	public static void traverseTreeNodesInorder1(TreeNode node) {
		if(node != null){
			traverseTreeNodesInorder1(node.left);
			System.out.println("中序1。当前节点： "+node.getEleName()+", 节点类型： 中。"+
				"左孩子："+((node.getLeft()==null)?null:node.getLeft().getEleName())+
				"右孩子："+((node.getRight()==null)?null:node.getRight().getEleName()));
			traverseTreeNodesInorder1(node.right);
		}
	}
	
	public static void traverseTreeNodesInorder2(TreeNode node) {
		if(node.left != null){
			traverseTreeNodesInorder2(node.left);
		}
			System.out.println("中序2。当前节点： "+node.getEleName()+", 节点类型： 中。"+
				"左孩子："+((node.getLeft()==null)?null:node.getLeft().getEleName())+
				"右孩子："+((node.getRight()==null)?null:node.getRight().getEleName()));
		if(node.right != null){
			traverseTreeNodesInorder2(node.right);
		}
	}
	
	public static void traverseTreeNodesPostorder(TreeNode node) {
		if(node.left != null){
			traverseTreeNodesPostorder(node.left);
		}
		if(node.right != null){
			traverseTreeNodesPostorder(node.right);
		}
		System.out.println("后序。当前节点： "+node.getEleName()+", 节点类型： 中。"+
				"左孩子："+((node.getLeft()==null)?null:node.getLeft().getEleName())+
				"右孩子："+((node.getRight()==null)?null:node.getRight().getEleName()));
	}
	
	/** 
	* @Title: createTree 
	* @Description: 创建二叉树——先序递归创建（代码未完成）
	* @param @param sourceNames
	* @param @return    设定文件 
	* @return TreeNode	根节点（包含树的所有节点信息） 
	* @throws
	* 需求：根据图形(test/algorithm/tree/tree.png)，用代码此创建二叉树。先中后序，用任意一种方式创建。
		步骤：（这里先序递归创建）
		1.将图形转换成初始数据。此二叉树先序可表示成：abd#e##fg###c##。简洁表达：abdefgc。#表示空节点。
		2.根据初始数据建树。 
	建树递归算法：
		分析：
		当前节点为空（#）时，不会为当前节点创建左右子树（孩子，左右子树其实也是节点）；当前节点不为空时，先创建此节点的左右子树，再把左右子树分别挂在此节点上。
			第几个节点		结果
		a	1			a(a的左孩子,a的右孩子)		此时a的左右孩子还在递归中，未出结果
		b	2			a(b,a的右孩子)				a的右孩子还在递归中。
		d	3			a(b(d,b的右孩子),a的右孩子)	a的右孩子，b的右孩子还在递归中。
		#	4			空节点，不创建空节点的左右孩子，原结果不变
		结论：每个节点都创建本节点的左右子树，并把左右节点放到此节点的左右孩子上。如果本节点是空节点，则不创建。
		左子树根节点是剩下节点中的第一个节点，右子树根节点是左边创建完了之后，剩下节点的第一个节点????
	入参：一个一个新节点。
		这里给的初始条件是所有节点，所以要转化一下，每次只取剩余节点的第一个节点作为当前节点。
	返回值：此节点的左右子树/孩子/节点。
	递归方法：创建此节点的左右子树。
	结束条件：此节点为空节点
	创建成功期望：把每个元素/节点设置其eleName、左节点是谁、右节点是谁，即创建完成。
	具体过程：（未完成）依次读取字符串中的每个字符，如果此字符/节点不为空，
							则此节点作为当前节点的左孩子；
							否则，当前节点的左孩子为空（没有左孩子），继续读取下一个字符/节点，直到不为空为止。
							返回当前节点（当前节点中就包含了当前节点和）
	注：节点的左右孩子信息一定在剩下的字符里。所以可以用上述操作，依次读取字符，让剩下的字符越来越少。
	
	问题点：
		初始树节点是依次输入渐渐生成；还是一次性输入生成。
	*/ 
	public static TreeNode createTreeFirstRecursive(TreeNode node,String[] sourceNames){
		String thisNodeName = sourceNames[0];//当前节点
		System.out.println("当前读取的字符： "+thisNodeName);
		if(sourceNames.length > 1){
			sourceNames = ArrayUtils.remove(sourceNames, 0);//剩余节点
		}
		
		System.out.println("剩余节点："+ArrayUtils.toString(sourceNames));
		if("#".equals(thisNodeName)){
			return null;
		}else{
			//this node
			node = new TreeNode(thisNodeName,0);
			//创建左子树，并将左子树挂在当前节点的左边
			node.setLeft(createTreeFirstRecursive(node.getLeft(),sourceNames));//用当前节点，创建左孩子
			//创建右子树，并将左子树挂在当前节点的右边???(这一句有问题，未完成，右子树的创建应该是左子树建好之后剩下的sourceNames。实际右子树不是最后左子树建好之后剩余的sourceNames)
			node.setRight(createTreeFirstRecursive(node.getRight(),sourceNames));//用当前节点，创建右孩子
			return node;
		}
	} 
	
	/** 
	* @Title: createTreeFirstRecursive2  (代码完成，未完全理解Scanner的地方) 
	* @Description: 
	* @param @param node
	* @param @param nodeName
	* @param @return    设定文件 
	* @return TreeNode    返回类型 
	* @throws 
	* 每次从文件里读取一行，作为一个节点，操作结束后，返回这个节点。
	* 	如果为空节点，则返回空。
	* 	如果不是空节点，则给此节点设置左孩子和右孩子，左孩子是此节点的左孩子作为参数去创建它的子树的结果，有孩子是此节点的有右孩子作为参数去创建它的右子树的结果。
	*/ 
	public static TreeNode createTreeFirstRecursive2(TreeNode node,Scanner nodeName){
		String thisNodeName = nodeName.next();
//		System.out.println("当前读取的字符： "+thisNodeName);
		if("#".equals(thisNodeName)){
			return null;
		}else{
			//this node
			node = new TreeNode(thisNodeName,0);
			//创建左子树，并将左子树挂在当前节点的左边
			node.setLeft(createTreeFirstRecursive2(node.getLeft(),nodeName));//用当前节点，创建左孩子
			//创建右子树，并将左子树挂在当前节点的右边
			node.setRight(createTreeFirstRecursive2(node.getRight(),nodeName));//用当前节点，创建右孩子
			return node;
		}
	} 
	
	/**
	 * 1.查找节点
1.2查找节点的前驱（如果节点只记录了后继，没有前驱信息）
	 */
	
	/**
	 * 2.插入节点，指定插入到某个节点的左或右分支上。
（如果要插入到的位置已有其他节点，则把这个其他分支挂到新节点下）
入参:新节点，目标节点，左或右（表示如果替换其他节点，其他节点应该挂在新节点的哪个分支）
	 */
	
	/**
	 * 3.删除节点
   先找到要删除节点的父亲
   再把要删除节点的孩子指向其父亲。    要删除节点的孩子，可通过要删除节点的父亲节点向下找到。所以关键在要删除节点的父亲的查找。（即找到一个节点其左孩子或右孩子是要删除的节点。）
入参：要删除的节点名称，删除性质（是连同此节点下的所有分支都删除还是只删除一个节点）
特殊情况，当要删除的节点有既有左孩子，又有右孩子时，删除后怎么重新整理树？？考研笔记中有此记录
如果节点中记录了每个节点的前驱信息，则只要找到要删除节点，就能找到其父亲和孩子，从而进行删除操作，这种简单情况可以试下。
	 */
	
	/** 
	* @Title: createTreeLevel 
	* @Description: 层次创建树
	* @param @param sourceNames2
	* @param @return    设定文件 
	* @return List<TreeNode>    返回类型 
	* @throws
	* 需求：
	* 	abcdefg 
	*/ 
	public static List<TreeNode> createTreeLevel(String[] sourceNames2){
		return null;
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
		@Override
		public String toString(){
			return "节点名:"+this.getEleName()+
					",左孩子："+(this.getLeft()==null?this.getLeft():this.getLeft().getEleName())+
					",右孩子："+(this.getRight()==null?this.getRight():this.getRight().getEleName());
			
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
