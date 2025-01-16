Parte 1

--------------------------------------------------------------------------------------------------------------------------------------------------

1 - Apresentar maiores compradores de vinhos, considere todos que realizaram mais de 1 compra nos últimos 6 meses

SELECT p.nome, COUNT(i.id) AS qtd_compras 
FROM pessoa p
JOIN investigacao_compra i ON p.id = i.pessoa_id
WHERE i.data_compra >= NOW() - INTERVAL '6 months'
GROUP BY p.nome
HAVING COUNT(i.id) > 1

--------------------------------------------------------------------------------------------------------------------------------------------------

2 - Lista de pessoas envolvidas na entrega (todo fluxo, carregamento, transporte e recebimento) 

SELECT p.nome, i.onde_estava_turno_crime, i.local_trabalho, i.papel 
FROM investigacao_pessoa_transporte i JOIN pessoa p ON p.id = i.pessoa_id

--------------------------------------------------------------------------------------------------------------------------------------------------


3- Listagem de veículos com características suspeitas 
(características suspeitas readme - veiculo azul tipo caminhao marca ford)

SELECT v.id, v.cor, v.placa, m.fabricante, m.tipo 
FROM veiculo v JOIN modelo_veiculo m ON v.modelo_id = m.id
WHERE v.cor = 'azul' AND LOWER(m.fabricante) = 'ford' AND LOWER(m.tipo) = 'caminhonete'


--------------------------------------------------------------------------------------------------------------------------------------------------

4- Listagem de pedágio dos veículos com características do suspeito

SELECT DISTINCT p.nome FROM investigacao_pedagio i JOIN veiculo v ON v.placa = i.placa
JOIN proprietario_veiculo pv ON v.id = pv.veiculo_id
JOIN proprietario pr ON pv.proprietario_id = pr.id
JOIN proprietario_pessoa_fisica f ON pr.id = f.proprietario_id
JOIN pessoa p ON p.id = f.pessoa_id 
JOIN pessoa_caracteristica c ON p.id = c.pessoa_id
WHERE c.cor_cabelo = 'vermelho' AND c.formato_cabelo = 'longo' AND c.cor_pele = 'laranja'


--------------------------------------------------------------------------------------------------------------------------------------------------

5- Listagem de ligações telefônicas de algumas pessoas investigadas

-- nome das pessoas q receberam ou ligaram q estao na lista de investigacao_telefone

SELECT p.nome FROM investigacao_telefone i
JOIN proprietario_telefone pt ON pt.telefone_id = i.destino_telefone_id
JOIN proprietario pr ON pt.proprietario_id = pr.id
JOIN proprietario_pessoa_fisica f ON f.proprietario_id = pr.id
JOIN pessoa p ON p.id = f.pessoa_id
WHERE i.data_hora BETWEEN '2024-11-18 08:30:00' AND '2024-11-18 11:00:00'

UNION

SELECT p.nome FROM investigacao_telefone i
JOIN proprietario_telefone pt ON pt.telefone_id = i.origem_telefone_id
JOIN proprietario pr ON pt.proprietario_id = pr.id
JOIN proprietario_pessoa_fisica f ON f.proprietario_id = pr.id
JOIN pessoa p ON p.id = f.pessoa_id
WHERE i.data_hora BETWEEN '2024-11-18 08:30:00' AND '2024-11-18 11:00:00'


------------------------------------------------------------------------------------------------------------------------------------------------------


