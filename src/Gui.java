import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.knowm.xchart.*;
import org.knowm.xchart.QuickChart;

public class Gui {
    JFrame frame1,frame2,frame3;        //三个窗口
    JButton button1,button2,button3;
    int model = -1,step = 0,times = 0;      //model用于判断单步还是连续运行，step表示单步运行点击次数，times表示迭代次数
    JPanel p1,p2,p3,p31,p32,p41,p42;
    JLabel[][] l1 = new JLabel[5][4];       //存储染色体的标签
    JLabel[] bestChr = new JLabel[4];
    JLabel ll1,ll2,ll3,ll4,ll5,lm1,lm11,lr1,lr11,lr2,lr22,lr3,lr33,ltimes1,ltimes2;
    XYChart xchart;
    XChartPanel xp;
    Gui(int nums, Chromosome[][] selectGenes, Chromosome[][] matingGenes, Chromosome[][] variationGenes, Chromosome best0, Chromosome[] population, Chromosome[] best) {
        frame1 = new JFrame("menu");
        frame2 = new JFrame("单步运行");
        frame3 = new JFrame(" ");
        button1 = new JButton("单步运行");
        button2 = new JButton("连续运行");
        button3 = new JButton("下一步");
        p1 = new JPanel(new GridLayout(5,1));
        p2 = new JPanel(new GridLayout(5, 4));
        p3 = new JPanel(new GridLayout(2,1));
        p31 = new JPanel(new GridLayout(2,1));
        p32 = new JPanel(new GridLayout(2,1));
        p41 = new JPanel(new GridLayout(5,5));
        p42 = new JPanel(new GridLayout(5,5));
        ll1 = new JLabel("染色体1：");
        ll2 = new JLabel("染色体2：");
        ll3 = new JLabel("染色体3：");
        ll4 = new JLabel("染色体4：");
        ll5 = new JLabel("染色体5：");
        lm1 = new JLabel("初始");
        lm11 = new JLabel("初始");
        lr1 = new JLabel("现最优染色体:");
        lr11 = new JLabel("现最优染色体:");
        lr2 = new JLabel("现最优适应度:");
        lr22 = new JLabel("现最优适应度:");
        lr3 = new JLabel(String.valueOf(best0.Eval()));
        lr33 = new JLabel(String.valueOf(best0.Eval()));
        ltimes1 = new JLabel("迭代次数：");
        ltimes2 = new JLabel("0");

        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 4; j++) {
                l1[i][j] = new JLabel(String.valueOf(population[i].genes[j]));
                l1[i][j].setBorder(new LineBorder(Color.BLACK));

            }
        for(int i = 0;i < 4;i++){
            bestChr[i] = new JLabel(String.valueOf(best0.genes[i]));
            bestChr[i].setBorder(new LineBorder(Color.BLACK));
        }

        //标签布局设置
        ll1.setHorizontalAlignment(JLabel.RIGHT);
        ll2.setHorizontalAlignment(JLabel.RIGHT);
        ll2.setHorizontalAlignment(JLabel.RIGHT);
        ll3.setHorizontalAlignment(JLabel.RIGHT);
        ll4.setHorizontalAlignment(JLabel.RIGHT);
        ll5.setHorizontalAlignment(JLabel.RIGHT);
        lm1.setHorizontalAlignment(JLabel.CENTER);
        lm1.setVerticalAlignment(JLabel.BOTTOM);
        lm11.setHorizontalAlignment(JLabel.CENTER);
        lm11.setVerticalAlignment(JLabel.BOTTOM);

        //面板布局设置
        p1.setSize(50,300);
        p2.setSize(600,300);
        p3.setSize(50,300);

