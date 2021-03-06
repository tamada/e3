This project is obsolete, therefore, archived.

# e3: 動的エントロピー計測器

* First released: 2012. 3.18 (Sun)
* Recently revised: 2012. 5. 8 (Wed)

## はじめに

このツールは計測対象となるJavaプログラムがどのような命令を実行しているのか計測するためのツールです．
Agentとして起動し，クラスファイルのロード後に計測する命令を挿入するようクラスファイルを書き換え，実行します．
実行後は，プログラム終了時にそれまで実行された命令列の順番と頻度をcsv形式で出力します．

## 計測方法

このツールは，２種類の実行方法があります．
１つ目は，計測するためのコードを埋め込む変換を静的に行う方法です．
もう一つはプログラムロード時に，クラスファイルを書き換え，そのまま実行する方法です．
両方とも，プログラム終了時に計測結果を標準出力に出力します．
出力内容はインストラクションの実行トレースと，その頻度です．

本ツールでは，実行されたインストラクションの計測・収集のために，コードをクラスファイルに埋め込みます．
例として，以下のHelloWorldで説明します．

```java
public class HelloWorld{
    public static void main(String[] args){
        System.out.println("Hello World");
    }
}
```

このプログラムをコンパイルすると，以下のようなバイトコードに変換されます．
コンスタントプールは無視しています．

```
public HelloWorld()
  Code:
   0:	aload_0
   1:	invokespecial	#1; //Method java/lang/Object."<init>":()V
   4:	return

public static void main(java.lang.String[]);
  Code:
   0:	getstatic	#2; //Field java/lang/System.out:Ljava/io/PrintStream;
   3:	ldc	#3; //String Hello World
   5:	invokevirtual	#4; //Method java/io/PrintStream.println:(Ljava/lang/String;)V
   8:	return
```

このツールは上記バイトコードの命令1つずつ集計のためのメソッド呼び出しを加えていきます．

```
public HelloWorld()
  Code:
   0:	ldc	#7; //String HelloWorld
   2:	ldc	#8; //String <init>
   4:	invokestatic	#14; //Method jp/cafebabe/e3/EntropyCounterManager.entryMethod:(Ljava/lang/String;Ljava/lang/String;)V
   7:	invokestatic	#17; //Method jp/cafebabe/e3/EntropyCounterManager.aload:()V
   10:	aload_0
   11:	invokestatic	#20; //Method jp/cafebabe/e3/EntropyCounterManager.invokespecial:()V
   14:	invokespecial	#22; //Method java/lang/Object."<init>":()V
   17:	invokestatic	#25; //Method jp/cafebabe/e3/EntropyCounterManager.returnMethod:()V
   20:	invokestatic	#28; //Method jp/cafebabe/e3/EntropyCounterManager.returnInsn:()V
   23:	return

public static void main(java.lang.String[]);
  Code:
   0:	ldc	#7; //String HelloWorld
   2:	ldc	#31; //String main
   4:	invokestatic	#14; //Method jp/cafebabe/e3/EntropyCounterManager.entryMethod:(Ljava/lang/String;Ljava/lang/String;)V
   7:	invokestatic	#34; //Method jp/cafebabe/e3/EntropyCounterManager.getstatic:()V
   10:	getstatic	#40; //Field java/lang/System.out:Ljava/io/PrintStream;
   13:	invokestatic	#43; //Method jp/cafebabe/e3/EntropyCounterManager.ldc:()V
   16:	ldc	#45; //String Hello World
   18:	invokestatic	#48; //Method jp/cafebabe/e3/EntropyCounterManager.invokevirtual:()V
   21:	invokevirtual	#54; //Method java/io/PrintStream.println:(Ljava/lang/String;)V
   24:	invokestatic	#25; //Method jp/cafebabe/e3/EntropyCounterManager.returnMethod:()V
   27:	invokestatic	#28; //Method jp/cafebabe/e3/EntropyCounterManager.returnInsn:()V
   30:	return
```

プログラムが終了するときに，今まで集計した結果を出力します．
上記の例であれば，以下のような出力になります．

