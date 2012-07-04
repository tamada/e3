import javax.swing.*;

public class HelloSwing{
  public static void main(String[] args){
    SwingUtilities.invokeLater(new Thread(){
      @Override
      public void run(){
        JFrame frame = new JFrame("Hello");
        frame.add(new JLabel("Hello World"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
      }
    });
  }
}
