# Machine Learning & Deep Learning

## Regressão Linear

1. Representação do Modelo
2. Função de Custo

### 1. Representação do Modelo

### Objectivo:

Vamos implementar o modelo $f_{w,b}$ para regressão linear com uma variável.

### Problema

Consideremos o caso simples da previsão do preço das casas.Por simplicidade consideremos apenas dois pontos de dados:


| Size (1000 sqft)     | Price (1000s of dollars) |
| -------------------| ------------------------ |
| 1.0               | 300                      |
| 2.0               | 500                      |

Queremos ajustar um modelo de regressão linear (linha preta no gráfico) a estes dados de forma a que possamos depois saber,por exemplo, qual o preço de uma casa com 1200 sqft

![img.png](img.png)

Consideremos os seguintes vectores:

```
final DLVector xTrain = new DLVector(new double[]{1.0, 2.0});
final DLVector yTrain = new DLVector(new double[]{300.0, 500.0});
LOG.info(String.format("xTrain: %s", xTrain));
LOG.info(String.format("yTrain: %s", yTrain));
```
```
INFO: xTrain: [ 1.0, 2.0]
INFO: yTrain: [ 300.0, 500.0]
```

#### Número de exemplos de treino
Se $m$ for o número de exemplos de treino, temos:

```
final int m = xTrain.dimension();
LOG.info(String.format("Número de exemplos de treino: %d", m));
```

```
INFO: Número de exemplos de treino: 2
```

Vamos usar ($x^{(i)}$, $y^{(i)}$) para referir o exemplo de treino $i$

### A função modelo

![img_1.png](img_1.png)

A função modelo para a regressão linear (que é uma função que mapeia valores de $x$ para $y$) é representada como:

```
$$ f_{w,b}(x^{(i)}) = wx^{(i)} + b \tag{1}$$
```

Vamos calcular alguns valores de  $f_{w,b}(x^{(i)})$ para os dois pontos de dados:

Para $x^{(0)}$, `f_wb = w * x[0] + b`

Para $x^{(1)}$, `f_wb = w * x[1] + b`

```
    /**
     * 
     * @param w Parâmetro do modelo
     * @param b Parâmetro do modelo
     * @return  Valores alvo
     */
    public DLVector computeModelOutput(final double w, final double b){
        final int m = xTrain.dimension();
        final double[] f_wb = new double[m];
        for (int i = 0; i < m; i++) {
            f_wb[i] = w * xTrain.component(i) + b;
        }
        return new DLVector(f_wb);
    }
```
**Nota**: Ver classe **pt.deeplearning.LinearRegression.java**

### Previsão

Agora que temos o modelo, podemos fazer algumas previsões. O mais importante é calcular os valores ideias dos parâmetros $w$ e $b$.
Por hora suponhamos que já temos bons valores para $w$ e $b$. Então, por exemplo, para prever o preço de uma casa com 1200sqft, temos:

```
final double xi = 1.2;
final double w = 200.0;
final double b = 100.0;
final double cost1200sqft = w * xi + b;
LOG.info(String.format("Preço previsto para uma casa de %s sqft: %f", xi*1000, cost1200sqft));
```
```
INFO: Preço previsto para uma casa de 1200.0 sqft: 340,000000
```
### 2. Função de Custo
