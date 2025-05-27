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
   [na realidade eu só estava apertando o "play" do vscode, mas imagino que 'javac SIGAA2.java' tambem sirva]

2. **Execução:**  
   [a main do programa fica em SIGAA2.java, basta executar codigo]

3. **Estrutura de Pastas:**  
   [## 🗂️ Estrutura do Projeto 
```
classes/
├── Aluno.java/            # 
├── AlunoEspecial.java/    # 
├── Boletim.java/          # 
├── Disciplina.java/       # 
├── Professor.java/        # 
├── Relatorio.java/        # 
├── SIGAA2.java/           # 
└── Turma.java/            # 
banco_de_dados/
├── alunos/                # 
├── boletins/              # 
   └──"matricula_do_aluno" #
├── disciplinas/           # 
├── professores/           # 
├── relatorios/            # 
└──  turmas/               # 
```]

3. **Versão do JAVA utilizada:**  
   [java version "1.8.0_441"
Java(TM) SE Runtime Environment (build 1.8.0_441-b07)
Java HotSpot(TM) 64-Bit Server VM (build 25.441-b07, mixed mode)]

---

## Vídeo de Demonstração

- [Inserir o link para o vídeo no YouTube/Drive aqui]

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

- [na verdade eu coloquei varias funcionalidades que não foram pedidas, o menu não quebra ao digitar coisas que não numeros, é quase sempre possivel retornar ao menu anterior, ao atualizar dados de aluno o sistema atualiza tudo sozinho (turmas, boletins e etc...), ao retirar turma/disciplina/aluno]

---

## Contato

- [gabrielvelho08@gmail.com]
