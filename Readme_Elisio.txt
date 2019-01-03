31/12/2018
Coisas feitas:
----> Requerido CRUD
	----> Requerido Por nome, sobrenome. nomeSobrenome.

Dificuldades: Pesquisas paramentrisadas por Municipio e TipoPessoa.




------------------------ Fim-------------------


31/12/2018
Coisas feitas:
	----> Requerente CRUD
	----> RequerentePor nome, sobrenome. nomeSobrenome.

Dificuldades: Pesquisas paramentrisadas por Municipio e TipoPessoa.



------------------------ Fim-----------------



29/12/2018
Coisas Feitas:
----> Implementação de JPA em:
	----> TipoPessoa;
		----> CRUD

	----> TipoDecisão;
		----> CRUD

	----> EspecieProcesso;
		----> CRUD

	----> EstadoProcesso;
		----> CRUD

	----> Advogado;
		----> CRUD
		----> Pesquisar por: nome, sobrenome, nome e sobrenoma, dataNascimento, dataInicioFuncoes.

	----> Juiz;
		----> CRUD
		----> Pesquisar por: nome, sobrenome, nome e sobrenoma, dataNascimento, dataInicioFuncoes.


Dificuldades: 
	----> Pesquisar por intervalo de datas;
	----> Trabalhar com Converters.
	

OBS.: Estou a trabalhar com energia privada dos visinhos, está a criar tbm alguns transtornos mas estou a contornar...




------------------- Fim ----------------


22/12/2018 
Coisas Feitas:
----> Clone da versão da Aplicacao com JPA
----> Actualização do tema do primefaces
----> Testes das funcionalidade do CRUD de Advogado(implementado pelo Fre, pra servir de exemplo.)





------------------- Fim ----------------


















03/10/2018
	---> Pesquisas parametrisadas em processo
		---> by_estado_processo

O.B.S: Dificuldades em passar o paramétro da página de consulta para o referido MBean


------------------- Fim ----------------


01/10/2018
	---> Paginas
		---> processo_novo
		---> processo_listar


------------------- Fim ----------------

24/09/2018
	---> ProcessoMBean


------------------- Fim ----------------


23/09/2018
Converteres relacionados a processo: 
	---> EspecieProcessoConverter
	---> RequerenteConverter
	---> RequeridoConverter
	---> AdvogadoConverter
	---> JuizConverter
	---> EstadoProcessoConverter
	---> TipoDecisaoConverter

------------------- Fim ----------------



19/09/2018
CRUD de 
	---> Processo

------------------- Fim ----------------

10/09/2018
        --->Páginas correspondentes as pesquisas parametrisadas em Juiz:
		---> findByNome
		---> findBySobrenome
		---> findByNomeSobrenome

------------------- Fim ----------------


06/09/2018
        --->Métodos parametrisados feitos em JuizDAO
		---> findByNome
		---> findBySobrenome
		---> findByNomeSobrenome
		---> findByDataNascimento
		---> findByDataInicioFuncoes
		---> findByIntervaloDataNascimento
		---> findByIntervaloDataInicioFuncoes

------------------- Fim ----------------


05/09/2018
        --->Páginas correspondentes as pesquisas parametrisadas
		---> findByNome
		---> findBySobrenome
		---> findByNomeSobrenome

------------------- Fim ----------------


05/09/2018
	--->JuizMBean
	--->Juiz_novo
        --->Juiz_listar

        --->Métodos parametrisados feitos em AdvogadoDAO
		---> findByNome
		---> findBySobrenome
		---> findByNomeSobrenome
		---> findByDataNascimento
		---> findByDataInicioFuncao	
		---> findByIntervaloDataNascimento
		---> findByIntervaloDataInicioFuncoes
		

------------------- Fim ----------------


04/09/2018
	--->AdvogadoMBean
	--->advogado_novo
        --->advogado_listar

------------------- Fim ----------------



30/08/2018
	--->TipoPessoaMBean
        --->tipo_pessoa_listar
------------------- Fim ----------------



26/08/2018
        --->menus
        --->pasta para as paginas
        --->pacote fenix.iure.converters
        --->pacote fenix.iure.bm
        --->TipoDecisaoMBean
        --->tipo_decisao_listar

------------------- Fim ----------------


12/08/2018
Iª FASE Fenixiuri
Coisas concluídas
  CRUD de:
        ---> TipoPessoa
        ---> TipoDecisâo
        ---> Advogado
        ---> Juiz

------------------- Fim ----------------