        //面板容器加入内容
        p1.add(ll1);
        p1.add(ll2);
        p1.add(ll3);
        p1.add(ll4);
        p1.add(ll5);
        p3.add(new JLabel());
        p3.add(lm1);
        p31.add(lm11);
        p31.add(p32);
        p32.add(button3);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                p2.add(l1[i][j]);
            }
        }
        p41.add(ltimes1);
        p41.add(ltimes2);
        for (int i = 0; i < 3; i++) {
            p41.add(new JLabel());
        }
        p41.add(new JLabel("解决问题："));
        p41.add(new JLabel("求函数y=x1^2 +"));
        p41.add(new JLabel(" x2^2 + x3^2 +"));
        p41.add(new JLabel("x4^2的最大值"));
        p41.add(new JLabel());
        p41.add(new JLabel("理论最优适应度:"));
        p41.add(new JLabel("4"));
        p41.add(new JLabel());
        p41.add(new JLabel());
        p41.add(new JLabel());
        p41.add(lr22);
        p41.add(lr33);
        p41.add(new JLabel());
        p41.add(new JLabel());
        p41.add(new JLabel());
        p41.add(lr11);
        for (int i = 0;i < 4;i++){
            p41.add(bestChr[i]);
        }

        //折线图
        double[] xx = new double[nums+1];
        double[] yy = new double[nums+1];
        for(int i = 0;i < nums+1;i++){
            xx[i] = i;
        }
        for(int i = 0;i < nums+1;i++){
            yy[i] = best[i].Eval();
        }
        xchart = QuickChart.getChart("折线图", "迭代次数", "适应度", "最优适应度", xx, yy);
        xp = new XChartPanel<XYChart>(xchart);

        //窗口设置
        frame1.setLayout(new GridLayout(2, 1));
        frame2.setLayout(new GridLayout(3, 3));
        frame3.setLayout(new GridLayout(3,3));
        frame1.setSize(600, 600);
        frame1.setLocation(650, 200);
        frame2.setSize(1400, 800);
        frame2.setLocation(300, 100);
        frame3.setSize(1400, 800);
        frame3.setLocation(300, 100);

        frame1.getContentPane().add(button1);
        frame1.getContentPane().add(button2);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true);

        //按钮事件触发
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model = 1;
                frame1.setVisible(false);
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model = 2;
                frame1.setVisible(false);
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (times < nums) {
                    if (step % 3 == 0) {
                        lm11.setText("选择");
                        for (int j = 0; j < 5; j++) {
                            for (int k = 0; k < 4; k++) {
                                l1[j][k].setText(String.valueOf(selectGenes[times][j].genes[k]));
                            }
                        }
                        ltimes2.setText(String.valueOf(times + 1));
                    } else if (step % 3 == 1) {
                        lm11.setText("交叉");
                        for (int j = 0; j < 5; j++) {
                            for (int k = 0; k < 4; k++) {
                                l1[j][k].setText(String.valueOf(matingGenes[times][j].genes[k]));
                            }
                        }
                    } else {
                        lm11.setText("变异");
                        for (int j = 0; j < 5; j++) {
                            for (int k = 0; k < 4; k++) {
                                l1[j][k].setText(String.valueOf(variationGenes[times][j].genes[k]));
                            }
                        }
                        for (int j = 0; j < 4; j++) {
                            bestChr[j].setText(String.valueOf(best[times + 1].genes[j]));
                        }
                        lr33.setText(String.valueOf(best[times + 1].Eval()));
                        times++;
                        if(times == nums){
                            System.out.println("算法迭代结束！");
                            ltimes2.setText("迭代结束！");
                        }
                    }
                    step++;
                }
            }
        });
    }


    public void runningGui(int nums, Chromosome[][] selectGenes, Chromosome[][] matingGenes, Chromosome[][] variationGenes, Chromosome[] best) {
        if(model == 1) {        //点击单步运行后进行绘制GUI
            System.out.println("用户选择单步运行");
            frame2.getContentPane().add(new JLabel());
            frame2.getContentPane().add(p31);
            frame2.getContentPane().add(new JLabel());
            frame2.getContentPane().add(p1);
            frame2.getContentPane().add(p2);
            frame2.getContentPane().add(p41);
            frame2.getContentPane().add(new JLabel());
            frame2.getContentPane().add(xp);
            frame2.getContentPane().add(new JLabel());
            frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame2.setVisible(true);
        }
        else {          //点击连续运行后绘制GUI
            System.out.println("用户选择连续运行");
            frame3.getContentPane().add(new JLabel());
            frame3.getContentPane().add(p3);
            frame3.getContentPane().add(new JLabel());
            frame3.getContentPane().add(p1);
            frame3.getContentPane().add(p2);
            frame3.getContentPane().add(p41);
            frame3.getContentPane().add(new JLabel());
            frame3.getContentPane().add(xp);
            frame3.getContentPane().add(new JLabel());
            for (int i = 0; i < nums; i++) {
                lm1.setText("选择");
                try {// 暂停0.5秒钟
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int j = 0; j < 5; j++) {
                    for (int k = 0; k < 4; k++) {
                        l1[j][k].setText(String.valueOf(selectGenes[i][j].genes[k]));
                    }
                }
                frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame3.setVisible(true);
                ltimes2.setText(String.valueOf(i + 1));
                try {// 暂停0.5秒钟
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                lm1.setText("交叉");
                try {// 暂停0.5秒钟
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int j = 0; j < 5; j++) {
                    for (int k = 0; k < 4; k++) {
                        l1[j][k].setText(String.valueOf(matingGenes[i][j].genes[k]));
                    }
                }
                frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame3.setVisible(true);
                try {// 暂停0.5秒钟
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                lm1.setText("变异");
                try {// 暂停0.5秒钟
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int j = 0; j < 5; j++) {
                    for (int k = 0; k < 4; k++) {
                        l1[j][k].setText(String.valueOf(variationGenes[i][j].genes[k]));
                    }
                }
                for(int j = 0;j < 4;j++){
                    bestChr[j].setText(String.valueOf(best[i+1].genes[j]));
                }
                lr33.setText(String.valueOf(best[i+1].Eval()));
                frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame3.setVisible(true);
                try {
                    // 暂停1秒钟
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            ltimes2.setText("迭代结束！");
            System.out.println("算法迭代结束！");
        }
    }
}
