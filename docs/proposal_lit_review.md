课题研究的目的和意义：
  时间序列预测是智能决策系统的重要基础任务，广泛应用于电力负荷调度、交通流量管理、气象预警、金融风险监测、工业设备运维和城市运行管理等场景。随着数据采集频率持续提升、业务预测周期不断拉长，模型不仅要刻画短期波动，还要在长预测步下稳定表达趋势变化、周期规律、局部扰动与跨变量关联。近年来从 Transformer 系列到时序基础模型（TSFM）的研究快速推进，显著提升了复杂序列拟合能力，但也暴露出“精度—稳定性—可解释性”难以兼得的问题[1-8]。

  在真实业务系统中，预测任务通常具有“序列长、变量多、分布漂移、噪声强、异常多发”的共性：电力负荷同时受季节、天气、节假日和行为模式驱动，交通流量存在工作日/周末切换和突发拥堵，工业与气象传感器数据则常伴随缺失、错位、尖峰和非平稳漂移。若模型仅在时域端到端拟合数值而缺乏结构化归因能力，长预测步下误差会沿时间累计并放大，直接影响调度、预警和资源配置质量。因此，围绕“可分解、可对齐、可融合、可解释”的长序列建模方法展开研究，既有明确理论价值，也有显著工程价值[9-14]。

  基于上述背景，本课题拟构建“结构化分解 + 时频协同”的长序列预测框架：首先在输入侧进行季节—趋势—残差三分解，显式降低传统二分解将噪声误并入季节项的风险；其次在建模侧引入时域与频域双分支，通过相位对齐与自适应频带融合提升跨周期表达稳定性；最后在融合侧通过动态权重与专家路由实现成分级预测协同。该思路可使模型在给出预测结果的同时，解释误差主要来自趋势失配、季节漂移还是残差扰动，为高风险场景中的模型治理提供依据[2][5][10][15]。

