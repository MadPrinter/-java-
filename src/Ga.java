import java.util.Random;
public class Ga {
    int f1,f2;  //选择交叉的两个染色体
    public Chromosome[] select(Chromosome[] population){        //选择
        Chromosome[] nextpopulation = new Chromosome[5];
        for(int i = 0;i < 5;i++){       //初始化用于存储下一代
            nextpopulation[i] = new Chromosome();
        }
        double total = 0;
        for(int i = 0;i < 5;i++){
            total += population[i].Eval();
        }
        double[] c = new double[5];
        for(int i = 0;i < 5;i++){
            c[i] = population[i].Eval() / total;
        }
        Random rand = new Random();
        for(int i = 0;i < 5;i++){       //轮盘赌
            double r = rand.nextDouble();
            if(r<c[0])
                System.arraycopy(population[0].genes, 0, nextpopulation[i].genes, 0, 4);
            else if(r>=c[0]&&r<c[0]+c[1])
                System.arraycopy(population[1].genes, 0, nextpopulation[i].genes, 0, 4);
            else if(r>=c[0]+c[1]&&r<c[0]+c[1]+c[2])
                System.arraycopy(population[2].genes, 0, nextpopulation[i].genes, 0, 4);
            else if(r>=c[0]+c[1]+c[2]&&r<c[0]+c[1]+c[2]+c[3])
                System.arraycopy(population[3].genes, 0, nextpopulation[i].genes, 0, 4);
            else
                System.arraycopy(population[4].genes, 0, nextpopulation[i].genes, 0, 4);
        }
        return nextpopulation;
    }

    public Chromosome[] mating(Chromosome[] population){     //交叉
        Chromosome[] nextpopulation = new Chromosome[5];
        for(int i = 0;i < 5;i++){       //初始化用于存储下一代
            nextpopulation[i] = new Chromosome();
        }
        for(int i = 0;i < 5;i++){
            System.arraycopy(population[i].genes, 0, nextpopulation[i].genes, 0, 4);
        }
        Random rand = new Random();
        f2=-1;
        f1 = rand.nextInt(5);
        do {
            f2 = rand.nextInt(5);
        }while(f1==f2);
        int num = rand.nextInt(2)+1;  //选择交叉的位数
        Chromosome child1 = new Chromosome();
        Chromosome child2 = new Chromosome();
        System.arraycopy(nextpopulation[f1].genes, 0, child1.genes, 0, 4);
        System.arraycopy(nextpopulation[f2].genes, 0, child2.genes, 0, 4);
        if(num == 1){    //p数组存储哪几个位交换
            int[] p = new int[1];
            p[0] = rand.nextInt(4);
            child1.genes[p[0]] = nextpopulation[f2].genes[p[0]];
            child2.genes[p[0]] = nextpopulation[f1].genes[p[0]];
        }
        else {
            int[] p = new int[2];
            p[0] = rand.nextInt(4);
            do{
                p[1] = rand.nextInt(4);
            } while (p[0] == p[1]);
            child1.genes[p[0]] = nextpopulation[f2].genes[p[0]];
            child1.genes[p[1]] = nextpopulation[f2].genes[p[1]];
            child2.genes[p[0]] = nextpopulation[f1].genes[p[0]];
            child2.genes[p[1]] = nextpopulation[f1].genes[p[1]];
        }
        System.arraycopy(child1.genes, 0, nextpopulation[f1].genes, 0, 4);
        System.arraycopy(child2.genes, 0, nextpopulation[f2].genes, 0, 4);
        return nextpopulation;
    }

    public Chromosome[] variation(Chromosome[] population){         //变异
        Chromosome[] nextpopulation = new Chromosome[5];
        for(int i = 0;i < 5;i++){       //初始化用于存储下一代
            nextpopulation[i] = new Chromosome();
        }
        for(int i = 0;i < 5;i++){
            System.arraycopy(population[i].genes, 0, nextpopulation[i].genes, 0, 4);
        }
        Random rand = new Random();
        if(rand.nextDouble()<1){ //变异概率设置为1
            nextpopulation[f1].genes[rand.nextInt(4)] = rand.nextFloat();
        }
        if(rand.nextDouble()<1){ //变异概率设置为1
            nextpopulation[f2].genes[rand.nextInt(4)] = rand.nextFloat();
        }
        return nextpopulation;
    }

    public Chromosome bestGenes(Chromosome[] population){       //计算最佳染色体
        int num = 0;    //标记最佳染色体是第几个
        for(int i = 1;i < 5;i++){
            if(population[num].Eval()<population[i].Eval())
                num = i;
        }
        return population[num];
    }
}
