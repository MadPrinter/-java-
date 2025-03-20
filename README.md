项目 README<br>
项目简介
<br>该项目是一个基于Java的简单遗传算法（Genetic Algorithm, GA）实现，用于求解一个特定的优化问题：在给定的函数y = x1^2 + x2^2 + x3^2 + x4^2中，找到使函数值最大的x1, x2, x3, x4值（其中x1, x2, x3, x4均在0到1之间）。项目还包含一个图形用户界面（GUI），用于展示遗传算法的迭代过程和结果。

<br>项目结构
<br>Chromosome.java: 定义染色体类，包含基因序列（genes数组）和适应度计算函数（Eval）。
<br>Ga.java: 定义遗传算法类，包含选择（select）、交叉（mating）、变异（variation）和寻找最佳染色体（bestGenes）的方法。
<br>Gui.java: 定义图形用户界面类，用于展示遗传算法的迭代过程和结果，包括单步运行和连续运行两种模式。
<br>Main.java: 主类，用于初始化种群、运行遗传算法并启动GUI。

<br>功能说明
<br>Chromosome类：<br>
genes：一个长度为4的浮点数数组，表示染色体的基因序列。<br>
Eval：计算染色体的适应度值，即函数y = x1^2 + x2^2 + x3^2 + x4^2的值。<br>
<br>Ga类：
<br>select：根据适应度值使用轮盘赌算法选择下一代种群。<br>
mating：对选择的染色体进行单点或双点交叉操作生成新的染色体。<br>
variation：对染色体进行变异操作，以一定概率改变基因值。<br>
bestGenes：从种群中找到适应度值最高的染色体。<br>
<br>Gui类：<br>
提供单步运行和连续运行两种模式，展示遗传算法的迭代过程和结果。<br>
使用XChart库绘制适应度值的折线图。<br>
<br>Main类：<br>
初始化种群和最佳染色体。<br>
运行遗传算法，迭代更新种群并记录最佳染色体。<br>
启动GUI，根据用户选择的模式展示遗传算法的迭代过程和结果。<br>

<br>使用说明
<br>运行项目：
<br>确保已安装Java开发环境（JDK）。
<br>使用IDE（如Eclipse、IntelliJ IDEA）或命令行工具编译并运行Main.java。<br>
<br>GUI操作：<br>
<br>在主窗口点击“单步运行”或“连续运行”按钮选择运行模式。
<br>在单步运行模式下，点击“下一步”按钮逐步查看遗传算法的迭代过程和结果。
<br>在连续运行模式下，算法将自动迭代并展示最终结果。<br>
<br>查看结果：
<br>在GUI中查看各代染色体的基因值和适应度值。
<br>查看适应度值的折线图，了解算法迭代过程中适应度值的变化趋势。

<br>注意事项
<br>本项目使用XChart库绘制折线图，需要确保项目中已包含XChart库的依赖。
<br>遗传算法的性能和结果可能受多种因素影响，包括种群大小、迭代次数、选择、交叉和变异策略等。
<br>GUI部分可能需要根据实际屏幕大小和分辨率进行调整。

<br>依赖项
<br>Java Development Kit (JDK)
<br>XChart库（用于绘制折线图）

<br>联系信息
<br>如有任何问题或建议，请联系MadPrinter。