国内外同类课题研究现状及发展趋势：
  从研究脉络看，时间序列预测经历了“统计建模—深度学习—高效长依赖—基础模型”四个阶段。早期 ARIMA、指数平滑和状态空间模型强调参数可解释与统计假设，但在复杂非线性与多变量耦合场景中上限明显；RNN/CNN/TCN 等方法提升了非线性表示能力，却仍受限于长程依赖衰减与跨变量关系建模不足。Transformer 的引入改变了这一局面：其全局注意力机制使长距离交互成为可能，随后 Informer、Autoformer、FEDformer、PatchTST、iTransformer 等工作持续改进复杂度、分解策略与变量建模方式，推动长序列预测进入结构化建模阶段[1][3][9][16-21]。

  2024 年以来，研究热点从“单一骨干网络竞争”转向“结构先验驱动的模块协同”。一类代表工作是分解增强范式：TimeMixer 通过 Past-Decomposable-Mixing 与 Future-Multipredictor-Mixing 在多尺度上联合趋势与季节信息，说明“先分解再混合”在长预测步下更稳健[1]；TimeMixer++ 进一步将多尺度时域与多分辨率频域统一为通用模式机，显示结构化归纳偏置已从单任务扩展到多任务统一建模[22]。另一类工作强调“简单架构 + 明确归因”，如 DLinear/NLinear/SparseTSF/FITS 等以线性映射、轻量参数与频域插值实现高性价比预测，证明在数据结构可被有效解耦时，模型规模并非唯一决定因素[9][17][23][24]。这类结果共同指向一个趋势：时序模型正在回归“问题结构本身”，而非盲目增大网络容量。

  多变量关系建模也在 2024 年后出现明显范式升级。iTransformer 将建模主轴从时间 token 转到变量 token，强化跨变量信息流，对多变量长序列表现出更优稳定性[3]；TimeXer面向外生变量注入，采用 patch-wise self-attention 与 variate-wise cross-attention 协同内生/外生信息，提高了复杂业务条件下的可迁移性[4]；UniTS、Timer、MOMENT 等工作进一步尝试用统一网络处理预测、填补、分类、异常检测等任务，推动“单任务最佳”走向“统一表征最优”[5][7][14]。这意味着未来模型评估不应只看单一数据集误差，还应关注任务迁移效率、跨域泛化和推理稳定性。

  与此同时，时频融合正从“辅助特征”走向“核心建模主线”。传统时域网络擅长局部动态但难稳定表达长周期；纯频域方法可压缩全局周期信息，但若采用硬频带切分或固定滤波，容易造成边界信息损失与相位失配。近期研究已出现三类改进方向：其一，频域学习主干化，直接在谱空间建模周期结构并与时域残差信号互补；其二，频带自适应化，通过软门控、可学习带宽与动态权重代替硬划分；其三，相位一致性建模，通过相位感知编码缓解不同变量、不同时间窗之间的相位漂移[10][11][25-27]。TFformer、TimesNet 及相关频域模型的经验表明，若能在时频两域建立可逆、可对齐的交互通道，长预测步稳定性通常优于单域建模[25][28]。

  非平稳性与鲁棒性是近年来另一条关键主线。现实数据常出现制度切换、季节漂移、突发异常与测量误差，导致“训练分布 ≠ 测试分布”。围绕该问题，研究社区形成了“归一化重参数化 + 鲁棒损失 + 分布适配”的组合路径：部分工作在输入侧进行去偏与尺度重整，减小不同窗口统计量差异；部分工作在训练侧引入抗异常目标函数或稳健估计，提高模型对极端噪声的容忍度；另有研究借助提示学习、跨模态对齐或上下文微调缓解跨域迁移退化[2][12][13][29][30]。总体看，长序列预测已从“追求平均精度”转向“追求最坏场景下的可靠性”。

  时序基础模型（TSFM）则代表了另一种宏观趋势：通过大规模跨域预训练获取通用时序知识。Time-LLM、Chronos、TimesFM、Moirai、Sundial、Tiny Time Mixers、In-Context Fine-Tuning 等工作分别从语言重编程、离散化建模、解码器预训练、通用 Transformer、上下文学习和轻量部署角度给出方案[6][8][31-35]。这些模型在零/少样本任务中展现出可观潜力，说明“数据规模 + 统一目标”能够显著提升泛化上限；但其局限也逐渐清晰：第一，面向具体行业任务时可解释性不足，难以回答“误差来自哪一类动态成分”；第二，频域贡献常被隐式吸收，难对关键频带做可审计分析；第三，异常冲击下的行为边界尚不稳定。也正因此，基础模型路线与结构化专用模型路线并非替代关系，更可能是“预训练通用底座 + 任务特化头部”的协同关系。

  从国内外研究布局看，国外团队在公开大规模预训练与统一评测上推进更快，强调跨域泛化和基础模型生态；国内团队在行业落地、鲁棒机制、轻量部署和多源异构融合方面进展显著，尤其关注电力、交通、工业等高价值场景中的稳定可用性。两者正在形成互补：前者提供可迁移底座，后者强化任务约束下的结构归因与工程鲁棒。未来 2-3 年，预计将出现四个并行方向：一是从二分解向三分解乃至更细粒度结构分解演进；二是从“时域主导+频域辅助”转向真正的双域协同；三是从固定网络转向专家化、路由化和可组合架构；四是从单一精度指标走向“精度+稳定性+解释性+能耗”的综合评价[12][22][31][35]。

  进一步看评测与方法演化的关系，当前社区已逐步意识到“榜单最优”不等于“场景最优”。许多公开数据集具有较强周期规律和相对干净的采样条件，容易放大模型在标准误差指标上的差异，却弱化了模型在缺失、异常、时钟偏移、概念漂移下的真实鲁棒差异。2024 年以来，越来越多工作开始补充 rolling-origin、跨数据集迁移、分布外测试和噪声注入测试，并关注推理延迟、显存占用与训练稳定性等工程指标[6][12][14][32]。这意味着未来研究不仅要回答“平均误差最低是谁”，还要回答“当输入变脏、周期错位或变量关系突变时，谁退化得更慢、恢复得更快”。从这一视角看，具备显式结构归因能力的方法更容易进行失效定位和在线修复，也更符合工业系统可观测、可审计、可治理的落地要求。

  从模型机理层面，长序列预测正呈现“分解层—表示层—融合层”三层协同的设计趋势。分解层负责将原始复杂序列映射为相对可学习的成分子空间，减少目标混杂；表示层针对不同成分采用匹配建模器，如趋势偏向低频平滑建模、季节偏向周期模板复用、残差偏向局部扰动刻画；融合层再通过门控、注意力或专家路由进行任务相关重组。近期 MoE 与动态路由在时序领域的引入，说明“单一参数共享”正在向“条件计算与模式专门化”过渡[33][35]。但现有方法仍存在两个断点：其一，许多模型虽做了时频联合，却缺乏统一的相位对齐机制，导致跨变量同步性不足；其二，频带划分常依赖固定区间，难适配不同数据域中的主导频率漂移。上述断点恰恰为“相位对齐 + 软频带 + 频域专家化”提供了明确研究空间。

  对国内应用场景而言，电力负荷、轨道交通、城市治理和工业运维普遍要求“长视距预测 + 异常容忍 + 可解释输出”三者同时成立。仅追求单次离线评测精度，往往难满足业务系统对稳定性的约束：例如在节假日切换、极端天气或设备维护窗口中，趋势项和季节项会同时发生幅度与相位变化，若模型无法分辨“可重复变化”与“一次性冲击”，则会在关键时段产生系统性偏差。因而，国内近年的研究和工程实践更加关注“结构显式化”的价值：一方面通过分解降低学习难度，另一方面通过可解释通道提升业务信任与决策可用性。总体判断是，未来主流方案将不是“纯黑箱大模型”或“纯规则小模型”的二选一，而是“结构化先验约束下的可扩展学习系统”：底层保留预训练带来的泛化能力，上层引入任务结构与机制创新完成领域适配。

  在此趋势下，本文拟提出的两项创新具有明确针对性与前沿契合度。其一，TriStream-TSF（season-trend-residual tri-decomposition with robust moving average）面向“二分解目标混杂”这一核心痛点，在分解层引入鲁棒滑动平均与显式残差通道：趋势分支强调低频平滑与漂移跟踪，季节分支建模可重复模式，残差分支捕获短时冲击与不可解释扰动。与仅含季节—趋势二分解的方法相比，TriStream-TSF 能减少噪声泄漏到季节项的概率，使下游学习目标更纯净，并提升异常场景下的稳定性与可解释性。

  其二，TFMix（time-frequency hybrid with phase-aligned Fourier positional encoding, soft frequency bands, frequency-domain MoE）对应“时频协同不足与相位错位”问题：在时域分支保留局部动态建模能力，在频域分支引入相位对齐傅里叶位置编码，显式校正跨变量、跨窗口的相位偏移；通过软频带划分避免硬边界造成的信息截断；再以频域 MoE 对不同频带模式进行专家化建模与动态路由，实现“低频趋势—中频季节—高频扰动”自适应分工。该设计可与 TriStream-TSF 形成前后衔接：前者先做可解释解耦，后者再做可对齐融合，最终在长预测步下兼顾精度、鲁棒性和可解释性。

  综上，国内外研究已从“更深网络”转向“更懂结构”的新阶段。面向真实复杂场景，下一代长序列预测模型需要同时回答四个问题：如何把混杂动态拆开、如何让时频信息对齐、如何在异常与漂移下保持稳定、如何把预测结果解释给业务决策者。TriStream-TSF 与 TFMix 的联合路线，正是在这一背景下提出：以前端三分解降低学习难度，以后端时频混合提升表达上限，以专家路由增强模式专门化能力，目标是在复杂多变量长序列任务中提供更可靠、更可解释的预测框架。

