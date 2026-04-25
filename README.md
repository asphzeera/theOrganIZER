# File Organizer - Java + Apache Tika

Sistema de organização de arquivos inteligente desenvolvido em Java 21, utilizando a biblioteca Apache Tika para detecção de tipos de arquivos (MIME types) e interface gráfica baseada em Swing. O projeto é gerenciado via Maven e configurado para execução em ambientes NixOS.

## Funcionalidades

* **Detecção por Conteúdo:** Utiliza o Apache Tika para analisar o cabeçalho real do arquivo, garantindo a identificação correta mesmo que a extensão tenha sido alterada manualmente.
* **Processamento Recursivo:** Capacidade de percorrer árvores de diretórios completas, processando arquivos em múltiplos níveis de subpastas.
* **Interface Gráfica (GUI):** Utilização de JDialog e JFileChooser para uma interação fluida na seleção de diretórios e parametrização da saída.
* **Organização Estruturada:** Os arquivos são movidos para uma estrutura hierárquica baseada em Categoria MIME e Extensão.

## Tecnologias e Ferramentas

* **Linguagem:** Java 21
* **Gerenciador de Dependências:** Maven
* **Processamento de Metadados:** Apache Tika (Core)
* **Interface Gráfica:** Java Swing / AWT
* **Ambiente:** NixOS (Suporte a nix-shell / flakes)

## Estrutura do Projeto

```text
organ/
├── organ-files/
│   ├── pom.xml             # Configurações do Maven e dependências
│   └── src/
│       └── main/java/      # Código-fonte (Main, Functions, Classes de Interface)
├── flake.nix               # Configuração de ambiente reprodutível
└── README.md
```

## Instruções de Execução

### Em ambientes NixOS
Com o ambiente configurado via flake ou shell.nix:

```bash
nix develop
cd organ-files
mvn compile exec:java -Dexec.mainClass="organ.project.Main"
```

### Outros ambientes
Realize a instalação do JDK 21 e Maven:

```bash
cd organ-files
mvn clean compile exec:java -Dexec.mainClass="organ.project.Main"
```

## Lógica de Organização

O algoritmo de movimentação segue a seguinte convenção de caminhos:
`[Diretório_Saída] / [Categoria_MIME] / [Extensão] / [Nome_do_Arquivo]`

Exemplos de saída:
* Arquivos PDF: `Organizado/application/pdf/documento.pdf`
* Arquivos de Imagem: `Organizado/image/jpeg/foto.jpg`
* Arquivos de Texto: `Organizado/text/plain/nota.txt`

## Licença

Projeto desenvolvido para fins acadêmicos e estudo de integração de bibliotecas externas e recursividade em Java.

---
**Desenvolvido por Asaph**
