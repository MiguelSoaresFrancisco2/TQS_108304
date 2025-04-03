alinea f)

## Resultados da análise

O meu Maven Java project do euromillions passou o teste Quality Gate.

### Resumo do dashboard:
- **Security:** 0 issues
- **Reliability:** 0 issues
- **Maintainability:** 39 issues
- **Coverage:** 78.5%
- **Duplications:** 0%
- **Security Hotspots:** 1


alinea g)

## Análise de Issues SonarQube

| Issue               | Problem description                                                                                           | How to solve                                                                                      |
|--------------------|----------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------|
| **Maintainability**  | Método de teste `testeEquals()` está vazio e sem explicação                                                   | Adicionar implementação, lançar `UnsupportedOperationException`, ou comentar a razão              |
| **Security Hotspot** | Utilização de `java.util.Random` para gerar números pseudoaleatórios num contexto sensível (classe `Dip`)     | Substituir por `java.security.SecureRandom` para garantir imprevisibilidade criptográfica         |
| **Reliability**      | Não existem issues                                                                                            | —                                                                                                 |
| **Security**         | Não existem issues                                                                                            | —                                                                                                 |

alinea h)


## h) Ferramentas externas usadas pelo SonarQube (Checkstyle, PMD e SpotBugs)

O SonarQube permite integrar ferramentas externas de análise estática, como:

- **Checkstyle**: Foca-se em convenções e estilo de código. Ajuda a manter o código limpo e consistente.

- **PMD**: Analisa o código à procura de más práticas e código problemático, como variáveis não usadas e duplicação de código.

- **SpotBugs**: Inspeciona o bytecode gerado pela aplicação e deteta potenciais erros de execução, como *null pointer exceptions* e uso incorreto de bibliotecas.

No SonarQube, é possível importar relatórios dessas ferramentas diretamente através das opções:
- `sonar.java.checkstyle.reportPaths`
- `sonar.java.pmd.reportPaths`
- `sonar.java.spotbugs.reportPaths`

Estas opções permitem carregar ficheiros XML com os resultados de análises feitas externamente, para que o SonarQube os apresente no seu dashboard.
