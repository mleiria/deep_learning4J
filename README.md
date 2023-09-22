# Machine Learning & Deep Learning

## Regressão Linear

1. Representação do Modelo

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
Se *m* for o número de exemplos de treino, temos:

```
final int m = xTrain.dimension();
LOG.info(String.format("Número de exemplos de treino: %d", m));
```

```
INFO: Número de exemplos de treino: 2
```

Vamos usar ($x^{(i)}$, $y^{(i)}$) para referir o exemplo de treino $i^{th}$

