import java.util.Random;
public class Chromosome {
    float[] genes = new float[4];       //基因序列构成染色体
    Chromosome(){       //初始化染色体
        Random rand = new Random();
        for(int i = 0;i < 4;i++){
            genes[i] = rand.nextFloat();
        }
    }
    public double Eval() {      //计算适应度值
        double result = 0;
        for (int i = 0; i < 4; i++) {
            result += Math.pow(genes[i], 2);    //求函数y = x1^2+x2^2+x3^2+x4^2，在0到1内最大值
        }
        return result;
    }
}