参考文献：
[1] Wang S, Wu H, Shi X, et al. TimeMixer: Decomposable Multiscale Mixing for Time Series Forecasting[C]//ICLR. 2024.
[2] Cao D, Jia F, Arik S O, et al. TEMPO: Prompt-based Generative Pre-trained Transformer for Time Series Forecasting[C]//ICLR. 2024.
[3] Liu Y, Hu T, Zhang H, et al. iTransformer: Inverted Transformers Are Effective for Time Series Forecasting[C]//ICLR. 2024.
[4] Wang Y, Wu H, Dong J, et al. TimeXer: Empowering Transformers for Time Series Forecasting with Exogenous Variables[C]//NeurIPS. 2024.
[5] Gao S, Koker T, Queen O, et al. UniTS: Building a Unified Time Series Model[C]//NeurIPS. 2024.
[6] Jin M, Wang S, Ma L, et al. Time-LLM: Time Series Forecasting by Reprogramming Large Language Models[C]//ICLR. 2024.
[7] Liu Y, Zhang H, Li C, et al. Timer: Generative Pre-trained Transformers Are Large Time Series Models[C]//ICML. 2024.
[8] Ansari A F, Stella L, Turkmen C, et al. Chronos: Learning the Language of Time Series[J]. TMLR, 2024.
[9] Xu Z, Zeng A, Xu Q. FITS: Modeling Time Series with 10k Parameters[C]//ICLR. 2024.
[10] Yi K, Zhang Q, Fan W, et al. Frequency-domain MLPs are More Effective Learners in Time Series Forecasting[C]//NeurIPS. 2024.
[11] Yang Y, Qiu X, Zhou J, et al. Adaptive Frequency Learning for Time Series Forecasting[C]//ICLR. 2024.
[12] Cheng H, Wen Q, Liu Y, Sun L. RobustTSF: Towards Theory and Design of Robust Time Series Forecasting with Anomalies[C]//ICLR. 2024.
[13] Lin S, Lin W, Wu W, et al. Frequency Adaptive Normalization for Non-stationary Time Series Forecasting[C]//NeurIPS. 2024.
[14] Goswami M, Szafer K, Choudhry A, et al. MOMENT: A Family of Open Time-series Foundation Models[C]//ICML. 2024.
[15] Woo G, Liu C, Kumar A, et al. Unified Training of Universal Time Series Forecasting Transformers[C]//ICML. 2024.
[16] Vaswani A, Shazeer N, Parmar N, et al. Attention Is All You Need[C]//NeurIPS. 2017.
[17] Zhou H, Zhang S, Peng J, et al. Informer: Beyond Efficient Transformer for Long Sequence Time-Series Forecasting[C]//AAAI. 2021.
[18] Wu H, Xu J, Wang J, Long M. Autoformer: Decomposition Transformers with Auto-Correlation for Long-Term Series Forecasting[C]//NeurIPS. 2021.
[19] Zhou T, Ma Z, Wen Q, et al. FEDformer: Frequency Enhanced Decomposed Transformer for Long-term Series Forecasting[C]//ICML. 2022.
[20] Nie Y, Nguyen N H, Sinthong P, Kalagnanam J. A Time Series is Worth 64 Words: Long-term Forecasting with Transformers[C]//ICLR. 2023.
[21] Cirstea R G, Guo C, Yang B, et al. PatchTST: 64 Words Are Enough for Long-term Forecasting[C]//ICLR. 2023.
[22] Wang S, Li J, Shi X, et al. TimeMixer++: A General Time Series Pattern Machine for Universal Predictive Analysis[C]//ICLR. 2025.
[23] Zeng A, Chen M, Zhang L, Xu Q. Are Transformers Effective for Time Series Forecasting?[C]//AAAI. 2023.
[24] Lin S, Lin W, Wu W, Chen H, Yang J. SparseTSF: Modeling Long-term Time Series Forecasting with 1k Parameters[C]//ICML. 2024.
[25] Zhang Y, Zhang W, Li X, et al. TFformer: A Time-Frequency Domain Bidirectional Sequence-Level Attention Based Transformer for Interpretable Long-Term Sequence Forecasting[J]. Pattern Recognition, 2024.
[26] Zeng A, Chen M, Zhang L, Xu Q. Time Series Forecasting with PatchTST and Channel-Independent Tokenization[J/OL]. arXiv:2211.14730, 2022.
[27] Wu H, Hu T, Liu Y, et al. TimesNet: Temporal 2D-Variation Modeling for General Time Series Analysis[C]//ICLR. 2023.
[28] Zhang Y, Li X, Wang J, et al. PMformer: A Novel Informer-Based Model for Accurate Long-Term Time Series Prediction[J]. Information Sciences, 2024.
[29] Sun C, Li H, Li Y, et al. TEST: Text Prototype Aligned Embedding to Activate LLM's Ability for Time Series[C]//ICLR. 2024.
[30] Liu X, Hu J, Li Y, et al. UniTime: A Language-Empowered Unified Model for Cross-Domain Time Series Forecasting[C]//WWW. 2024.
[31] Das A, Kong W, Sen R, Zhou Y. A Decoder-only Foundation Model for Time-series Forecasting[C]//ICML. 2024.
[32] Ekambaram V, Jati A, Dayama P, et al. Tiny Time Mixers: Fast Pre-trained Models for Enhanced Zero/Few-Shot Forecasting of Multivariate Time Series[C]//NeurIPS. 2024.
[33] Liu X, Liu J, Woo G, et al. Moirai-MoE: Empowering Time Series Foundation Models with Sparse Mixture of Experts[C]//KDD. 2024.
[34] Faw M, Sen R, Zhou Y, Das A. In-Context Fine-Tuning for Time-Series Foundation Models[C]//ICML. 2025.
[35] Liu Y, Qin G, Shi Z, et al. Sundial: A Family of Highly Capable Time Series Foundation Models[C]//ICML. 2025.
