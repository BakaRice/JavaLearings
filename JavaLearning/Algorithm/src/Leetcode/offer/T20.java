package Leetcode.offer;

import exam.virtual.T2;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: JavaLearning
 * Created by Rice on 2020/6/10 21:31
 * https://leetcode-cn.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/
 */
public class T20 {
    public boolean isNumber(String s) {
        Map[] states = {
                new HashMap<>() {{ put(' ', 0); put('s', 1); put('d', 2); put('.', 4); }}, // 0. 空格
                new HashMap<>() {{ put('d', 2); put('.', 4); }},                           // 1. 幂符号钱正负号
                new HashMap<>() {{ put('d', 2); put('.', 3); put('e', 5); put(' ', 8); }}, // 2. 小数点前数字
                new HashMap<>() {{ put('d', 3); put('e', 5); put(' ', 8); }},              // 3. 小数点，小数点后数字
                new HashMap<>() {{ put('d', 3); }},                                        // 4. 当小数点前为空格时，小数点，小数点后的数字
                new HashMap<>() {{ put('s', 6); put('d', 7); }},                           // 5. 幂符号
                new HashMap<>() {{ put('d', 7); }},                                        // 6. 幂符号后的正负号
                new HashMap<>() {{ put('d', 7); put(' ', 8); }},                           // 7. 幂符号后的数字
                new HashMap<>() {{ put(' ', 8); }}                                         // 8. 结尾的空格
        };
        int p = 0;
        char t;
        for (char c:s.toCharArray()){
            if(c >= '0' && c <= '9') t = 'd';
            else if(c == '+' || c == '-') t = 's';
            else if(c == 'e' || c == 'E') t = 'e';
            else if(c == '.' || c == ' ') t = c;
            else t = '?';
            if (!states[p].containsKey(t)) return false;
            p = (int) states[p].get(t);
        }
        return  p == 2 || p ==3 || p==7 ||p==8;
    }

    public boolean isNumber2(String s) {
        if(s == null || s.length() == 0){
            return false;
        }
        //标记是否遇到相应情况
        boolean numSeen = false;
        boolean dotSeen = false;
        boolean eSeen = false;
        char[] str = s.trim().toCharArray();
        for(int i = 0;i < str.length; i++){
            if(str[i] >= '0' && str[i] <= '9'){
                numSeen = true;
            }else if(str[i] == '.'){
                //.之前不能出现.或者e
                if(dotSeen || eSeen){
                    return false;
                }
                dotSeen = true;
            }else if(str[i] == 'e' || str[i] == 'E'){
                //e之前不能出现e，必须出现数
                if(eSeen || !numSeen){
                    return false;
                }
                eSeen = true;
                numSeen = false;//重置numSeen，排除123e或者123e+的情况,确保e之后也出现数
            }else if(str[i] == '-' || str[i] == '+'){
                //+-出现在0位置或者e/E的后面第一个位置才是合法的
                if(i != 0 && str[i-1] != 'e' && str[i-1] != 'E'){
                    return false;
                }
            }else{//其他不合法字符
                return false;
            }
        }
        return numSeen;
    }
    public static void main(String[] args) {
        T20 t20 = new T20();
        t20.isNumber("-1E-16");
        t20.isNumber("-1e-16");
    }
}
