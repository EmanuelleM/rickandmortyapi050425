# Rick and Morty Api Client!

## O que é esse app
Esse é um aplicativo desenvolvido para exibir a lista de personagens de Rick and Morty.

A exibição dos personagens é feita em formato de grade. 

É possível clicar nas imagens e ir para visualização de detalhes.

Também é possível filtrar os personagens de acordo com o status de vida: morto, vivo e desconhecido.

Esse app, se executado pela primeira vez com internet, vai carregar os resultados da API. Se executado uma próxima vez com a internet desligada, ele vai carregar o resultado anterior salvo em memória.


### Rick and Morty Api

Essa é uma API que retorna informações sobre diversas partes do universo de Rick and Morty. Ela pode ser consumida tanto via Rest quanto em GraphQL.

Mais informações podem ser encontradas aqui: [The Rick and Morty Api](http://rickandmortyapi.com/documentation)


### Bibliotecas

- Injeção de dependencia: Kodein
- Consumo de api: GraphQL
- Construção de tela: Compose
- Renderização de imagens: Coil
- Testes: Mockk e Junit

Todas as versoes de bibliotecas estão concentradas no arquivo: libs.versions.toml

##Arquitetura

MVVM, MVI e Clean Architecture. 

### Estrutura de pacotes

Esse é um app criado a partir do um módulo app e consumindo recursos de rede do módulo commom. As partes relacionadas a cada função do app estão divididas em pacotes internos.

### App
**characterslist**: contém todo o dominio da exibição da lista de personagens e o mecanismo de filtragem.
**characterdetail**: contém todo o domínio da exibição de detalhes do personagem.
**navigation**: configurações de navegação entre as telas
**theme**: onfiguração dos temas da app, fonte e esquema de cores.

### Common

Contém estruturas comuns aos fluxos do app

	- chamada para API
	- conversão do resultado para os modelos
	- injeção do serviço
	- estados da view 


### Injeção de dependência

A biblioteca utilizada para a injeção de dependência foi o Kodein. Cada módulo ou feature deve definir seus próprios módulos Kodein. Isso permite que cada módulo encapsule suas dependências e exponha apenas o necessário.


## Compatibilidade

- SDK mínimo: 24
- SDK target: 33
- Orientação de tela: portrait e landscape
