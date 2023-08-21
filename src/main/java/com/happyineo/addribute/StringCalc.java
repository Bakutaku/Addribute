package com.happyineo.addribute;

import java.util.*;

public class StringCalc {


    private String formula;   // 計算式格納用


    /**
     * コンストラクタ<br>
     * 計算式の登録
     * @param formula
     */
    public StringCalc(String formula) {
        // 空白を消す
        this.formula = formula.replace("\\s","");
    }

    /**
     * 変数の値を書き換える
     * @param x 変数名
     * @param num 変更後の値
     * @return {@link StringCalc}
     */
    public StringCalc replace(String x,double num){
        // 値を書き換える
        this.formula = this.formula.replace(x,String.valueOf(num));

        return this;
    }

    /**
     * 変数の値を書き換える
     * @param x 変数名
     * @param num 変更後の値
     * @return {@link StringCalc}
     */
    public StringCalc replace(String x,int num){
        // 値を書き換える
        this.formula = this.formula.replace(x,String.valueOf(num));

        return this;
    }

    /**
     * 計算式を逆ポーランド記法に変換する
     * @return 逆ポーランド記法
     */
    private String build(String formula){

        Map<String,Integer> weight = new HashMap<>();   // 優先順位用
        weight.put("^", 4);
        weight.put("*", 3);
        weight.put("/", 3);
        weight.put("+", 2);
        weight.put("-", 2);
        weight.put("(", 1);


        Stack<String> stack = new Stack<>(); // 演算子一時保管用
        StringBuilder builder = new StringBuilder();    // 結果出力用

        for(String str : formula.split("\\s")){
            if(str.matches("^([1-9]\\d*|0)(\\.\\d+)?$|^(-[1-9]\\d*|0)(\\.\\d+)?$")){
                // 数字の場合

                // 数字をそのまま出力
                builder.append(str).append(" ");

            }else if(str.equals("(")){
                // 「(」の場合
                // 待機列に追加する(優先度によって記号を出力するかどうかを決めているため)
                stack.push(str);

            }else if(str.equals(")")){
                // 「)」の場合
                // 「(」が出てくるまでスタックした演算子を出力する
                while(!stack.peek().equals("(")){

                    builder.append(stack.pop()).append(" ");

                }
                // 「(」を削除
                stack.pop();
            }else {
                // 演算子の場合
                System.out.println(str);
                // 現在の演算子の優先度の方が優先度が高くなるまでスタックの中身を出力する
                while(!stack.isEmpty() && weight.get(str) <= weight.get(stack.peek())){

                    builder.append(stack.pop()).append(" ");

                }
                // 現在の演算子をスタックに追加する
                stack.push(str);
            }
        }
        // スタックに残っている演算子を出力する
        while(!stack.isEmpty()){
            builder.append(stack.pop()).append(" ");
        }

        return builder.toString();
    }


    /**
     * 計算を行う
     * @return 計算結果
     */
    public double calc(){

    }

}
