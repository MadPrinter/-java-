public class Main {
    public static void main(String[] args) {
        int nums = 100;        //设置迭代次数
        Chromosome best0= new Chromosome();        //存储初代最优解
        Chromosome[] best= new Chromosome[nums+1];        //存储历代最优解
        for(int i = 0;i <= nums;i++) {       //初始化
                best[i] = new Chromosome();
        }
        Chromosome[] population = new Chromosome[5];        //设置初代种群
        Chromosome[] p1copy = new Chromosome[5];        //拷贝一份
        for(int i = 0;i < 5;i++){       //初始化
            population[i] = new Chromosome();
            p1copy[i] = new Chromosome();
            for(int j = 0;j < 5;j++)
                System.arraycopy(population[i].genes, 0, p1copy[i].genes, 0, 4);
        }
        Ga ga = new Ga();       //遗传算法
        best0 = ga.bestGenes(population);        //最优染色体
        best[0] = best0;
        System.out.println("初始最优适应度："+best0.Eval());
        Chromosome[][] selectGenes = new Chromosome[nums][5];    //存储每次选择更新后的种群
        Chromosome[][] matingGenes = new Chromosome[nums][5];    //存储每次交叉更新后的种群
        Chromosome[][] variationGenes = new Chromosome[nums][5]; //存储每次变异更新后的种群
        for(int i = 0;i < nums;i++) {    //初始化
            for (int j = 0; j < 5; j++) {
                selectGenes[i][j] = new Chromosome();
                matingGenes[i][j] = new Chromosome();
                variationGenes[i][j] = new Chromosome();
            }
        }

        int times = 0;
        while(times++ < nums){      //迭代次数
            population = ga.select(population);       //选择
            for(int i = 0;i < 5;i++)
                System.arraycopy(population[i].genes, 0, selectGenes[times-1][i].genes, 0, 4);
            population = ga.mating(population);       //交叉
            for(int i = 0;i < 5;i++)
                System.arraycopy(population[i].genes, 0, matingGenes[times-1][i].genes, 0, 4);
            population = ga.variation(population);        //变异
            for(int i = 0;i < 5;i++)
                System.arraycopy(population[i].genes, 0, variationGenes[times-1][i].genes, 0, 4);
            if (best[times-1].Eval()<ga.bestGenes(population).Eval()) {    //如果有更优则更新
                for(int i = 0;i < 4;i++) {
                    best[times].genes[i] = ga.bestGenes(population).genes[i];
                }
            }
            else {
                System.arraycopy(best[times - 1].genes, 0, best[times].genes, 0, 4);        //没有则保持
            }
        }

        System.out.println("结束最优适应度："+best[nums-1].Eval());
        Gui gui = new Gui(nums,selectGenes,matingGenes,variationGenes,best0,p1copy,best);
        while(gui.model == -1) {
            try {// 暂停1秒钟
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("用户未选择模式！");
        }

        if(gui.model == 1) {
            gui.runningGui(nums,selectGenes,matingGenes,variationGenes,best);
        }
        else{
            gui.runningGui(nums,selectGenes,matingGenes,variationGenes,best);
        }
    }
}