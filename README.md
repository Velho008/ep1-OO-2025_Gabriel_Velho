# Sistema Acadêmico - FCTE

## Descrição do Projeto

Desenvolvimento de um sistema acadêmico para gerenciar alunos, disciplinas, professores, turmas, avaliações e frequência, utilizando os conceitos de orientação a objetos (herança, polimorfismo e encapsulamento) e persistência de dados em arquivos.

O enunciado do trabalho pode ser encontrado aqui:
- [Trabalho 1 - Sistema Acadêmico](https://github.com/lboaventura25/OO-T06_2025.1_UnB_FCTE/blob/main/trabalhos/ep1/README.md)

## Dados do Aluno

- **Nome completo:** [Gabriel Velho de Souza]
- **Matrícula:** [242015218]
- **Curso:** [engenharias]
- **Turma:** [06]

---

## Instruções para Compilação e Execução

1. **Compilação:**  
   [é necessario clonar meu repositorio na IDE que for usada, recomendavel o uso do VSCODE, usar comando git clone https://github.com/Velho008/ep1-OO-2025_Gabriel_Velho]

2. **Execução:**  
   [a main do programa fica em SIGAA2.java, basta executar codigo]

3. **Estrutura de Pastas:**  
## 📂 Estrutura do Projeto 
```
ep1-OO-2025_Gabriel_Velho
│
├── classes/
│      ├── Aluno.java             # construtores, sets, gets e parte de arquivos usada em alunos
│      ├── AlunoEspecial.java     # construtores, sets, gets e parte de arquivos usada em alunos especiais
│      ├── Boletim.java           # construtores, sets, gets e parte de arquivos usada em boletins
│      ├── Disciplina.java        # construtores, sets, gets e parte de arquivos usada em disciplinas
│      ├── Professor.java         # construtores, sets, gets e parte de arquivos usada em professores
│      ├── Relatorio.java         # construtores, sets, gets e parte de arquivos usada em relatorios
│      ├── SIGAA2.java            # classe main e diversos outros metodos/funções
│      └── Turma.java             # construtores, sets, gets e parte de arquivos usada em turmas
│
├──  banco_de_dados/              # criado de maneira automatica, assim como suas subpastas
│      ├── alunos/                # local onde alunos são armazenados, para garantir sua persistencia
│      ├── boletins/              # local onde diretorios de boletim são armazenados, para garantir sua persistencia
│         └──"matricula_do_aluno"/# local onde boletins são armazenados, para garantir sua persistencia, muda para cada matricula de aluno
│      ├── disciplinas/           # local onde disciplinas são armazenadas, para garantir sua persistencia
│      ├── professores/           # local onde professores são armazenados, para garantir sua persistencia
│      ├── relatorios/            # local onde relatorios são armazenados, para garantir sua persistencia
│      └── turmas/                # local onde turmas são armazenadas, para garantir sua persistencia
│
├── imgs/                         # prints usadas no README.md
│      ├── print_cadastro.png
│      ├── print_menus.png
│      └── print_notas.png
│
└── README.md
```

3. **Versão do JAVA utilizada:**  
   [java version "1.8.0_441"
Java(TM) SE Runtime Environment (build 1.8.0_441-b07)
Java HotSpot(TM) 64-Bit Server VM (build 25.441-b07, mixed mode)]

---

## Vídeo de Demonstração

- [(https://drive.google.com/file/d/1pNgnd3x1PG1dVmj07rmA_kb1RGmBPvlP/view?usp=drive_link)]

---

## Prints da Execução

1. Menu Principal:  
   ![Inserir Print 1](/imgs/print_menus.png)

2. Cadastro de Aluno:  
   ![Inserir Print 2](/imgs/print_cadastro.png)

3. Relatório de Frequência/Notas:  
   ![Inserir Print 3](/imgs/print_notas.png)

---

## Principais Funcionalidades Implementadas

- [x] Cadastro, listagem, matrícula e trancamento de alunos (Normais e Especiais)
- [x] Cadastro de disciplinas e criação de turmas (presenciais e remotas)
- [x] Matrícula de alunos em turmas, respeitando vagas e pré-requisitos
- [x] Lançamento de notas e controle de presença
- [x] Cálculo de média final e verificação de aprovação/reprovação
- [x] Relatórios de desempenho acadêmico por aluno, turma e disciplina
- [x] Persistência de dados em arquivos (.txt ou .csv)
- [x] Tratamento de duplicidade de matrículas
- [x] Uso de herança, polimorfismo e encapsulamento

---

## Observações (Extras ou Dificuldades)

- [no meu video eu apenas comento sobre o sistema, não executo ele, na verdade eu coloquei varias funcionalidades que não foram pedidas, o menu não quebra ao digitar coisas que não numeros, é quase sempre possivel retornar ao menu anterior, ao atualizar dados de aluno o sistema atualiza tudo sozinho (turmas, boletins e etc...), ao retirar turma/disciplina/aluno o sistema é automaticamente atualizado para prevenir falhas]

---

## Contato

- [gabrielvelho08@gmail.com]
