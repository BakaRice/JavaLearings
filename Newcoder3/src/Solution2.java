
import java.util.Arrays;

    public class Solution2 {

        public class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;
            TreeNode(int x) { val = x; }
        }
        //pre{1,2,4,7,3,5,6,8}
        //in{4,7,2,1,5,3,8,6}
        public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
            if (pre.length == 0 || in.length == 0) {
                return null;
            }
            TreeNode root = new TreeNode(pre[0]);
            // 在中序中找到前序的根
            for (int i = 0; i < in.length; i++) {
                if (in[i] == pre[0]) {
                    // 左子树，注意 copyOfRange 函数，左闭右开
                    root.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, i + 1), Arrays.copyOfRange(in, 0, i));
                    // 右子树，注意 copyOfRange 函数，左闭右开
                    root.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i + 1, pre.length), Arrays.copyOfRange(in, i + 1, in.length));
                    break;
                }
            }
            return root;
        }
    }