```
Hello World
############## execution trace (class,method,opcode,name) ###############
HelloWorld,main,178,getstatic
HelloWorld,main,18,ldc
HelloWorld,main,182,invokevirtual
HelloWorld,main,177,return
################ frequency of trace (opcode,name,count) #################
18,ldc,1
177,return,1
178,getstatic,1
182,invokevirtual,1
################################ entropy ################################
2.0
########## kolmogorov complexity (algorithm,after,before,rate) ##########
deflate,12,4,3.000000
```

## 使い方

### 変換のみを行う場合

通常はこちらの起動方法で起動します．
以下のように，`java` コマンドの `-jar` オプションに渡せば実行できます．

```sh
$ java -jar e3-1.1.jar [OPTIONS] <TARGETS...>
OPTIONS
  -d, --dest <dest>: set output destination. Default is current directory.
  -h, --help:        show this message

TARGETS
  only accept Java class files.
```

`-d` は変換後のクラスファイルの出力先を表します．
デフォルトはカレントディレクトリです．
変換後のプログラムは，この`jar`ファイル（`e3-1.1.jar`）にもクラスパスを通さなければ，実行できなくなります．


### Java Agentとして起動

必要なところにクラスパスを通しておかなくてはいけません．
具体的には以下のようなコマンドで実行します．
以下は複数業に分けて書いていますが，実際は１行で書いてください．
配布ファイルのトップディレクトリで実行します
（`src`, `target`, `pom.xml`, `README`の4つのファイル，ディレクトリが存在するディレクトリ）．

```sh
$ java -javaagent target/e3-1.1.jar
       -classpath 'target/e3-1.1.jar:XXXXX'
       YYYYY
```

ここで，`YYYYY`は計測対象となるクラスファイルの指定，`XXXXX`は`YYYYY`が存在するディレクトリもしくはjarファイルのパスです．
計測しない場合は以下のように実行されるはずです．

```sh
$ java -classpath XXXXX YYYYY
```

実行後，現在のディレクトリに `hoge` ディレクトリが作成され，変換されたクラスファイルが出力されるようになっています．
そのディレクトリ内のクラスファイルを対象に静的に分析できるようになっています．

### 変換対象

e3 は変換対象でないプログラムを判定するため，フィルタリングを行います．
デフォルトでは標準ライブラリなど一般的に良く使われているライブラリは変換対象として選択されず，解析の対象外としています．
具体的な内容は
`src/sample/resources/filtering.csv`，もしくは `jp.cafebabe.e3.DefaultTransformFilter` を参照して下さい．

独自にフィルタリング対象を指定したい場合，実行時に設定ファイルで指定できます．
このアルゴリズムは，パッケージ名を含むクラス名が前方一致すれば変換対象としません．
そのため，設定ファイルには変換対象に含めたくないクラスのパッケージを指定して下さい．
設定ファイルの各項目はコンマ(,)もしくは改行で区切られます．
シャープ(#)で始まる行はコメント行と見なされます．

この設定ファイルを指定する場合は，以下のように指定できます．
javaagentの jar ファイルの指定後，`=`以降が，javaagent への引数です．
filterが引数名であり，引数名と値の区切り文字はコロン(:)です．
ここでは，引数の値として`filter.csv`を指定していますが，実際には適切なファイルを指定して下さい．

なお，パッケージ階層の区切りは，Javaプログラムの場合ピリオド(.)ですが，バイトコードの場合スラッシュ(/)です．
この設定ファイルでは，どちらの文字での指定でもサポートしています．

```sh
$ java -javaagent:target/e3-1.1.jar=filter:filter.csv
       -classpath XXXXX
       YYYYY
```

## 依存ライブラリ

このツールは以下のライブラリを使用しています．

* Maven 3 (コンパイルに用いています)
* asm 4.0 (バイトコードの書き換えに用いています)

ツールのディレクトリで，`mvn package` と入力すれば，コンパイルされ，
`target` ディレクトリに `e3-1.1.jar` が作成されます．

## 連絡先

〒603-8555 京都市北区上賀茂本山
京都産業大学 情報理工学部
玉田 春昭
