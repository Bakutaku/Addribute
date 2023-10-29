package com.happyineo.addribute;

import com.happyineo.addribute.type.LogType;

import java.util.*;

import static com.happyineo.addribute.Utils.log;

public class StringCalc {

    private String input;   // 初期値(入力された時の式)
    private String formula;   // 計算式格納用
    private String build;   // 逆ポーランド記法格納用


    /**
     * コンストラクタ<br>
     * 計算式の登録
     * @param formula
     */
    public StringCalc(String formula) {
        // 空白を消す
        this.input = formula.replaceAll("\\s","");

        // 式を設定
        this.reset();
    }

    /**
     * 代入した変数の中身をリセットする
     * @return {@link StringCalc}
     */
    public StringCalc reset(){
        // 生成した逆ポーランド記法をリセットする
        this.build = null;
        // 式を初期値にする
        this.formula = this.input;

        return this;
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
    public StringCalc build(){

        Map<String,Integer> weight = new HashMap<>();   // 優先順位用
        weight.put("^", 4);
        weight.put("*", 3);
        weight.put("/", 3);
        weight.put("+", 2);
        weight.put("-", 2);
        weight.put("(", 1);

        Stack<String> stack = new Stack<>(); // 演算子一時保管用
        StringBuilder builder = new StringBuilder();    // 結果出力用

        // 数字と演算子を分割してループする(分割には正規表記を使用)
        for(String str : this.formula.split("(?<=[-+*/()^])|(?=[-+*/()^])")){
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

        // 保存
        this.build = builder.toString();

        return this;
    }


    /**
     * 計算を行う
     * @return 計算結果
     */
    public double calc(){

        double answer = 0;  // 答え格納用

        // 変換が終わっているか確かめる
        if(build == null){
            // 終わっていない場合

            // 逆ポーランド記法に変換する
            this.build();

            // 再度実行
            answer = this.calc();
        }else{
            // 変換済みの場合

            Stack<Double> stack = new Stack<>();    // 数字格納用

            // 空白で区切り各値をループで調べる
            for(String val : this.build.split("\\s")){

                // 数字かどうか
                if(val.matches("^([1-9]\\d*|0)(\\.\\d+)?$|^(-[1-9]\\d*|0)(\\.\\d+)?$")){
                    // 数字の場合
                    // スタックに数字を追加
                    stack.push(Double.valueOf(val));

                    // 次のループへ
                    continue;
                }

                // 取り出した値格納用
                double num1;
                double num2;

                switch (val){
                    case "+":
                        // 値を取り出す
                        num1 = stack.pop();
                        num2 = stack.pop();

                        // 計算を行いスタックに格納する
                        stack.push(num1 + num2);

                        break;

                    case "-":
                        // 値を取り出す
                        num1 = stack.pop();
                        num2 = stack.pop();

                        // 計算を行いスタックに格納する
                        stack.push(num1 - num2);

                        break;

                    case "*":
                        // 値を取り出す
                        num1 = stack.pop();
                        num2 = stack.pop();

                        // 計算を行いスタックに格納する
                        stack.push(num1 * num2);

                        break;

                    case "/":
                        // 値を取り出す
                        num1 = stack.pop();
                        num2 = stack.pop();

                        // 計算を行いスタックに格納する
                        try {
                            stack.push(num2 / num1);
                        } catch (ArithmeticException e) {
                            // 0演算の場合、とりあえず0を入れておく
                            stack.push(0.0);
                        }

                        break;

                    case "^":
                        // 値を取り出す
                        num1 = stack.pop();
                        num2 = stack.pop();

                        // 計算を行いスタックに格納する
                        stack.push(Math.pow(num2,num1));

                        break;

                    default:
                        // 変数が変換されていない場合

                        // 初期値として0を格納する
                        stack.push(0.0);
                }
            }

            // 結果を取り出す
            answer = stack.pop();

        }

        // 結果を返す
        return answer;
    }

}
