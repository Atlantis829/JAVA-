package src;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class gamewindow extends JFrame implements java.awt.event.KeyListener,ActionListener{

    int x = 0;
    int y = 0;
    int step;

    JMenuItem item1 = new JMenuItem("重新游戏");
    //JMenuItem item2 = new JMenuItem("重新登录");
    JMenuItem item3 = new JMenuItem("关闭游戏");
    JMenuItem item4 = new JMenuItem("微信");

    int[][] data = new int [4][4];

    int[][] win = {
        {1,2,3,4},
        {5,6,7,8},
        {9,10,11,12},
        {13,14,15,16}
    };

    public gamewindow () {
        this.setSize(603,680);
        this.setTitle("拼图游戏内测版");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);
        this.setAlwaysOnTop(true);
        this.setLayout(null);
        this.addKeyListener(this);
        initbar();
        random();
        initimage();
        this.setVisible(true);
    }


    private void initbar (){

        JMenuBar bar = new JMenuBar();
        JMenu menu1 = new JMenu("功能");
        JMenu menu2 = new JMenu("关于我们");
            
        menu1.add(item1);
        //menu1.add(item2);
        menu1.add(item3);
        menu2.add(item4);

        item1.addActionListener(this);
        //item2.addActionListener(this);
        item3.addActionListener(this);
        item4.addActionListener(this);

        bar.add(menu1);
        bar.add(menu2);
        this.setJMenuBar(bar);
    }



    private void random() {
        int[] arr = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            int index = r.nextInt(arr.length);
            int temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 16) {
                x = i/4;
                y = i%4;
            } 
             data[i / 4][i % 4] = arr[i];
                   
        }
    }


    private void initimage() {

        //清空所有图片
        this.getContentPane().removeAll();

        if (victory()) {
            JLabel winJLabel = new JLabel(new ImageIcon("image\\win.png"));
            winJLabel.setBounds(203,283,197,73);
            this.getContentPane().add(winJLabel);
        }

        JLabel stepCount = new JLabel("步数：" + step);
        stepCount.setBounds(50,30,100,20);
        this.getContentPane().add(stepCount);

        int number;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                number = data[i][j];
                ImageIcon image = new ImageIcon("image\\"+number+".jpg");
                JLabel lable = new JLabel(image);
                this.add(lable); 
                lable.setBounds(j*105+83, i*105+134, 105, 105);
                lable.setBorder(new BevelBorder(0));
                this.getContentPane().add(lable); 
            }
        }

        ImageIcon bg = new ImageIcon("image\\background.png");
        JLabel background = new JLabel(bg);
        background.setBounds(40,40 ,508, 560);
        this.getContentPane().add(background);

        //刷新界面
        this.getContentPane().repaint();

    }

    public boolean victory(){
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if(data[i][j] != win[i][j]){
                    return false;
                }
            }
        }
        return true;
    }



    @Override
    public void keyTyped(KeyEvent e) {
        
        return;
    }


    @Override
    public void keyPressed(KeyEvent e) {
        
        int code = e.getKeyCode();
        if (code == 65){
            this.getContentPane().removeAll();
            JLabel all = new JLabel(new ImageIcon("image\\all.jpg"));
            all.setBounds(83,134,420,420);
            this.getContentPane().add(all);
            JLabel background = new JLabel(new ImageIcon("image\\background.png"));
            background.setBounds(40, 40, 508, 560);
            this.getContentPane().add(background);
            this.getContentPane().repaint();
        }
    }


    @Override
    public void keyReleased(KeyEvent e) {
        if(victory()){
            return;
        }
        //左：37 上：38 右：39 下：40
        int code = e.getKeyCode();
        System.out.println(code);
        if (code == 37) {
            System.out.println("向左移动");
            if(y == 3){
                return;
            }
            data[x][y] = data[x][y + 1];
            data[x][y + 1] = 0;
            y++;
            step++;
            initimage();

        } else if (code == 38) {
            System.out.println("向上移动");
            if(x == 3){
                return;
            }
            data[x][y] = data[x + 1][y];
            data[x + 1][y] = 0;
            x++;
            step++;
            initimage();
        } else if (code == 39) {
            System.out.println("向右移动");
            if(y == 0){
                return;
            }
            data[x][y] = data[x][y - 1];
            data[x][y - 1] = 0;
            y--;
            step++;
            initimage();
        } else if (code == 40) {
            System.out.println("向下移动");
            if(x == 0){
                return;
            }
            data[x][y] = data[x - 1][y];
            data[x - 1][y] = 0;
            x--;
            step++;
            initimage();
        }else if(code == 65){
            initimage();
        }else if(code == 92){
            data = new int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,16}
            };
            initimage();
        }
        
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj == item1){
            System.out.println("重新游戏");
            step = 0;
            random();
            initimage();
        }else /*if(obj == item2){
            System.out.println("重新登录");
            this.setVisible(false);
            new loginwindow();
        }else */if(obj == item3){
            System.out.println("关闭游戏");
            System.exit(0);
        }else  if(obj == item4){
            System.out.println("微信");
            JDialog jDialog = new JDialog();
            JLabel jLabelabout = new JLabel(new ImageIcon("image\\about.jpg"));
            jLabelabout.setBounds(200, 300, 500,200);
            jDialog.getContentPane().add(jLabelabout);
            jDialog.setSize(400,500);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);
            jDialog.setModal(true);
            jDialog.setVisible(true);
        }
        
    }



}
