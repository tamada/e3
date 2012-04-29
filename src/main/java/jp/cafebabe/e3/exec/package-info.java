/**
 * <p>
 * This package contains the classes to measure the entropy at
 * runtime.  For example, {@link jp.cafebabe.e3.exec.OpcodeManager
 * <code>OpcodeManager</code>} conducts interconversion between opcode
 * and its name, and {@link jp.cafebabe.e3.exec.EntropyCounterManager
 * <code>EntropyCounterManager</code>} is the facade class for
 * measurement.
 * </p><p>
 * エントロピー計測の実行に必要なクラスをまとめたパッケージです．
 * オペコードから，命令名を取得するための OpcodeManagerや，
 * メソッドごとに計測するための MethodEntropyCounter や，
 * 計測のための Facade となる EntropyCounterManager が存在します．
 * </p>
 */
package jp.cafebabe.e3.exec;
